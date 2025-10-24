package com.wetech.backend_spring_wetech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListCardsDTO {
    private CardStatsDTO card1;
    private CardStatsDTO card2;
    private CardStatsDTO card3;
    private CardStatsDTO card4; // Tổng số khóa học hiện có
}