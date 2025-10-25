package com.wetech.backend_spring_wetech.dto;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String fullname;
    private String phone;
    private String email;
}

