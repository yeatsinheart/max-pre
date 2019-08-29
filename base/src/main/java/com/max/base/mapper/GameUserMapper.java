package com.max.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.max.base.entity.GameUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 游戏角色信息 Mapper 接口
 *
 * @author zane
 * @since 2019-08-29
 */
@Mapper
public interface GameUserMapper extends BaseMapper<GameUser> {

}
