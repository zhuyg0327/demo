package com.rent.controller;

import com.alibaba.fastjson.JSONObject;
import com.rent.entity.BaseAppUser;
import com.rent.entity.People;
import com.rent.entity.User;
import com.rent.service.BaseAppUserService;
import com.rent.service.UserService;
import com.rent.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    BaseAppUserService baseAppUserService;
    @Autowired
    UserService userService;
//    public static void main(String[] args) {
//        TestController t = new TestController();
//        t.test1();
//    }

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

    @RequestMapping("/test2")
    public void test2() {
        List<User> list1 = userService.queryAll();
        List<BaseAppUser> list2 = baseAppUserService.queryAll();
        List<Object> list3 = new ArrayList<>();
        // 根据单个字段字段去除重复
        User user1 = new User();
        user1.setId("001");
        user1.setAccount("lb");
        user1.setUserPassword("123456");
        user1.setUserName("李白");
        list1.add(user1);
        List<User> userList = list1.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User -> User.getId()))), ArrayList::new));
        //获取list2的id信息
        List<String> ids = list2.stream().map(BaseAppUser::getId).collect(Collectors.toList());
        for (User user : list1) {
            //如果ids集合不包含list1中的id，那就放入list3里面
            if (!ids.contains(user.getId())) {
                list3.add(user);
            }
        }
        Response.json(list1);
    }

    @RequestMapping("/test3")
    public void test3() {
        List<User> list1 = userService.queryAll();
        List<BaseAppUser> list2 = baseAppUserService.queryAll();
        List<User> list = new ArrayList<>();
        for (BaseAppUser baseuser : list2) {
            User user = new User();
            user.setId(baseuser.getId());
            user.setAccount(baseuser.getAccount());
            user.setUserId(baseuser.getUserId());
            user.setUserName(baseuser.getUserName());
            user.setUserPassword(baseuser.getUserPassword());
            user.setTelephone(baseuser.getTelephone());
            user.setSex(baseuser.getSex());
            user.setSort(baseuser.getSort());
            user.setStatus(baseuser.getStatus());
            user.setDeptId(baseuser.getDeptId());
            user.setDeptName(baseuser.getDeptName());
            user.setPower(baseuser.getPower());
            list.add(user);
        }
        if (list.size() > 0 && list != null) {
            userService.updateBatch(list);
        }
        Response.json(list);
    }
}
