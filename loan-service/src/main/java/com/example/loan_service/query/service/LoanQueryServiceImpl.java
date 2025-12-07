package com.example.loan_service.query.service;

import com.example.loan_service.dto.LoanResponse;
import com.example.loan_service.entity.Loan;
import com.example.loan_service.exception.LoanNotFoundException;
import com.example.loan_service.map.LoanMapper;
import com.example.loan_service.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoanQueryServiceImpl implements LoanQueryService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

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
                .toList();
    }
}
