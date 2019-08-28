package com.max.client.controller;

import com.max.base.dto.UserDto;
import com.max.base.dto.WalletOrderDto;
import com.max.base.service.WalletOrderService;
import com.max.core.redis.RedisResult;
import com.max.core.redis.RedisService;
import com.max.core.result.Result;
import com.max.core.result.ResultCode;
import com.max.core.result.ResultGenerator;
import com.max.event.recharge.RechargeEvent;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RechargeController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private WalletOrderService walletOrderService;
    @Autowired
    private RechargeEvent rechargeEvent;

    @ApiOperation(value = "/recharge", tags = {"提交充值订单"})
    @PostMapping("/recharge")
    public Result recharge(@RequestHeader String token, WalletOrderDto rechargeOrder) {
        RedisResult<UserDto> loginedUser = redisService.getResult(token, UserDto.class);
        UserDto user = loginedUser.getResult();
        if (null == user) {
            return ResultGenerator.genFailResult(ResultCode.TOEKNUNVALIBLE);
        }
        rechargeOrder.setUserId(user.getId());
        rechargeOrder = null ;//todo walletOrderService.recharge(rechargeOrder);
        return ResultGenerator.genSuccessResult(rechargeOrder);
    }

    @ApiOperation(value = "/recharge/process", tags = {"充值在途订单"})
    @PostMapping("/recharge/process")
    public Result rechargeprocess(@RequestHeader String token) {
        RedisResult<UserDto> loginedUser = redisService.getResult(token, UserDto.class);
        UserDto user = loginedUser.getResult();
        if (null == user) {
            return ResultGenerator.genFailResult(ResultCode.TOEKNUNVALIBLE);
        }
        WalletOrderDto rechargeOrder = null;//todo walletOrderService.rechargeProcess(user);
        return ResultGenerator.genSuccessResult(rechargeOrder);
    }

    @ApiOperation(value = "/recharge/process", tags = {"撤销充值在途订单"})
    @PostMapping("/canclerecharge")
    public Result canclerecharge(WalletOrderDto cancle) {
        boolean result = false;//todo walletOrderService.cancleRecharge(cancle);
        return ResultGenerator.genSuccessResult(result);
    }

}
