package com.wetech.backend_spring_wetech.service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.Margin;
import com.wetech.backend_spring_wetech.dto.PdfUploadResponse;
import com.wetech.backend_spring_wetech.utils.CloudinaryUtils;
import com.wetech.backend_spring_wetech.utils.MockMultipartFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Service for generating PDF files from HTML using Playwright.
 * Reuses a singleton browser instance for performance optimization.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PdfService {

    private final Browser browser;
    private final CloudinaryUtils cloudinaryUtils;
    
    public byte[] generatePdfFromHtml(String html, Boolean landscape) {
        log.info("Starting PDF generation from HTML");
        
        if (html == null || html.trim().isEmpty()) {
            throw new IllegalArgumentException("HTML content cannot be empty");
        }

        Page page = null;
        try {
            // Create a new page from the browser
            page = browser.newPage();
            log.debug("New page created");

            // Set HTML content
            page.setContent(html);
            log.debug("HTML content set");

            // Wait until network is idle for all resources to load
            page.waitForLoadState(LoadState.NETWORKIDLE);
            log.debug("Network idle state reached");

            // Generate PDF with specific options
            byte[] pdfContent = page.pdf(new Page.PdfOptions()
                    .setFormat("A4")
                    .setMargin(new Margin()
                            .setTop("15mm")
                            .setBottom("20mm")
                            .setLeft("15mm")
                            .setRight("15mm")
                    )
                    .setScale(landscape != null && landscape ? 0.65 : 1.0)
                    .setLandscape(landscape != null && landscape)
                    .setPrintBackground(true));
            log.info("PDF generated successfully, size: {} bytes", pdfContent.length);

            return pdfContent;

        } catch (Exception e) {
            log.error("Error generating PDF from HTML", e);
            throw new RuntimeException("Failed to generate PDF: " + e.getMessage(), e);
        } finally {
            // Always close the page to free resources
            if (page != null) {
                try {
                    page.close();
                    log.debug("Page closed successfully");
                } catch (Exception e) {
                    log.warn("Error closing page", e);
                }
            }
        }
    }
    
    public PdfUploadResponse generateAndUploadPdf(Long formId, MultipartFile htmlFile, Boolean landscape) {
        try {
            String htmlContent = new String(htmlFile.getBytes(), StandardCharsets.UTF_8);
            log.debug("HTML content read from file, size: {} bytes", htmlContent.length());

            // Generate PDF
            byte[] pdfContent = generatePdfFromHtml(htmlContent, landscape);
            log.debug("PDF generated, size: {} bytes", pdfContent.length);

            // Create a temporary file to upload
            File tempFile = File.createTempFile("pdf_", ".pdf");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(pdfContent);
                log.debug("Temporary PDF file created");
            }

            String fileName = "form_" + formId + "_" + System.currentTimeMillis() + ".pdf";
            String cloudinaryUrl = cloudinaryUtils.uploadToCloudinary(
                    new MockMultipartFile(fileName, pdfContent)
            );
            log.info("PDF uploaded to Cloudinary: {}", cloudinaryUrl);

            // Clean up temporary file
            if (!tempFile.delete()) {
                log.warn("Failed to delete temporary file: {}", tempFile.getAbsolutePath());
            }

            return PdfUploadResponse.builder()
                    .url(cloudinaryUrl)
                    .fileName(fileName + ".pdf")
                    .build();

        } catch (Exception e) {
            log.error("Error generating and uploading PDF", e);
            throw new RuntimeException("Failed to generate and upload PDF: " + e.getMessage(), e);
        }
    }
}

