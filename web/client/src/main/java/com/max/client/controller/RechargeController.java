package com.max.client.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.max.base.dto.UserDto;
import com.max.base.dto.WalletOrderDto;
import com.max.base.entity.WalletOrder;
import com.max.base.service.WalletOrderService;
import com.max.core.constant.PayWayEnum;
import com.max.core.constant.WalletOrderProcessEnum;
import com.max.core.constant.WalletOrderTypeEnum;
import com.max.core.exception.ServiceException;
import com.max.core.redis.RedisResult;
import com.max.core.redis.RedisService;
import com.max.core.result.Result;
import com.max.core.result.ResultCode;
import com.max.core.result.ResultGenerator;
import com.max.core.utils.MoneyUtil;
import com.max.core.utils.OrderCreator;
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
    public Result<WalletOrderDto> recharge(@RequestHeader String token, WalletOrderDto rechargeOrder) {
        RedisResult<UserDto> loginedUser = redisService.getResult(token, UserDto.class);
        UserDto user = loginedUser.getResult();
        if (null == user) {
            throw new ServiceException(ResultCode.TOEKNUNVALIBLE);
        }
        rechargeOrder.setUserId(user.getId());
        boolean success = false;
        WalletOrder order = null;
        while (!success) {
            order = OrderCreator.create(user, rechargeOrder.getMoney(), PayWayEnum.getByCode(rechargeOrder.getPayWay()), WalletOrderTypeEnum.RECHARGE);
            success = walletOrderService.save(order);
        }
        return ResultGenerator.genSuccessResult(rechargeOrder);
    }

    @ApiOperation(value = "/recharge/process", tags = {"充值在途订单"})
    @PostMapping("/recharge/process")
    public Result<WalletOrderDto> rechargeprocess(@RequestHeader String token) {
        RedisResult<UserDto> loginedUser = redisService.getResult(token, UserDto.class);
        UserDto user = loginedUser.getResult();
        if (null == user) {
            throw new ServiceException(ResultCode.TOEKNUNVALIBLE);
        }
        QueryWrapper<WalletOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id", user.getId())
                .eq("type", WalletOrderTypeEnum.RECHARGE.getCode());
        queryWrapper.and(wrapper -> wrapper.ne("process", WalletOrderProcessEnum.OrderProcessEnum.FINISH).or().ne("process", WalletOrderProcessEnum.OrderProcessEnum.FINISH));
        WalletOrder waitOrder = walletOrderService.getOne(queryWrapper);
        WalletOrderDto rechargeOrder = new WalletOrderDto();
        rechargeOrder.setOrderId(waitOrder.getOrderId());
        rechargeOrder.setType(waitOrder.getType());
        rechargeOrder.setPayWay(waitOrder.getPayWay());
        rechargeOrder.setMoney(MoneyUtil.toBigDecimalMoney(waitOrder.getMoney()));
        rechargeOrder.setSeries(waitOrder.getSeries());
        rechargeOrder.setCreateTime(waitOrder.getCreateTime());
        rechargeOrder.setUpdateTime(waitOrder.getUpdateTime());
        rechargeOrder.setPayer(waitOrder.getPayer());
        rechargeOrder.setReceiver(waitOrder.getReceiver());
        rechargeOrder.setProcess(waitOrder.getProcess());
        rechargeOrder.setProcessResult(waitOrder.getProcessResult());
        rechargeOrder.setTryNum(waitOrder.getTryNum());
        return ResultGenerator.genSuccessResult(rechargeOrder);
    }

    @ApiOperation(value = "/recharge/delete", tags = {"撤销充值在途订单"})
    @PostMapping("/recharge/delete")
    public Result<Boolean> delete(@RequestHeader String token, WalletOrderDto cancle) {
        RedisResult<UserDto> loginedUser = redisService.getResult(token, UserDto.class);
        UserDto user = loginedUser.getResult();
        if (null == user) {
            throw new ServiceException(ResultCode.TOEKNUNVALIBLE);
        }
        WalletOrder order = OrderCreator.delete();
        UpdateWrapper<WalletOrder> queryWrapper = new UpdateWrapper<>();
        queryWrapper
                .eq("user_id", user.getId())
                .eq("type", WalletOrderTypeEnum.RECHARGE.getCode())
                .eq("series", cancle.getSeries());
        queryWrapper.and(wrapper -> wrapper.ne("process", WalletOrderProcessEnum.OrderProcessEnum.FINISH).or().ne("process", WalletOrderProcessEnum.OrderProcessEnum.FINISH));
       //todo 事务里面做所有订单修改操作
        boolean success = walletOrderService.update(order, queryWrapper);
        return ResultGenerator.genSuccessResult(success);
    }

}
