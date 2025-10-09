package com.example.product_service.dto.Pricing;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PricingResponse {
    private Long pricingId;
    private Long productId;
    private double unitPrice;
}
