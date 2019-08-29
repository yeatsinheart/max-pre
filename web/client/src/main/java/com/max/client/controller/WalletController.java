package com.max.client.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.max.base.dto.LogBankDto;
import com.max.base.dto.UserDto;
import com.max.base.dto.req.WalletOrderReqDto;
import com.max.base.entity.GameUser;
import com.max.base.entity.LogBank;
import com.max.base.entity.WalletOrder;
import com.max.base.service.GameUserService;
import com.max.base.service.LogBankService;
import com.max.base.service.UserService;
import com.max.base.service.WalletOrderService;
import com.max.core.constant.WalletOrderProcessEnum;
import com.max.core.constant.WalletOrderTypeEnum;
import com.max.core.exception.ServiceException;
import com.max.core.redis.RedisResult;
import com.max.core.redis.RedisService;
import com.max.core.redis.impl.RedisKeyHelper;
import com.max.core.result.Result;
import com.max.core.result.ResultCode;
import com.max.core.result.ResultGenerator;
import com.max.core.utils.MoneyUtil;
import com.max.supplier.GameDispatch;
import com.max.supplier.dto.GameTransferOutDto;
import com.max.supplier.service.GameService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

@RestController
public class WalletController {
    @Autowired
    private LogBankService logBankService;
    @Autowired
    private WalletOrderService walletOrderService;
    @Autowired
    private UserService userService;
    @Autowired
    private GameUserService gameUserService;
    @Autowired
    private RedisService redisService;
    //@Autowired
    private GameDispatch gameDispatch;

    /*绑定银行卡*/
    @ApiOperation(value = "/band", tags = {"绑定银行卡"})
    @PostMapping("/band")
    public Result band(@RequestHeader String token, LogBankDto bank) {
        //新建支付密码
        RedisResult<UserDto> userInRedis = redisService.getResult(RedisKeyHelper.TOKEN_ONLINE + token, UserDto.class);
        UserDto userInDb = userInRedis.getResult();
        if (null != userInDb) {
            if (StringUtils.isBlank(userInDb.getWithdrawPasswd())) {
                throw new ServiceException(ResultCode.NO_WALLET_PASSWD);
            }
            LogBank logBank = new LogBank();
            logBank.setBankUserName(bank.getBankUserName());
            logBank.setBankAccount(bank.getBankAccount());
            logBank.setBankType(3);
            logBank.setBankId(bank.getBankId());
            logBank.setUserId(userInDb.getId());
            boolean success = logBankService.save(logBank);
            if (success) {
                return ResultGenerator.genSuccessResult(success);
            }
            throw new ServiceException(ResultCode.BIND_BANK_ERR);
        }
        throw new ServiceException(ResultCode.KICKED_OUT);
    }

    /*我的钱包//todo 游戏钱包资金归集*/
    @ApiOperation(value = "/wallet", tags = {"我的钱包：归并"})
    @PostMapping("/wallet")
    public Result wallet(@RequestHeader String token) {
        RedisResult<UserDto> userInRedis = redisService.getResult(RedisKeyHelper.TOKEN_ONLINE + token, UserDto.class);
        UserDto userInDb = userInRedis.getResult();
        if (null != userInDb) {
            //查询各个游戏账户余额，并归集到钱包，所有都成功
            QueryWrapper<GameUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userInDb.getId());
            List<GameUser> gameUsers = gameUserService.list(queryWrapper);
            //查询钱包余额
            Vector<BigDecimal> wallets = new Vector<>();
            gameUsers.parallelStream().forEach(supplier -> {
                GameService gameService = gameDispatch.getGameServiceBySupplierId(supplier.getSupplierId().toString());

                BigDecimal money = gameService.balance(supplier);
                GameTransferOutDto transferOut = new GameTransferOutDto();
                //需要生成转出订单
                transferOut.setMoney(money);
                transferOut.setUser(supplier);
                wallets.add(money);
            });


        }
        throw new ServiceException(ResultCode.KICKED_OUT);
    }

    /*我的余额//todo 游戏钱包资金查询*/
    @ApiOperation(value = "/money", tags = {"我的余额：不归并"})
    @PostMapping("/money")
    public Result money(@RequestHeader String token) {
        RedisResult<UserDto> userInRedis = redisService.getResult(RedisKeyHelper.TOKEN_ONLINE + token, UserDto.class);
        UserDto userInDb = userInRedis.getResult();
        if (null != userInDb) {
            //查询各个游戏账户余额，并归集到钱包，所有都成功
            QueryWrapper<GameUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userInDb.getId());
            Vector<GameUser> wallets = new Vector<>();
            List<GameUser> gameUsers = gameUserService.list(queryWrapper);
            //查询钱包余额
            gameUsers.parallelStream().forEach(supplier -> {
                GameService gameService = gameDispatch.getGameServiceBySupplierId(supplier.getSupplierId().toString());
                BigDecimal money = BigDecimal.ZERO;
                 money = gameService.balance(supplier);
                supplier.setGameBalance(MoneyUtil.toStringMoney(money));
                supplier.setGameUserName(null);
                supplier.setGameUserPasswd(null);
                wallets.add(supplier);
            });
            return null;
        }
        throw new ServiceException(ResultCode.KICKED_OUT);
    }

    /*我的资金明细*/
    @ApiOperation(value = "/moneyhistory", tags = {"我的资金明细--已经成功的"})
    @PostMapping("/moneyhistory")
    public Result moneyhistory(WalletOrderReqDto walletLogRequest) {
        QueryWrapper<WalletOrder> successOrder = new QueryWrapper<>();
        successOrder.eq("user_id", walletLogRequest.getUserId())
                .eq("process", WalletOrderProcessEnum.OrderProcessEnum.FINISH.getCode());
        IPage<WalletOrder> walletOrders = walletOrderService.page(
                new Page<WalletOrder>(walletLogRequest.getPage(), walletLogRequest.getSize()),
                successOrder);
        return ResultGenerator.genSuccessResult(walletOrders);
    }

}
