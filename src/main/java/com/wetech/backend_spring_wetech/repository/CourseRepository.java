package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.dto.CourseCategoryStatsDTO;
import com.wetech.backend_spring_wetech.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, CourseRepositoryCustom {
    public Course findFirstByCourseId(Long courseId);

    @Query("""
    SELECT new com.wetech.backend_spring_wetech.dto.CourseCategoryStatsDTO(
        c.typeCourse,
        COUNT(DISTINCT c.courseId),
        COUNT(DISTINCT t.userId),
        COALESCE(SUM(t.transferAmount), 0)
    )
    FROM Course c
    LEFT JOIN ListItem l ON c.courseId = l.idCourse
    LEFT JOIN Transaction t ON l.idTransaction = t.idTransaction and t.status = 'SUCCESS'
    GROUP BY c.typeCourse
    ORDER BY c.typeCourse
    """)
    List<CourseCategoryStatsDTO> getCategoryStats();

    @Query("SELECT COUNT(c) FROM Course c")
    Long getTotalCourses();

}

