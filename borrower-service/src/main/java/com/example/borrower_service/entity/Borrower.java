package com.example.borrower_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "borrower")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowerId;
    @Column(nullable = false)
    private String borrowerLastName;
    @Column(nullable = false)
    private String borrowerFirstName;
    @Column(nullable = false)
    private String borrowerName;
    @Column(unique = true,nullable = false)
    private String borrowerEmail;
    private String borrowerContactNumber;
    private String borrowerAddress;
    @Column(length = 100)
    private String createdBorrower;
    private LocalDate lastModifiedDateTime;
    @Column(length = 100)
    private String lastModifiedBorrower;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    @Column(nullable = false)
    private boolean active;
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.active = true;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }
}