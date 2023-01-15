package com.hamster.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "订单商品信息")
public class OrderItemVo {

    @ApiModelProperty(value = "商品id")
    private Long skuId;

    @ApiModelProperty(value = "商品code")
    private String code;

    @ApiModelProperty(value = "商品名称")
    private String skuName;

    @ApiModelProperty(value = "商品图片")
    private String picture;

    @ApiModelProperty(value = "商品实付价格")
    private Long price;

    @ApiModelProperty(value = "商品原价")
    private Long originPrice;

    @ApiModelProperty(value = "商品数量")
    private Long quantity;

    @ApiModelProperty(value = "商品尺码")
    private String size;
}
