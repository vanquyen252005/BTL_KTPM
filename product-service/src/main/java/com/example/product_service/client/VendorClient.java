package com.example.product_service.client;

import com.example.product_service.dto.CategoryDto;
import com.example.product_service.dto.VendorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "vendor-service")
public interface VendorClient {
        @GetMapping("/vendors/{id}")
        VendorDto getCategoryById(@PathVariable("id") int id);
}
