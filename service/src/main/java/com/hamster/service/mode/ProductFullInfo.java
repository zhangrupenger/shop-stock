package com.hamster.service.mode;


import lombok.Data;

@Data
public class ProductFullInfo{

    private Long skuId;

    private String code;

    private String skuName;

    private String picture;
    private Long price;

    private String color;

    private String size;

    private Long stock;

}
