package com.hamster.web.RequestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "入库请求入参")
@ToString
public class StockInRquestParam {
    @ApiModelProperty(value = "商品编码")
    @NotNull(message = "商品编码 不能为空")
    private String code;
    @ApiModelProperty(value = "商品名称")
    @NotNull(message = "商品名称 不能为空")
    private String skuName;
    @ApiModelProperty(value = "商品价格")
    @NotNull(message="价格 不能为空")
    private Long price;
    @ApiModelProperty(value = "商品型号")
    @NotNull(message="尺码 不能为空")
    private String size;
    @ApiModelProperty(value = "入库数量")
    @NotNull(message = "入库数量 不能为空")
    private Integer number;
}
