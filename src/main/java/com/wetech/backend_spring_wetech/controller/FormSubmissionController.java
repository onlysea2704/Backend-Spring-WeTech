package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.FormSubmissionRequestDTO;
import com.wetech.backend_spring_wetech.service.FormSubmissionService;
import com.wetech.backend_spring_wetech.service.ProcedureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/form-submission")
@RequiredArgsConstructor
@Slf4j
public class FormSubmissionController {
    private final FormSubmissionService formSubmissionService;
    private final ProcedureService procedureService;
    

    @GetMapping("/get/data-json")
    public ResponseEntity<Map<String, Object>> getDataJson(@RequestParam("formId") Long formId) {
        log.info("Getting form submission data for formId: {}", formId);
        try {
            Map<String, Object> data = formSubmissionService.get(formId);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            log.error("Error getting form data", e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/get/pdf-file-url")
    public ResponseEntity<Map<String, String>> getPdfFileUrl(@RequestParam("code") String code) {
        log.info("Getting PDF file URL for code: {}", code);
        try {
            Map<String, String> data = formSubmissionService.getPdfFileUrlByCode(code);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            log.error("Error getting PDF file URL", e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/get/all-pdf-file-urls")
    public ResponseEntity<List<Map<String, String>>> getAllPdfFileUrls(@RequestParam("procedureId") Long procedureId) {
        log.info("Getting all PDF file URLs for procedureId: {}", procedureId);
        try {
            List<Map<String, String>> data = procedureService.getAllPdfFileUrlsByProcedure(procedureId);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            log.error("Error getting all PDF file URLs", e);
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody FormSubmissionRequestDTO dto) {
        log.info("Creating form submission");
        try {
            Long id = formSubmissionService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (Exception e) {
            log.error("Error creating form submission", e);
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Long> update(@RequestBody FormSubmissionRequestDTO dto) {
        log.info("Updating form submission");
        try {
            Long id = formSubmissionService.update(dto);
            return ResponseEntity.status(HttpStatus.OK).body(id);
        } catch (Exception e) {
            log.error("Error updating form submission", e);
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity<Boolean> confirmFormInfo(
            @RequestParam("formId") Long formId,
            @RequestPart("htmlFile") MultipartFile htmlFile,
            @RequestParam(value = "landscape", required = false, defaultValue = "false") Boolean landscape
    ) {

        try {
            // Validate file
            if (htmlFile == null || htmlFile.isEmpty()) {
                log.warn("HTML file is empty");
                return ResponseEntity.badRequest().build();
            }
            
            // Process form submission with PDF generation
            boolean result = formSubmissionService.confirmFormInfo(formId, htmlFile, landscape);
            
            if (result) {
                log.info("Form submission confirmed successfully");
                return ResponseEntity.ok(true);
            } else {
                log.warn("Failed to confirm form submission");
                return ResponseEntity.status(400).build();
            }
        } catch (Exception e) {
            log.error("Error confirming form submission", e);
            return ResponseEntity.status(400).build();
        }
    }

    
}
