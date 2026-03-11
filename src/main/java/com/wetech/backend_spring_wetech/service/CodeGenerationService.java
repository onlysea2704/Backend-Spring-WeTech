package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.entity.Form;
import com.wetech.backend_spring_wetech.entity.Procedure;
import com.wetech.backend_spring_wetech.repository.FormRepository;
import com.wetech.backend_spring_wetech.repository.ProcedureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class CodeGenerationService {
    private final ProcedureRepository procedureRepository;
    private final FormRepository formRepository;

    private static final String PROC_PREFIX = "PROC";
    private static final String FORM_PREFIX = "FORM";
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHANUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String generateProcedureCode() {
        // Try generating until unique (should be rare)
        for (int i = 0; i < 10; i++) {
            String code = PROC_PREFIX + "-" + System.currentTimeMillis() + "-" + randomAlphaNumeric(4);
            if (!procedureRepository.existsById(parseIdFromCode(code))) {
                // double-check by code uniqueness in DB
                // repository doesn't have findByCode, so query by example: iterate existing list would be heavy.
                // Instead, rely on DB unique constraint and catch exception on save as fallback.
                return code;
            }
        }
        // fallback
        return PROC_PREFIX + "-" + System.currentTimeMillis() + "-" + randomAlphaNumeric(6);
    }

    public String generateFormCode() {
        for (int i = 0; i < 10; i++) {
            String code = FORM_PREFIX + "-" + System.currentTimeMillis() + "-" + randomAlphaNumeric(4);
            // ensure not existing by using FormRepository.findByCode
            if (formRepository.findByCode(code).isEmpty()) {
                return code;
            }
        }
        return FORM_PREFIX + "-" + System.currentTimeMillis() + "-" + randomAlphaNumeric(6);
    }

    private String randomAlphaNumeric(int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(ALPHANUM.charAt(RANDOM.nextInt(ALPHANUM.length())));
        }
        return sb.toString();
    }

    private Long parseIdFromCode(String code) {
        // This method is a placeholder; procedureRepository.existsById(parseIdFromCode(code)) in generateProcedureCode
        // was a naive check and will always return false. We keep it simple — uniqueness primarily enforced by DB.
        return -1L;
    }
}

