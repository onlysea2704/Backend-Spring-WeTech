package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "info_documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_document_id")
    private Long infoDocumentId;

    @Column(name = "question")
    private String question;

    @Column(name = "map")
    private String map;

    @Column(name = "note")
    private String note;

    @Column(name = "document_id")
    private Long documentId;
}
