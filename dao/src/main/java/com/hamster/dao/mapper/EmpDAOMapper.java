package com.hamster.dao.mapper;

import com.hamster.dao.domain.EmpDAO;
import com.hamster.dao.domain.EmpDAOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmpDAOMapper {
    int countByExample(EmpDAOExample example);

    int deleteByExample(EmpDAOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EmpDAO record);

    int insertSelective(EmpDAO record);

    List<EmpDAO> selectByExample(EmpDAOExample example);

    EmpDAO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EmpDAO record, @Param("example") EmpDAOExample example);

    int updateByExample(@Param("record") EmpDAO record, @Param("example") EmpDAOExample example);

    int updateByPrimaryKeySelective(EmpDAO record);

    int updateByPrimaryKey(EmpDAO record);
}