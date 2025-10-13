package com.example.vendor_service.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class VendorResponse {
    private Long vendorId;
    private String vendorName;
    private String vendorContactNumber;
    private String vendorCompany;
    private String createdVendor;
    private LocalDate lastModifiedDateTime;
    private String lastModifiedVendor;
    private boolean active;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
