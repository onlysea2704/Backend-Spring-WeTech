package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.CourseCategoryStatsDTO;
import com.wetech.backend_spring_wetech.dto.ListCardsDTO;
import com.wetech.backend_spring_wetech.dto.TransactionUserDTO;
import com.wetech.backend_spring_wetech.dto.UserDto;
import com.wetech.backend_spring_wetech.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard/get-info-card")
    public ResponseEntity<ListCardsDTO> getInfoCarDashboard() {
        ListCardsDTO cards = dashboardService.getInfoCarDashboard();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/dashboard/stats-by-category")
    public ResponseEntity<List<CourseCategoryStatsDTO>> getStatsByCategory() {
        return ResponseEntity.ok(dashboardService.getCourseCategoryStats());
    }

    @GetMapping("/user/get-all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtoList = dashboardService.getAllUser();
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/monthly/get-info-card")
    public ResponseEntity<ListCardsDTO> getMonthlyInfoCard() {
        ListCardsDTO cards = dashboardService.getDashboardData();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/customer/get-info-card")
    public ListCardsDTO getCustomerStatsCards() {
        return dashboardService.getCustomerStatsCards();
    }

    @GetMapping("/transaction/get-data-table")
    public List<TransactionUserDTO> getAllTransactions() {
        return dashboardService.getAllTransactionsWithUserInfo();
    }
}
