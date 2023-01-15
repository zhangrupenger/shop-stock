package com.hamster.service.exception;

public class BusinessException extends Exception{
    private int code;
    private String msg;

    public BusinessException( int code,String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public BusinessException(BusinessError businessError) {
        super(businessError.getMsg());
        this.code = businessError.getCode();
        this.msg = businessError.getMsg();
    }

    public BusinessException(CodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
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

    public enum BusinessError{
        STOCK_NOT_ENOUGH(10001,"库存不足")
        ;
        private int code;
        private String msg;

        BusinessError(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
