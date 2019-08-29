package com.max.core.utils;

import com.max.core.constant.RedisConstant;
import com.max.core.redis.RedisService;
import com.max.core.redis.impl.RedisKeyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//todo 用户登录后信息工具
//todo 用户黑名单
//todo 虚假用户

/**
 * token信息
 */
@Component
public class UserInfoUtil {
    @Autowired
    private static RedisService redisService;

    /**
     * 获取已登录用户token
     */
    public static String getToke(Integer userId) {
        return null;
    }

    /**
     * 获取已登录用户token
     */
    public static boolean validToken(Integer userId) {
        return false;
    }

    /**
     * 用户token缓存
     */
    public static void continueToken(String token, Integer userId) {
        //增加缓存时间 10分鐘
        redisService.set(RedisKeyHelper.TOKEN_ONLINE + token, userId, RedisConstant.USER_KEEP_TIME);
        redisService.set(RedisKeyHelper.USER_ONLINE + userId, token, RedisConstant.USER_KEEP_TIME);
    }

    /**
     * 用户token踢下线
     */
    public static boolean knicked(String token) {
        return false;
    }
}
