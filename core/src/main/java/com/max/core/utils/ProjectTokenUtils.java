package com.max.core.utils;

import java.util.UUID;

/**
 * Created by ken on 2018/3/16.
 */
public class ProjectTokenUtils {

    public static String genToken() {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        return token;
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }
}
