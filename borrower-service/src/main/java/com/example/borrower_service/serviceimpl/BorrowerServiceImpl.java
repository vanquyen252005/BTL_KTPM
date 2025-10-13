package com.example.borrower_service.serviceimpl;

import com.example.borrower_service.dto.BorrowerRequest;
import com.example.borrower_service.dto.BorrowerResponse;
import com.example.borrower_service.entity.Borrower;
import com.example.borrower_service.exception.BorrowerNotFoundException;
import com.example.borrower_service.map.BorrowerMapper;
import com.example.borrower_service.repository.BorrowerRepository;
import com.example.borrower_service.service.BorrowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowerServiceImpl implements BorrowerService {

    private final BorrowerRepository borrowerRepository;
    private final BorrowerMapper borrowerMapper;

    @Override
    public BorrowerResponse createBorrower(BorrowerRequest borrowerRequest) {
        Borrower borrower = borrowerMapper.toEntity(borrowerRequest);
        return borrowerMapper.toResponse(borrowerRepository.save(borrower));
    }

    @Override
    public BorrowerResponse updateBorrower(BorrowerRequest borrowerRequest, Long id) {
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new BorrowerNotFoundException("Borrower not found"));
        borrowerMapper.updateEntityFromDto(borrowerRequest, borrower);
        return borrowerMapper.toResponse(borrowerRepository.save(borrower));
    }

    @Override
    public void deleteBorrower(Long id) {
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new BorrowerNotFoundException("Borrower not found"));
        borrower.setActive(false);
        borrowerRepository.delete(borrower);
    }

    @Override
    public BorrowerResponse getBorrower(Long id) {
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new BorrowerNotFoundException("Borrower not found"));
        return borrowerMapper.toResponse(borrower);
    }

    @Override
    public List<BorrowerResponse> getAllActiveBorrowers() {
        List<BorrowerResponse> borrowerResponses = new ArrayList<>();
        List<Borrower> borrowers = borrowerRepository.findAll();
        for (Borrower borrower : borrowers) {
            borrowerResponses.add(borrowerMapper.toResponse(borrower));
        }
        return borrowerResponses;
    }
}
