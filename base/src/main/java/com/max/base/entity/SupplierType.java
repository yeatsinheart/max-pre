package com.max.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 服务提供类型
 *
 * @author zane
 * @since 2019-08-29
 */
@Data
@ApiModel(value = "SupplierType对象", description = "服务提供类型")
public class SupplierType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务提供类型ID")
    @TableId(value = "supplier_type_id", type = IdType.AUTO)
    private Integer supplierTypeId;

    @ApiModelProperty(value = "服务类型")
    private String supplierType;


}
