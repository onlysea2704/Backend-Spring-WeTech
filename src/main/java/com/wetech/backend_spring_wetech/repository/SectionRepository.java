package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    public List<Section> findByCourseId(Long courseId);
}
