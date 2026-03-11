package com.wetech.backend_spring_wetech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCardDto {
    private String fullName;
    @NotBlank
    @Size(max = 12)
    private String cccd;

    private String gender;

    private String dob;

    private String nationality;

    private String ethnicity;

    private String permanentStreet;
    private String permanentWard;
    private String permanentProvince;

    private String currentStreet;
    private String currentWard;
    private String currentProvince;
}
