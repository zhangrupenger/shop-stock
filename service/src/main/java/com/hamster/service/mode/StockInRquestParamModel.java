package com.hamster.service.mode;

import lombok.Data;

@Data
public class StockInRquestParamModel {

    private String code;

    private String skuName;

    private Long price;

    private String size;

    private Integer number;
}
