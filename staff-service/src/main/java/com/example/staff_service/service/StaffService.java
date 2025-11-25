package com.example.staff_service.service;

import com.example.staff_service.dto.StaffRequest;
import com.example.staff_service.dto.StaffResponse;
import java.util.List;

public interface StaffService {
    StaffResponse createStaff(StaffRequest request);
    List<StaffResponse> getAllStaffs();
    StaffResponse getStaffById(Long id);
    void deleteStaff(Long id);
}