package com.example.vendor_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VendorRequest {

    @NotBlank(message = "Tên nhà cung cấp không được để trống")
    private String vendorName;
    @NotBlank(message = "Số điện thoại nhà cung cấp không được để trống")
    private String vendorContactNumber;
    @NotBlank(message = "Tên công ty nhà cung cấp không được để trống")
    private String vendorCompany;
    private String createdVendor;
    private String lastModifiedVendor;
}
