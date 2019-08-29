package com.max.client.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.max.base.dto.UserDto;
import com.max.base.entity.User;
import com.max.base.service.UserService;
import com.max.client.dto.LoginUserRequest;
import com.max.client.dto.SigninUserRequest;
import com.max.core.constant.RedisConstant;
import com.max.core.constant.UserConstant;
import com.max.core.exception.ServiceException;
import com.max.core.redis.RedisResult;
import com.max.core.redis.RedisService;
import com.max.core.redis.impl.RedisKeyHelper;
import com.max.core.result.Result;
import com.max.core.result.ResultCode;
import com.max.core.result.ResultGenerator;
import com.max.core.utils.MoneyUtil;
import com.max.core.utils.ProjectTokenUtils;
import com.max.core.utils.RandomName;
import com.max.event.login.LoginEvent;
import com.max.event.signin.SigninEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@Api(value = "/user", tags = {"用户"})
public class UserController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;
    @Autowired
    private SigninEvent signinEvent;
    @Autowired
    private LoginEvent loginEvent;

    @ApiOperation(value = "/login", tags = {"账号密码登录"})
    @PostMapping("/login")
    public Result<UserDto> login(@RequestHeader(required = false) String token, @Validated LoginUserRequest login, String code) {
        RedisResult<UserDto> userInRedis = redisService.getResult(RedisKeyHelper.TOKEN_ONLINE + token, UserDto.class);
        UserDto userInDb = userInRedis.getResult();
        if (null == userInDb) {
            String loginCodeInRedis = redisService.getResult(RedisConstant.CODE_SEND_LOGIN + login.getName(), String.class).getResult();
            if (StringUtils.isEmpty(loginCodeInRedis)) {
                throw new ServiceException(ResultCode.VALIDATAE_CODE_EXPIRED);
            }
            if (StringUtils.isNotBlank(code) && code.equals(loginCodeInRedis)) {
                /*用户名 或者 密码 错误*/
                QueryWrapper<User> userByUserName = new QueryWrapper<User>().eq("name", login.getName());
                User user = userService.getOne(userByUserName);//todo .login(loginRequest);
                if (null == user) {
                    throw new ServiceException("账号不存在");
                }
                if (!user.getPasswd().equals(login.getPasswd())) {
                    throw new ServiceException("账号与密码不匹配");
                }
                userInDb.setId(user.getId());
                userInDb.setNick(user.getNick());
                userInDb.setStatus(user.getStatus());
                userInDb.setBalance(MoneyUtil.toBigDecimalMoney(user.getBalance()));
                userInDb.setToken(ProjectTokenUtils.genToken());
            } else {
                throw new ServiceException(ResultCode.INVALID_SMS_CODE);
            }
        }
        loginEvent.handle(userInDb);
        return ResultGenerator.genSuccessResult(userInDb);
    }

    /*手机号验证码登录*/
    @ApiOperation(value = "/phone/login", tags = {"手机号验证码登录"})
    @PostMapping("/phone/login")
    public Result<UserDto> loginPhone(@RequestHeader(required = false) String token, @Validated LoginUserRequest login, String code) {
        RedisResult<UserDto> userInRedis = redisService.getResult(RedisKeyHelper.TOKEN_ONLINE + token, UserDto.class);
        UserDto userInDb = userInRedis.getResult();
        if (null == userInDb) {
            String loginCodeInRedis = redisService.getResult(RedisConstant.SMS_CODE_SEND_LOGIN + login.getName(), String.class).getResult();
            if (StringUtils.isEmpty(loginCodeInRedis)) {
                throw new ServiceException(ResultCode.VALIDATAE_CODE_EXPIRED);
            }
            if (StringUtils.isNotBlank(code) && code.equals(loginCodeInRedis)) {
                UserDto request = login.getUserDto();
                User user = userService.getOne(new QueryWrapper<User>().eq("name", login.getName()));//todo .login(loginRequest);
                if (null == user) {
                    throw new ServiceException("账号不存在");
                }
                userInDb.setId(user.getId());
                userInDb.setNick(user.getNick());
                userInDb.setStatus(user.getStatus());
                userInDb.setBalance(MoneyUtil.toBigDecimalMoney(user.getBalance()));
                userInDb.setToken(ProjectTokenUtils.genToken());
            } else {
                throw new ServiceException(ResultCode.INVALID_SMS_CODE);
            }
        }
        loginEvent.handle(userInDb);
        return ResultGenerator.genSuccessResult(userInDb);
    }

    @ApiOperation(value = "/heart", tags = {"心跳检测"})
    @PostMapping("/heart")
    public Result heart(@RequestHeader(required = false) String token) throws ServiceException {
        //当前
        if (StringUtils.isEmpty(token)) {
            return ResultGenerator.genSuccessResult();
        } else {
            RedisResult<UserDto> loginedUser = redisService.getResult(token, UserDto.class);
            if (null == loginedUser.getResult()) {
                throw new ServiceException(ResultCode.KICKED_OUT);
            }
            //增加缓存时间 10分鐘
            redisService.setTTL(RedisKeyHelper.TOKEN_ONLINE + loginedUser.getResult().getToken(), UserConstant.USER_REDIS_KEEP_TIME);
            redisService.setTTL(RedisKeyHelper.USER_ONLINE + loginedUser.getResult().getName(), UserConstant.USER_REDIS_KEEP_TIME);
            return ResultGenerator.genSuccessResult();
        }
    }

    @ApiOperation(value = "/signin", tags = {"注册"})
    @PostMapping("/signin")
    public Result<UserDto> signin(SigninUserRequest signinUserRequest, String code) throws ServiceException {
        //获取已发送的手机验证码
        String signinCodeInRedis = redisService.getResult(RedisKeyHelper.USER_PHONE_SIGNIN_CODE + signinUserRequest.getName(), String.class).getResult();
        if (StringUtils.isEmpty(signinCodeInRedis)) {
            throw new ServiceException(ResultCode.VALIDATAE_CODE_EXPIRED);
        }
        //手机验证码正确
        if (StringUtils.isNotBlank(code) && code.equals(signinCodeInRedis)) {
            UserDto request = new UserDto();
            request.setName(signinUserRequest.getName());
            request.setPasswd(signinUserRequest.getPasswd());
            request.setNick(RandomName.randomName(false, 4));
            UserDto userInDb = null;
            User user = new User();
            user.setName(signinUserRequest.getName());
            user.setPasswd(signinUserRequest.getPasswd());
            user.setNick(RandomName.getRandomJianHan(4));
            user.setBalance("0");
            user.setStatus(0);
            user.setCreateTime(LocalDateTime.now());
            boolean success = userService.save(user);
            if (!success) {
                throw new ServiceException(ResultCode.USER_NAME_EXIST);
            }
            user = userService.getOne(new QueryWrapper<User>().eq("name", user.getName()));
            userInDb.setId(user.getId());
            userInDb.setNick(user.getNick());
            userInDb.setStatus(user.getStatus());
            userInDb.setBalance(MoneyUtil.toBigDecimalMoney(user.getBalance()));
            userInDb.setToken(ProjectTokenUtils.genToken());
            signinEvent.handle(userInDb);
            return ResultGenerator.genSuccessResult(userInDb);
        }
        throw new ServiceException(ResultCode.INVALID_SMS_CODE);
    }
}
