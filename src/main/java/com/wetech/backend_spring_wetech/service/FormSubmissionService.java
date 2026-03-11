package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.dto.FormSubmissionRequestDTO;
import com.wetech.backend_spring_wetech.entity.Form;
import com.wetech.backend_spring_wetech.entity.FormSubmission;
import com.wetech.backend_spring_wetech.entity.MyProcedure;
import com.wetech.backend_spring_wetech.entity.Procedure;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.repository.FormSubmissionRepository;
import com.wetech.backend_spring_wetech.repository.MyProcedureRepository;
import com.wetech.backend_spring_wetech.utils.CloudinaryUtils;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class FormSubmissionService {
    private FormService formService;
    private UserService userService;
    private FormSubmissionRepository formSubmissionRepository;
    private CloudinaryUtils cloudinaryUtils;
    private MyProcedureRepository myProcedureRepository;
    private ProcedureService procedureService;

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

    public boolean confirmFormInfo(Long formId, MultipartFile pdfFile) {
        Form form = formService.findById(formId);
        User user = userService.getCurrentUser();
        FormSubmission formSubmission = formSubmissionRepository.findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());
        if (formSubmission == null) {
            return false;
        }

        try {
            String url;
            if (formSubmission.getPdfFileUrl() != null && !formSubmission.getPdfFileUrl().isEmpty()) {
                // overwrite existing file
                url = cloudinaryUtils.updateToCloudinary(pdfFile, formSubmission.getPdfFileUrl());
            } else {
                // fresh upload
                url = cloudinaryUtils.uploadToCloudinary(pdfFile);
            }

            if (url == null) return false;

            formSubmission.setPdfFileUrl(url);
            formSubmissionRepository.save(formSubmission);
            return true;
        } catch (Exception e) {
            // Could add logging here
            return false;
        }
    }

    public String getPdfFileUrl(Long formId) {
        Form form = formService.findById(formId);
        User user = userService.getCurrentUser();
        FormSubmission formSubmission = formSubmissionRepository.findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());
        if (formSubmission == null) {
            return null;
        } else {
            return formSubmission.getPdfFileUrl();
        }
    }

    // New method: get pdf file url by form code
    public Map<String, String> getPdfFileUrlByCode(String code) {
        Form form = formService.findByCode(code);
        User user = userService.getCurrentUser();
        FormSubmission formSubmission = formSubmissionRepository.findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());
        if (formSubmission == null) {
            return null;
        } else {
            return Map.of(
                    "url", formSubmission.getPdfFileUrl() != null ? formSubmission.getPdfFileUrl() : ""
            );
        }
    }

    public List<Map<String, String>> getAllPdfFileUrlsByProcedure(Long procedureId) {
        User user = userService.getCurrentUser();
        // check if user has a MyProcedure record for this procedure
        MyProcedure myProc = myProcedureRepository.findByUserIdAndProcedureId(user.getUserId(), procedureId);
//        if (myProc == null || myProc.getStatus().equals(MyProcedure.Status.DRAFT)) {
//            return new ArrayList<>();
//        }

        Procedure procedure = procedureService.findById(procedureId);
        List<Map<String, String>> urls = new ArrayList<>();
        if (procedure.getForms() == null || procedure.getForms().isEmpty()) return urls;

        for (Form form : procedure.getForms()) {
            FormSubmission fs = formSubmissionRepository.findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(form.getFormId(), user.getUserId());
            if (fs != null && fs.getPdfFileUrl() != null) {
                Map<String, String> url = Map.of(
                        "url", fs.getPdfFileUrl(),
                        "code", form.getCode(),
                        "id", form.getFormId().toString()
                );
                urls.add(url);
            }
        }

        return urls;
    }
}
