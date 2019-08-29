package com.max.core.constant;

public enum WalletOrderTypeEnum {
    RECHARGE(1, "充值"),
    WIDTHDRAW(2, "提现"),
    TRANSFER_IN(3, "转入游戏"),
    TRANSFER_OUT(4, "游戏转出"),
    NULL(999, "未知");
    private Integer code;
    private String value;

    WalletOrderTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValue(String code) {
        for (WalletOrderTypeEnum ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getValue();
            }
        }
        return NULL.getValue();
    }

    /**
     * 扣钱的操作
     */
    public static boolean subtractMoney2Wallet(Integer code) {

        if (code == WalletOrderTypeEnum.WIDTHDRAW.getCode()) {
            return true;
        }else if (code == WalletOrderTypeEnum.TRANSFER_IN.getCode()) {
            return true;
        }
        return false;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
