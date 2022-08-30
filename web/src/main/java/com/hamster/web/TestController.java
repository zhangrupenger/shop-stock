package com.hamster.web;

import com.hamster.dao.domain.EmpDAO;
import com.hamster.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class TestController {
    @Autowired
    private TestService testService;
    @GetMapping("userInfo")
    public EmpDAO getUserId(){
        return testService.getUserMsg();
    }
}
