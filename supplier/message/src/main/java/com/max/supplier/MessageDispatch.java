package com.max.supplier;

import com.max.supplier.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MessageDispatch {
    @Autowired
    private HashMap<String, MessageService> messageServiceHashMap;

    public MessageService getMessageServiceBySupplierId(String key) {
        //todo 选择器key值选择到相应的服务
        return messageServiceHashMap.get(key);
    }
}
