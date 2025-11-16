package com.example.product_service.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "pricing")  // tên bảng của bạn
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pricingId;

    private double unitPrice;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    private String createdUser;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDateTime;

    private String lastModifiedUser;

    private BigDecimal pricingDiscountPercentage;

    @Temporal(TemporalType.DATE)
    private Date pricingEffectiveDate;

    @Temporal(TemporalType.DATE)
    private Date pricingExpireDate;

    private BigDecimal version;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    // BẮT BUỘC: constructor rỗng
    public Pricing() {}

    // Getter + Setter (hoặc dùng Lombok @Data)
    // Nếu dùng Lombok:
    // @Data
}
