package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    public List<Video> findBySectionId(Long sectionId);
}

