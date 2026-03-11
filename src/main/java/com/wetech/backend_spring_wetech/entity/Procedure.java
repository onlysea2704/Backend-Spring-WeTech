package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "procedures")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "procedure_id")
    private Long procedureId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "service_type_label")
    private String serviceTypeTitle;

    @Column(name = "link_image")
    private String linkImage;

    @Column(name = "real_price")
    private Double realPrice;

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "type_company")
    private String typeCompany;

    @Column(name = "type_company_label")
    private String typeCompanyTitle;

    @Column(name = "number_register")
    private Integer numberRegister;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "procedure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Form> forms;

    @PrePersist
    public void prePersist() {
        LocalDate currentDate = LocalDate.now();
        createdAt = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
