package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.dto.FormDTO;
import com.wetech.backend_spring_wetech.entity.Form;
import com.wetech.backend_spring_wetech.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class FormService {
    private FormRepository formRepository;

    public Form findById(Long formId) {
        return formRepository.findById(formId).orElseThrow(() -> new RuntimeException("Form not found"));
    }

    public Form findByCode(String code) {
        Optional<Form> opt = formRepository.findByCode(code);
        return opt.orElseThrow(() -> new RuntimeException("Form not found by code"));
    }

    public void convertToForm(FormDTO formDTO, Form form) {
        form.setCode(formDTO.getCode());
        form.setName(formDTO.getName());
        form.setType(formDTO.getType());
    }
}
