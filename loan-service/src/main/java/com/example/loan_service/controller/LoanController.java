package com.example.loan_service.controller;

import com.example.loan_service.dto.LoanRequest;
import com.example.loan_service.dto.LoanResponse;
import com.example.loan_service.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    // Tạo hồ sơ vay
    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(@RequestBody @Valid LoanRequest request) {
        return new ResponseEntity<>(loanService.createLoan(request), HttpStatus.CREATED);
    }

    // Lấy chi tiết
    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    // Lấy tất cả (Dành cho Admin xem)
    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    // Duyệt hồ sơ (Chuyển trạng thái sang APPROVED)
    @PutMapping("/{id}/approve")
    public ResponseEntity<LoanResponse> approveLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.approveLoan(id));
    }

    // Giải ngân (Chuyển trạng thái sang DISBURSED + Set ngày)
    @PutMapping("/{id}/disburse")
    public ResponseEntity<LoanResponse> disburseLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.disburseLoan(id));
    }
}
