package com.hamster.dao.stock.impl;

import com.hamster.dao.domain.Stock;
import com.hamster.dao.domain.StockExample;
import com.hamster.dao.mapper.StockMapper;
import com.hamster.dao.stock.StockDao;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class StockDaoImpl implements StockDao {

    @Resource
    private StockMapper stockMapper;

    @Override
    public void insertStock(Stock stock) {
        stock.setCtime(System.currentTimeMillis());
        stockMapper.insertSelective(stock);
    }

    @Override
    public void updateSkuStock(long skuId, long poiId, int number) {
        StockExample stockExample = new StockExample();
        stockExample.createCriteria().andSkuIdEqualTo(skuId).andPoiIdEqualTo(poiId);
        List<Stock> stocks = stockMapper.selectByExample(stockExample);
        if (CollectionUtils.isEmpty(stocks)) {
            Stock stock = new Stock();
            stock.setSkuId(skuId);
            stock.setPoiId(poiId);
            stock.setStock((long)number);
            insertStock(stock);
        }else {
            Stock stock = stocks.get(0);
            stock.setStock(stock.getStock() + number);
            stockMapper.updateByPrimaryKey(stock);
        }
    }
}
