package com.hamster.dao.mapper;

import com.hamster.dao.domain.StockStream;
import com.hamster.dao.domain.StockStreamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockStreamMapper {
    long countByExample(StockStreamExample example);

    int deleteByExample(StockStreamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StockStream record);

    int insertSelective(StockStream record);

    List<StockStream> selectByExample(StockStreamExample example);

    StockStream selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StockStream record, @Param("example") StockStreamExample example);

    int updateByExample(@Param("record") StockStream record, @Param("example") StockStreamExample example);

    int updateByPrimaryKeySelective(StockStream record);

    int updateByPrimaryKey(StockStream record);
}