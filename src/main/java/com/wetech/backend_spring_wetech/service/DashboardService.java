package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.dto.DashboardDTO;
import com.wetech.backend_spring_wetech.dto.RevenueCardDTO;
import com.wetech.backend_spring_wetech.dto.UserDto;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.repository.CourseRepository;
import com.wetech.backend_spring_wetech.repository.ListItemRepository;
import com.wetech.backend_spring_wetech.repository.TransactionRepository;
import com.wetech.backend_spring_wetech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final TransactionRepository transactionRepository;
    private final ListItemRepository listItemRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    public DashboardDTO getDashboardData() {
        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();

        int prevMonth = (currentMonth == 1) ? 12 : currentMonth - 1;
        int prevYear = (currentMonth == 1) ? currentYear - 1 : currentYear;

        // --- 1. Doanh thu khóa học ---
        Double courseNow = transactionRepository.getRevenueByTypeAndMonth("course", currentMonth, currentYear);
        Double coursePrev = transactionRepository.getRevenueByTypeAndMonth("course", prevMonth, prevYear);
        double courseChange = calcChangePercent(courseNow, coursePrev);

        // --- 2. Doanh thu thủ tục pháp lý ---
        Double procedureNow = transactionRepository.getRevenueByTypeAndMonth("procedure", currentMonth, currentYear);
        Double procedurePrev = transactionRepository.getRevenueByTypeAndMonth("procedure", prevMonth, prevYear);
        double procedureChange = calcChangePercent(procedureNow, procedurePrev);

        // --- 3. Tổng doanh thu ---
        Double totalNow = transactionRepository.getTotalRevenueByMonth(currentMonth, currentYear);
        Double totalPrev = transactionRepository.getTotalRevenueByMonth(prevMonth, prevYear);
        double totalChange = calcChangePercent(totalNow, totalPrev);

        // --- 4. Tổng số khóa học ---
        Long totalCourses = courseRepository.count();

        // Build DTO
        RevenueCardDTO courseRevenue = new RevenueCardDTO("Doanh thu khóa học", courseNow, courseChange);
        RevenueCardDTO procedureRevenue = new RevenueCardDTO("Doanh thu thủ tục pháp lý", procedureNow, procedureChange);
        RevenueCardDTO totalRevenue = new RevenueCardDTO("Tổng doanh thu", totalNow, totalChange);

        return new DashboardDTO(courseRevenue, procedureRevenue, totalRevenue, totalCourses);
    }

    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = new UserDto();
//            userDto.setUsername(user.getUsername());
            userDto.setUserId(user.getUserId());
            userDto.setEmail(user.getEmail());
            userDto.setSdt(user.getSdt());
            userDto.setRole(user.getRole());
            userDto.setFullname(user.getFullname());
            userDto.setCreated(user.getCreated());
            userDtos.add(userDto);
        }
        return userDtos;
    }


    private double calcChangePercent(Double current, Double previous) {
        if (previous == null || previous == 0) return 1.0;
        return ((current - previous) / previous) * 100;
    }
}


