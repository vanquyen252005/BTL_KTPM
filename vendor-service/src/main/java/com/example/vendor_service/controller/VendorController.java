package com.example.vendor_service.controller;

import com.example.vendor_service.dto.VendorRequest;
import com.example.vendor_service.dto.VendorResponse;
import com.example.vendor_service.service.VendorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vendors")
public class VendorController {

    private final VendorService vendorService;

    // ✅ GET all vendors
    @GetMapping
    public ResponseEntity<List<VendorResponse>> getAllVendors() {
        List<VendorResponse> vendors = vendorService.getAllActiveVendors();
        return ResponseEntity.ok(vendors);
    }

    // ✅ GET vendor by id
    @GetMapping("/{id}")
    public ResponseEntity<VendorResponse> getVendorById(@PathVariable Long id) {
        VendorResponse vendor = vendorService.getVendor(id);
        return ResponseEntity.ok(vendor);
    }

    // ✅ POST create new vendor
    @PostMapping
    public ResponseEntity<VendorResponse> createVendor(@Valid @RequestBody VendorRequest vendorRequest) {
        VendorResponse created = vendorService.createVendor(vendorRequest);
        return ResponseEntity.ok(created);
    }

    // ✅ PUT update vendor
    @PutMapping("/{id}")
    public ResponseEntity<VendorResponse> updateVendor(@PathVariable Long id,
                                                       @Valid @RequestBody VendorRequest vendorRequest) {
        VendorResponse updated = vendorService.updateVendor(vendorRequest, id);
        return ResponseEntity.ok(updated);
    }

    // ✅ DELETE vendor (soft delete hoặc hard delete tuỳ service)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.ok("Vendor deleted successfully");
    }
}
