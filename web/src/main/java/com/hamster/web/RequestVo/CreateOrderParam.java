package com.hamster.web.RequestVo;

import io.swagger.annotations.ApiModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("下单入参")
@ToString
public class CreateOrderParam {
    @ApiModelProperty(value = "订单实付(不传则使用 商品实付单价*数量计算)")
    private Long totalpay;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "购买的商品列表")
    @NotNull(message = "商品列表 不能为空")
    private List<OrderItemReq> orderItemReqs;
}
