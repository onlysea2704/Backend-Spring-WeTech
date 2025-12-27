package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.entity.Cart;
import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.repository.CartRepository;
import com.wetech.backend_spring_wetech.repository.CourseRepository;
import com.wetech.backend_spring_wetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private UserService userService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/get-item")
    public List<Course> getItem() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);

        List<Cart> cartList = cartRepository.findByUserId(user.getUserId());
        List<Course> courseList = new ArrayList<>();
        for (Cart cart : cartList) {
            Course course = courseRepository.findFirstByCourseId(cart.getCourseId());
            courseList.add(course);
        }
        return courseList;
    }

    @GetMapping("/add")
    public Cart addCart(@RequestParam("courseId") Long courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);

        Cart cart = new Cart();
        cart.setUserId(user.getUserId());
        cart.setCourseId(courseId);
        return cartRepository.save(cart);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteCart(@RequestParam("courseId") Long courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);
        try {
            Cart cart = cartRepository.findFirstByUserIdAndCourseId(user.getUserId(), courseId);
            cartRepository.deleteById(cart.getCartId());
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
}
