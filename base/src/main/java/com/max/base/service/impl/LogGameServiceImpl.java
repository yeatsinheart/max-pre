package com.max.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.base.entity.LogGame;
import com.max.base.mapper.LogGameMapper;
import com.max.base.service.LogGameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 游戏记录 服务实现类
 *
 * @author zane
 * @since 2019-08-28
 */
@Slf4j
@Service
public class LogGameServiceImpl extends ServiceImpl<LogGameMapper, LogGame> implements LogGameService {

}
