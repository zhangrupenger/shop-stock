package com.hamster.service.mode;

import lombok.Data;

@Data
public class OrderInfoModel {
    private String orderId;
    private Long totalPay;
    private String skuName;
    private String size;
    private Integer quantity;
    private Long ctime;

}
