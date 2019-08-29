package com.max.transaction.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class WalletMoneyChangeRequest implements Serializable {
    private Integer userId;
    private BigDecimal money;
}
