package com.rent.service.impl;

import com.rent.dao.UserDao;
import com.rent.entity.User;
import com.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userInfoDao;
    //查询用户信息根据id
    @Override
   public User queryById(Map<String, Object> map){
        User user=userInfoDao.queryById(map);
        return user;
    }
}
