package com.max.base.service.impl;

import com.max.base.entity.LogWalletLimit;
import com.max.base.mapper.LogWalletLimitMapper;
import com.max.base.service.LogWalletLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import com.max.base.mapper.LogWalletLimitMapper;

/**
 * 钱包变动记录 服务实现类
 * @author zane
 * @since 2019-08-28
 */
@Slf4j
@Service
public class LogWalletLimitServiceImpl extends ServiceImpl<LogWalletLimitMapper, LogWalletLimit> implements LogWalletLimitService {

}
