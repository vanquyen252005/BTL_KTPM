package com.example.report_service.controller;

import com.example.report_service.entity.Report;
import com.example.report_service.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping("/today")
    public ResponseEntity<Report> getTodayReport() {
        return ResponseEntity.ok(reportService.getTodayReport());
    }
}