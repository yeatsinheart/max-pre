package com.max.client.controller;

import com.max.transaction.dto.WalletMoneyChangeRequest;
import com.max.transaction.service.WalletTransactionalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class TestController {
    @Autowired
    private WalletTransactionalService walletTransactionalService;
    @RequestMapping("/test/add")
    public void test(WalletMoneyChangeRequest request){
       boolean test =  walletTransactionalService.addMoney(request);
       log.error(String.valueOf(test));
    }
    @RequestMapping("/test/sub")
    public void sub(WalletMoneyChangeRequest request){
        boolean test =  walletTransactionalService.minusMoney(request);
        log.error(String.valueOf(test));
    }
}
