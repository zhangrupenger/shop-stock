package com.hamster.service.exception;

public enum CodeEnum {
    SUCCESS(0,"success"),
    ERROR(1, "error"),
    PARAM_ERROR(2, "paramError"),
    INNER_ERROR(3, "innerError"),
    LOG_ERROR(4, "loginError"),
    ;

    CodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
