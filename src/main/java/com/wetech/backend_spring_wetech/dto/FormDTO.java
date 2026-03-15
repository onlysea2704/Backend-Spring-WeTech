package com.wetech.backend_spring_wetech.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wetech.backend_spring_wetech.entity.Form;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormDTO {
    private Long formId;
    private String code;
    private String name;
    private String type;
    private LocalDateTime createdAt;
    private String url;

    public FormDTO(Form form) {
        this.formId = form.getFormId();
        this.code = form.getCode();
        this.name = form.getName();
        this.type = form.getType();
        this.createdAt = form.getCreatedAt();
    }

    public FormDTO(Form form, String url) {
        this(form);
        this.url = url;
    }
}
