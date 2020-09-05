package com.rent.service;

import com.rent.entity.User;

import java.util.Map;

public interface UserService {
    //查询用户信息根据id
    User queryById(Map<String, Object> map);
}
