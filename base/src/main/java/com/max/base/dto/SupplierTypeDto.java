package com.max.base.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.max.base.entity.SupplierType;
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
@ApiModel(value = "SupplierTypeDto对象", description = "服务提供类型")
public class SupplierTypeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务提供类型ID")
    @TableId(value = "supplier_type_id", type = IdType.AUTO)
    private Integer supplierTypeId;

    @ApiModelProperty(value = "服务类型")
    private String supplierType;

    public SupplierType toSupplierType() {
        SupplierType supplierType = new SupplierType();
        supplierType.setSupplierTypeId(this.supplierTypeId);
        supplierType.setSupplierType(this.supplierType);
        return supplierType;
    }

}
