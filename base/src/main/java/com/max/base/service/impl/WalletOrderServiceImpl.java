package com.max.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.base.entity.WalletOrder;
import com.max.base.mapper.WalletOrderMapper;
import com.max.base.service.WalletOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 账户订单 服务实现类
 *
 * @author zane
 * @since 2019-08-29
 */
@Slf4j
@Service
public class WalletOrderServiceImpl extends ServiceImpl<WalletOrderMapper, WalletOrder> implements WalletOrderService {

}
