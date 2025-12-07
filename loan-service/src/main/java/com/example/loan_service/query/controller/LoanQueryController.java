package com.example.loan_service.query.controller;

import com.example.loan_service.dto.LoanResponse;
import com.example.loan_service.query.service.LoanQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanQueryController {

    private final LoanQueryService loanQueryService;

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanQueryService.getLoanById(id));
    }

    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAllLoans() {
        return ResponseEntity.ok(loanQueryService.getAllLoans());
    }
}
