package com.example.loan_service.service.impl;

import com.example.loan_service.dto.LoanRequest;
import com.example.loan_service.dto.LoanResponse;
import com.example.loan_service.entity.Loan;
import com.example.loan_service.exception.LoanNotFoundException;
import com.example.loan_service.map.LoanMapper;
import com.example.loan_service.repository.LoanRepository;
import com.example.loan_service.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    // private final LoanProducer loanProducer; // Uncomment khi bạn đã tạo Kafka Producer

    @Override
    public LoanResponse createLoan(LoanRequest request) {
        Loan loan = loanMapper.toEntity(request);

        loan.setLoanStatus("PENDING");
        loan.setCreatedDate(LocalDateTime.now());
        loan.setLoanNumber("LN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        Loan savedLoan = loanRepository.save(loan);

        // Có thể bắn Kafka event "LOAN_CREATED" ở đây nếu cần
        return loanMapper.toResponse(savedLoan);
    }

    @Override
    public LoanResponse getLoanById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));
        return loanMapper.toResponse(loan);
    }

    @Override
    public List<LoanResponse> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(loanMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoanResponse approveLoan(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        //Chỉ được duyệt khi đang PENDING
        if (loan.getLoanStatus() != "PENDING") {
            throw new RuntimeException("Loan is not in PENDING state");
        }

        loan.setLoanStatus("APPROVED");
        return loanMapper.toResponse(loanRepository.save(loan));
    }

    @Override
    public LoanResponse disburseLoan(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        //Chỉ giải ngân khi đã APPROVED
        if (loan.getLoanStatus() != "APPROVED") {
            throw new RuntimeException("Loan must be APPROVED before disbursement");
        }

        loan.setLoanStatus("DISBURSED");
        loan.setDisbursementDate(LocalDateTime.now());

        Loan savedLoan = loanRepository.save(loan);

        // kafka send event
        // loanProducer.sendEvent(new LoanEvent("LOAN_DISBURSED", loan.getBorrowerId(), loan.getAmount()));

        return loanMapper.toResponse(savedLoan);
    }
}