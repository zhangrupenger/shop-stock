package com.hamster.web.controller;

import com.hamster.dao.domain.Emp;
import com.hamster.service.TestService;
import com.hamster.web.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Api(value = "测试")
@Slf4j
public class TestController {
    @Autowired
    private TestService testService;
    @GetMapping("userInfo")
    @ApiOperation(value = "获取用户信息")
    public UserInfoVo getUserId(@ApiParam(value = "数据id", required = true, example = "1") @RequestParam long id) {
        Emp userMsg = testService.getUserMsg(id);
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userMsg, userInfoVo);
        log.info("userInfo, {}",userInfoVo);
        return userInfoVo;
    }
}
