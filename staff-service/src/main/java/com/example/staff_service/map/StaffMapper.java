package com.example.staff_service.map;

import com.example.staff_service.dto.StaffRequest;
import com.example.staff_service.dto.StaffResponse;
import com.example.staff_service.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    @Mapping(target = "staffId", ignore = true)
    @Mapping(target = "staffCode", ignore = true)
    // Nếu request có status thì map, không thì để service xử lý
    Staff toEntity(StaffRequest request);
    StaffResponse toResponse(Staff staff);
    void updateEntity(StaffRequest request, @MappingTarget Staff staff);
}