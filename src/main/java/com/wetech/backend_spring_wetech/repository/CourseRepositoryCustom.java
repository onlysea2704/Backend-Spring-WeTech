package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.Course;

import java.util.List;

public interface CourseRepositoryCustom {

    public List<Course> getTop();
//    public Course checkHaveCourse(Long id, Long courseId);
    public List<Course> findByType(List<String> type);
}
