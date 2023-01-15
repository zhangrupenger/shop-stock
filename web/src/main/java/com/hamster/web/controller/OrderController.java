package com.hamster.web.controller;

import com.hamster.dao.domain.OrderItem;
import com.hamster.dao.domain.ShopOrder;
import com.hamster.service.OrderService;
import com.hamster.service.exception.BusinessException;
import com.hamster.service.exception.CodeEnum;
import com.hamster.service.mode.OrderDetail;
import com.hamster.service.mode.OrderInfoModel;
import com.hamster.web.RequestVo.CreateOrderParam;
import com.hamster.web.vo.OrderInfoVo;
import com.hamster.web.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("order")
@Api(value = "订单相关api")
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("getOrderList")
    @ApiOperation(value = "查询订单列表")
    public ResultVo<List<OrderInfoModel>> getOrderList(@RequestParam(required = false) String skuCode, @RequestParam int page, @RequestParam int pageSize, @ApiParam(hidden = true) @RequestAttribute Long poiId) throws BusinessException {
        log.info("getOrderList request param skuCode:{},page:{},pageSize:{}", skuCode, page, pageSize);
        List<OrderInfoModel> orderInfoBySkuCode = orderService.getOrderInfoBySkuCode(skuCode, page, pageSize, poiId);
        return new ResultVo<List<OrderInfoModel>>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), orderInfoBySkuCode);
    }

    @GetMapping("getOrderDetail")
    @ApiOperation(value = "查询订单详情")
    public ResultVo<OrderInfoVo> getOrderDetail(@RequestParam(required = false) String orderId) throws BusinessException {
        OrderDetail orderDetail = orderService.getOrderDetail(orderId);
        OrderInfoVo vo = new OrderInfoVo();
        BeanUtils.copyProperties(orderDetail, vo);
        return new ResultVo<OrderInfoVo>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), vo);
    }

    @PostMapping("submit")
    @ApiOperation(value = "提交订单")
    public ResultVo submit(@Valid @RequestBody CreateOrderParam createOrderParam, @ApiParam(hidden = true) @RequestAttribute Long userId, @ApiParam(hidden = true) @RequestAttribute Long poiId) throws BusinessException {
        log.info("submit request param createOrderParam:{}", createOrderParam);
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setRemark(createOrderParam.getRemark());
        shopOrder.setTotalPay(createOrderParam.getTotalpay());
        shopOrder.setUserId(userId);
        shopOrder.setPoiId(poiId);
        String orderId = String.valueOf(System.currentTimeMillis()).substring(0, 9) + userId % 100;
        shopOrder.setOrderId(orderId);
        shopOrder.setCtime(System.currentTimeMillis());
        List<OrderItem> orderItems = createOrderParam.getOrderItemReqs().stream().map(oi -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(oi.getSkuId());
            orderItem.setOrderId(orderId);
            orderItem.setPrice(oi.getPrice());
            orderItem.setQuantity(oi.getQuantity());
            orderItem.setCtime(System.currentTimeMillis());
            return orderItem;
        }).collect(Collectors.toList());
        orderService.submitOrder(shopOrder, orderItems);
        return new ResultVo(CodeEnum.SUCCESS);

    }
}
