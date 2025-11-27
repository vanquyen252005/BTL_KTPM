package com.example.category_service.map;

import com.example.category_service.dto.CategoryRequest;
import com.example.category_service.dto.CategoryResponse;
import com.example.category_service.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequest borrowerRequest);

    CategoryResponse toResponse(Category category);

    void updateEntityFromDto(CategoryRequest categoryRequest, @MappingTarget Category category);
}
