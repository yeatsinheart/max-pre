package com.max.base.mapper;

import com.max.base.entity.Bank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * 支持的银行 Mapper 接口
 * @author zane
 * @since 2019-08-28
 */
 @Mapper
public interface BankMapper extends BaseMapper<Bank> {

}
