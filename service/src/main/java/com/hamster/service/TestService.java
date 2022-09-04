package com.hamster.service;

import com.hamster.dao.domain.Emp;
import com.hamster.dao.domain.EmpExample;
import com.hamster.dao.mapper.EmpMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class TestService {
    @Resource
    private EmpMapper empDAOMapper;

    public Emp getUserMsg(long id){
        EmpExample example = new EmpExample();
        example.createCriteria().andIdEqualTo(1L);
        List<Emp> empDAOS = empDAOMapper.selectByExample(example);
        log.info("userDao={}",empDAOS);
        return empDAOS.get(0);
    }
}
