package com.hamster.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "订单信息")
public class OrderInfoVo {
    @ApiModelProperty(value = "订单id")
    private String orderId;
    @ApiModelProperty(value = "销售人员名称")
    private String userName;
    @ApiModelProperty(value = "销售时间")
    private Long ctime;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "实付")
    private Long totalPay;
    @ApiModelProperty(value = "商品数量")
    private Integer quantity;
    @ApiModelProperty(value = "尺码")
    private String size;
    @ApiModelProperty(value = "商品名称")
    private String skuName;
}
