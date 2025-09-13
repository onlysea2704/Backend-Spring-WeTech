package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.CourseRequest;
import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.MyCourse;
import com.wetech.backend_spring_wetech.entity.Section;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.service.CourseService;
import com.wetech.backend_spring_wetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/course")
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
    public ResponseEntity<Object> checkHaveCourse(@RequestParam Long courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);
        boolean statusCheck = courseService.checkHaveCourse(courseId, user.getUserId());
        return ResponseEntity.ok().body(statusCheck);
    }

    @GetMapping("/find-my-course")
    public List<Course> findMyCourse() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);
        return courseService.findMyCourse(user.getUserId());
    }

    // API tạo khóa học
    @PostMapping(value = "/create-course", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Course> createCourse(
            @RequestPart(value = "course", required = false) Course courseRequest,
            @RequestPart(value = "image", required = false) MultipartFile image) throws Exception {
        Course newCourse = courseService.createCourse(courseRequest, image);
        return ResponseEntity.ok(newCourse);
    }

    @PostMapping(value = "/update-course", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Course> updateCourse(
            @RequestPart(value = "course", required = false) Course course,
            @RequestPart(value = "image", required = false) MultipartFile image) throws Exception {
        Course newCourse = courseService.updateCourse(course, image);
        return ResponseEntity.ok(newCourse);
    }

    @DeleteMapping("/delete-course")
    public ResponseEntity<Object> deleteCourse(@RequestParam Long courseId) {
        boolean status = courseService.deleteCourse(courseId);
        return ResponseEntity.ok(status);
    }

}
