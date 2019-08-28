package com.max.base.dto.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.max.base.entity.WalletOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 订单
 *
 * @author zane
 * @since 2019-08-28
 */
@Data

@ApiModel(value = "WalletOrderResponseDto响应对象", description = "订单")
public class WalletOrderResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "操作：1充值 2提现 3转入游戏 4转出游戏 5活动入款")
    private Integer walletOrderType;

    @ApiModelProperty(value = "金额,元角分厘")
    private String walletOrderMoney;

    @ApiModelProperty(value = "订单号")
    private String walletOrderSeries;

    @ApiModelProperty(value = "订单状态 0 处理中 1已结束")
    private Integer walletOrderStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除 1 已删除")
    private Integer deleted;

    @ApiModelProperty(value = "银行信息")
    private Integer logBankId;

    public WalletOrder toWalletOrder() {
        WalletOrder walletOrder = new WalletOrder();
        walletOrder.setOrderId(this.orderId);
        walletOrder.setUserId(this.userId);
        walletOrder.setWalletOrderType(this.walletOrderType);
        walletOrder.setWalletOrderMoney(this.walletOrderMoney);
        walletOrder.setWalletOrderSeries(this.walletOrderSeries);
        walletOrder.setWalletOrderStatus(this.walletOrderStatus);
        walletOrder.setCreateTime(this.createTime);
        walletOrder.setUpdateTime(this.updateTime);
        walletOrder.setDeleted(this.deleted);
        walletOrder.setLogBankId(this.logBankId);
        return walletOrder;
    }
}
