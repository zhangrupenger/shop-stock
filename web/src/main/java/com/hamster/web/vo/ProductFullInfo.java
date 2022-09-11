package com.hamster.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "商品和库存信息")
public class ProductFullInfo extends SkuInfoVo{
    @ApiModelProperty(value = "库存")
    private Long stock;
}
