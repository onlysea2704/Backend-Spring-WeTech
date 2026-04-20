package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.dto.FormSubmissionRequestDTO;
import com.wetech.backend_spring_wetech.entity.Form;
import com.wetech.backend_spring_wetech.entity.FormSubmission;
import com.wetech.backend_spring_wetech.entity.MyProcedure;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.repository.FormSubmissionRepository;
import com.wetech.backend_spring_wetech.repository.MyProcedureRepository;
import com.wetech.backend_spring_wetech.utils.CloudinaryUtils;
import com.wetech.backend_spring_wetech.utils.MockMultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private HtmlToDocxService htmlToDocxService;

    public Map<String, Object> get(Long formId) {
        Form form = formService.findById(formId);
        User user = userService.getCurrentUser();

        FormSubmission formSubmission = formSubmissionRepository
                .findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());
        if (formSubmission == null)
            return null;

        return formSubmission.getDataJson();
    }

    public Long create(FormSubmissionRequestDTO dto) {
        Form form = formService.findById(dto.getFormId());
        User user = userService.getCurrentUser();
        FormSubmission formSubmission = formSubmissionRepository
                .findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());
        if (formSubmission != null) {
            throw new RuntimeException("Form submission already exists");
        } else {
            FormSubmission newFormSubmission = FormSubmission.builder()
                    .form(form)
                    .user(user)
                    .dataJson(dto.getDataJson())
                    .build();
            boolean existingMyProcedure = myProcedureRepository.existsByUserIdAndProcedureId(user.getUserId(),
                    form.getProcedure().getProcedureId());
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
        FormSubmission formSubmission = formSubmissionRepository
                .findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());
        if (formSubmission == null) {
            throw new RuntimeException("Form submission not found");
        } else {
            formSubmission.setDataJson(dto.getDataJson());
            return formSubmissionRepository.save(formSubmission).getId();
        }
    }

    public boolean confirmFormInfo(Long formId, MultipartFile htmlFile, MultipartFile docxFile, Boolean landscape) {
        Form form = formService.findById(formId);
        User user = userService.getCurrentUser();
        FormSubmission formSubmission = formSubmissionRepository
                .findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());

        if (formSubmission == null) {
            log.warn("FormSubmission not found for formId: {} and userId: {}", formId, user.getUserId());
            return false;
        }

        try {
            String pdfUrl = pdfService.generateAndUploadPdf(formId, htmlFile, landscape).getUrl();
            String docxUrl;

            if (docxFile != null && !docxFile.isEmpty()) {
                // Use frontend generated docx file directly
                String fileName = "form_" + formId + "_" + java.util.UUID.randomUUID() + ".docx";
                docxUrl = cloudinaryUtils.uploadToCloudinary(new MockMultipartFile(fileName, docxFile.getBytes()));
            } else {
                // Fallback to Pandoc if frontend docx generation failed
                docxUrl = htmlToDocxService.convertAndSaveDocx(formId, htmlFile);
            }

            if (pdfUrl == null || docxUrl == null) {
                throw new RuntimeException("Failed to upload PDF to Cloudinary");
            }

            // Save PDF URL to FormSubmission
            formSubmission.setPdfFileUrl(pdfUrl);
            formSubmission.setDocxFileUrl(docxUrl);
            formSubmissionRepository.save(formSubmission);

            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid HTML content: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error confirming form submission with PDF generation: " + e.getMessage());
        }
    }

    // New method: get file url by form code and file type (pdf/docx)
    public Map<String, String> getFileUrlByCode(String code, String fileType) {
        Form form = formService.findByCode(code);
        User user = userService.getCurrentUser();
        FormSubmission formSubmission = formSubmissionRepository
                .findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());

        // MyProcedure myProcedure =
        // myProcedureRepository.findByUserIdAndProcedureId(user.getUserId(),
        // form.getProcedure().getProcedureId());
        // if (myProcedure == null || myProcedure.getStatus() ==
        // MyProcedure.Status.DRAFT) {
        // throw new RuntimeException("User has not paid for this procedure");
        // }

        if (formSubmission == null) {
            return null;
        }

        String url;
        if ("docx".equalsIgnoreCase(fileType)) {
            url = formSubmission.getDocxFileUrl() != null ? formSubmission.getDocxFileUrl() : "";
        } else {
            // default to pdf
            url = formSubmission.getPdfFileUrl() != null ? formSubmission.getPdfFileUrl() : "";
        }
        return Map.of("url", url);
    }
}
