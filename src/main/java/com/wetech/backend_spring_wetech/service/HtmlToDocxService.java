package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.entity.Form;
import com.wetech.backend_spring_wetech.entity.FormSubmission;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.repository.FormSubmissionRepository;
import com.wetech.backend_spring_wetech.utils.CloudinaryUtils;
import com.wetech.backend_spring_wetech.utils.MockMultipartFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

/**
 * Service to convert HTML to DOCX using the external pandoc executable,
 * upload the resulting DOCX to Cloudinary and persist the URL on FormSubmission.
 *
 * Note: pandoc must be installed and available on the PATH for this to work.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class HtmlToDocxService {

    private final FormService formService;
    private final UserService userService;
    private final FormSubmissionRepository formSubmissionRepository;
    private final CloudinaryUtils cloudinaryUtils;

    public String convertAndSaveDocx(Long formId, MultipartFile htmlFile) {
        Form form = formService.findById(formId);
        User user = userService.getCurrentUser();

        FormSubmission formSubmission = formSubmissionRepository.findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());
        if (formSubmission == null) {
            throw new RuntimeException("FormSubmission not found for formId: " + formId + " and user");
        }

        File tempHtml = null;
        File tempDocx = null;
        try {
            // Create temp files
            tempHtml = Files.createTempFile("html_", ".html").toFile();
            tempDocx = Files.createTempFile("docx_", ".docx").toFile();

            // Write HTML bytes to temp file
            Files.write(tempHtml.toPath(), htmlFile.getBytes());

            // Run pandoc to convert html -> docx
            ProcessBuilder pb = new ProcessBuilder(
                    "pandoc",
                    tempHtml.getAbsolutePath(),
                    "-o",
                    tempDocx.getAbsolutePath()
            );
            pb.redirectErrorStream(true);
            Process p = pb.start();
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                String out = new String(p.getInputStream().readAllBytes());
                log.error("Pandoc conversion failed: {}", out);
                throw new RuntimeException("Pandoc conversion failed: " + out);
            }

            // Read docx bytes
            byte[] docxBytes = Files.readAllBytes(tempDocx.toPath());

            // Upload to Cloudinary
            String fileName = "form_" + formId + "_" + UUID.randomUUID() + ".docx";
            String cloudUrl = cloudinaryUtils.uploadToCloudinary(new MockMultipartFile(fileName, docxBytes));
            if (cloudUrl == null) {
                throw new RuntimeException("Cloudinary upload returned null url");
            }

            // Save URL to formSubmission
            formSubmission.setDocxFileUrl(cloudUrl);
            formSubmissionRepository.save(formSubmission);

            log.info("DOCX generated and uploaded: {}", cloudUrl);
            return cloudUrl;

        } catch (IOException | InterruptedException e) {
            log.error("Error converting HTML to DOCX", e);
            throw new RuntimeException("Failed to convert HTML to DOCX: " + e.getMessage(), e);
        } finally {
            try {
                if (tempHtml != null) Files.deleteIfExists(tempHtml.toPath());
                if (tempDocx != null) Files.deleteIfExists(tempDocx.toPath());
            } catch (IOException e) {
                log.warn("Failed to delete temp files", e);
            }
        }
    }
}


