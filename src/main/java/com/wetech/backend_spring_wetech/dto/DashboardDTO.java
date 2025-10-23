package com.wetech.backend_spring_wetech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
    private RevenueCardDTO courseRevenue;
    private RevenueCardDTO procedureRevenue;
    private RevenueCardDTO totalRevenue;
    private Long totalCourses; // Tổng số khóa học hiện có
}

