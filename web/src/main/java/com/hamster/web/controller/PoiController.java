package com.hamster.web.controller;

import com.hamster.dao.domain.Poi;
import com.hamster.dao.domain.PoiFullInfo;
import com.hamster.service.PoiService;
import com.hamster.service.exception.BusinessException;
import com.hamster.service.exception.CodeEnum;
import com.hamster.web.RequestVo.PoiParams;
import com.hamster.web.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("poi")
@Api(value = "门店操作poi")
@Slf4j
public class PoiController {
    @Autowired
    private PoiService poiService;

    @PostMapping("addPoi")
    @ApiOperation(value = "新增门店")
    public ResultVo<Poi> addPoi(@RequestBody PoiParams poiParams, @ApiParam(hidden = true) @RequestAttribute Long userId) throws BusinessException {
        Poi poi = poiService.addPoi(poiParams.getPoiName(), poiParams.getPoiAddress(), poiParams.getRole(), userId);
        return new ResultVo<>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), poi);
    }

    @GetMapping("getPoiList")
    @ApiOperation(value = "获取门店列表")
    public ResultVo<List<PoiFullInfo>> getPoiList(@ApiParam(hidden = true) @RequestAttribute Long userId) throws BusinessException {
        List<PoiFullInfo> poiList = poiService.getPoiList(userId);
        return new ResultVo<List<PoiFullInfo>>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), poiList);
    }
}
