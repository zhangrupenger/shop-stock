package com.hamster.service;

import com.hamster.dao.domain.Sku;
import com.hamster.dao.domain.SkuFullInfo;
import com.hamster.dao.domain.Stock;
import com.hamster.dao.domain.StockStream;
import com.hamster.dao.sku.SkuDao;
import com.hamster.dao.stock.StockDao;
import com.hamster.service.exception.BusinessException;
import com.hamster.service.exception.CodeEnum;
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

    public List<Sku> searchProductByCode(String code, long poiId) throws BusinessException {
        try {
            return skuDao.getSkuListByCode(code, poiId);
        } catch (Exception e) {
            log.error("searchProductByCode found error, code:{}",code,e);
            throw new BusinessException(CodeEnum.INNER_ERROR.getCode(),CodeEnum.INNER_ERROR.getMsg());
        }
    }


    public void stockIn(StockInRquestParamModel request, long poiId) throws BusinessException{
        Sku skuListByCodeAndSize = null;
        try {
            skuListByCodeAndSize = skuDao.getSkuListByCodeAndSize(request.getCode(),poiId,request.getSize());
            if (Objects.isNull(skuListByCodeAndSize)) {
                generateSkuAndStockIn(request,poiId);
                return;
            }
            stockDao.updateSkuStock(skuListByCodeAndSize.getId(), poiId, request.getNumber());
        } catch (Exception e) {
            log.error("stockIn found error,requestParam:{}", request, e);
            throw new BusinessException(CodeEnum.INNER_ERROR.getCode(),CodeEnum.INNER_ERROR.getMsg());
        }
    }

    public List<ProductFullInfo> searchProductFullInfoByCode(String code,long poiId, int page, int pageSize) throws BusinessException{
        try {
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
        } catch (Exception e) {
            log.error("searchProductFullInfoByCode found error",e);
            throw new BusinessException(CodeEnum.INNER_ERROR.getCode(),CodeEnum.INNER_ERROR.getMsg());
        }
    }
    public List<ProductFullInfo> searchProductFullInfoMergeSizeByCode(String code,long poiId, int page, int pageSize) throws BusinessException{
        try {
            List<SkuFullInfo> skuFullINfoLists = skuDao.searchProductFullInfoMergeSizeByCode(code, poiId, page, pageSize);
            if (CollectionUtils.isEmpty(skuFullINfoLists)) {
                return null;
            }
            List<ProductFullInfo> productFullInfos = skuFullINfoLists.stream().map(skuFullInfo -> {
                ProductFullInfo productFullInfo = new ProductFullInfo();
                BeanUtils.copyProperties(skuFullInfo, productFullInfo);
                return productFullInfo;
            }).collect(Collectors.toList());
            return productFullInfos;
        } catch (Exception e) {
            log.error("stockIn found error,code:{}, code:{},page:{},pageSize:{}", code, page, pageSize, e);
            throw new BusinessException(CodeEnum.INNER_ERROR.getCode(),CodeEnum.INNER_ERROR.getMsg());
        }
    }
    public List<StockStream> searchStockStramHistory(Long skuId) throws BusinessException{
        try {
            return stockDao.getStorckStramBySkuId(skuId);
        } catch (Exception e) {
            log.error("stockIn found error,skuId:{}",skuId, e);
            throw new BusinessException(CodeEnum.INNER_ERROR.getCode(),CodeEnum.INNER_ERROR.getMsg());
        }
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
