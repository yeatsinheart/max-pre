package com.max.client.dto;

import com.max.client.utils.IpUtil;
import lombok.Data;

import java.io.Serializable;

@Data
public class SigninUserRequest implements Serializable {
    private String name;
    private String passwd;

    private String ip = IpUtil.getIpAddr();

}
