package com.wetech.backend_spring_wetech.dto;

import lombok.Data;
import com.wetech.backend_spring_wetech.entity.Video;

import java.util.List;

@Data
public class SectionWithVideosDTO {
    private Long sectionId;
    private String name;
    private List<Video> videos;
    private Long courseId;
    // Constructor
    public SectionWithVideosDTO(Long sectionId, String name, List<Video> videos, Long courseId) {
        this.sectionId = sectionId;
        this.name = name;
        this.videos = videos;
        this.courseId = courseId;
    }
}
