package com.example.report_service.service;

import com.example.report_service.entity.Report;
import java.util.List;

public interface ReportQueryService {
    List<Report> getAllReports();
    Report getTodayReport();
}