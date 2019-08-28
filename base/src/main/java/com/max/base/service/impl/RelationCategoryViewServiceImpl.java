package com.max.base.service.impl;

import com.max.base.entity.RelationCategoryView;
import com.max.base.mapper.RelationCategoryViewMapper;
import com.max.base.service.RelationCategoryViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import com.max.base.mapper.RelationCategoryViewMapper;

/**
 * 分类与视图关系 服务实现类
 * @author zane
 * @since 2019-08-28
 */
@Slf4j
@Service
public class RelationCategoryViewServiceImpl extends ServiceImpl<RelationCategoryViewMapper, RelationCategoryView> implements RelationCategoryViewService {

}
