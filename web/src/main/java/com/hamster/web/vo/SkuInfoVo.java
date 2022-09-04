package com.hamster.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "商品信息")
@Data
public class SkuInfoVo {
    @ApiModelProperty(value = "商品id")
    private Long skuId;

    @ApiModelProperty(value = "商品code")
    private String code;

    @ApiModelProperty(value = "商品名称")
    private String skuName;

    @ApiModelProperty(value = "商品图片")
    private String picture;

    @ApiModelProperty(value = "商品价格")
    private Long price;

    @ApiModelProperty(value = "商品颜色")
    private String color;

    @ApiModelProperty(value = "商品尺码")
    private String size;
}
