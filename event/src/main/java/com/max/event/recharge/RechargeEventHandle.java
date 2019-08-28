package com.max.event.recharge;

import com.max.base.dto.WalletOrderDto;

public interface RechargeEventHandle {
    void handle(WalletOrderDto recharge);
}
