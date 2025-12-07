package com.example.loan_service.command.service;

import com.example.loan_service.dto.LoanRequest;
import com.example.loan_service.dto.LoanResponse;

public interface LoanCommandService {

    LoanResponse createLoan(LoanRequest request);

    LoanResponse approveLoan(Long id);

    LoanResponse disburseLoan(Long id);
}
