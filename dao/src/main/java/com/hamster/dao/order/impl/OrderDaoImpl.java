package com.hamster.dao.order.impl;

import com.hamster.dao.domain.*;
import com.hamster.dao.mapper.OrderExtMapper;
import com.hamster.dao.mapper.OrderItemMapper;
import com.hamster.dao.mapper.ShopOrderMapper;
import com.hamster.dao.order.OrderDao;
import com.hamster.dao.param.PageParam;
import com.hamster.dao.utils.PageConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Resource
    private ShopOrderMapper shopOrderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private OrderExtMapper orderExtMapper;

    @Override
    public List<OrderInfo> getOrderInfo(String code, Long poiId, int page, int size) {
        PageParam pageParam = PageConvertUtils.getPageParam(page, size);
        List<OrderInfo> orderInfoByCode = orderExtMapper.getOrderInfoByCode(code, poiId, pageParam);
        return orderInfoByCode;
    }

    @Override
    public OrderInfo getOrderInfoByOrderId(String orderId) {
        if (StringUtils.isBlank(orderId)) {
            return null;
        }
        OrderInfo orderInfo = orderExtMapper.getOrderInfoByOrderId(orderId);
        return orderInfo;
    }

    @Override
    public void insertOrder(ShopOrder shopOrder) {
        shopOrder.setValid((short) 1);
        shopOrder.setCtime(System.currentTimeMillis());
        shopOrderMapper.insertSelective(shopOrder);
    }

    @Override
    public void insertOrderItem(List<OrderItem> orderItemList) {
        for (OrderItem orderItem : orderItemList) {
            orderItem.setValid((short) 1);
            orderItem.setCtime(System.currentTimeMillis());
            orderItemMapper.insertSelective(orderItem);
        }
    }
}
