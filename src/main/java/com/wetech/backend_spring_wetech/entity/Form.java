package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "form")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formId;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "procedure_id")
    @NonNull
    private Procedure procedure;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<FormSubmission> formSubmissions;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
