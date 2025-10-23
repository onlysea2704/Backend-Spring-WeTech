package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.SectionWithDocumentDTO;
import com.wetech.backend_spring_wetech.dto.SectionWithVideosDTO;
import com.wetech.backend_spring_wetech.entity.DocumentSection;
import com.wetech.backend_spring_wetech.entity.Video;
import com.wetech.backend_spring_wetech.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/get-by-section-id")
    public ResponseEntity<Object> getBySectionId(@RequestParam("sectionId") Long sectionId){
        List<DocumentSection> listDocument = documentService.getDocumentBySectionId(sectionId);
        return new ResponseEntity<>(listDocument, HttpStatus.OK);
    }

    @GetMapping("/find-by-courseId")
    public ResponseEntity<Object> getVideoByCourseId(@RequestParam("courseId") long courseId) {
        List<SectionWithDocumentDTO> videos = documentService.findByCourseId(courseId);
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/create")
    public ResponseEntity<Object> createVideo(
            @RequestParam Long sectionId
    ){
        DocumentSection documentSection = documentService.create(sectionId);
        return ResponseEntity.ok(documentSection);
    }

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> update(
            @RequestPart(value = "documentInfo", required = true) DocumentSection documentInfo,
            @RequestPart(value = "document", required = false) MultipartFile document
    ) throws IOException {
        DocumentSection newDocument = documentService.update(documentInfo, document);
        return ResponseEntity.ok(newDocument);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> deleteDocument(@RequestParam Long documentId){
        boolean statusDelete = documentService.deleteDocument(documentId);
        return new ResponseEntity<>(statusDelete, HttpStatus.OK);
    }
}
