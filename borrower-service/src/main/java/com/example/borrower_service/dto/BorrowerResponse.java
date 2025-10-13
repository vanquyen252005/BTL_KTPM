package com.example.borrower_service.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BorrowerResponse {
    private Long borrowerId;
    private String borrowerLastName;
    private String borrowerFirstName;
    private String borrowerName;
    private String borrowerEmail;
    private String borrowerContactNumber;
    private String borrowerAddress;
    private String createdBorrower;
    private LocalDate createdAt;
    private String lastModifiedBorrower;
    private LocalDate lastModifiedDateTime;
    private LocalDate updatedAt;
    private boolean active;

}
