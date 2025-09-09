package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.MyCourse;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.service.CourseService;
import com.wetech.backend_spring_wetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    public List<Course> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/get-top")
    public List<Course> getTop() {
        return courseService.getTop();
    }

    @GetMapping("/find-by-type")
    public List<Course> findByType(@RequestBody List<String> types) {
        return courseService.findByType(types);
    }

    @GetMapping("/find-by-course-id")
    public Course findByCourseId(@RequestParam Long courseId) {
        return courseService.findByCourseId(courseId);
    }

    @GetMapping("/check-have-course")
    public boolean checkHaveCourse(@RequestParam Long courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);
        return courseService.checkHaveCourse(courseId, user.getUserId());
    }

    @GetMapping("/find-my-course")
    public List<Course> findMyCourse() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);
        return courseService.findMyCourse(user.getUserId());
    }
//    @PostMapping("/create-my-course")
//    public MyCourse createMyCourse(@RequestParam Long courseId) {
//        return courseService.createMyCourse(courseId);
//    }

    // API tạo khóa học
    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(
//            @RequestParam String title,
//            @RequestParam String description,
            @RequestParam("image") MultipartFile image) throws Exception {
        Course course = courseService.createCourse(image);
        return ResponseEntity.ok(course);
    }
//
//    @PostMapping("update")
//    public Course update(@RequestBody Course course) {
//
//    }
//
//    @PostMapping("delete")
//    public Course delete(@RequestBody Course course) {
//
//    }
}
