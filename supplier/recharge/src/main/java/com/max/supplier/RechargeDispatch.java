package com.max.supplier;

import com.max.supplier.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RechargeDispatch {
    @Autowired
    private HashMap<String, RechargeService> rechargeServiceHashMap;

    public RechargeService getGameServiceBySupplierId(String key) {
        //todo 选择器key值选择到相应的服务
        return rechargeServiceHashMap.get(key);
    }
}
