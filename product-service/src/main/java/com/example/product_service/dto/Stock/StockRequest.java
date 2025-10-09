package com.example.product_service.dto.Stock;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class StockRequest {
    private Long stockId;

    private Date createdDateTime;

    private String createdUser;

    private Date lastModifiedDateTime;

    private String lastModifiedUser;

    private int quantity;

    private BigDecimal version;
    private Long vendorId;
}
