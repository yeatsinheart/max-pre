package com.max.client.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.max.base.dto.UserDto;
import com.max.base.dto.req.WalletOrderReqDto;
import com.max.base.entity.LogBank;
import com.max.base.entity.WalletLimit;
import com.max.base.entity.WalletOrder;
import com.max.base.service.LogBankService;
import com.max.base.service.WalletLimitService;
import com.max.base.service.WalletOrderService;
import com.max.core.constant.PayWayEnum;
import com.max.core.constant.WalletOrderTypeEnum;
import com.max.core.exception.ServiceException;
import com.max.core.redis.RedisResult;
import com.max.core.redis.RedisService;
import com.max.core.result.Result;
import com.max.core.result.ResultCode;
import com.max.core.result.ResultGenerator;
import com.max.core.utils.OrderCreator;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Result<WalletOrderReqDto> withdraw(@RequestHeader String token, WalletOrderReqDto withdrawOrder) {
        RedisResult<UserDto> loginedUser = redisService.getResult(token, UserDto.class);
        UserDto user = loginedUser.getResult();
        if (null == user) {
            throw new ServiceException(ResultCode.TOEKNUNVALIBLE);
        }
        //是否绑定密码
        if (StringUtils.isBlank(user.getPasswd())) {
            throw new ServiceException(ResultCode.NO_WALLET_PASSWD);
        }
        //是否绑定银行卡
        LogBank bankCard = null;
        QueryWrapper<LogBank> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id", user.getId())
                .eq("bank_type", 3)
                .orderByDesc("create_time")
        ;
        List<LogBank> logBanks = logBankService.list(queryWrapper);
        if (CollectionUtils.isEmpty(logBanks)) {
            throw new ServiceException(ResultCode.NO_USER_BANK);
        }
        //校验提现约束
        boolean pass = false;
        // todo 提现条件  walletLimitService.passLimited(user);
        if (pass) {
            withdrawOrder.setUserId(user.getId());
            // todo 事务内    扣钱生成提现订单
            boolean success = false;
            WalletOrder order = null;
            while (!success) {
                order = OrderCreator.create(user, withdrawOrder.getMoney(), PayWayEnum.getByCode(withdrawOrder.getPayWay()), WalletOrderTypeEnum.WIDTHDRAW);
                success = walletOrderService.save(order);
            }
            withdrawOrder.setSeries(order.getSeries());
            return ResultGenerator.genSuccessResult(withdrawOrder);
        }

        throw new ServiceException(ResultCode.WITHDRAW_LIMIT);
    }

    /*todo 提现约束条件*/
    @ApiOperation(value = "/limit", tags = {"提现 约束条件"})
    @PostMapping("/limit")
    public Result limit(@RequestHeader String token) {
        RedisResult<UserDto> loginedUser = redisService.getResult(token, UserDto.class);
        UserDto user = loginedUser.getResult();
        if (null == user) {
            throw new ServiceException(ResultCode.TOEKNUNVALIBLE);
        }
        WalletLimit limit = null;
        return ResultGenerator.genSuccessResult(limit);
    }
}
