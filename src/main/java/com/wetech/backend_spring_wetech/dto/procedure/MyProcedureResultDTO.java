package com.wetech.backend_spring_wetech.dto.procedure;

import com.wetech.backend_spring_wetech.entity.MyProcedure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyProcedureResultDTO {
    private Long procedureId;
    private String serviceType;
    private String serviceTypeTitle;
    private String typeCompany;
    private String typeCompanyTitle;
    private String code;
    private int submissionCount;
    private LocalDateTime submissionDate;
    private String taxAuthority;
    private MyProcedure.Status status;
}
