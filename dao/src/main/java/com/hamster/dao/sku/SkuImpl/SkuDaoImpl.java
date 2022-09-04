package com.hamster.dao.sku.SkuImpl;

import com.hamster.dao.domain.Sku;
import com.hamster.dao.domain.SkuExample;
import com.hamster.dao.mapper.SkuMapper;
import com.hamster.dao.sku.SkuDao;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SkuDaoImpl implements SkuDao {
    @Resource
    private SkuMapper skuMapper;

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
}
