package com.max.base.service.impl;

import com.max.base.entity.Supplier;
import com.max.base.mapper.SupplierMapper;
import com.max.base.service.SupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import com.max.base.mapper.SupplierMapper;

/**
 * 服务提供 服务实现类
 * @author zane
 * @since 2019-08-28
 */
@Slf4j
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {

}
