package com.hamster.dao.mapper;

import com.hamster.dao.domain.Poi;
import com.hamster.dao.domain.PoiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PoiMapper {
    long countByExample(PoiExample example);

    int deleteByExample(PoiExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Poi record);

    int insertSelective(Poi record);

    List<Poi> selectByExample(PoiExample example);

    Poi selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Poi record, @Param("example") PoiExample example);

    int updateByExample(@Param("record") Poi record, @Param("example") PoiExample example);

    int updateByPrimaryKeySelective(Poi record);

    int updateByPrimaryKey(Poi record);
}