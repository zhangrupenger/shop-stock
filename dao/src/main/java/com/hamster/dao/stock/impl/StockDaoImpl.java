package com.hamster.dao.stock.impl;

import com.hamster.dao.domain.Stock;
import com.hamster.dao.domain.StockExample;
import com.hamster.dao.domain.StockStream;
import com.hamster.dao.domain.StockStreamExample;
import com.hamster.dao.mapper.StockMapper;
import com.hamster.dao.mapper.StockStreamMapper;
import com.hamster.dao.stock.StockDao;
import com.hamster.dao.stock.StockOperatorEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class StockDaoImpl implements StockDao {

    @Resource
    private StockMapper stockMapper;
    @Resource
    private StockStreamMapper stockStreamMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertStock(Stock stock) {
        stock.setCtime(System.currentTimeMillis());
        stockMapper.insertSelective(stock);
        StockStream stockStream = new StockStream();
        stockStream.setSkuId(stock.getSkuId());
        stockStream.setQuantity(stock.getStock());
        stockStream.setOperator(StockOperatorEnum.STOCK_IN.getOperator());
        stockStream.setRemark(StockOperatorEnum.STOCK_IN.getDesc());
        stockStream.setCtime(System.currentTimeMillis());
        stockStream.setValid((short)1);
        stockStreamMapper.insertSelective(stockStream);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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

        StockStream stockStream = new StockStream();
        stockStream.setSkuId(skuId);
        stockStream.setQuantity((long)number);
        stockStream.setOperator(StockOperatorEnum.STOCK_IN.getOperator());
        stockStream.setRemark(StockOperatorEnum.STOCK_IN.getDesc());
        stockStream.setCtime(System.currentTimeMillis());
        stockStream.setValid((short)1);
        stockStreamMapper.insertSelective(stockStream);
    }

    @Override
    public List<StockStream> getStorckStramBySkuId(Long skuId) {
        StockStreamExample stockExample = new StockStreamExample();
        stockExample.createCriteria().andSkuIdEqualTo(skuId);
        return stockStreamMapper.selectByExample(stockExample);
    }

    @Override
    public List<Stock> getStock(List<Long> skuIds, long poiId) {
        StockExample example = new StockExample();
        example.createCriteria().andSkuIdIn(skuIds).andPoiIdEqualTo(poiId);
        return stockMapper.selectByExample(example);
    }

    @Override
    public void deStock(long skuId, long poiId, int number, String orderId) {
        StockExample stockExample = new StockExample();
        stockExample.createCriteria().andSkuIdEqualTo(skuId).andPoiIdEqualTo(poiId);
        List<Stock> stocks = stockMapper.selectByExample(stockExample);
        Stock stock = stocks.get(0);
        stock.setStock(stock.getStock() - number);
        stockMapper.updateByPrimaryKey(stock);

        StockStream stockStream = new StockStream();
        stockStream.setSkuId(skuId);
        stockStream.setQuantity((long)number);
        stockStream.setOperator(StockOperatorEnum.SALE.getOperator());
        stockStream.setRemark(StockOperatorEnum.SALE.getDesc());
        stockStream.setCtime(System.currentTimeMillis());
        stockStream.setValid((short)1);
        stockStream.setExtId(orderId);
        stockStreamMapper.insertSelective(stockStream);
    }
}
