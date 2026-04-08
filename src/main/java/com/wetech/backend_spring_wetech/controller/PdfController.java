package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.ApiResponse;
import com.wetech.backend_spring_wetech.dto.PdfGenerateRequest;
import com.wetech.backend_spring_wetech.dto.PdfUploadResponse;
import com.wetech.backend_spring_wetech.service.PdfService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    /**
     * Generate PDF from HTML and return as downloadable file.
     * 
     * @param request Request body containing HTML content
     * @return ResponseEntity with PDF file and proper headers
     */
    @PostMapping("/generate")
    public ResponseEntity<byte[]> generatePdf(@RequestBody PdfGenerateRequest request) {
        log.info("Received PDF generation request");
        
        try {
            // Generate PDF from HTML
            byte[] pdfContent = pdfService.generatePdfFromHtml(request.getHtml());

            // Prepare response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            
            // Generate file name with timestamp
            String fileName = "document_" + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".pdf";
            
            // Set Content-Disposition header for download
            headers.setContentDisposition(
                    ContentDisposition.attachment()
                            .filename(fileName, StandardCharsets.UTF_8)
                            .build()
            );
            headers.setContentLength(pdfContent.length);

            log.info("PDF generated and prepared for download: {}", fileName);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfContent);

        } catch (IllegalArgumentException e) {
            log.error("Invalid request: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Error generating PDF", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Generate PDF from HTML file and upload to Cloudinary.
     * Accepts an HTML file, extracts the content, and uploads the generated PDF.
     * 
     * @param htmlFile HTML file containing the content to convert to PDF
     * @return ApiResponse containing upload result or error message
     */
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<PdfUploadResponse>> generateAndUploadPdf(
            @RequestParam("file") MultipartFile htmlFile) {
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
            
            PdfUploadResponse response = pdfService.generateAndUploadPdf(html, fileName);
            
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

