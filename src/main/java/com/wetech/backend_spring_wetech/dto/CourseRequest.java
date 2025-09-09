package com.wetech.backend_spring_wetech.dto;

import lombok.Data;

@Data
public class CourseRequest {
    private String title;
    private String description;
    private String author;
    private Double realPrice;
    private Double salePrice;
    private String typeCourse;
    private String intro1;
    private String intro2;
    private String createdAt; // String để client gửi, Service sẽ convert sang LocalDateTime
}

