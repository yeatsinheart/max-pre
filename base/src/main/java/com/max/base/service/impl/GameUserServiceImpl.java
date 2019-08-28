package com.max.base.service.impl;

import com.max.base.entity.GameUser;
import com.max.base.mapper.GameUserMapper;
import com.max.base.service.GameUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import com.max.base.mapper.GameUserMapper;

/**
 * 游戏角色信息 服务实现类
 * @author zane
 * @since 2019-08-28
 */
@Slf4j
@Service
public class GameUserServiceImpl extends ServiceImpl<GameUserMapper, GameUser> implements GameUserService {

}
