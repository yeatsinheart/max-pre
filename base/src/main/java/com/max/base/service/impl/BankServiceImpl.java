package com.max.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.base.entity.Bank;
import com.max.base.mapper.BankMapper;
import com.max.base.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 支持的银行 服务实现类
 *
 * @author zane
 * @since 2019-08-29
 */
@Slf4j
@Service
public class BankServiceImpl extends ServiceImpl<BankMapper, Bank> implements BankService {

}
