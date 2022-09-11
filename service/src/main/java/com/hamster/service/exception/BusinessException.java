package com.hamster.service.exception;

public class BusinessException extends Exception{
    private int code;
    private String msg;

    public BusinessException( int code,String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
