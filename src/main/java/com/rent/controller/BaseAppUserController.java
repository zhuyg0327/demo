package com.rent.controller;

import com.rent.Annotation.AuthToken;
import com.rent.Base.Md5TokenGenerator;
import com.rent.Base.user.UserConfig;
import com.rent.entity.BaseAppUser;
import com.rent.entity.User;
import com.rent.service.BaseAppUserService;
import com.rent.service.UserService;
import com.rent.util.CommonUtil;
import com.rent.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/api/user")
public class BaseAppUserController {
    @Autowired
    BaseAppUserService baseAppUserService;
    @Autowired
    private Md5TokenGenerator tokenGenerator;@Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(BaseAppUserController.class);

    //登录
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(String username, String password, Integer power) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || power == null) {
            Response.json("result", "用户名密码不能为空！");
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("power", power);
        BaseAppUser user = baseAppUserService.login(map);
        if (user != null) {
            String token = SetRedisData(user.getUserId(), password);
            Response.result(200, "登录成功", token);
        } else {
            Response.json("result", "failed");
        }
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @AuthToken
    public void test() {
        logger.info("**************测试start**************");
        User userinfo= UserConfig.getUserInfo();
        Response.json(userinfo);
    }

    /**
     * 在redis中进行数据的绑定
     *
     * @param userId
     * @param password
     * @return
     * @Title: SetRedisData
     * @Description: TODO
     * @author zyg  2020年8月30日
     */
    private String SetRedisData(String userId, String password) {
        //此处主要设置你的redis的ip和端口号，我的redis是在本地
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String token = tokenGenerator.generate(userId, password);
        jedis.set(userId, token);
        //设置key过期时间，到期会自动删除
        jedis.expire(userId, CommonUtil.TOKEN_EXPIRE_TIME);
        //将token和userid以键值对的形式存入到redis中进行双向绑定
        jedis.set(token, userId);
        jedis.expire(token, CommonUtil.TOKEN_EXPIRE_TIME);
        Long currentTime = System.currentTimeMillis();
        jedis.set(token + userId, currentTime.toString());
        //用完关闭
        jedis.close();
        return token;
    }
}
