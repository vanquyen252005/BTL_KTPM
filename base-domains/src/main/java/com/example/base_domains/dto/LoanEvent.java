package com.example.base_domains.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanEvent {

    //String: "LOAN_CREATED", "LOAN_DISBURSED"...)
    private String eventType;
    private String loanNumber;
    private Long borrowerId;
    private BigDecimal amount;
    private BigDecimal interestRate;
    private Integer durationMonths;
    private String loanType;
    private LocalDateTime eventDate;
}