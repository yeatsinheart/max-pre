package com.max.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 服务提供
 *
 * @author zane
 * @since 2019-08-29
 */
@Data
@ApiModel(value = "Supplier对象", description = "服务提供")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务提供ID")
    private Integer supplierId;

    @ApiModelProperty(value = "服务提供类型ID")
    private Integer supplierTypeId;

    @ApiModelProperty(value = "服务提供名字")
    private String supplierName;

    @ApiModelProperty(value = "私钥")
    private String privateKey;

    @ApiModelProperty(value = "服务提供公钥")
    private String supplierPublicKey;

    @ApiModelProperty(value = "服务提供的商户号")
    private String merchantNo;

    @ApiModelProperty(value = "服务提供网关地址")
    private String host;

    @ApiModelProperty(value = "版本")
    private String version;


}
