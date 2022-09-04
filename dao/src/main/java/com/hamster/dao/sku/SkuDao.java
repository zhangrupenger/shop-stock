package com.hamster.dao.sku;

import com.hamster.dao.domain.Sku;

import java.util.List;


public interface SkuDao {
    List<Sku> getSkuListByCode (String code, long poiId);
    Sku getSkuListByCodeAndSize (String code, long poiId, String size);
    default void insertSku(Sku sku){};
}
