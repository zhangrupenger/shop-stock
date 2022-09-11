package com.hamster.dao.param;

import lombok.Data;

@Data
public class PageParam {
    private int offset;
    private int limit;
}
