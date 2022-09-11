package com.hamster.web.controller;

import com.google.common.collect.Lists;
import com.hamster.dao.domain.Sku;
import com.hamster.dao.domain.StockStream;
import com.hamster.service.StockManagerService;
import com.hamster.service.exception.BusinessException;
import com.hamster.service.exception.CodeEnum;
import com.hamster.service.mode.StockInRquestParamModel;
import com.hamster.web.RequestVo.StockInRquestParam;
import com.hamster.web.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "库存管理相关API")
@RestController
@RequestMapping("/stock/manager")
@Slf4j
public class StockManagerController {

    @Resource
    private StockManagerService stockManagerService;

    @GetMapping("searchProductByCode")
    @ApiOperation(value = "ResultVo«List«T»»", notes = "根据code查询商品信息")
    public ResultVo<List<SkuInfoVo>> searchProductByCode(@ApiParam(value = "商品码") @RequestParam String code) throws BusinessException {
        List<Sku> skus = stockManagerService.searchProductByCode(code, 1L);
        List<SkuInfoVo> skuInfoVos = VoConvertUtils.convertToSkInfoVo(skus);
        return new ResultVo(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), skuInfoVos);
    }

    @GetMapping("searchProductFullInfoByCode")
    @ApiOperation(value = "ResultVo«List«T»»", notes = "根据code查询包含库存的商品信息")
    public ResultVo<List<ProductFullInfo>> searchProductFullInfoByCode(@ApiParam(value = "商品码") @RequestParam(required = false) String code,
                                                                       @ApiParam(value = "页码") @RequestParam int page, @ApiParam(value = "每页条数") @RequestParam int pageSize) throws BusinessException {

        List<com.hamster.service.mode.ProductFullInfo> productFullInfos = stockManagerService.searchProductFullInfoByCode(code, 1L, page, pageSize);
        return new ResultVo(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), productFullInfos);


    }

    @GetMapping("searchProductFullInfoMergeSizeByCode")
    @ApiOperation(value = "ResultVo«List«T»»", notes = "根据code查询包含库存的商品信息(根据code聚合)")
    public ResultVo<List<ProductFullInfo>> searchProductFullInfoMergeSizeByCode(@ApiParam(value = "商品码") @RequestParam(required = false) String code,
                                                                                @ApiParam(value = "页码") @RequestParam int page, @ApiParam(value = "每页条数") @RequestParam int pageSize) throws BusinessException {
        log.info("请求参数 code:{}",code);
        List<com.hamster.service.mode.ProductFullInfo> productFullInfos = stockManagerService.searchProductFullInfoMergeSizeByCode(code, 1L, page, pageSize);
        return new ResultVo(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), productFullInfos);


    }

    @GetMapping("searchStockStramHistory")
    @ApiOperation(value = "ResultVo«List«T»»", notes = "根据skuid查询库存流水记录")
    public ResultVo<List<StockStreamVo>> searchStockStramHistory(@ApiParam(value = "skuId") @RequestParam(required = true) Long skuId) throws BusinessException {
        try {
            List<StockStreamVo> stockStreamVos = Lists.newArrayList();
            List<StockStream> stockStreams = stockManagerService.searchStockStramHistory(skuId);
            if (CollectionUtils.isNotEmpty(stockStreams)) {
                stockStreamVos = stockStreams.stream().map(stockStream -> {
                    StockStreamVo sso = new StockStreamVo();
                    BeanUtils.copyProperties(stockStream, sso);
                    return sso;
                }).collect(Collectors.toList());
            }
            return new ResultVo(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), stockStreamVos);
        } catch (Exception e) {
            log.error("stockIn found error,skuId:{}", skuId, e);
            return new ResultVo(CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMsg());
        }

    }


    @PostMapping("stockIn")
    @ApiOperation(notes = "根据skuId入库", value = "ResultVo")
    public ResultVo stockIn(@Valid @RequestBody StockInRquestParam param) throws BusinessException {
        log.info("request:{}", param);
        StockInRquestParamModel request = new StockInRquestParamModel();
        BeanUtils.copyProperties(param, request);
        stockManagerService.stockIn(request, 1L);
        return new ResultVo(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg());

    }

}
