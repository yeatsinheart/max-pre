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
import com.max.core.constant.PayWayEnum;
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
import com.max.transaction.dto.OrderCreateRequest;
import com.max.transaction.service.OrderTransactionalService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class WalletController {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    @Autowired
    private LogBankService logBankService;
    @Autowired
    private WalletOrderService walletOrderService;
    @Autowired
    private OrderTransactionalService orderTransactionalService;
    @Autowired
    private UserService userService;
    @Autowired
    private GameUserService gameUserService;
    @Autowired
    private RedisService redisService;
    @Autowired
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
            //获取该用户的所有游戏账号
            List<GameUser> gameUsers = gameUserService.list(queryWrapper);
            //查询钱包余额
            //todo 添加多线程
            //List<Future<T>> futuresList = new ArrayList<>();
            Vector<BigDecimal> wallets = new Vector<>();
            //遍历游戏账号 获取相应的金额
            //todo 需要 多线程 生成游戏转出订单 确认是否有提供转出所有的功能
            gameUsers.parallelStream().forEach(supplier -> {
                //获取提供商服务
                GameService gameService = gameDispatch.getGameServiceBySupplierId(supplier.getSupplierId().toString());
                BigDecimal money = gameService.balance(supplier);
                GameTransferOutDto transferOut = new GameTransferOutDto();
                transferOut.setMoney(money);
                transferOut.setUser(supplier);
                OrderCreateRequest create = new OrderCreateRequest();
                create.setUserId(supplier.getUserId());
                create.setType(WalletOrderTypeEnum.TRANSFER_OUT.getCode());
                create.setPayWay(PayWayEnum.GAME_TRANSFER.getCode());
                create.setMoney(money);
                create.setPayer(supplier.getSupplierId().toString());
                create.setReceiver(supplier.getUserId().toString());
                WalletOrder order = orderTransactionalService.createOrder(create);
                wallets.add(money);
            });


        }
        throw new ServiceException(ResultCode.KICKED_OUT);
    }

    /*我的余额*/
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
            //todo 玩家账号余额 list 封装成页面的 钱包《游戏，余额》
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
