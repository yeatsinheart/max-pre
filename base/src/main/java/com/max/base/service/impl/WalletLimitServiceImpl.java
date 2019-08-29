package com.max.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.base.entity.WalletLimit;
import com.max.base.mapper.WalletLimitMapper;
import com.max.base.service.WalletLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 系统级别提现设置 服务实现类
 *
 * @author zane
 * @since 2019-08-29
 */
@Slf4j
@Service
public class WalletLimitServiceImpl extends ServiceImpl<WalletLimitMapper, WalletLimit> implements WalletLimitService {

}
