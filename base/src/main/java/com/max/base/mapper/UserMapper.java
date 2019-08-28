package com.max.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.max.base.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表 Mapper 接口
 *
 * @author zane
 * @since 2019-08-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
