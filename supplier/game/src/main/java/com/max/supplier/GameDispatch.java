package com.max.supplier;

import com.max.supplier.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GameDispatch {
    @Autowired
    private Map<String, GameService> gameServiceHashMap;

    public GameService getGameServiceBySupplierId(String key) {
        //todo 选择器key值选择到相应的服务
        return gameServiceHashMap.get(key);
    }
}
