package com.max.supplier;

import com.max.supplier.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageDispatch {
    @Autowired
    private Map<String, MessageService> messageServiceHashMap;

    public MessageService getMessageServiceBySupplierId(String key) {
        //todo 选择器key值选择到相应的服务
        return messageServiceHashMap.get(key);
    }
}
