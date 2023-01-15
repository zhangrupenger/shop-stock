package com.hamster.dao.sku;

import com.hamster.dao.domain.Sku;
import com.hamster.dao.domain.SkuFullInfo;

import java.util.List;


public interface SkuDao {
    List<Sku> getSkuListByCode (String code, long poiId);
    Sku getSkuListByCodeAndSize (String code, long poiId, String size);
    default void insertSku(Sku sku){};

    List<SkuFullInfo> getSkuFullListByCodeForPage (String code, long poiId, int page, int pageSize);
    List<SkuFullInfo> searchProductFullInfoMergeSizeByCode(String code, long poiId, int page, int pageSize);

    List<Sku> getSkuListByid(List<Long> skuIds);

}
