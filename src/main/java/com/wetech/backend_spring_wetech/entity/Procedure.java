package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "real_price")
    private Double realPrice;

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "type_company")
    private String typeCompany;

    @Column(name = "number_register")
    private Integer numberRegister;
}
