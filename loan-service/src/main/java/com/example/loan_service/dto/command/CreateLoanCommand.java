package com.example.loan_service.dto.command;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateLoanCommand {
    @NotNull(message = "Borrower ID is required")
    private Long borrowerId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "1000000", message = "Minimum loan amount is 1,000,000")
    private BigDecimal loanAmount;

    @NotNull(message = "Interest Rate is required")
    private BigDecimal interestRate;

    @NotNull(message = "Duration is required")
    private Integer durationMonths;
}
