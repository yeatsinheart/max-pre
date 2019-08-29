package com.max.base.dto.response;

import com.max.base.entity.Supplier;
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

@ApiModel(value = "SupplierResponseDto响应对象", description = "服务提供")
public class SupplierResponseDto implements Serializable {

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

    public Supplier toSupplier() {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(this.supplierId);
        supplier.setSupplierTypeId(this.supplierTypeId);
        supplier.setSupplierName(this.supplierName);
        supplier.setPrivateKey(this.privateKey);
        supplier.setSupplierPublicKey(this.supplierPublicKey);
        supplier.setMerchantNo(this.merchantNo);
        supplier.setHost(this.host);
        supplier.setVersion(this.version);
        return supplier;
    }
}
