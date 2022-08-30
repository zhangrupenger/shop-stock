package com.hamster.service;

import com.hamster.dao.domain.EmpDAO;
import com.hamster.dao.domain.EmpDAOExample;
import com.hamster.dao.mapper.EmpDAOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private EmpDAOMapper empDAOMapper;

    public EmpDAO getUserMsg(){
        EmpDAOExample example = new EmpDAOExample();
        example.createCriteria().andIdEqualTo(1L);
        List<EmpDAO> empDAOS = empDAOMapper.selectByExample(example);
        return empDAOS.get(0);
    }
}
