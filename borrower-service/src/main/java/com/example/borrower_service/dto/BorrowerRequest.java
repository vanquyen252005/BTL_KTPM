package com.example.borrower_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BorrowerRequest {
    @NotBlank(message = "Ho va ten khong duoc de trong")
    private String borrowerLastName;
    @NotBlank(message = "Ho va ten khong duoc de trong")
    private String borrowerFirstName;
    @NotBlank(message = "Ho va ten khong duoc de trong")
    private String borrowerName;
    @Email(message = "Email khong hop le")
    @NotBlank(message = "Email khong duoc de trong")
    private String borrowerEmail;
    private String borrowerContactNumber;
    private String borrowerAddress;
    private String createdBorrower;
    private String lastModifiedBorrower;
}
