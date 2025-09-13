package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.entity.Section;
import com.wetech.backend_spring_wetech.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

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
            sectionRepository.deleteById(sectionId);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
