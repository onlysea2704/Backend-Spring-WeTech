package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long idTransaction;

    @Column(name = "transferAmount")
    private Double transferAmount;

    @Column(name = "transactionStart")
    private LocalDateTime transactionStart;

    @Column(name = "transactionDate")
    private LocalDateTime transactionDate;

    @Column(name = "status")
    private String status;

    @Column(name = "id_user")
    private Long userId;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<ListItem> items;
}

