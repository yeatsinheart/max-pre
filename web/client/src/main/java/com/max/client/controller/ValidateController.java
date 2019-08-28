package com.max.client.controller;

import com.max.client.dto.LoginUserRequest;
import com.max.core.constant.RedisConstant;
import com.max.core.constant.UserConstant;
import com.max.core.redis.RedisService;
import com.max.core.redis.impl.RedisKeyHelper;
import com.max.core.result.Result;
import com.max.core.result.ResultCode;
import com.max.core.result.ResultGenerator;
import com.max.core.utils.ImageCodeGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Slf4j
@RestController
public class ValidateController {
    @Autowired
    private RedisService redisService;
    private static int codeLength = 4;

    @ApiOperation(value = "/login/code", tags = {"登录图片验证码"})
    @RequestMapping("/login/code")
    public Result login(@NotNull String name) {
        String code = ImageCodeGenerator.generateVerifyCode(codeLength);
        ServletOutputStream out = null;
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        try {
            out = response.getOutputStream();
            int w = 200, h = 80;
            ImageCodeGenerator.outputImage(w, h, response.getOutputStream(), code);
            redisService.set(RedisConstant.CODE_SEND_LOGIN + name, code, RedisConstant.CODE_VALID_TIME);
            out.flush();
        } catch (Exception e) {
            log.error("获取图片验证码失败", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return ResultGenerator.genSuccessResult(code);
    }

    @ApiOperation(value = "/login/phone/code", tags = {"手机短信登录验证码"})
    @RequestMapping("/login/phone/code")
    public Result loginphone(@NotNull String name, String code) {
        //网页验证码
        String pageCode = redisService.getResult(RedisConstant.CODE_SEND_LOGIN + name, String.class).getResult();
        if (StringUtils.isEmpty(pageCode)) {
            return ResultGenerator.genFailResult(ResultCode.VALIDATAE_CODE_EXPIRED);
        }
        //短信验证码
        if (StringUtils.isNotBlank(code) && code.equals(pageCode)) {
            String phoneCode = redisService.getResult(RedisConstant.SMS_CODE_SEND_LOGIN + name, String.class).getResult();
            if (StringUtils.isNotBlank(phoneCode)) {
                return ResultGenerator.genFailResult(ResultCode.PHONE_VALIDATAE_CODE_SENDED);
            }
            String phone =  ImageCodeGenerator.generateVerifyCode(codeLength);
            redisService.set(RedisConstant.SMS_CODE_SEND_LOGIN + name, phone, RedisConstant.SMS_CODE_SEND_GAP_TIME);
            return ResultGenerator.genSuccessResult(code);
        } else {
            return ResultGenerator.genFailResult(ResultCode.INVALID_SMS_CODE);
        }

    }


    @ApiOperation(value = "/signin/code", tags = {"账号注册：图片验证码"})
    @RequestMapping("/signin/code")
    public Result signin(@NotNull String name) {
        String code =  ImageCodeGenerator.generateVerifyCode(codeLength);
        redisService.set(RedisConstant.CODE_SEND_SIGN + name, code, UserConstant.VALIDATE_REDIS_KEEP_TIME);
        return ResultGenerator.genSuccessResult(code);
    }

    @ApiOperation(value = "/signin/phone/code", tags = {"账号注册：短信验证码"})
    @RequestMapping("/signin/phone/code")
    public Result signinphone(@NotNull String name, String code) {
        //网页验证码
        String pageCode = redisService.getResult(RedisConstant.CODE_SEND_SIGN + name, String.class).getResult();
        if (StringUtils.isEmpty(pageCode)) {
            return ResultGenerator.genFailResult(ResultCode.VALIDATAE_CODE_EXPIRED);
        }
        //短信验证码
        if (StringUtils.isNotBlank(code) && code.equals(pageCode)) {
            String phoneCode = redisService.getResult(RedisConstant.SMS_CODE_SEND_SIGN + name, String.class).getResult();
            if (StringUtils.isNotBlank(phoneCode)) {
                return ResultGenerator.genFailResult(ResultCode.PHONE_VALIDATAE_CODE_SENDED);
            }
            String phone =  ImageCodeGenerator.generateVerifyCode(codeLength);
            redisService.set(RedisConstant.SMS_CODE_SEND_SIGN + name, phone, UserConstant.VALIDATE_REDIS_KEEP_TIME);
            return ResultGenerator.genSuccessResult(code);
        }
        return ResultGenerator.genFailResult(ResultCode.INVALID_SMS_CODE);
    }
}
