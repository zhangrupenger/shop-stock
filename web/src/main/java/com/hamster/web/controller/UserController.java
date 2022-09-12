package com.hamster.web.controller;

import com.hamster.dao.domain.UserInfo;
import com.hamster.service.AuthServiceUtils;
import com.hamster.service.UserInfoService;
import com.hamster.service.exception.BusinessException;
import com.hamster.service.exception.CodeEnum;
import com.hamster.web.vo.LoginInfo;
import com.hamster.web.vo.ResultVo;
import com.hamster.web.vo.WxLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(value = "用户相关API")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private static final String APPID = "wxa946b86538783ba7";
    private static final String  APPSECRET = "04ea6eb291d6ab2d05b3bcf62f33ae7d";

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private UserInfoService userInfoService;

    @GetMapping("login")
    @ApiOperation(notes = "登录", value = "ResultVo")
    public ResultVo<LoginInfo> login(@RequestParam String code, @RequestParam(required = false) String userName,
                                     @RequestParam(required = false) String picture) throws BusinessException {
        log.info("userName:{},picture:{},code:{}",userName,picture,code);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", APPID);
        paramMap.put("secret", APPSECRET);
        paramMap.put("js_code", code);

        ResponseEntity<WxLoginVo> responseEntity = restTemplate.getForEntity("https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type=authorization_code", WxLoginVo.class, paramMap);
        // 获取状态码
        int statusCodeValue = responseEntity.getStatusCodeValue();
        if (statusCodeValue != 200) {
            throw new BusinessException(CodeEnum.LOG_ERROR.getCode(), "验证登录异常");
        }
        // 获取body
        WxLoginVo result = responseEntity.getBody();
        if (StringUtils.isNotEmpty(result.getErrcode()) && !result.getErrcode().equals("0")) {
            log.error("登录发生异常code，result:{}", result);
            return new ResultVo(CodeEnum.LOG_ERROR.getCode(), CodeEnum.LOG_ERROR.getMsg(), result);
        }
        log.info("微信登录校验成功 result={}", result);
        UserInfo userInfo = userInfoService.getUserInfo(result.getOpenid(), userName, picture);
        log.info("内部用户信息 userInfo={}", userInfo);
        String token = AuthServiceUtils.getToken(String.valueOf(userInfo.getId()));
        log.info("token = {}",token);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setPicture(userInfo.getPicture());
        loginInfo.setUserName(userInfo.getUserName());
        loginInfo.setToken(token);
        return new ResultVo(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), loginInfo);
    }
    @GetMapping("getFullMessageToken")
    @ApiOperation(notes = "选定门店后换取全信息token", value = "ResultVo")
    public ResultVo<String> getFullMessageToken(@RequestParam Long poiId, @RequestParam Integer role, @RequestAttribute Long userId) {
        String token = AuthServiceUtils.getToken(String.valueOf(userId), String.valueOf(poiId), String.valueOf(role));
        log.info("getFullMessageToken token = {}",token);
        return new ResultVo(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), token);
    }
}
