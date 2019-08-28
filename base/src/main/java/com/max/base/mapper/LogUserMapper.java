package com.max.base.mapper;

import com.max.base.entity.LogUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * 用户操作记录 Mapper 接口
 * @author zane
 * @since 2019-08-28
 */
 @Mapper
public interface LogUserMapper extends BaseMapper<LogUser> {

}
