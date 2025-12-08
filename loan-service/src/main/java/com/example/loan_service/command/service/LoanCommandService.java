package com.example.loan_service.command.service;

import com.example.loan_service.dto.command.CreateLoanCommand;
import com.example.loan_service.dto.LoanResponse;

public interface LoanCommandService {

    //LoanResponse createLoan(LoanRequest request);

    void approveLoan(Long id);

    void disburseLoan(Long id);

    Long createLoan(CreateLoanCommand command);
}
