package com.max.base.service.impl;

import com.max.base.entity.User;
import com.max.base.mapper.UserMapper;
import com.max.base.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import com.max.base.mapper.UserMapper;

/**
 * 用户表 服务实现类
 * @author zane
 * @since 2019-08-28
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
