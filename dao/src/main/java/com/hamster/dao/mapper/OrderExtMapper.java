package com.hamster.dao.mapper;

import com.hamster.dao.domain.OrderInfo;
import com.hamster.dao.domain.SkuFullInfo;
import com.hamster.dao.param.PageParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderExtMapper {
    List<OrderInfo> getOrderInfoByCode(@Param("code") String code,@Param("poiId") Long poiId, @Param("page") PageParam page);
    OrderInfo getOrderInfoByOrderId(@Param("orderId") String orderId);
}
