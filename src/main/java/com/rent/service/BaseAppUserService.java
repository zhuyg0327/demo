package com.rent.service;

import com.rent.entity.BaseAppUser;

import java.util.Map;

public interface BaseAppUserService {
    //登录
   BaseAppUser login(Map<String,Object> map);
    //查询用户信息根据id
    BaseAppUser queryById(Map<String,Object> map);
}
