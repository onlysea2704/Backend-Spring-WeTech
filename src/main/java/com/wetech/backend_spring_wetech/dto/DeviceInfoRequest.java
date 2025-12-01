package com.wetech.backend_spring_wetech.dto;

import lombok.Data;

@Data
public class DeviceInfoRequest {
    private String userAgent;
    private String screen;
    private Integer cpuCores;
    private String ram;
    private String gpu;
    private String platform;
}
