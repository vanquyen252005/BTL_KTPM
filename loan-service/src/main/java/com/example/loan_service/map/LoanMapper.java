package com.example.loan_service.map;

import com.example.loan_service.dto.LoanRequest;
import com.example.loan_service.dto.LoanResponse;
import com.example.loan_service.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {
    // Khi tạo mới, bỏ qua ID, Status, Date (Service sẽ tự set)
    @Mapping(target = "loanId", ignore = true)
    @Mapping(target = "loanNumber", ignore = true)
    @Mapping(target = "loanStatus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "disbursementDate", ignore = true)
    Loan toEntity(LoanRequest request);

    LoanResponse toResponse(Loan loan);
}