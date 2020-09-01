package com.rent.util;

import com.rent.interceptor.TokenInterceptor;
import redis.clients.jedis.Jedis;

public class CurrentUser {
    /**
     * 从token中获取用户信息
     * 朱阳刚 2020.09.01
     *
     * @return
     */
    public static String getUserId() {
        String token = TokenInterceptor.getToken();
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String userId = jedis.get(token);
        return userId;
    }
}
