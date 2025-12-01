package com.wetech.backend_spring_wetech.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private DeviceInfoRequest deviceInfoRequest;
}
