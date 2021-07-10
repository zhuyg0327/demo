package com.demo.controller;

import com.demo.entity.BaseAppUser;
import com.demo.service.BaseAppUserService;
import com.demo.util.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/api/user")
public class BaseAppUserController {
    @Autowired
    BaseAppUserService baseAppUserService;

    private static final Logger logger = LoggerFactory.getLogger(BaseAppUserController.class);

    /**
     * 根据账号、密码以及用户身份登录
     *
     * @param account
     * @param password
     * @param power
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(String account, String password, Integer power) {
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password) || power == null) {
            Response.json("result", "账号密码不能为空！");
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("account", account);
        map.put("password", password);
        map.put("power", power);
        BaseAppUser user = baseAppUserService.login(map);
        if (user != null) {
            Response.result(200, "登录成功", user);
        } else {
            Response.json("result", "登录失败");
        }
    }

}
