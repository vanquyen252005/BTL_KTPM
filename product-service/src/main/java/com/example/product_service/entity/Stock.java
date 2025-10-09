package com.example.product_service.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.util.Date;

public class Stock {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long stockId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    private String createdUser;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDateTime;

    private String lastModifiedUser;

    private int quantity;

    private BigDecimal version;
    private Long vendorId;
    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;
}
