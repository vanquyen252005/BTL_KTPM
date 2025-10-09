package com.example.product_service.map;

import com.example.product_service.dto.Product.ProductRequest;
import com.example.product_service.dto.Product.ProductResponse;
import com.example.product_service.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequest productRequest);
    ProductResponse toResponse(Product product);
    void updateEntityFromDto(ProductRequest productRequest, @MappingTarget Product product);
}
