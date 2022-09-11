package com.hamster.web.RequestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "创建门店请求入参")
public class PoiParams {
    @ApiModelProperty(value = "角色 1:店主  2：店员")
    @NotNull(message = "角色 不能为空")
    private Integer role;
    @ApiModelProperty(value = "门店名称")
    @NotNull(message = "门店名称 不能为空")
    private String poiName;
    @ApiModelProperty(value = "门店地址")
    @NotNull(message = "门店地址 不能为空")
    private String poiAddress;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getPoiAddress() {
        return poiAddress;
    }

    public void setPoiAddress(String poiAddress) {
        this.poiAddress = poiAddress;
    }
}
