package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.entity.DocumentSection;
import com.wetech.backend_spring_wetech.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/create")
    public ResponseEntity<Object> createDocument(
            @RequestPart(value = "sectionId", required = true) Long sectionId,
            @RequestPart(value = "document", required = true) MultipartFile document
    ) throws IOException {
        DocumentSection newDocument = documentService.createDocument(sectionId, document);
        return new ResponseEntity<>(newDocument, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> deleteDocument(@RequestParam Long documentId){
        boolean statusDelete = documentService.deleteDocument(documentId);
        return new ResponseEntity<>(statusDelete, HttpStatus.OK);
    }
}
