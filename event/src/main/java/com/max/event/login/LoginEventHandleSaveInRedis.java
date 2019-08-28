package com.max.event.login;

import com.max.base.dto.UserDto;
import com.max.core.constant.RedisConstant;
import com.max.core.constant.UserConstant;
import com.max.core.redis.RedisService;
import com.max.core.redis.impl.RedisKeyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginEventHandleSaveInRedis implements LoginEventHandle {
    @Autowired
    private RedisService redisService;
    public void handle(UserDto user){
        //考虑token实现方式
        redisService.set(RedisConstant.TOKEN_ONLINE , user, RedisConstant.USER_KEEP_TIME);
        redisService.set(RedisConstant.USER_ONLINE + user.getId().toString(), user, RedisConstant.USER_KEEP_TIME);
    }
}
