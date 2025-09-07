package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.MyCourse;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.repository.CourseRepository;
import com.wetech.backend_spring_wetech.repository.MyCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MyCourseRepository myCourseRepository;

    @Autowired
    private UserService userService;

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public List<Course> getTop() {
        return courseRepository.getTop();
    }

    public List<Course> findByType(List<String> types) {
        return courseRepository.findByType(types);
    }

    public Course findByCourseId(Long courseId) {
        return courseRepository.findFirstByCourseId(courseId);
    }

    public Boolean checkHaveCourse(Long courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);

        MyCourse exist = myCourseRepository.findFirstByCourseIdAndUserId(courseId,  user.getUserId());
        return exist != null? true : false;
    }

//    public MyCourse createMyCourse(Long courseId) {
//
//    }

//    public Course createCourse(Course course) {
//        return courseRepository.save(course);
//    }
//
//    public Course updateCourse(Course course) {
//        return courseRepository.save(course);
//    }
//
//    public void deleteCourse(Long courseId) {
//        courseRepository.deleteById(courseId);
//    }

}
