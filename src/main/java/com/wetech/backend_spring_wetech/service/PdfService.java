package com.wetech.backend_spring_wetech.service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.Margin;
import com.wetech.backend_spring_wetech.dto.PdfUploadResponse;
import com.wetech.backend_spring_wetech.utils.CloudinaryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

    /**
     * Generate PDF from HTML content.
     * Creates a new page for each request and closes it after use.
     * 
     * @param html HTML content to be converted to PDF
     * @return PDF content as byte array
     */
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
                            .setTop("20mm")
                            .setBottom("20mm")
                            .setLeft("15mm")
                            .setRight("15mm")
                    )
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

    /**
     * Generate PDF from HTML and upload to Cloudinary.
     * 
     * @param html HTML content to be converted to PDF
     * @param fileName Name of the file to be uploaded
     * @return PdfUploadResponse containing the Cloudinary URL
     */
    public PdfUploadResponse generateAndUploadPdf(String html, String fileName, Boolean landscape) {
        log.info("Starting PDF generation and upload process");

        try {
            // Generate PDF
            byte[] pdfContent = generatePdfFromHtml(html, landscape);
            log.debug("PDF generated, size: {} bytes", pdfContent.length);

            // Create a temporary file to upload
            File tempFile = File.createTempFile("pdf_", ".pdf");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(pdfContent);
                log.debug("Temporary PDF file created");
            }

            // Create MultipartFile from temporary file
            ByteArrayInputStream bais = new ByteArrayInputStream(pdfContent);
            
            // Upload to Cloudinary
            String cloudinaryUrl = cloudinaryUtils.uploadToCloudinary(
                    new MockMultipartFile(fileName + ".pdf", bais.readAllBytes())
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


    /**
     * Mock implementation of MultipartFile for uploading to Cloudinary.
     * This is a helper class to convert byte array to MultipartFile.
     */
    public static class MockMultipartFile implements MultipartFile {
        private final String filename;
        private final byte[] content;

        public MockMultipartFile(String filename, byte[] content) {
            this.filename = filename;
            this.content = content;
        }

        @Override
        public String getName() {
            return filename;
        }

        @Override
        public String getOriginalFilename() {
            return filename;
        }

        @Override
        public String getContentType() {
            return "application/pdf";
        }

        @Override
        public boolean isEmpty() {
            return content == null || content.length == 0;
        }

        @Override
        public long getSize() {
            return content == null ? 0 : content.length;
        }

        @Override
        public byte[] getBytes() throws IOException {
            return content;
        }

        @Override
        public java.io.InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(content);
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
            try (FileOutputStream fos = new FileOutputStream(dest)) {
                fos.write(content);
            }
        }
    }
}

