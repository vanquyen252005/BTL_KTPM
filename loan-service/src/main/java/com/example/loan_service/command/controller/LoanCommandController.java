package com.example.loan_service.command.controller;

import com.example.loan_service.command.service.LoanCommandService;
import com.example.loan_service.dto.LoanRequest;
import com.example.loan_service.dto.LoanResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanCommandController {

    private final LoanCommandService loanCommandService;

    // Tạo hồ sơ vay
    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(@RequestBody @Valid LoanRequest request) {
        return new ResponseEntity<>(loanCommandService.createLoan(request), HttpStatus.CREATED);
    }

    // Duyệt hồ sơ (APPROVED)
    @PutMapping("/{id}/approve")
    public ResponseEntity<LoanResponse> approveLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanCommandService.approveLoan(id));
    }

    // Giải ngân (DISBURSED)
    @PutMapping("/{id}/disburse")
    public ResponseEntity<LoanResponse> disburseLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanCommandService.disburseLoan(id));
    }
}
