package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document_procedures")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentProcedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "name")
    private String name;

    @Column(name = "link_material_raw")
    private String linkMaterialRaw;

    @Column(name = "procedure_id")
    private Long procedureId;

    @Column(name = "link_material_edit")
    private String linkMaterialEdit;
}

