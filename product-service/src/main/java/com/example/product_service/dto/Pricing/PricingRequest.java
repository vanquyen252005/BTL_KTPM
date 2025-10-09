package com.example.product_service.dto.Pricing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PricingRequest {
    private Long productId;
    private double unitPrice;
    private Date pricingEffectiveDate;
    private Date pricingExpireDate;
    private String createdUser;
    private BigDecimal pricingDiscountPercentage;
}
