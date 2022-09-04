package com.hamster.service;

import com.hamster.dao.domain.Sku;
import com.hamster.dao.domain.Stock;
import com.hamster.dao.sku.SkuDao;
import com.hamster.dao.stock.StockDao;
import com.hamster.service.mode.StockInRquestParamModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class StockManagerService {
    @Resource
    private SkuDao skuDao;
    @Resource
    private StockDao stockDao;

    public List<Sku> searchProductByCode(String code, long poiId) {
        return skuDao.getSkuListByCode(code, poiId);
    }


    public void stockIn(StockInRquestParamModel request, long poiId) {
        Sku skuListByCodeAndSize = skuDao.getSkuListByCodeAndSize(request.getCode(),poiId,request.getSize());
        if (Objects.isNull(skuListByCodeAndSize)) {
            generateSkuAndStockIn(request,poiId);
        }

        stockDao.updateSkuStock(skuListByCodeAndSize.getId(), poiId, request.getNumber());
    }


    @Transactional(rollbackFor=Exception.class)
    public void generateSkuAndStockIn(StockInRquestParamModel request, long poiId) {
        Sku insertSku = new Sku();
        BeanUtils.copyProperties(request, insertSku);
        insertSku.setPoiId(poiId);
        skuDao.insertSku(insertSku);
        Long skuId = insertSku.getId();
        Stock stock = new Stock();
        stock.setSkuId(skuId);
        stock.setPoiId(poiId);
        stock.setStock(request.getNumber().longValue());
        stockDao.insertStock(stock);
    }
}
