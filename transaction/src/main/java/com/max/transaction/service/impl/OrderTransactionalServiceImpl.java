package com.max.transaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.max.base.entity.WalletOrder;
import com.max.base.service.WalletOrderService;
import com.max.core.constant.WalletOrderProcessEnum;
import com.max.core.constant.WalletOrderTypeEnum;
import com.max.core.exception.ServiceException;
import com.max.core.utils.MoneyUtil;
import com.max.transaction.dto.OrderCreateRequest;
import com.max.transaction.dto.WalletMoneyChangeRequest;
import com.max.transaction.service.OrderTransactionalService;
import com.max.transaction.service.WalletTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
/**
 *
 * 订单流程一定要控制好
 * //todo 订单流程异常状态要分析好
 * */
@Service
public class OrderTransactionalServiceImpl implements OrderTransactionalService {
    @Autowired
    private WalletOrderService walletOrderService;
    @Autowired
    private WalletTransactionalService walletTransactionalService;

    /**
     * 创建钱包订单
     * 根据订单类型，确定是abs(Money) 还是-abs(Money)
     * 如果是扣钱操作，需要先扣走钱包资金
     *
     * @param request
     */
    @Transactional
    @Override
    public WalletOrder createOrder(OrderCreateRequest request) {
        WalletOrder order = new WalletOrder();
        order.setUserId(request.getUserId());
        order.setMoney(MoneyUtil.toStringMoney(request.getMoney()));
        order.setProcessResult(WalletOrderProcessEnum.OrderProcessResultEnum.WAITING.getCode());
        order.setType(request.getType());
        order.setPayWay(request.getPayWay());
        order.setPayer(request.getPayer());
        order.setReceiver(request.getReceiver());
        order.setProcess(WalletOrderProcessEnum.getProcessOfStart(request.getType()).getCode());
        order.setTryNum(0);
        order.setFailMsg(null);
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        order.setSeries(UUID.randomUUID().toString().replace("-", ""));

        WalletMoneyChangeRequest changeRequest = new WalletMoneyChangeRequest();
        changeRequest.setUserId(request.getUserId());
        changeRequest.setMoney(request.getMoney().abs());
        //扣钱操作 账户钱足够
        if (WalletOrderTypeEnum.subtractMoney2Wallet(request.getType())
                && walletTransactionalService.findMoney(changeRequest).compareTo(request.getMoney()) != -1) {
            //先扣钱 再创建订单
            boolean success = walletTransactionalService.minusMoney(changeRequest);
            if (!success) {
                throw new ServiceException("钱包扣钱失败");
            } else {
                boolean orderIsCreated = walletOrderService.save(order);
                if (!orderIsCreated) {
                    throw new ServiceException("订单创建失败");

                }
                return order;
            }
        }
        throw new ServiceException("账户余额不足");
    }

    /**
     * 进入下一个流程
     * 如果next是成功,同步钱包操作,+增加钱,-直接成功
     * 如果next是撤销/失败操作，+完成，-钱包加金额
     *
     * @param order
     */
    @Override
    public boolean nextProcess(WalletOrder order) {
        order.setProcess(WalletOrderProcessEnum.nextProcess(order.getProcess(), order.getProcessResult()).getCode());
        order.setProcessResult(WalletOrderProcessEnum.OrderProcessResultEnum.WAITING.getCode());
        order.setTryNum(0);
        order.setFailMsg(null);
        order.setUpdateTime(LocalDateTime.now());
        UpdateWrapper<WalletOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_id", order.getOrderId());
        boolean success = walletOrderService.update(order, updateWrapper);
        return success;
    }

    /**
     * 失败 : 拒绝  撤销
     * 如果next是成功,同步钱包操作,+增加钱,-直接成功
     * 如果next是撤销/失败操作，+完成，-钱包加金额
     *
     * @param order
     */
    @Override
    public boolean fail(WalletOrder order, String failMsg) {
        order.setProcess(WalletOrderProcessEnum.OrderProcessEnum.FAIL.getCode());
        order.setProcessResult(WalletOrderProcessEnum.OrderProcessResultEnum.WAITING.getCode());
        order.setTryNum(0);
        order.setFailMsg(failMsg);
        order.setUpdateTime(LocalDateTime.now());
        UpdateWrapper<WalletOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_id", order.getOrderId());
        boolean success = walletOrderService.update(order, updateWrapper);
        WalletMoneyChangeRequest changeRequest = new WalletMoneyChangeRequest();
        changeRequest.setUserId(order.getUserId());
        changeRequest.setMoney(MoneyUtil.toBigDecimalMoney(order.getMoney()).abs());
        //扣钱单子
        if (success && WalletOrderTypeEnum.subtractMoney2Wallet(order.getType())) {
            //先扣钱 再创建订单
            boolean recoverySuccess = walletTransactionalService.addMoney(changeRequest);
            if (!recoverySuccess) {
                throw new ServiceException("钱包恢复失败");
            }
        }
        return success;
    }

}
