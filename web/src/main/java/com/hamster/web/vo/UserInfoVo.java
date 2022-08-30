package com.hamster.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户表")
@Data
public class UserInfoVo {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "门店id")
    private Long poiId;

    private Long entryTime;

    private String quitTime;

    private Integer role;

    private Long baseSalary;

    private Long performanceScale;
}
