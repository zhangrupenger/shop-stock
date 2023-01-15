package com.hamster.dao.domain;

import lombok.Data;

@Data
public class OrderInfo {
    private String orderId;
    private Long totalPay;
    private String skuName;
    private String size;
    private Integer quantity;
    private Long ctime;
    private String remark;
    private Long userId;
}
