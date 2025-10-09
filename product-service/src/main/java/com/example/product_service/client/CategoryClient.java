package com.example.product_service.client;

import com.example.product_service.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "category-service")
public interface CategoryClient {
    @GetMapping("/categories/{id}")
    CategoryDto getCategoryById(@PathVariable("id") int id);
}
