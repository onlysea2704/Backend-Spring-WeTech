package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.MyCourse;
import com.wetech.backend_spring_wetech.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

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
        return courseService.checkHaveCourse(courseId);
    }

//    @PostMapping("/create-my-course")
//    public MyCourse createMyCourse(@RequestParam Long courseId) {
//        return courseService.createMyCourse(courseId);
//    }

//    @PostMapping("create")
//    public Course create(@RequestBody Course course) {
//
//    }
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
