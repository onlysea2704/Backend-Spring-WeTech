package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.entity.Section;
import com.wetech.backend_spring_wetech.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping("/get-section-by-course-id")
    public ResponseEntity<Object> getSectionByCourseId(@RequestParam Long courseId) {
        List<Section> listSections = sectionService.getSections(courseId);
        return ResponseEntity.ok(listSections);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createSection(@RequestBody Section section) {
        Section newSection = sectionService.create(section);
        return ResponseEntity.ok(newSection);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateSection(@RequestBody Section section) {
        Section updatedSection = sectionService.update(section);
        return ResponseEntity.ok(updatedSection);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> deleteSection(@RequestParam Long sectionId) {
        boolean statusDeleted = sectionService.delete(sectionId);
        return ResponseEntity.ok(statusDeleted);
    }
}
