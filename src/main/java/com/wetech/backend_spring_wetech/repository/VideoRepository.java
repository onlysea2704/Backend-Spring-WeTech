package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    public List<Video> findBySectionId(Long sectionId);

    @Query("select v from Video v inner join Section s on s.sectionId = v.sectionId where s.courseId = :courseId")
    public List<Video> findByCourseId(@Param("courseId") Long courseId);
}

