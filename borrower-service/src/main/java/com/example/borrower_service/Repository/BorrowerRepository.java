package com.example.borrower_service.Repository;

import com.example.borrower_service.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    Optional<Borrower> findByBorrowerEmail(String borrowerEmail);
    List<Borrower> findByActiveTrue();
}
