package com.wetech.backend_spring_wetech.dto;

import com.wetech.backend_spring_wetech.entity.DocumentSection;
import com.wetech.backend_spring_wetech.entity.Video;
import lombok.Data;

import java.util.List;

@Data
public class SectionWithDocumentDTO {
    private Long sectionId;
    private String name;
    private List<DocumentSection> documentSections;
    private Long courseId;
    // Constructor
    public SectionWithDocumentDTO(Long sectionId, String name, List<DocumentSection> documentSections, Long courseId) {
        this.sectionId = sectionId;
        this.name = name;
        this.documentSections = documentSections;
        this.courseId = courseId;
    }
}
