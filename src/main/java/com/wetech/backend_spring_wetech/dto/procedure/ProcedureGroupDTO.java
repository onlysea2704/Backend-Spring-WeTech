package com.wetech.backend_spring_wetech.dto.procedure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcedureGroupDTO {
    private String serviceType;
    private String serviceTypeTitle;
    private List<ProcedureDTO> procedures;
}
