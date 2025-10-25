package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.dto.*;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.repository.CourseRepository;
import com.wetech.backend_spring_wetech.repository.ListItemRepository;
import com.wetech.backend_spring_wetech.repository.TransactionRepository;
import com.wetech.backend_spring_wetech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ListCardsDTO getInfoCarDashboard() {

        // --- 1. Doanh thu tổng khóa học ---
        Double courseRe = transactionRepository.getRevenueByType("course");
        // --- 2. Doanh thu tổng thủ tục pháp lý ---
        Double procedureRe = transactionRepository.getRevenueByType("procedure");
        // --- 3. Tổng doanh thu ---
        Double totalRe = courseRe+procedureRe;
        // --- 4. Tổng số khóa học ---
        Double totalCourses = (double) courseRepository.count();

        // Build DTO
        CardStatsDTO courseRevenue = new CardStatsDTO("Doanh thu khóa học", courseRe, null);
        CardStatsDTO procedureRevenue = new CardStatsDTO("Doanh thu thủ tục pháp lý", procedureRe, null);
        CardStatsDTO totalRevenue = new CardStatsDTO("Tổng doanh thu", totalRe, null);
        CardStatsDTO totalCourseCard = new CardStatsDTO("Tổng số khóa học", totalCourses, null);

        return new ListCardsDTO(courseRevenue, procedureRevenue, totalRevenue, totalCourseCard);
    }

    public ListCardsDTO getDashboardData() {
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
        Double totalCourses = (double) courseRepository.count();

        // Build DTO
        CardStatsDTO courseRevenueCard = new CardStatsDTO("Doanh thu khóa học", courseNow, courseChange);
        CardStatsDTO procedureRevenueCard = new CardStatsDTO("Doanh thu thủ tục pháp lý", procedureNow, procedureChange);
        CardStatsDTO totalRevenueCard = new CardStatsDTO("Tổng doanh thu", totalNow, totalChange);
        CardStatsDTO totalCourseCard = new CardStatsDTO("Tổng số khóa học", totalCourses, null);

        return new ListCardsDTO(courseRevenueCard, procedureRevenueCard, totalRevenueCard, totalCourseCard);
    }

    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = new UserDto();
//            userDto.setUsername(user.getUsername());
            userDto.setUserId(user.getUserId());
            userDto.setEmail(user.getUsername());
            userDto.setSdt(user.getSdt());
            userDto.setRole(user.getRole());
            userDto.setFullname(user.getFullName());
            userDto.setCreated(user.getCreated());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    public List<CourseCategoryStatsDTO> getCourseCategoryStats() {
        return courseRepository.getCategoryStats();
    }

    public ListCardsDTO getCustomerStatsCards() {
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();
        int day  = now.getDayOfMonth();

        Long totalCustomers = userRepository.getTotalCustomers();
        Long newCustomers = userRepository.getNewCustomersThisMonth(month, year);
        Long purchasedCustomers = userRepository.getCustomersWithPurchase();
        Long totalCourses = userRepository.getNewCustomersToday(day, month, year);

        return new ListCardsDTO(
                new CardStatsDTO("Tổng số khách hàng", totalCustomers.doubleValue(), null),
                new CardStatsDTO("Khách đã mua hàng", purchasedCustomers.doubleValue(), null),
                new CardStatsDTO("Khách mới trong tháng", newCustomers.doubleValue(), null),
                new CardStatsDTO("Khách mới trong ngày", totalCourses.doubleValue(), null)
        );
    }

    public List<TransactionUserDTO> getAllTransactionsWithUserInfo() {
        return transactionRepository.getAllTransactionsWithUserInfo();
    }

    private double calcChangePercent(Double current, Double previous) {
        if (previous == null || previous == 0) return 1.0;
        return ((current - previous) / previous) * 100;
    }
}


