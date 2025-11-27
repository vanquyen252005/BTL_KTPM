package com.example.report_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @Column(unique = true, nullable = false)
    private LocalDate reportDate;

    private Long totalLoansCreated;
    private Long totalLoansApproved;
    private BigDecimal totalDisbursedAmount;
}