package com.example.product_service.repository;

import com.example.product_service.entity.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingRepository extends JpaRepository<Pricing,Long> {
}
