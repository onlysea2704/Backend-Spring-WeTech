package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.dto.FormSubmissionRequestDTO;
import com.wetech.backend_spring_wetech.entity.Form;
import com.wetech.backend_spring_wetech.entity.FormSubmission;
import com.wetech.backend_spring_wetech.entity.MyProcedure;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.repository.FormSubmissionRepository;
import com.wetech.backend_spring_wetech.repository.MyProcedureRepository;
import com.wetech.backend_spring_wetech.utils.CloudinaryUtils;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@Slf4j
public class FormSubmissionService {
    private FormService formService;
    private UserService userService;
    private FormSubmissionRepository formSubmissionRepository;
    private CloudinaryUtils cloudinaryUtils;
    private MyProcedureRepository myProcedureRepository;
    private PdfService pdfService;

    public Map<String, Object> get(Long formId) {
        Form form = formService.findById(formId);
        User user = userService.getCurrentUser();

        FormSubmission formSubmission = formSubmissionRepository.findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());
        if (formSubmission == null) return null;

        return formSubmission.getDataJson();
    }

    public Long create(FormSubmissionRequestDTO dto) {
        Form form = formService.findById(dto.getFormId());
        User user = userService.getCurrentUser();
        FormSubmission formSubmission = formSubmissionRepository.findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());
        if (formSubmission != null) {
            throw new RuntimeException("Form submission already exists");
        } else {
            FormSubmission newFormSubmission = FormSubmission.builder()
                    .form(form)
                    .user(user)
                    .dataJson(dto.getDataJson())
                    .build();
            boolean existingMyProcedure = myProcedureRepository.existsByUserIdAndProcedureId(user.getUserId(),form.getProcedure().getProcedureId());
            if (!existingMyProcedure) {
                MyProcedure myProcedure = MyProcedure.builder()
                        .userId(user.getUserId())
                        .procedureId(form.getProcedure().getProcedureId())
                        .build();
                myProcedureRepository.save(myProcedure);
            }
            return formSubmissionRepository.save(newFormSubmission).getId();
        }
    }

    public Long update(FormSubmissionRequestDTO dto) {
        Form form = formService.findById(dto.getFormId());
        User user = userService.getCurrentUser();
        FormSubmission formSubmission = formSubmissionRepository.findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());
        if (formSubmission == null) {
            throw new RuntimeException("Form submission not found");
        } else {
            formSubmission.setDataJson(dto.getDataJson());
            return formSubmissionRepository.save(formSubmission).getId();
        }
    }

    public boolean confirmFormInfo(Long formId, MultipartFile htmlFile) {
        Form form = formService.findById(formId);
        User user = userService.getCurrentUser();
        FormSubmission formSubmission = formSubmissionRepository.findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());
        
        if (formSubmission == null) {
            log.warn("FormSubmission not found for formId: {} and userId: {}", formId, user.getUserId());
            return false;
        }

        try {
            // Read HTML content from file
            String htmlContent = new String(htmlFile.getBytes(), StandardCharsets.UTF_8);
            log.debug("HTML content read from file, size: {} bytes", htmlContent.length());
            
            if (htmlContent.trim().isEmpty()) {
                log.warn("HTML file content is empty");
                return false;
            }
            
            // Generate PDF from HTML and upload to Cloudinary
            String fileName = "form_" + formId + "_" + System.currentTimeMillis();
            byte[] pdfContent = pdfService.generatePdfFromHtml(htmlContent);
            log.debug("PDF generated, size: {} bytes", pdfContent.length);
            
            // Upload PDF to Cloudinary using MockMultipartFile
            String url = cloudinaryUtils.uploadToCloudinary(
                    new PdfService.MockMultipartFile(fileName + ".pdf", pdfContent)
            );
            
            if (url == null) {
                log.error("Failed to upload PDF to Cloudinary");
                return false;
            }

            // Save PDF URL to FormSubmission
            formSubmission.setPdfFileUrl(url);
            formSubmissionRepository.save(formSubmission);
            log.info("FormSubmission confirmed with PDF URL: {}", url);
            
            return true;
            
        } catch (IllegalArgumentException e) {
            log.error("Invalid HTML content: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Error confirming form submission with PDF generation", e);
            return false;
        }
    }

    // New method: get pdf file url by form code
    public Map<String, String> getPdfFileUrlByCode(String code) {
        Form form = formService.findByCode(code);
        User user = userService.getCurrentUser();
        FormSubmission formSubmission = formSubmissionRepository.findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());

//        MyProcedure myProcedure = myProcedureRepository.findByUserIdAndProcedureId(user.getUserId(), form.getProcedure().getProcedureId());
//        if (myProcedure == null || myProcedure.getStatus() == MyProcedure.Status.DRAFT) {
//            throw new RuntimeException("User has not paid for this procedure");
//        }

        if (formSubmission == null) {
            return null;
        } else {
            return Map.of(
                    "url", formSubmission.getPdfFileUrl() != null ? formSubmission.getPdfFileUrl() : ""
            );
        }
    }
}
