package com.hamster.dao.mapper;

import com.hamster.dao.domain.Stock;
import com.hamster.dao.domain.StockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockMapper {
    long countByExample(StockExample example);

    int deleteByExample(StockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Stock record);

    int insertSelective(Stock record);

    List<Stock> selectByExample(StockExample example);

    Stock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByExample(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);
}