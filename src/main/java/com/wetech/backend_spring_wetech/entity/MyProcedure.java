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

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "procedure_id")
    private Long procedureId;
}
