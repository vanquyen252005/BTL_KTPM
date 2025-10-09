package com.example.product_service.dto.Stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockResponse {
    private Long stockId;
    private Long vendorId;
    private int quantity;
}
