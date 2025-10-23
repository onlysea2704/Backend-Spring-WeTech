package com.wetech.backend_spring_wetech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCategoryStatsDTO {
    private String categoryName;
    private Long courseCount;
    private Long buyerCount;
    private Double revenue;
}
