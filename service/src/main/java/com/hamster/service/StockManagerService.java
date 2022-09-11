package com.hamster.service;

import com.hamster.dao.domain.Sku;
import com.hamster.dao.domain.SkuFullInfo;
import com.hamster.dao.domain.Stock;
import com.hamster.dao.domain.StockStream;
import com.hamster.dao.sku.SkuDao;
import com.hamster.dao.stock.StockDao;
import com.hamster.service.mode.ProductFullInfo;
import com.hamster.service.mode.StockInRquestParamModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            return;
        }
        stockDao.updateSkuStock(skuListByCodeAndSize.getId(), poiId, request.getNumber());
    }

    public List<ProductFullInfo> searchProductFullInfoByCode(String code,long poiId, int page, int pageSize) {
        List<SkuFullInfo> skuFullINfoLists = skuDao.getSkuFullListByCodeForPage(code, poiId, page, pageSize);
        if (CollectionUtils.isEmpty(skuFullINfoLists)) {
            return null;
        }
        List<ProductFullInfo> productFullInfos = skuFullINfoLists.stream().map(skuFullInfo -> {
            ProductFullInfo productFullInfo = new ProductFullInfo();
            BeanUtils.copyProperties(skuFullInfo, productFullInfo);
            return productFullInfo;
        }).collect(Collectors.toList());
        return productFullInfos;
    }

    public List<StockStream> searchStockStramHistory(Long skuId) {
        return stockDao.getStorckStramBySkuId(skuId);
    }

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
