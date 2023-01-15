package com.hamster.dao.mapper;

import com.hamster.dao.domain.ShopOrder;
import com.hamster.dao.domain.ShopOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopOrderMapper {
    long countByExample(ShopOrderExample example);

    int deleteByExample(ShopOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    List<ShopOrder> selectByExample(ShopOrderExample example);

    ShopOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopOrder record, @Param("example") ShopOrderExample example);

    int updateByExample(@Param("record") ShopOrder record, @Param("example") ShopOrderExample example);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);
}