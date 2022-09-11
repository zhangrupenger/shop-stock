package com.hamster.dao.stock;

public enum StockOperatorEnum {
    STOCK_IN(1,"入库"),
    STOCK_OUT(2, "出库")
    ;
    private int operator;
    private String desc;

    StockOperatorEnum(int operator, String desc) {
        this.operator = operator;
        this.desc = desc;
    }

    public int getOperator() {
        return operator;
    }

    public String getDesc() {
        return desc;
    }
}
