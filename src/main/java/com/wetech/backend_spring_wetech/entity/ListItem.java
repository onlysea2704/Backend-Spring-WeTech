package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "list_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_course")
    private Long idCourse;

    @Column(name = "id_procedure")
    private Long idProcedure;

    @Column(name = "type_item")
    private String typeItem;

    @Column(name = "id_transaction")
    private Long idTransaction;
}
