package com.example.loan_service.query.service;

import com.example.loan_service.dto.query.LoanDetailView;
import com.example.loan_service.dto.query.LoanSummaryView;
import com.example.loan_service.entity.Loan;
import com.example.loan_service.exception.LoanNotFoundException;
import com.example.loan_service.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // Tối ưu hiệu năng cho thao tác Đọc
public class LoanQueryServiceImpl implements LoanQueryService {

    private final LoanRepository loanRepository;

    @Override
    public LoanDetailView getLoanById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));

        return mapToDetailView(loan);
    }

    @Override
    public List<LoanSummaryView> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(this::mapToSummaryView)
                .toList();
    }


    private LoanDetailView mapToDetailView(Loan loan) {
        LoanDetailView view = new LoanDetailView();
        view.setLoanId(loan.getLoanId()); // Chú ý: Entity thường là getId(), DTO bạn đặt là loanId
        view.setLoanNumber(loan.getLoanNumber());
        view.setBorrowerId(loan.getBorrowerId());
        view.setLoanAmount(loan.getLoanAmount()); // Entity thường đặt tên là amount
        view.setInterestRate(loan.getInterestRate());
        view.setDurationMonths(loan.getDurationMonths());
        view.setLoanStatus(loan.getLoanStatus());
        view.setCreatedDate(loan.getCreatedDate());
        view.setDisbursementDate(loan.getDisbursementDate());
        return view;
    }

    private LoanSummaryView mapToSummaryView(Loan loan) {
        LoanSummaryView view = new LoanSummaryView();
        view.setLoanId(loan.getLoanId());
        view.setLoanNumber(loan.getLoanNumber());
        view.setBorrowerId(loan.getBorrowerId());
        view.setLoanAmount(loan.getLoanAmount());
        view.setDurationMonths(loan.getDurationMonths());
        view.setLoanStatus(loan.getLoanStatus());
        return view;
    }
}