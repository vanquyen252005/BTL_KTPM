package com.example.vendor_service.repository;

import com.example.vendor_service.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    List<Vendor> findByActiveTrue();
}
