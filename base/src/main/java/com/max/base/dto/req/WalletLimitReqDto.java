package com.max.base.dto.req;

import com.max.base.entity.WalletLimit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统级别提现设置
 *
 * @author zane
 * @since 2019-08-29
 */
@Data
@ApiModel(value = "WalletLimitReqDto请求对象", description = "系统级别提现设置")
public class WalletLimitReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer walletLimitidId;

    @ApiModelProperty(value = "限制方式")
    private String walletLimitWay;

    @ApiModelProperty(value = "限制值")
    private String walletLimitValue;

    public WalletLimit toWalletLimit() {
        WalletLimit walletLimit = new WalletLimit();
        walletLimit.setWalletLimitidId(this.walletLimitidId);
        walletLimit.setWalletLimitWay(this.walletLimitWay);
        walletLimit.setWalletLimitValue(this.walletLimitValue);
        return walletLimit;
    }
}
