package com.example.loan_service.query.service;

import com.example.loan_service.dto.LoanResponse;

import java.util.List;

public interface LoanQueryService {

    LoanResponse getLoanById(Long id);

    List<LoanResponse> getAllLoans();
}
