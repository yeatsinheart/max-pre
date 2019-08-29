package com.max.supplier;

import com.max.supplier.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsDispatch {
    @Autowired
    private Map<String, SmsService> smsServiceHashMap;

    public SmsService getGameServiceBySupplierId(String key) {
        //todo 选择器key值选择到相应的服务
        return smsServiceHashMap.get(key);
    }
}
