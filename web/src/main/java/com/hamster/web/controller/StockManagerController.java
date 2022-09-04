package com.hamster.web.controller;

import com.hamster.dao.domain.Sku;
import com.hamster.service.StockManagerService;
import com.hamster.service.mode.StockInRquestParamModel;
import com.hamster.web.RequestVo.StockInRquestParam;
import com.hamster.web.vo.CodeEnum;
import com.hamster.web.vo.ResultVo;
import com.hamster.web.vo.SkuInfoVo;
import com.hamster.web.vo.VoConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "库存管理相关API")
@RestController
@RequestMapping("/stock/manager")
@Slf4j
public class StockManagerController {

    @Resource
    private StockManagerService stockManagerService;

    @GetMapping("searchProductByCode")
    @ApiOperation(value = "ResultVo«List«T»»", notes = "根据code查询商品信息")
    public ResultVo<List<SkuInfoVo>> searchProductByCode(@ApiParam(value = "商品码") @RequestParam String code) {
        List<Sku> skus = stockManagerService.searchProductByCode(code, 1L);
        List<SkuInfoVo> skuInfoVos = VoConvertUtils.convertToSkInfoVo(skus);
        return new ResultVo(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), skuInfoVos);
    }


    @PostMapping("stockIn")
    @ApiOperation(notes = "根据skuId入库", value = "ResultVo")
    public ResultVo stockIn(@RequestBody StockInRquestParam param) {
        log.info("request:{}", param);
        StockInRquestParamModel request = new StockInRquestParamModel();
        BeanUtils.copyProperties(param, request);
        try {
            stockManagerService.stockIn(request, 1L);
            return new ResultVo(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg());
        } catch (Exception e) {
            log.error("stockIn found error,requestParam:{}", param, e);
            return new ResultVo(CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMsg());
        }
    }

}
