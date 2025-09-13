package com.wetech.backend_spring_wetech.dto;

import lombok.Data;

@Data
public class WebhookPayload {
    private Long id;
    private String gateway;
    private String transactionDate;
    private String accountNumber;
    private String code;
    private String content;
    private String transferType;
    private Double transferAmount;
    private Long accumulated;
    private String subAccount;
    private String referenceCode;
    private String description;

//    // getters và setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getGateway() { return gateway; }
//    public void setGateway(String gateway) { this.gateway = gateway; }
//
//    public String getTransactionDate() { return transactionDate; }
//    public void setTransactionDate(String transactionDate) { this.transactionDate = transactionDate; }
//
//    public String getAccountNumber() { return accountNumber; }
//    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
//
//    public String getCode() { return code; }
//    public void setCode(String code) { this.code = code; }
//
//    public String getContent() { return content; }
//    public void setContent(String content) { this.content = content; }
//
//    public String getTransferType() { return transferType; }
//    public void setTransferType(String transferType) { this.transferType = transferType; }
//
//    public Long getTransferAmount() { return transferAmount; }
//    public void setTransferAmount(Long transferAmount) { this.transferAmount = transferAmount; }
//
//    public Long getAccumulated() { return accumulated; }
//    public void setAccumulated(Long accumulated) { this.accumulated = accumulated; }
//
//    public String getSubAccount() { return subAccount; }
//    public void setSubAccount(String subAccount) { this.subAccount = subAccount; }
//
//    public String getReferenceCode() { return referenceCode; }
//    public void setReferenceCode(String referenceCode) { this.referenceCode = referenceCode; }
//
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
}

