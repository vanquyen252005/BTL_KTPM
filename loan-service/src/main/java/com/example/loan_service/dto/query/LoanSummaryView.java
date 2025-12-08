package com.example.loan_service.dto.query;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoanSummaryView {
    private Long loanId;
    private String loanNumber;
    private Long borrowerId;
    private BigDecimal loanAmount;
    private Integer durationMonths;
    private String loanStatus;
}
