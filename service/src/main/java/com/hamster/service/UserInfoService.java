package com.hamster.service;

import com.hamster.dao.domain.UserInfo;
import com.hamster.dao.user.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@Slf4j
public class UserInfoService {
    @Resource
    private UserDao userDao;

    public UserInfo getUserInfo(String openId) {
        UserInfo userInfo = userDao.getByOpenId(openId);
        if (Objects.nonNull(userInfo)) {
            return userInfo;
        }
        return userDao.insertUser(openId);
    }
}
