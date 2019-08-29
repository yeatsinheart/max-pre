package com.max.transaction.service;

import com.max.base.entity.WalletOrder;
import com.max.transaction.dto.OrderCreateRequest;

//todo 钱包/订单事务处理工具
public interface OrderTransactionalService {

    /**
     * 创建钱包订单
     * 根据订单类型，确定是abs(Money) 还是-abs(Money)
     * 如果是扣钱操作，需要先扣走钱包资金
     */
    WalletOrder createOrder(OrderCreateRequest request);

    /**
     * 进入下一个流程
     * 如果next是成功,同步钱包操作,+增加钱,-直接成功
     * 如果next是撤销/失败操作，+完成，-钱包加金额
     */
    boolean nextProcess(WalletOrder order);

    /**
     * 失败 : 拒绝  撤销
     * 如果next是成功,同步钱包操作,+增加钱,-直接成功
     * 如果next是撤销/失败操作，+完成，-钱包加金额
     */
    boolean fail(WalletOrder order,String failMsg);

}
