package com.hamster.web.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginInfo {
    private String picture;
    private String userName;
    private String token;
}
