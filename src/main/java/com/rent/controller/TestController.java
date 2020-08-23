package com.rent.controller;

import com.alibaba.fastjson.JSONObject;
import com.rent.entity.People;

public class TestController {
    public static void main(String[] args) {
        TestController t = new TestController();
        t.test1();
    }

    /**
     * Java实现JSONObject对象与Json字符串互相转换
     */
    public void test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "wjw");
        jsonObject.put("age", 22);
        jsonObject.put("sex", "男");
        jsonObject.put("school", "商职");
        String jsonStr = JSONObject.toJSONString(jsonObject);
        System.out.println(jsonStr);
        jsonObject.clear();
        String jsonStr1 = "{\"school\":\"商职\",\"sex\":\"男\",\"name\":\"wjw\",\"age\":22}";
        jsonObject = JSONObject.parseObject(jsonStr1);
        System.out.println(jsonObject.getString("name"));
        System.out.println(jsonObject.getInteger("age"));
    }

    /**
     * 实体类 转 JSONObject对象
     */
    public void test1() {
        People people = new People();
        people.setId("00001");
        people.setUserName("李白");
        people.setSort("1");
        // 转换为json字符串
        String personStr = JSONObject.toJSONString(people);
        System.out.println("personStr:" + personStr);
        // 转换为json对象
        JSONObject personObject = JSONObject.parseObject(personStr);
        System.out.println("personObject:" + personObject);
        System.out.println("name:" + personObject.getString("userName"));
    }
}
