package com.example.vendor_service.serviceimpl;

import com.example.vendor_service.dto.VendorRequest;
import com.example.vendor_service.dto.VendorResponse;
import com.example.vendor_service.entity.Vendor;
import com.example.vendor_service.exception.VendorNotFoundException;
import com.example.vendor_service.map.VendorMapper;
import com.example.vendor_service.repository.VendorRepository;
import com.example.vendor_service.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Override
    public VendorResponse createVendor(VendorRequest vendorRequest) {
        Vendor vendor = vendorMapper.toEntity(vendorRequest);
        return vendorMapper.toResponse(vendorRepository.save(vendor));
    }

    @Override
    public VendorResponse updateVendor(VendorRequest vendorRequest, Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found"));
        vendorMapper.updateEntityFromDto(vendorRequest, vendor);
        return vendorMapper.toResponse(vendorRepository.save(vendor));
    }

    @Override
    public void deleteVendor(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found"));
        vendor.setActive(false);
        vendorRepository.save(vendor);
    }

    @Override
    public VendorResponse getVendor(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found"));
        return vendorMapper.toResponse(vendor);
    }

    @Override
    public List<VendorResponse> getAllActiveVendors() {
        List<VendorResponse> vendorResponses = new ArrayList<>();
        List<Vendor> vendors = vendorRepository.findByActiveTrue();
        for (Vendor vendor : vendors) {
            vendorResponses.add(vendorMapper.toResponse(vendor));
        }
        return vendorResponses;
    }
}
