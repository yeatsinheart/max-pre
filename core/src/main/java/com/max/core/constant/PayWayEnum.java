package com.max.core.constant;


public enum PayWayEnum {
    /**
     * 充值方式
     */
    WANGYIN(101, "网银支付"),
    ZHIFUBAO(102, "支付宝"),
    WEIXIN(103, "微信支付"),
    QQ(104, "QQ支付"),
    UNION(105, "银联扫码"),
    JD_SCAN_PAY(106, "京东扫码支付"),
    BANKCARD_ZHUANZ(107, "银行卡转账"),
    ZHIFUBAO_ZHUANZ(108, "支付宝转账"),
    WEIXIN_ZHUANZ(109, "微信转账"),
    QQ_SCAN_PAY(110, "QQ扫码支付"),
    QUICK_PAY(111, "快捷支付"),
    UNION_PAY(112, "云闪付"),
    /**
     * 提现方式
     */
    MAN_WITHDRAW(201, "人工操作"),
    /**
     * 游戏转账
     */
    GAME_TRANSFER(301, "游戏转账"),


    NULL(999, "未知支付方式");

    private Integer code;
    private String value;

    PayWayEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static PayWayEnum getByCode(Integer code) {
        for (PayWayEnum ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele;
            }
        }
        return NULL;
    }

    public static String getValue(Integer code) {
        for (PayWayEnum ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getValue();
            }
        }
        return NULL.getValue();
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
