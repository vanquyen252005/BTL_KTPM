package com.example.product_service.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private Long productId;
    private double productBuyingPrice;
    private double productSellingPrice;
    private ProductType productType;
    private String categoryName;
}
