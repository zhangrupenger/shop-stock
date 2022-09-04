package com.hamster.service;

import com.hamster.dao.domain.EmpDAO;
import com.hamster.dao.domain.EmpDAOExample;
import com.hamster.dao.mapper.EmpDAOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class TestService {
    @Resource
    private EmpDAOMapper empDAOMapper;

    public EmpDAO getUserMsg(long id){
        EmpDAOExample example = new EmpDAOExample();
        example.createCriteria().andIdEqualTo(1L);
        List<EmpDAO> empDAOS = empDAOMapper.selectByExample(example);
        log.info("userDao={}",empDAOS);
        return empDAOS.get(0);
    }
}
