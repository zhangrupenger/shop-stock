package com.hamster.dao.order;


import com.hamster.dao.domain.OrderInfo;
import com.hamster.dao.domain.OrderItem;
import com.hamster.dao.domain.ShopOrder;

import java.util.List;

public interface OrderDao {
    List<OrderInfo> getOrderInfo(String code, Long poiId, int page, int size);
    void insertOrder(ShopOrder shopOrder);
    void insertOrderItem(List<OrderItem> orderItemList);
    public OrderInfo getOrderInfoByOrderId(String orderId);
}
