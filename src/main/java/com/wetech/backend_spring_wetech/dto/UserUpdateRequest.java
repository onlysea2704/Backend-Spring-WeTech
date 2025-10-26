package com.wetech.backend_spring_wetech.dto;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String fullName;
    private String phone;
    private String email;
    private String linkImage;
}

