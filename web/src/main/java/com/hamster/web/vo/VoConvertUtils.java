package com.hamster.web.vo;

import com.hamster.dao.domain.Sku;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class VoConvertUtils {
    public static List<SkuInfoVo> convertToSkInfoVo(List<Sku> skus) {
        List<SkuInfoVo> skuInfoVos = skus.stream().map(sku -> {
            SkuInfoVo vo = new SkuInfoVo();
            BeanUtils.copyProperties(sku, vo);
            vo.setSkuId(sku.getId());
            return vo;
        }).collect(Collectors.toList());
        return skuInfoVos;
    }
}
