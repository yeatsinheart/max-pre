package com.max.base.service.impl;

import com.max.base.entity.CategoryGame;
import com.max.base.mapper.CategoryGameMapper;
import com.max.base.service.CategoryGameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import com.max.base.mapper.CategoryGameMapper;

/**
 * 分类 服务实现类
 * @author zane
 * @since 2019-08-28
 */
@Slf4j
@Service
public class CategoryGameServiceImpl extends ServiceImpl<CategoryGameMapper, CategoryGame> implements CategoryGameService {

}
