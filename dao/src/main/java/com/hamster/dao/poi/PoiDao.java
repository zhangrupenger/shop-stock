package com.hamster.dao.poi;

import com.hamster.dao.domain.Emp;
import com.hamster.dao.domain.Poi;
import com.hamster.dao.domain.PoiFullInfo;

import java.util.List;

public interface PoiDao {
    Poi insertPoi(String poiName, String address);
    Emp getEmpByPoiAndRole(Long poiId, int role);
    void addEmp(Long poiId, int role, Long userId);
    List<PoiFullInfo> getPoiList(Long userId);

}
