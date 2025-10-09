package com.example.product_service.entity;

import com.example.product_service.dto.Product.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long productId;

    private int categoryId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    private String createdUser;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDateTime;

    private String lastModifiedUser;

    private double productBuyingPrice;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private String productName;

    private double productSellingPrice;

    private BigDecimal version;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Pricing> pricings;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Stock> stocks;

    private boolean isActive;

}
