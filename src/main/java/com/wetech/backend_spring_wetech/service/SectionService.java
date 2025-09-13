package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.entity.Section;
import com.wetech.backend_spring_wetech.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public List<Section> getSections(Long courseId) {
        List<Section> sections = new ArrayList<>();
        sections = sectionRepository.findByCourseId(courseId);
        return sections;
    }

    public Section create(Long courseId, Section section) {
        Section newSection = new Section();
        newSection.setCourseId(courseId);
        newSection.setName(section.getName());
        return sectionRepository.save(newSection);
    }

    public Section update(Section section){
        return sectionRepository.save(section);
    }

    public boolean delete(Section section){
        try {
            sectionRepository.delete(section);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
