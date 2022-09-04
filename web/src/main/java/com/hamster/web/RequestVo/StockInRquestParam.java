package com.hamster.web.RequestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ApiModel(value = "入库请求入参")
@ToString
public class StockInRquestParam {
    @ApiModelProperty(value = "商品编码")
    private String code;
    @ApiModelProperty(value = "商品名称")
    private String skuName;
    @ApiModelProperty(value = "商品价格")
    private Long price;
    @ApiModelProperty(value = "商品型号")
    private String size;
    @ApiModelProperty(value = "入库数量")
    private Integer number;
}
