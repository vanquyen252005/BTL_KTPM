package com.example.loan_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String loanNumber;

    private Long borrowerId;

    private BigDecimal loanAmount;

    private BigDecimal interestRate;

    private Integer durationMonths;

    private String loanStatus; // PENDING, APPROVED, REJECTED, PAID

    private LocalDateTime disbursementDate; // Ngày giải ngân
    private LocalDateTime createdDate;

}