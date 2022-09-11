package com.hamster.dao.mapper;

import com.hamster.dao.domain.PoiFullInfo;
import com.hamster.dao.domain.SkuFullInfo;
import com.hamster.dao.param.PageParam;
import com.hamster.dao.param.SkuFullQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuStockMapper{
    List<SkuFullInfo> searchProductFullInfoByCode(@Param("skuFullQueryParam") SkuFullQueryParam skuFullQueryParam, @Param("page") PageParam page);
    List<SkuFullInfo> searchProductFullInfoMergeSizeByCode(@Param("skuFullQueryParam") SkuFullQueryParam skuFullQueryParam, @Param("page") PageParam page);
    List<PoiFullInfo> getPoiFullInfoList(@Param("userId") Long userId);
}
