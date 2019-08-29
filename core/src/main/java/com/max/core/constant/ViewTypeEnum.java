package com.max.core.constant;

public enum ViewTypeEnum {
    GONGGAO(1,"公告"),
    LUNBOTU(2,"轮播图"),
    ALERT(3,"弹窗"),
    GAME(4,"游戏"),
    ACTIVITY(5,"活动"),
    RECHARGE(6,"充值方式");
    ViewTypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    private int code;
    private String value;
    public static String getValue(String code) {
        for (ViewTypeEnum ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getValue();
            }
        }
        return null;
    }
    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
