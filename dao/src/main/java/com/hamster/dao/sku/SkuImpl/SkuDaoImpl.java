package com.hamster.dao.sku.SkuImpl;

import com.hamster.dao.domain.Sku;
import com.hamster.dao.domain.SkuExample;
import com.hamster.dao.domain.SkuFullInfo;
import com.hamster.dao.mapper.SkuMapper;
import com.hamster.dao.mapper.SkuStockMapper;
import com.hamster.dao.param.PageParam;
import com.hamster.dao.param.SkuFullQueryParam;
import com.hamster.dao.sku.SkuDao;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SkuDaoImpl implements SkuDao {
    @Resource
    private SkuMapper skuMapper;
    @Resource
    private SkuStockMapper skuStockMapper;

    @Override
    public List<Sku> getSkuListByCode(String code, long poiId) {
        SkuExample skuExample = new SkuExample();
        skuExample.createCriteria().andCodeEqualTo(code).andPoiIdEqualTo(poiId);
        return skuMapper.selectByExample(skuExample);
    }

    @Override
    public Sku getSkuListByCodeAndSize(String code, long poiId, String size) {
        SkuExample skuExample = new SkuExample();
        skuExample.createCriteria().andCodeEqualTo(code).andPoiIdEqualTo(poiId).andSizeEqualTo(size);
        List<Sku> skus = skuMapper.selectByExample(skuExample);
        if (CollectionUtils.isEmpty(skus)) {
            return null;
        }
        return skus.get(0);
    }

    @Override
    public void insertSku(Sku sku) {
        sku.setCtime(System.currentTimeMillis());
        sku.setValid((short) 1);
        skuMapper.insertSelective(sku);
    }

    @Override
    public List<SkuFullInfo> getSkuFullListByCodeForPage(String code, long poiId, int page, int pageSize) {
        SkuFullQueryParam skuFullQueryParam = new SkuFullQueryParam();
        skuFullQueryParam.setCode(code);
        skuFullQueryParam.setPoiId(poiId);
        PageParam pageParam = new PageParam();
        pageParam.setOffset(page > 0 ? (page - 1) * pageSize : 0);
        pageParam.setLimit(pageSize);
        return skuStockMapper.searchProductFullInfoByCode(skuFullQueryParam, pageParam);

    }
}
