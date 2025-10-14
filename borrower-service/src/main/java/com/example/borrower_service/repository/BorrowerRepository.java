package com.example.borrower_service.repository;

import com.example.borrower_service.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    Optional<Borrower> findByBorrowerEmail(String borrowerEmail);
    List<Borrower> findByActiveTrue();
}
