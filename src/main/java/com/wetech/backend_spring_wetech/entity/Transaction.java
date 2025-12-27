package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long idTransaction;

    @Column(name = "transfer_amount")
    private Double transferAmount;

    @Column(name = "transaction_start")
    private LocalDateTime transactionStart;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "status")
    private String status;

    @Column(name = "id_user")
    private Long userId;

    @Column(name = "code")
    private String code;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_address")
    private String companyAddress;

    @Column(name = "need_vat")
    private String needVat;
}

