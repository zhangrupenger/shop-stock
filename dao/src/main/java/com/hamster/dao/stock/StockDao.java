package com.hamster.dao.stock;

import com.hamster.dao.domain.Stock;

public interface StockDao {
    void insertStock(Stock stock);
    void updateSkuStock(long skuId, long poiId, int number);
}
