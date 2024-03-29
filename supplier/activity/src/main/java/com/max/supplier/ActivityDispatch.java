package com.max.supplier;

import com.max.supplier.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ActivityDispatch {
    @Autowired
    private Map<String, ActivityService> activityServiceHashMap;

    public ActivityService getActivityServiceBySupplierId(String key) {
        //todo 选择器key值选择到相应的服务
        return activityServiceHashMap.get(key);
    }
}
