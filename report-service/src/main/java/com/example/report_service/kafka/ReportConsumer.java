package com.example.report_service.kafka;

import com.example.base_domains.dto.LoanEvent;
import com.example.report_service.entity.Report;
import com.example.report_service.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportConsumer {

    private final ReportRepository reportRepository;

    @KafkaListener(topics = "loan_events", groupId = "report-group")
    @Transactional
    public void handleLoanEvent(LoanEvent event) {
        log.info("Nhận sự kiện Kafka: {} - Amount: {}", event.getEventType(), event.getAmount());

        LocalDate today = LocalDate.now();

        // Tìm báo cáo hôm nay, nếu chưa có thì tạo mới giá trị 0
        Report report = reportRepository.findByReportDate(today)
                .orElse(Report.builder()
                        .reportDate(today)
                        .totalLoansCreated(0L)
                        .totalLoansApproved(0L)
                        .totalDisbursedAmount(BigDecimal.ZERO)
                        .build());

        // Cập nhật số liệu dựa trên loại sự kiện (Dùng String check)
        String type = event.getEventType();

        if ("LOAN_CREATED".equalsIgnoreCase(type)) {
            report.setTotalLoansCreated(report.getTotalLoansCreated() + 1);
        }
        else if ("LOAN_APPROVED".equalsIgnoreCase(type)) {
            report.setTotalLoansApproved(report.getTotalLoansApproved() + 1);
        }
        else if ("LOAN_DISBURSED".equalsIgnoreCase(type)) {
            // Cộng dồn tiền giải ngân
            report.setTotalDisbursedAmount(
                    report.getTotalDisbursedAmount().add(event.getAmount())
            );
        }

        reportRepository.save(report);
        log.info("Đã cập nhật báo cáo ngày: {}", today);
    }
}