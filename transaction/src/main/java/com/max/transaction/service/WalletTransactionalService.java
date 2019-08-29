package com.max.transaction.service;

import com.max.transaction.dto.WalletMoneyChangeRequest;

import java.math.BigDecimal;

public interface WalletTransactionalService {
    /**
     * 查钱
     */
    BigDecimal findMoney(WalletMoneyChangeRequest moneyChangeRequest);

    /**
     * 加钱
     */
    boolean addMoney(WalletMoneyChangeRequest moneyChangeRequest);

    /**
     * 扣钱
     */
    boolean minusMoney(WalletMoneyChangeRequest moneyChangeRequest);
}
