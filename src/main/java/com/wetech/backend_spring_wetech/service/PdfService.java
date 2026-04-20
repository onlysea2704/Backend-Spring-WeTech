package com.wetech.backend_spring_wetech.service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.Margin;
import com.wetech.backend_spring_wetech.dto.PdfUploadResponse;
import com.wetech.backend_spring_wetech.utils.CloudinaryUtils;
import com.wetech.backend_spring_wetech.utils.MockMultipartFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

/**
 * Service for generating PDF files from HTML using Playwright.
 * Reuses a singleton browser instance for performance optimization.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PdfService implements InitializingBean {

    private final Playwright playwright;
    private final CloudinaryUtils cloudinaryUtils;
    private Browser browser;

    @Override
    public void afterPropertiesSet() {
        log.info("Eagerly launching Chromium browser during startup...");
        getBrowser();
    }

    public synchronized Browser getBrowser() {
        if (browser == null || !browser.isConnected()) {
            if (browser != null) {
                try {
                    browser.close();
                } catch (Exception e) {
                    log.warn("Error closing disconnected browser", e);
                }
            }
            log.info("Launching new Chromium browser instance");
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setArgs(java.util.List.of(
                            "--disable-dev-shm-usage",
                            "--no-sandbox",
                            "--disable-setuid-sandbox",
                            "--disable-gpu")));
        }
        return browser;
    }

    public <T> T executeWithBrowser(Function<Browser, T> action) {
        try {
            return action.apply(getBrowser());
        } catch (Exception ex) {
            log.warn("Browser failed, retrying once...", ex);

            // reset browser
            synchronized (this) {
                if (browser != null) {
                    try {
                        browser.close();
                    } catch (Exception ignore) {
                    }
                    browser = null;
                }
            }

            // retry 1 lần
            return action.apply(getBrowser());
        }
    }

    public byte[] generatePdfFromHtml(String html, Boolean landscape) {
        log.info("Starting PDF generation from HTML");

        if (html == null || html.trim().isEmpty()) {
            throw new IllegalArgumentException("HTML content cannot be empty");
        }

        return executeWithBrowser(browser -> {
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            try {
                // Create a new page from the browser
                page.setDefaultTimeout(80000.0); // 80 seconds timeout
                log.debug("New page created");

                // Set HTML content
                page.setContent(html);
                log.debug("HTML content set");

                // Wait until network is idle for all resources to load (up to 90 seconds)
                page.waitForLoadState(LoadState.NETWORKIDLE);
                log.debug("Network idle state reached");

                // Generate PDF with specific options
                byte[] pdfContent = page.pdf(new Page.PdfOptions()
                        .setFormat("A4")
                        .setMargin(new Margin()
                                .setTop("15mm")
                                .setBottom("20mm")
                                .setLeft("15mm")
                                .setRight("15mm"))
                        .setScale(landscape != null && landscape ? 0.70 : 1.0)
                        .setLandscape(landscape != null && landscape)
                        .setPrintBackground(true));
                log.info("PDF generated successfully, size: {} bytes", pdfContent.length);

                return pdfContent;

            } catch (Exception e) {
                log.error("Error generating PDF from HTML", e);
                throw new RuntimeException("Failed to generate PDF: " + e.getMessage(), e);
            } finally {
                // Always close the page to free resources
                if (context != null) {
                    try {
                        context.close();
                        log.debug("Page closed successfully");
                    } catch (Exception e) {
                        log.warn("Error closing page", e);
                    }
                }
            }
        });
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
                    new MockMultipartFile(fileName, pdfContent));
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
