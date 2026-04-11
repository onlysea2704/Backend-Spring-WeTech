package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.ApiResponse;
import com.wetech.backend_spring_wetech.dto.PdfUploadResponse;
import com.wetech.backend_spring_wetech.service.PdfService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * REST Controller for PDF generation endpoints.
 * Provides endpoints to generate PDF from HTML and optionally upload to Cloudinary.
 */
@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
@Slf4j
public class PdfController {
    private final PdfService pdfService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<PdfUploadResponse>> generateAndUploadPdf(
            @RequestParam("file") MultipartFile htmlFile,
            @RequestParam(value = "landscape", required = false, defaultValue = "false") Boolean landscape
    ) {
        log.info("Received PDF generation and upload request with HTML file: {}", htmlFile.getOriginalFilename());
        
        try {
            // Validate file is not empty
            if (htmlFile.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("HTML file is empty"));
            }
            
            // Read HTML content from file
            String html = new String(htmlFile.getBytes(), StandardCharsets.UTF_8);
            
            if (html.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("HTML file content is empty"));
            }
            
            String fileName = "document_" + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            
            PdfUploadResponse response = pdfService.generateAndUploadPdf(html, fileName, landscape);
            
            log.info("PDF successfully uploaded: {}", response.getUrl());
            return ResponseEntity.ok(ApiResponse.success(response));

        } catch (IllegalArgumentException e) {
            log.error("Invalid request: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        } catch (RuntimeException e) {
            log.error("Error generating and uploading PDF: {}", e.getMessage());
            return ResponseEntity.status(500)
                    .body(ApiResponse.of(500, e.getMessage(), null));
        } catch (Exception e) {
            log.error("Unexpected error during PDF upload", e);
            return ResponseEntity.status(500)
                    .body(ApiResponse.of(500, "Failed to generate and upload PDF: " + e.getMessage(), null));
        }
    }
}

