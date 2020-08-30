package com.rent.dao;

import com.rent.entity.BaseAppUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface BaseAppUserDao {
     //登录
     BaseAppUser login(Map<String,Object> map);
}
