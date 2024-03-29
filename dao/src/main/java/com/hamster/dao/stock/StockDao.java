package com.hamster.dao.stock;

import com.hamster.dao.domain.Stock;
import com.hamster.dao.domain.StockStream;

import java.util.List;

public interface StockDao {
    void insertStock(Stock stock);
    void updateSkuStock(long skuId, long poiId, int number);
    List<StockStream> getStorckStramBySkuId(Long skuId);
    List<Stock> getStock(List<Long> skuIds, long poiId);

    void deStock(long skuId, long poiId, int number, String orderId);

}
