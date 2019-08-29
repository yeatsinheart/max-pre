package com.max.base.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.max.base.entity.WalletOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 账户订单
 *
 * @author zane
 * @since 2019-08-29
 */
@Data
@ApiModel(value = "WalletOrderDto对象", description = "账户订单")
public class WalletOrderDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "订单类型 枚举WalletOrderTypeEnum")
    private Integer type;

    @ApiModelProperty(value = "支付方式 枚举PayWayEnum")
    private Integer payWay;

    @ApiModelProperty(value = "金额,元角分厘")
    private BigDecimal money;

    @ApiModelProperty(value = "订单号")
    private String series;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "付款人信息")
    private String payer;

    @ApiModelProperty(value = "收款人信息")
    private String receiver;

    @ApiModelProperty(value = "当前环节 OrderProcessEnum")
    private Integer process;

    @ApiModelProperty(value = "当前环节操作结果 OrderProcessResultEnum")
    private Integer processResult;

    @ApiModelProperty(value = "失败重试次数：流程切换以后就清零")
    private Integer tryNum;

    @ApiModelProperty(value = "失败原因")
    private String failMsg;

}
