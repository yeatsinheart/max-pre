package com.max.event.recharge;

import com.max.base.dto.WalletOrderDto;
import com.max.core.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RechargeEventHandleDefault implements RechargeEventHandle {
    @Autowired
    private RedisService redisService;

    public void handle(WalletOrderDto recharge) {

    }
}
