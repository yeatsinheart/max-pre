package com.max.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.max.base.entity.WalletOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账户订单 Mapper 接口
 *
 * @author zane
 * @since 2019-08-29
 */
@Mapper
public interface WalletOrderMapper extends BaseMapper<WalletOrder> {

}
