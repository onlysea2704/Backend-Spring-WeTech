package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document_sections")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "name")
    private String name;

    @Column(name = "link")
    private String link;

    @Column(name = "section_id")
    private Long sectionId;
}
