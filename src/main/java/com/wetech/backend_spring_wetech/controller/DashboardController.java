package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.DashboardDTO;
import com.wetech.backend_spring_wetech.dto.UserDto;
import com.wetech.backend_spring_wetech.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/get-info-card")
    public ResponseEntity<DashboardDTO> getDashboard() {
        DashboardDTO cards = dashboardService.getDashboardData();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtoList = dashboardService.getAllUser();
        return ResponseEntity.ok(userDtoList);
    }
}
