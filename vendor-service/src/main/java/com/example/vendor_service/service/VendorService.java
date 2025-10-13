package com.example.vendor_service.service;

import com.example.vendor_service.dto.VendorRequest;
import com.example.vendor_service.dto.VendorResponse;

import java.util.List;

public interface VendorService {
    VendorResponse createVendor(VendorRequest vendorRequest);
    VendorResponse updateVendor(VendorRequest vendorRequest, Long id);
    void deleteVendor(Long id);
    VendorResponse getVendor(Long id);
    List<VendorResponse> getAllActiveVendors();
}
