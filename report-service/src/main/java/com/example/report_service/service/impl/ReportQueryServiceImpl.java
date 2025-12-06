package com.example.report_service.service.impl;

import com.example.report_service.entity.Report;
import com.example.report_service.repository.ReportRepository;
import com.example.report_service.service.ReportQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportQueryServiceImpl implements ReportQueryService {

    private final ReportRepository reportRepository;

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public Report getTodayReport() {
        LocalDate today = LocalDate.now();
        // Trả về object rỗng nếu chưa có data
        return reportRepository.findByReportDate(today)
                .orElse(Report.builder()
                        .reportDate(today)
                        .totalLoansCreated(0L)
                        .totalLoansApproved(0L)
                        .totalDisbursedAmount(BigDecimal.ZERO)
                        .build());
    }
}