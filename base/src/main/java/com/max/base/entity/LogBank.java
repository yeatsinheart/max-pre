package com.max.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 用户银行卡记录
 *
 * @author zane
 * @since 2019-08-28
 */
@Data
@ApiModel(value = "LogBank对象", description = "用户银行卡记录")
public class LogBank implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "log_bank_id", type = IdType.AUTO)
    private Integer logBankId;

    @ApiModelProperty(value = "支持的银行ID")
    private String bankId;

    @ApiModelProperty(value = "使用人")
    private Integer userId;

    @ApiModelProperty(value = "持卡人姓名")
    private String bankUserName;

    @ApiModelProperty(value = "卡号")
    private String bankAccount;

    @ApiModelProperty(value = "类型：2充值成功 3绑定卡 1充值订单 ")
    private Integer bankType;


}
