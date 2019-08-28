package com.max.base.mapper;

import com.max.base.entity.RelationCategoryView;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * 分类与视图关系 Mapper 接口
 * @author zane
 * @since 2019-08-28
 */
 @Mapper
public interface RelationCategoryViewMapper extends BaseMapper<RelationCategoryView> {

}
