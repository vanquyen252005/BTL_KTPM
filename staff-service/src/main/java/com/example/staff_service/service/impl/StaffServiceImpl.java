package com.example.staff_service.service.impl;

import com.example.staff_service.dto.StaffRequest;
import com.example.staff_service.dto.StaffResponse;
import com.example.staff_service.entity.Staff;
import com.example.staff_service.map.StaffMapper;
import com.example.staff_service.repository.StaffRepository;
import com.example.staff_service.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    @Override
    public StaffResponse createStaff(StaffRequest request) {
        Staff staff = staffMapper.toEntity(request);

        staff.setStaffCode("STF-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase());

        // mặc định set là "ACTIVE"
        if (staff.getStatus() == null || staff.getStatus().isEmpty()) {
            staff.setStatus("ACTIVE");
        }

        return staffMapper.toResponse(staffRepository.save(staff));
    }

    @Override
    public List<StaffResponse> getAllStaffs() {
        return staffRepository.findAll().stream()
                .map(staffMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StaffResponse getStaffById(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với ID: " + id));

        return staffMapper.toResponse(staff);
    }

    @Override
    public void deleteStaff(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với ID: " + id));
        staffRepository.delete(staff);
        staff.setStatus("RESIGNED");
        staffRepository.save(staff);
    }
}