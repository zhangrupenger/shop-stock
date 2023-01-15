package com.hamster.service;

import com.hamster.dao.domain.*;
import com.hamster.dao.order.OrderDao;
import com.hamster.dao.sku.SkuDao;
import com.hamster.dao.stock.StockDao;
import com.hamster.dao.user.UserDao;
import com.hamster.service.exception.BusinessException;
import com.hamster.service.exception.CodeEnum;
import com.hamster.service.mode.OrderDetail;
import com.hamster.service.mode.OrderInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private SkuDao skuDao;
    @Resource
    private StockDao stockDao;
    @Resource
    private UserDao userDao;

    public List<OrderInfoModel> getOrderInfoBySkuCode(String skuCode, int page, int pageSize, long poiId) throws BusinessException {
        try {
            List<OrderInfo> orderInfo = orderDao.getOrderInfo(skuCode, poiId, page, pageSize);
            if (CollectionUtils.isNotEmpty(orderInfo)) {
                return orderInfo.stream().map(o -> {
                    OrderInfoModel orm = new OrderInfoModel();
                    BeanUtils.copyProperties(o, orm);
                    return orm;
                }).collect(Collectors.toList());
            }
            return null;
        } catch (Exception e) {
            log.error("getOrderInfoBySkuCode found error", e);
            throw new BusinessException(CodeEnum.INNER_ERROR.getCode(), CodeEnum.INNER_ERROR.getMsg());
        }
    }


    public OrderDetail getOrderDetail(String orderId) throws BusinessException {
        OrderInfo orderInfo = orderDao.getOrderInfoByOrderId(orderId);
        OrderDetail detail = new OrderDetail();
        BeanUtils.copyProperties(orderInfo, detail);
        UserInfo user = userDao.getUserByUserId(orderInfo.getUserId());
        if (Objects.nonNull(user)) {
            detail.setUserName(user.getUserName());
        }
        detail.setRemark(orderInfo.getRemark());
        return detail;
    }

    public void submitOrder(ShopOrder shopOrder, List<OrderItem> items) throws BusinessException {
        try {
            List<Long> skuIdList = items.stream().map(OrderItem::getSkuId).collect(Collectors.toList());
            List<Stock> stocks = stockDao.getStock(skuIdList, shopOrder.getPoiId());
            //库存校验
            stockCheck(items, stocks);
            //插入订单信息
            insertOrderInfo(shopOrder, items, skuIdList);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("submitOrder found error", e);
            throw new BusinessException(CodeEnum.INNER_ERROR);
        }

    }

    private void stockCheck(List<OrderItem> items, List<Stock> stocks) throws BusinessException {

        if (CollectionUtils.isNotEmpty(stocks)) {
            Map<Long, Stock> stockMap = stocks.stream().collect(Collectors.toMap(Stock::getSkuId, Function.identity()));
            for (OrderItem item : items) {
                Stock stock = stockMap.get(item.getSkuId());
                if (Objects.isNull(stock) || item.getQuantity() > stock.getStock()) {
                    log.error("库存不足");
                    throw new BusinessException(BusinessException.BusinessError.STOCK_NOT_ENOUGH);
                }
            }
        } else {
            log.error("查不到库存信息");
            throw new BusinessException(BusinessException.BusinessError.STOCK_NOT_ENOUGH);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertOrderInfo(ShopOrder shopOrder, List<OrderItem> items, List<Long> skuIdList) {
        for (OrderItem item : items) {
            stockDao.deStock(item.getSkuId(), shopOrder.getPoiId(), item.getQuantity(), shopOrder.getOrderId());
        }


        List<Sku> skus = skuDao.getSkuListByid(skuIdList);
        long totalOrigin = 0;
        long totalPay = 0;
        if (CollectionUtils.isNotEmpty(skus)) {
            Map<Long, Sku> skuMap = skus.stream().collect(Collectors.toMap(Sku::getId, Function.identity()));
            for (OrderItem oi : items) {
                Sku sku = skuMap.get(oi.getSkuId());
                oi.setOriginPrice(sku.getPrice());
                oi.setSkuCode(sku.getCode());
                oi.setPicture(sku.getPicture());
                oi.setSkuName(sku.getSkuName());
                oi.setSize(sku.getSize());
                totalOrigin += sku.getPrice() * oi.getQuantity();
                totalPay += oi.getPrice() * oi.getQuantity();
            }
        }
        if (shopOrder.getTotalPay() == null || shopOrder.getTotalPay() == 0) {
            shopOrder.setTotalPay(totalPay);
        }
        shopOrder.setOriginPrice(totalOrigin);
        shopOrder.setCtime(System.currentTimeMillis());
        orderDao.insertOrder(shopOrder);
        orderDao.insertOrderItem(items);
    }
}
