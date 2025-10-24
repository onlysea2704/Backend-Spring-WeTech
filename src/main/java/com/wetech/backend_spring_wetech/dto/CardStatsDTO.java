package com.wetech.backend_spring_wetech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardStatsDTO {
    private String title;       // Tên thẻ: "Doanh thu khóa học", "Thủ tục pháp lý", "Tổng doanh thu"
    private Double value;       // Giá trị doanh thu
    private Double changePercent; // % tăng giảm so với tháng trước
}

