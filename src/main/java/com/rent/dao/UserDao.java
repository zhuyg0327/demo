package com.rent.dao;

import com.rent.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserDao {
     //查询用户信息根据id
      User queryById(Map<String, Object> map);


}
