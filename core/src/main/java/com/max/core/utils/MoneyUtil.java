package com.max.core.utils;

/**
 * Created by ken on 2018/5/14.
 */

import java.math.BigDecimal;

public class MoneyUtil {
    public static BigDecimal toBigDecimalMoney(String balance) {
        return new BigDecimal(balance).divide(new BigDecimal(1000)).setScale(2);
    }

    public static String toStringMoney(BigDecimal money) {
        return money.multiply(new BigDecimal(1000)).setScale(0).toString();
    }
}
