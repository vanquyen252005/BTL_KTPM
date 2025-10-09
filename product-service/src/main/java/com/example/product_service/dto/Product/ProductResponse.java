package com.example.product_service.dto.Product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long productId;
    private String productName;
    private double productBuyingPrice;
    private double productSellingPrice;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private String categoryName;
}
