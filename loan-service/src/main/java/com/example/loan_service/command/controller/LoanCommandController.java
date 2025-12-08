package com.example.loan_service.command.controller;

import com.example.loan_service.command.service.LoanCommandService;
import com.example.loan_service.dto.command.CreateLoanCommand; // Giả sử bạn đã tách DTO
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

    @PostMapping
    public ResponseEntity<Long> createLoan(@RequestBody @Valid CreateLoanCommand command) {
        Long loanId = loanCommandService.createLoan(command);
        return new ResponseEntity<>(loanId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Void> approveLoan(@PathVariable Long id) {
        loanCommandService.approveLoan(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/disburse")
    public ResponseEntity<Void> disburseLoan(@PathVariable Long id) {
        loanCommandService.disburseLoan(id);
        return ResponseEntity.ok().build();
    }
}