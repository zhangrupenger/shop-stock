package com.hamster.service;

import com.hamster.dao.domain.UserInfo;
import com.hamster.dao.user.UserDao;
import com.hamster.service.exception.BusinessException;
import com.hamster.service.exception.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@Slf4j
public class UserInfoService {
    @Resource
    private UserDao userDao;

    public UserInfo getUserInfo(String openId, String userName, String picture) throws BusinessException {
        UserInfo userInfo = userDao.getByOpenId(openId);
        if (Objects.nonNull(userInfo)) {
            return userInfo;
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(picture)) {
            log.warn("未注册用户， openId:{}", openId);
            throw new BusinessException(CodeEnum.NOT_REGISTER_ERROR.getCode(), CodeEnum.NOT_REGISTER_ERROR.getMsg());
        }
        return userDao.insertUser(openId, userName, picture);
    }
}
