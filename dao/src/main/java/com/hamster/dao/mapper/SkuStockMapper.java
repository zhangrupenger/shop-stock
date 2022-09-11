package com.hamster.dao.mapper;

import com.hamster.dao.domain.SkuFullInfo;
import com.hamster.dao.param.PageParam;
import com.hamster.dao.param.SkuFullQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuStockMapper{
    public List<SkuFullInfo> searchProductFullInfoByCode(@Param("skuFullQueryParam") SkuFullQueryParam skuFullQueryParam, @Param("page") PageParam page);
}
