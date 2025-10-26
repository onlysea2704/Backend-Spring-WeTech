package com.wetech.backend_spring_wetech.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private Long userId;
//    private String username;
    private String fullname;
    private String sdt;
    private String email;
    private String role;
    private String linkImage;
    private Date created;
}
