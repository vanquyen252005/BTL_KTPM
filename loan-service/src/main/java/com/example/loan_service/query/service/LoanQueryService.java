package com.example.loan_service.query.service;

import com.example.loan_service.dto.LoanResponse;
import com.example.loan_service.dto.query.LoanDetailView;
import com.example.loan_service.dto.query.LoanSummaryView;

import java.util.List;

public interface LoanQueryService {

    LoanDetailView getLoanById(Long id);

    List<LoanSummaryView> getAllLoans();
}
