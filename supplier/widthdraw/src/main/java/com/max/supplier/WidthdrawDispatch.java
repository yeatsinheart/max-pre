package com.max.supplier;

import com.max.supplier.service.WidthdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class WidthdrawDispatch {
    @Autowired
    private HashMap<String, WidthdrawService> gameServiceHashMap;

    public WidthdrawService getWidthdrawServiceBySupplierId(String key) {
        //todo 选择器key值选择到相应的服务
        return gameServiceHashMap.get(key);
    }
}
