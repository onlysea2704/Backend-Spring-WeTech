package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.FormSubmissionRequestDTO;
import com.wetech.backend_spring_wetech.service.FormSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/form-submission")
@RequiredArgsConstructor
public class FormSubmissionController {
    private final FormSubmissionService formSubmissionService;

    @GetMapping("/get/data-json")
    public ResponseEntity<Map<String, Object>> getDataJson(@RequestParam("formId") Long formId) {
        return ResponseEntity.ok(formSubmissionService.get(formId));
    }

    @GetMapping("/get/pdf-file-url")
    public ResponseEntity<Map<String, String>> getPdfFileUrl(@RequestParam("code") String code) {
        return ResponseEntity.ok(formSubmissionService.getPdfFileUrlByCode(code));
    }

    @GetMapping("/get/all-pdf-file-urls")
    public ResponseEntity<List<Map<String, String>>> getAllPdfFileUrls(@RequestParam("procedureId") Long procedureId) {
        return ResponseEntity.ok(formSubmissionService.getAllPdfFileUrlsByProcedure(procedureId));
    }

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody FormSubmissionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(formSubmissionService.create(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<Long> update(@RequestBody FormSubmissionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(formSubmissionService.update(dto));
    }

    @PostMapping("/confirm")
    public ResponseEntity<Boolean> confirmFormInfo(
            @RequestParam("formId") Long formId,
            @RequestPart("pdfFile") MultipartFile pdfFile
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(formSubmissionService.confirmFormInfo(formId, pdfFile));
    }
}
