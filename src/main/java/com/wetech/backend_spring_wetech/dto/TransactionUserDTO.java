package com.wetech.backend_spring_wetech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionUserDTO {
    private Long idTransaction;
    private Double transferAmount;
    private LocalDateTime transactionDate;
    private String status;
    private String code;
    private String fullName;
    private String sdt;
}
