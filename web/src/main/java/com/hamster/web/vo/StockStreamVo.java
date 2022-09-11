package com.hamster.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "库存流水记录")
public class StockStreamVo {
    @ApiModelProperty(value = "记录id")
    private Integer id;

    @ApiModelProperty(value = "商品id")
    private Long skuId;

    @ApiModelProperty(value = "操作类型")
    private Integer operator;

    @ApiModelProperty(value = "操作数量")
    private Long quantity;

    @ApiModelProperty(value = "操作备注")
    private String remark;

    @ApiModelProperty(value = "库存记录关联id")
    private String extId;

    @ApiModelProperty(value = "创建时间")
    private Long ctime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId == null ? null : extId.trim();
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

}