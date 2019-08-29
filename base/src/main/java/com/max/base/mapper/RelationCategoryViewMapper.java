package com.max.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.max.base.entity.RelationCategoryView;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类与视图关系 Mapper 接口
 *
 * @author zane
 * @since 2019-08-29
 */
@Mapper
public interface RelationCategoryViewMapper extends BaseMapper<RelationCategoryView> {

}
