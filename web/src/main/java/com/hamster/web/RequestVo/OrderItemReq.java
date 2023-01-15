package com.hamster.web.RequestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("购买商品信息")
@ToString
public class OrderItemReq {
    @NotNull(message = "skuId 不能为空")
    @ApiModelProperty(value = "商品ID")
    private Long skuId;
    @NotNull(message = "实付单价不能为空 不能为空")
    @ApiModelProperty(value = "实付单价")
    private Long price;
    @NotNull(message = "销售数量 不能为空")
    @ApiModelProperty(value = "销售数量")
    private Integer quantity;

}
