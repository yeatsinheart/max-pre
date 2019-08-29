package com.max.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 钱包变动记录
 *
 * @author zane
 * @since 2019-08-29
 */
@Data
@ApiModel(value = "LogWalletLimit对象", description = "钱包变动记录")
public class LogWalletLimit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "限制记录ID")
    @TableId(value = "log_wallet_limit_id", type = IdType.AUTO)
    private Integer logWalletLimitId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "限制内容ID")
    private Integer walletLimitId;

    @ApiModelProperty(value = "变动值，区分正负，元角分厘")
    private String changedValue;


}
