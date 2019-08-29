package com.max.base.dto.response;

import com.max.base.entity.LogBank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户银行卡记录
 *
 * @author zane
 * @since 2019-08-29
 */
@Data

@ApiModel(value = "LogBankResponseDto响应对象", description = "用户银行卡记录")
public class LogBankResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer logBankId;

    @ApiModelProperty(value = "支持的银行ID")
    private Integer bankId;

    @ApiModelProperty(value = "使用人")
    private Integer userId;

    @ApiModelProperty(value = "持卡人姓名")
    private String bankUserName;

    @ApiModelProperty(value = "卡号")
    private String bankAccount;

    @ApiModelProperty(value = "类型：2充值成功 3绑定卡 1充值订单 ")
    private Integer bankType;

    public LogBank toLogBank() {
        LogBank logBank = new LogBank();
        logBank.setLogBankId(this.logBankId);
        logBank.setBankId(this.bankId);
        logBank.setUserId(this.userId);
        logBank.setBankUserName(this.bankUserName);
        logBank.setBankAccount(this.bankAccount);
        logBank.setBankType(this.bankType);
        return logBank;
    }
}
