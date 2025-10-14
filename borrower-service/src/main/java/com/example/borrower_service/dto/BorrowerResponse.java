package com.example.borrower_service.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BorrowerResponse {
    private Long borrowerId;
    private String borrowerLastName;
    private String borrowerFirstName;
    private String borrowerEmail;
    private String borrowerContactNumber;
    private String borrowerAddress;
    private String createdBorrower;// thong tin quan tri noi bo
    private LocalDate createdAt;
    private String lastModifiedBorrower;//thong tin quan tri noi bo
    private LocalDate lastModifiedDateTime;
    private LocalDate updatedAt;
    private boolean active;
}
