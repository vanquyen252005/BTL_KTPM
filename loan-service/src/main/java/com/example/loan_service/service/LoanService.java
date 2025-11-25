package com.example.loan_service.service;

import com.example.loan_service.dto.LoanRequest;
import com.example.loan_service.dto.LoanResponse;
import java.util.List;

public interface LoanService {
    LoanResponse createLoan(LoanRequest request);
    LoanResponse getLoanById(Long id);
    List<LoanResponse> getAllLoans();
    LoanResponse approveLoan(Long id);   // Duyệt vay
    LoanResponse disburseLoan(Long id);  // Giải ngân
}