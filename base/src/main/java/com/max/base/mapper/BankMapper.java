package com.max.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.max.base.entity.Bank;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支持的银行 Mapper 接口
 *
 * @author zane
 * @since 2019-08-29
 */
@Mapper
public interface BankMapper extends BaseMapper<Bank> {

}
