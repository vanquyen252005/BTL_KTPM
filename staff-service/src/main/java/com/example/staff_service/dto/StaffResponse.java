package com.example.staff_service.dto;

import lombok.Data;

@Data
public class StaffResponse {
    private Long staffId;
    private String staffCode;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String department;
    private String status;
}