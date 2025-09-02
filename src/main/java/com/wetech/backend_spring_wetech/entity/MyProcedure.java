package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "my_procedures")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyProcedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_procedure_id")
    private Long myProcedureId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "procedure_id")
    private Procedure procedure;
}
