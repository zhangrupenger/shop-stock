package com.hamster.dao.user;

import com.hamster.dao.domain.UserInfo;

public interface UserDao {
    UserInfo insertUser(String openId, String userName, String picture);
    UserInfo getByOpenId(String openId);
}
