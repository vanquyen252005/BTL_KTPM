package com.example.vendor_service.map;

import com.example.vendor_service.dto.VendorRequest;
import com.example.vendor_service.dto.VendorResponse;
import com.example.vendor_service.entity.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VendorMapper {

    Vendor toEntity(VendorRequest vendorRequest);

    VendorResponse toResponse(Vendor vendor);

    void updateEntityFromDto(VendorRequest vendorRequest, @MappingTarget Vendor vendor);
}
