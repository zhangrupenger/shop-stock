package com.hamster.service.mode;

import lombok.Data;

@Data
public class OrderDetail extends OrderInfoModel{
    private String userName;
    private String remark;
}
