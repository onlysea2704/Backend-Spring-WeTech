package com.wetech.backend_spring_wetech.dto;

import lombok.Data;

@Data
public class ConsultingRequest {
    private String name;
    private String email;
    private String phone;
    private String service;
}
