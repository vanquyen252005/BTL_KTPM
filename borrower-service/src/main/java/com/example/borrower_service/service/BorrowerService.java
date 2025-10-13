package com.example.borrower_service.service;

import com.example.borrower_service.dto.BorrowerRequest;
import com.example.borrower_service.dto.BorrowerResponse;

import java.util.List;

public interface BorrowerService {
    BorrowerResponse createBorrower(BorrowerRequest borrowerRequest);
    BorrowerResponse updateBorrower(BorrowerRequest borrowerRequest, Long id);
    void deleteBorrower(Long id);
    BorrowerResponse getBorrower(Long id);
    List<BorrowerResponse> getAllActiveBorrowers();
}
