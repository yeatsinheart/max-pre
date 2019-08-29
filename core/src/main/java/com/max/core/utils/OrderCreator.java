package com.max.core.utils;

import com.max.base.dto.UserDto;
import com.max.base.entity.WalletOrder;
import com.max.core.constant.PayWayEnum;
import com.max.core.constant.WalletOrderProcessEnum;
import com.max.core.constant.WalletOrderTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderCreator {
    public static WalletOrder create(UserDto user, BigDecimal money , PayWayEnum payWayEnum,WalletOrderTypeEnum orderTypeEnum){
        WalletOrder order = new WalletOrder();
        order.setUserId(user.getId());
        order.setType(orderTypeEnum.getCode());
        //支付方式
        order.setPayWay(payWayEnum.getCode());

        order.setMoney(MoneyUtil.toStringMoney(money));
        order.setSeries(UUID.randomUUID().toString().replace("-", ""));
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        order.setPayer("付款人");
        order.setReceiver("收款人");
        order.setProcess(WalletOrderProcessEnum.OrderProcessEnum.OPERATION.getCode());
        order.setProcessResult(WalletOrderProcessEnum.OrderProcessResultEnum.WAITING.getCode());
        order.setTryNum(0);
        return order;
    }
    public static WalletOrder delete(){
        WalletOrder order = new WalletOrder();
        order.setProcess(WalletOrderProcessEnum.OrderProcessEnum.FAIL.getCode());
        order.setFailMsg("用户撤销");
        return order;
    }
}
