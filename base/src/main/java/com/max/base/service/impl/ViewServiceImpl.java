package com.max.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.base.entity.View;
import com.max.base.mapper.ViewMapper;
import com.max.base.service.ViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 界面元素视图 服务实现类
 *
 * @author zane
 * @since 2019-08-29
 */
@Slf4j
@Service
public class ViewServiceImpl extends ServiceImpl<ViewMapper, View> implements ViewService {

}
