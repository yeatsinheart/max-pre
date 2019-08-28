package com.max.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.max.base.entity.WalletLimit;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统级别提现设置 Mapper 接口
 *
 * @author zane
 * @since 2019-08-28
 */
@Mapper
public interface WalletLimitMapper extends BaseMapper<WalletLimit> {

}
