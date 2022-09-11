package com.hamster.dao.user.impl;

import com.hamster.dao.domain.UserInfo;
import com.hamster.dao.domain.UserInfoExample;
import com.hamster.dao.mapper.UserInfoMapper;
import com.hamster.dao.user.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserDaoImpl implements UserDao {

    @Resource
    private UserInfoMapper userMapper;

    @Override
    public UserInfo insertUser(String openId) {
        UserInfo user = new UserInfo();
        user.setOpenId(openId);
        user.setCtime(System.currentTimeMillis());
        user.setValid((short)1);
        userMapper.insertSelective(user);
        return user;
    }

    @Override
    public UserInfo getByOpenId(String openId) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userMapper.selectByExample(userInfoExample);
        if (CollectionUtils.isNotEmpty(userInfos)) {
            return userInfos.get(0);
        }
        return null;
    }
}
