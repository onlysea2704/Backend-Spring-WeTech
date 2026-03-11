package com.wetech.backend_spring_wetech.dto.procedure;

import com.wetech.backend_spring_wetech.dto.FormDTO;
import com.wetech.backend_spring_wetech.entity.Form;
import com.wetech.backend_spring_wetech.entity.Procedure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcedureDTO {
    private Long procedureId;
    private String title;
    private String description;
    private String linkImage;
    private String serviceType;
    private String serviceTypeTitle;
    private Double realPrice;
    private Double salePrice;
    private String typeCompany;
    private String typeCompanyTitle;
    private List<FormDTO> forms;

    public ProcedureDTO(Procedure procedure) {
        this.procedureId = procedure.getProcedureId();
        this.title = procedure.getTitle();
        this.description = procedure.getDescription();
        this.linkImage = procedure.getLinkImage();
        this.serviceType = procedure.getServiceType();
        this.serviceTypeTitle = procedure.getServiceTypeTitle();
        this.realPrice = procedure.getRealPrice();
        this.salePrice = procedure.getSalePrice();
        this.typeCompany = procedure.getTypeCompany();
        this.typeCompanyTitle = procedure.getTypeCompanyTitle();
        this.forms = procedure.getForms().stream().map(FormDTO::new).toList();
    }
}
