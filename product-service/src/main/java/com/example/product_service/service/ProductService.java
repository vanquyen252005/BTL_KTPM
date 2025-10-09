package com.example.product_service.service;

import com.example.product_service.dto.Product.ProductRequest;
import com.example.product_service.dto.Product.ProductResponse;
import com.example.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse updateProduct(ProductRequest productRequest,Long id);
    void deleteProduct(Long id);
    ProductResponse getProduct(Long id);
    List<ProductResponse> getALlActiveProducts();

}
