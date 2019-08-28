package com.max.client.controller;

import com.max.base.dto.UserDto;
import com.max.base.dto.req.WalletOrderReqDto;
import com.max.base.entity.LogBank;
import com.max.base.entity.WalletLimit;
import com.max.base.entity.WalletOrder;
import com.max.base.service.LogBankService;
import com.max.base.service.WalletLimitService;
import com.max.base.service.WalletOrderService;
import com.max.core.redis.RedisResult;
import com.max.core.redis.RedisService;
import com.max.core.result.Result;
import com.max.core.result.ResultCode;
import com.max.core.result.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithdrawController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private WalletOrderService walletOrderService;
    @Autowired
    private LogBankService logBankService;
    @Autowired
    private WalletLimitService walletLimitService;

    /*提现*/
    @ApiOperation(value = "/withdraw", tags = {"提现"})
    @PostMapping("/withdraw")
    public Result withdraw(@RequestHeader String token, WalletOrderReqDto withdrawOrder) {
        RedisResult<UserDto> loginedUser = redisService.getResult(token, UserDto.class);
        UserDto user = loginedUser.getResult();
        if (null == user) {
            return ResultGenerator.genFailResult(ResultCode.TOEKNUNVALIBLE);
        }
        //是否绑定密码
        if (StringUtils.isBlank(user.getPasswd())) {
            return ResultGenerator.genFailResult(ResultCode.NO_WALLET_PASSWD);
        }
        //是否绑定银行卡
        LogBank bankCard =null;
        //todo bankCard = logBankService.getCard(user);
        if (null == bankCard) {
            return ResultGenerator.genFailResult(ResultCode.NO_USER_BANK);
        }
        //新建钱包，如果有有则通过，无则创建  获取账户可用提现订单号
        //绑定银行卡 有则通过，无则提示创建银行卡
        //校验提现约束
        boolean pass = false;
                // todo walletLimitService.passLimited(user);
        if (pass) {
            withdrawOrder.setUserId(user.getId());
            //扣钱生成提现订单
            WalletOrder withdrawOrderDto = null;//todo walletOrderService.withdraw(withdrawOrder);
            return ResultGenerator.genSuccessResult(withdrawOrderDto);
        }
        return ResultGenerator.genFailResult(ResultCode.WITHDRAW_LIMIT);
    }

    /*提现 约束条件*/
    @ApiOperation(value = "/limit", tags = {"提现 约束条件"})
    @PostMapping("/limit")
    public Result limit(@RequestHeader String token) {
        RedisResult<UserDto> loginedUser = redisService.getResult(token, UserDto.class);
        UserDto user = loginedUser.getResult();
        if (null == user) {
            return ResultGenerator.genFailResult(ResultCode.TOEKNUNVALIBLE.getCode(), ResultCode.TOEKNUNVALIBLE.getMessage());
        }
        WalletLimit limit = null ;// todo walletLimitService.getLimit(user);
        return ResultGenerator.genSuccessResult(limit);
    }
}
