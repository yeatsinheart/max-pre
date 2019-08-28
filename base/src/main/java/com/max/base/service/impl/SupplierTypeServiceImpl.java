package com.max.base.service.impl;

import com.max.base.entity.SupplierType;
import com.max.base.mapper.SupplierTypeMapper;
import com.max.base.service.SupplierTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import com.max.base.mapper.SupplierTypeMapper;

/**
 * 服务提供类型 服务实现类
 * @author zane
 * @since 2019-08-28
 */
@Slf4j
@Service
public class SupplierTypeServiceImpl extends ServiceImpl<SupplierTypeMapper, SupplierType> implements SupplierTypeService {

}