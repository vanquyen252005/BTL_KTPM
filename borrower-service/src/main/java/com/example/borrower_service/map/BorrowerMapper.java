package com.example.borrower_service.map;

import com.example.borrower_service.dto.BorrowerRequest;
import com.example.borrower_service.dto.BorrowerResponse;
import com.example.borrower_service.entity.Borrower;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BorrowerMapper {

    Borrower toEntity(BorrowerRequest borrowerRequest);

    BorrowerResponse toResponse(Borrower borrower);

    void updateEntityFromDto(BorrowerRequest borrowerRequest, @MappingTarget Borrower borrower);
}
