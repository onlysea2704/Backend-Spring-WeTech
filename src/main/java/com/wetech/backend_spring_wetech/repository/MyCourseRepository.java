package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.MyCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MyCourseRepository extends JpaRepository<MyCourse, Long> {
    MyCourse findFirstByCourseIdAndUserId(Long courseId, Long userId);

    @Query("SELECT C FROM Course C INNER JOIN MyCourse MC ON MC.courseId = C.courseId WHERE MC.userId = :userId")
    List<Course> findMyCourseByUserId(@Param("userId") Long userId);
}

