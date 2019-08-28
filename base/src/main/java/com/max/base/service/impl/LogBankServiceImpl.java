package com.max.base.service.impl;

import com.max.base.entity.LogBank;
import com.max.base.mapper.LogBankMapper;
import com.max.base.service.LogBankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import com.max.base.mapper.LogBankMapper;

/**
 * 用户银行卡记录 服务实现类
 * @author zane
 * @since 2019-08-28
 */
@Slf4j
@Service
public class LogBankServiceImpl extends ServiceImpl<LogBankMapper, LogBank> implements LogBankService {

}
