package com.hamster.dao.utils;

import com.hamster.dao.param.PageParam;

public class PageConvertUtils {
    public static PageParam getPageParam(int page, int pageSize) {
        PageParam pageParam = new PageParam();
        pageParam.setOffset(page > 0 ? (page - 1) * pageSize : 0);
        pageParam.setLimit(pageSize);
        return pageParam;
    }
}
