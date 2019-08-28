package com.max.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.base.entity.LogUser;
import com.max.base.mapper.LogUserMapper;
import com.max.base.service.LogUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户操作记录 服务实现类
 *
 * @author zane
 * @since 2019-08-28
 */
@Slf4j
@Service
public class LogUserServiceImpl extends ServiceImpl<LogUserMapper, LogUser> implements LogUserService {

}
