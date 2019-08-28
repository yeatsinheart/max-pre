package com.max.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.max.base.entity.Supplier;
import org.apache.ibatis.annotations.Mapper;

/**
 * 服务提供 Mapper 接口
 *
 * @author zane
 * @since 2019-08-28
 */
@Mapper
public interface SupplierMapper extends BaseMapper<Supplier> {

}
