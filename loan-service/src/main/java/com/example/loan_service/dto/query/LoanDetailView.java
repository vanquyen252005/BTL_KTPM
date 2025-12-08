package com.example.loan_service.dto.query;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoanDetailView {
    private Long loanId;
    private String loanNumber;
    private Long borrowerId;
    private BigDecimal loanAmount;
    private BigDecimal interestRate;
    private Integer durationMonths;
    private String loanStatus;
    private LocalDateTime createdDate;
    private LocalDateTime disbursementDate;
}
