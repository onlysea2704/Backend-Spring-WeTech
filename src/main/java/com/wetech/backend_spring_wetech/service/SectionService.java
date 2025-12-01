package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.entity.DocumentSection;
import com.wetech.backend_spring_wetech.entity.Section;
import com.wetech.backend_spring_wetech.entity.Video;
import com.wetech.backend_spring_wetech.repository.DocumentSectionRepository;
import com.wetech.backend_spring_wetech.repository.SectionRepository;
import com.wetech.backend_spring_wetech.repository.VideoRepository;
import com.wetech.backend_spring_wetech.utils.CloudinaryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private DocumentSectionRepository documentSectionRepository;
    @Autowired
    private CloudinaryUtils cloudinaryUtils;

    public List<Section> getSections(Long courseId) {
        List<Section> sections = new ArrayList<>();
        sections = sectionRepository.findByCourseId(courseId);
        return sections;
    }

    public Section create(Section section) {
        return sectionRepository.save(section);
    }

    public Section update(Section section){
        return sectionRepository.save(section);
    }

    public boolean delete(Long sectionId) {
        try {
            List<Video> videos = videoRepository.findBySectionId(sectionId);
            List<DocumentSection> documentSections = documentSectionRepository.findBySectionId(sectionId);
            for (Video video : videos) {
                cloudinaryUtils.deleteFromCloudinary(video.getLink());
            }
            for (DocumentSection documentSection : documentSections) {
                cloudinaryUtils.deleteFromCloudinary(documentSection.getLink());
            }
            sectionRepository.deleteById(sectionId);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
