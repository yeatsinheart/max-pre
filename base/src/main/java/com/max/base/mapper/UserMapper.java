package com.max.base.mapper;

import com.max.base.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * 用户表 Mapper 接口
 * @author zane
 * @since 2019-08-28
 */
 @Mapper
public interface UserMapper extends BaseMapper<User> {

}
