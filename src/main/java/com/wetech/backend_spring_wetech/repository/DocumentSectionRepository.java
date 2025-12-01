package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.DocumentSection;
import com.wetech.backend_spring_wetech.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentSectionRepository extends JpaRepository<DocumentSection, Long> {
    public List<DocumentSection> findBySectionId(Long sectionId);
    public DocumentSection findFirstByDocumentId(Long documentId);

    @Query("select d from DocumentSection d inner join Section s on s.sectionId = d.sectionId where s.courseId = :courseId")
    public List<DocumentSection> findByCourseId(@Param("courseId") Long courseId);
}
