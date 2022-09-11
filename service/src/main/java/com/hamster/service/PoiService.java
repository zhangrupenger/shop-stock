package com.hamster.service;

import com.hamster.dao.domain.Emp;
import com.hamster.dao.domain.EmpExample;
import com.hamster.dao.domain.Poi;
import com.hamster.dao.domain.PoiFullInfo;
import com.hamster.dao.mapper.EmpMapper;
import com.hamster.dao.poi.PoiDao;
import com.hamster.service.exception.BusinessException;
import com.hamster.service.exception.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class PoiService {
    @Resource
    private PoiDao poiDao;

    @Transactional(rollbackFor = Exception.class)
    public Poi addPoi(String poiName, String poiAddress, int role, Long userId) throws BusinessException {
        try {
            Poi poi = poiDao.insertPoi(poiName, poiAddress);
            poiDao.addEmp(poi.getId(), role, userId);
            return poi;
        } catch (Exception e) {
            log.error("addPoi found error", e);
            throw new BusinessException(CodeEnum.INNER_ERROR.getCode(), CodeEnum.INNER_ERROR.getMsg());
        }
    }

    public List<PoiFullInfo> getPoiList(Long userId) throws BusinessException {
        try {
            return poiDao.getPoiList(userId);
        } catch (Exception e) {
            log.error("getPoiList found error,userId={}", userId, e);
            throw new BusinessException(CodeEnum.INNER_ERROR.getCode(), CodeEnum.INNER_ERROR.getMsg());
        }
    }
}
