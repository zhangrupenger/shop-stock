package com.hamster.dao.poi.impl;

import com.hamster.dao.domain.*;
import com.hamster.dao.mapper.EmpMapper;
import com.hamster.dao.mapper.PoiMapper;
import com.hamster.dao.mapper.SkuStockMapper;
import com.hamster.dao.mapper.UserInfoMapper;
import com.hamster.dao.poi.PoiDao;
import com.hamster.dao.user.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class PoiDaoImpl implements PoiDao {

    @Resource
    private PoiMapper poiMapper;

    @Resource
    private EmpMapper empMapper;

    @Resource
    private SkuStockMapper skuStockMapper;

    @Override
    public Poi insertPoi(String poiName, String address) {
        Poi poi = new Poi();
        poi.setPoiName(poiName);
        poi.setAddress(address);
        poi.setCtime(System.currentTimeMillis());
        poi.setValid((short)1);
        poiMapper.insertSelective(poi);
        return poi;
    }

    @Override
    public Emp getEmpByPoiAndRole(Long poiId, int role) {
        EmpExample example = new EmpExample();
        EmpExample.Criteria criteria = example.createCriteria().andPoiIdEqualTo(poiId).andRoleEqualTo(role);
        List<Emp> emps = empMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(emps)) {
            return emps.get(0);
        }
        return null;
    }

    @Override
    public void addEmp(Long poiId, int role, Long userId) {
        Emp emp = new Emp();
        emp.setUserId(userId);
        emp.setPoiId(poiId);
        emp.setEntryTime(System.currentTimeMillis());
        emp.setRole(role);
        emp.setCtime(System.currentTimeMillis());
        emp.setValid((short)1);
        empMapper.insertSelective(emp);
    }

    @Override
    public List<PoiFullInfo> getPoiList(Long userId) {
        List<PoiFullInfo> poiFullInfoList = skuStockMapper.getPoiFullInfoList(userId);
        return poiFullInfoList;
    }
}
