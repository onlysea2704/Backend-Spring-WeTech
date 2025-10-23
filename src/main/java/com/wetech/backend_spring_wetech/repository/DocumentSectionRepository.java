package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.DocumentSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentSectionRepository extends JpaRepository<DocumentSection, Long> {
    public List<DocumentSection> findBySectionId(Long sectionId);
    public List<DocumentSection> findByDocumentId(Long documentId);
}
