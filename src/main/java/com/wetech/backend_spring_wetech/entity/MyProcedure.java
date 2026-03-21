package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "my_procedures",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "procedure_id"})
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyProcedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_procedure_id")
    private Long myProcedureId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "procedure_id")
    private Long procedureId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
    private int submissionCount;
    private LocalDateTime submissionDate;
    private String taxAuthority;

    public enum Status {
        DRAFT,
        PAID,
        PENDING,
        SUCCESS,
        FAILED
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        status = Status.DRAFT;
        submissionCount = 0;
    }
}
