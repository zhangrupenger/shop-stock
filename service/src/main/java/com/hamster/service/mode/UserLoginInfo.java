package com.hamster.service.mode;

import lombok.Data;

@Data
public class UserLoginInfo {
    private Long userId;
    private Integer role;
    private Long poiId;
}
