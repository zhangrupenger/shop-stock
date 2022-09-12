package com.hamster.service.exception;

public enum CodeEnum {
    SUCCESS(0,"success"),
    ERROR(1, "请刷新后重试"),
    PARAM_ERROR(2, "参数有误哦"),
    INNER_ERROR(3, "网络不太稳定，刷新下试试"),
    LOG_ERROR(4, "请先登录"),
    NOT_REGISTER_ERROR(5, "请到个人中心登录或注册哦"),
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
