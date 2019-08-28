package com.max.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.base.entity.WalletSeries;
import com.max.base.mapper.WalletSeriesMapper;
import com.max.base.service.WalletSeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 钱包流水号 服务实现类
 *
 * @author zane
 * @since 2019-08-28
 */
@Slf4j
@Service
public class WalletSeriesServiceImpl extends ServiceImpl<WalletSeriesMapper, WalletSeries> implements WalletSeriesService {

}
