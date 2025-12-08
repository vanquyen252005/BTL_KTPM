package com.example.loan_service.command.service;

import com.example.loan_service.dto.command.CreateLoanCommand;
import com.example.loan_service.entity.Loan;
import com.example.loan_service.exception.LoanNotFoundException;
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
    // private final LoanProducer loanProducer;

    @Override
    public Long createLoan(CreateLoanCommand command) {
        // Map từ Command sang Entity
        Loan loan = new Loan();
        loan.setBorrowerId(command.getBorrowerId());
        loan.setLoanAmount(command.getLoanAmount());
        loan.setInterestRate(command.getInterestRate());
        loan.setDurationMonths(command.getDurationMonths());

        // Set các dữ liệu hệ thống tự sinh
        loan.setLoanStatus("PENDING");
        loan.setCreatedDate(LocalDateTime.now());
        loan.setLoanNumber("LN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        Loan savedLoan = loanRepository.save(loan);

        // publish event LOAN_CREATED nếu cần (chỉ bắn event, ko return data)

        return savedLoan.getLoanId();
    }

    @Override
    public void approveLoan(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));

        // Validate Logic nghiệp vụ
        if (!"PENDING".equals(loan.getLoanStatus())) {
            throw new IllegalStateException("Loan is not in PENDING state. Current state: " + loan.getLoanStatus());
        }

        loan.setLoanStatus("APPROVED");
        loanRepository.save(loan);

        // publish event LOAN_APPROVED ở đây
    }

    @Override
    public void disburseLoan(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));

        if (!"APPROVED".equals(loan.getLoanStatus())) {
            throw new IllegalStateException("Loan must be APPROVED before disbursement. Current state: " + loan.getLoanStatus());
        }

        loan.setLoanStatus("DISBURSED");
        loan.setDisbursementDate(LocalDateTime.now());

        loanRepository.save(loan);

        // loanProducer.sendEvent(new LoanDisbursedEvent(id, loan.getBorrowerId(), loan.getAmount()));
    }
}