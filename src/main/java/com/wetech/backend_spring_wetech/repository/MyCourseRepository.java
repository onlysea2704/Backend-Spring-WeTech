package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.MyCourse;
import com.wetech.backend_spring_wetech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCourseRepository extends JpaRepository<MyCourse, Long> {
    int findFirstByCourseAndUser(Course course, User user);

    int findFirstByMyCourseId(Long myCourseId);

    int findFirstByMyCourseIdAndUserId(Long myCourseId, Long userId);
}

