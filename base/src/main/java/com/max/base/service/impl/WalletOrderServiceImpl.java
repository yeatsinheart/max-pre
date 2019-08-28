package com.max.base.service.impl;

import com.max.base.entity.WalletOrder;
import com.max.base.mapper.WalletOrderMapper;
import com.max.base.service.WalletOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import com.max.base.mapper.WalletOrderMapper;

/**
 * 订单 服务实现类
 * @author zane
 * @since 2019-08-28
 */
@Slf4j
@Service
public class WalletOrderServiceImpl extends ServiceImpl<WalletOrderMapper, WalletOrder> implements WalletOrderService {

}
