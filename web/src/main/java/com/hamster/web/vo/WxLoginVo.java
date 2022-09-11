package com.hamster.web.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WxLoginVo {
    private String openid;
    private String session_key;
    private String unionid;
    private String errcode;
    private String errmsg;
}
