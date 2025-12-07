package com.example.loan_service.command.service;

import com.example.loan_service.dto.LoanRequest;
import com.example.loan_service.dto.LoanResponse;
import com.example.loan_service.entity.Loan;
import com.example.loan_service.exception.LoanNotFoundException;
import com.example.loan_service.map.LoanMapper;
import com.example.loan_service.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class LoanCommandServiceImpl implements LoanCommandService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    // private final LoanProducer loanProducer;

    @Override
    public LoanResponse createLoan(LoanRequest request) {
        Loan loan = loanMapper.toEntity(request);

        loan.setLoanStatus("PENDING");
        loan.setCreatedDate(LocalDateTime.now());
        loan.setLoanNumber("LN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        Loan savedLoan = loanRepository.save(loan);

        //publish event LOAN_CREATED nếu cần
        return loanMapper.toResponse(savedLoan);
    }

    @Override
    public LoanResponse approveLoan(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        if (!"PENDING".equals(loan.getLoanStatus())) {
            throw new IllegalStateException("Loan is not in PENDING state");
        }

        loan.setLoanStatus("APPROVED");
        Loan saved = loanRepository.save(loan);

        // publish event LOAN_APPROVED nếu cần
        return loanMapper.toResponse(saved);
    }

    @Override
    public LoanResponse disburseLoan(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        if (!"APPROVED".equals(loan.getLoanStatus())) {
            throw new IllegalStateException("Loan must be APPROVED before disbursement");
        }

        loan.setLoanStatus("DISBURSED");
        loan.setDisbursementDate(LocalDateTime.now());

        Loan savedLoan = loanRepository.save(loan);

        // loanProducer.sendEvent(...);

        return loanMapper.toResponse(savedLoan);
    }
}
