package com.max.transaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.max.base.entity.User;
import com.max.base.service.UserService;
import com.max.core.exception.ServiceException;
import com.max.core.utils.MoneyUtil;
import com.max.transaction.dto.WalletMoneyChangeRequest;
import com.max.transaction.service.WalletTransactionalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
public class WalletTransactionalServiceImpl implements WalletTransactionalService {
    @Autowired
    private UserService userService;


    /**
     * 查钱
     *
     * @param moneyChangeRequest
     */
    @Override
    public BigDecimal findMoney(WalletMoneyChangeRequest moneyChangeRequest) {
        User user = userService.getById(moneyChangeRequest.getUserId());
        if (null == user) {
            log.error("用户ID{}不存在", moneyChangeRequest);
            return BigDecimal.ZERO;
        }
        return MoneyUtil.toBigDecimalMoney(user.getBalance());
    }

    /**
     * 加钱
     *
     * @param moneyChangeRequest
     */
    @Transactional
    @Override
    public boolean addMoney(WalletMoneyChangeRequest moneyChangeRequest) {
        User user = userService.getById(moneyChangeRequest.getUserId());
        if (null == user) {
            log.error("用户ID{}不存在", moneyChangeRequest);
            return false;
        }
        User changeMoney = new User();
        changeMoney.setBalance(MoneyUtil.toStringMoney(
                MoneyUtil.toBigDecimalMoney(user.getBalance())
                        .add(moneyChangeRequest.getMoney().abs())
                )
        );
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", user.getId());
        userService.update(changeMoney, userUpdateWrapper);
        return true;
    }

    /**
     * 扣钱
     *
     * @param moneyChangeRequest
     */
    @Transactional
    @Override
    public boolean minusMoney(WalletMoneyChangeRequest moneyChangeRequest) {
        User user = userService.getById(moneyChangeRequest.getUserId());
        if (null == user) {
            log.error("用户ID{}不存在", moneyChangeRequest);
            return false;
        }
        User changeMoney = new User();
        BigDecimal newBalance = MoneyUtil.toBigDecimalMoney(user.getBalance()).subtract(moneyChangeRequest.getMoney().abs());
        if (newBalance.compareTo(BigDecimal.ZERO) == -1) {
            log.warn("用户余额" + MoneyUtil.toBigDecimalMoney(user.getBalance()) + "不足" + moneyChangeRequest.getMoney().abs());
            return false;
        }
        changeMoney.setBalance(MoneyUtil.toStringMoney(newBalance));
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", user.getId());
        userService.update(changeMoney, userUpdateWrapper);
        return true;
    }
}
