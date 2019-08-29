package com.max.core.constant;

public interface RedisConstant {
    /**
     * 用户数据保存在redis时间
     */
    Integer USER_KEEP_TIME = 60 * 10;
    /**
     * 短信发送时间隔控制
     */
    Integer SMS_CODE_SEND_GAP_TIME = 60;
    /**
     * 短信验证码有效时间
     */
    Integer CODE_VALID_TIME = 90;
    /**
     * 网页账号密码登录验证码编码
     */
    String CODE_SEND_LOGIN = "CODE_SEND_LOGIN_";
    /**
     * 登录短信验证码编码
     */
    String SMS_CODE_SEND_LOGIN = "SMS_CODE_SEND_LOGIN_";
    /**
     * 网页注册验证码编码
     */
    String CODE_SEND_SIGN = "CODE_SEND_SIGN_";
    /**
     * 注册短信验证码编码
     */
    String SMS_CODE_SEND_SIGN = "SMS_CODE_SEND_SIGN_";

    String TOKEN_ONLINE = "TOKEN_ONLINE_";
    String USER_ONLINE = "USER_ONLINE_";
}
