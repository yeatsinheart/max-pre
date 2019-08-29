package com.max.core.constant;


public enum PayWayEnum {
    /**
     * 充值方式
     */
    WANGYIN(1, "网银支付"),
    ZHIFUBAO(2, "支付宝"),
    WEIXIN(3, "微信支付"),
    QQ(4, "QQ支付"),
    UNION(5, "银联扫码"),
    JD_SCAN_PAY(6, "京东扫码支付"),
    BANKCARD_ZHUANZ(7, "银行卡转账"),
    ZHIFUBAO_ZHUANZ(8, "支付宝转账"),
    WEIXIN_ZHUANZ(9, "微信转账"),
    QQ_SCAN_PAY(9, "QQ扫码支付"),
    QUICK_PAY(24, "快捷支付"),
    UNION_PAY(25, "云闪付"),

    /**
     * 提现方式
     */

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
