package com.example.product_service.serviceImpl;

import com.example.product_service.dto.Product.ProductRequest;
import com.example.product_service.dto.Product.ProductResponse;
import com.example.product_service.entity.Product;
import com.example.product_service.exception.ProductNotFoundException;
import com.example.product_service.map.ProductMapper;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product is not found"));
        productMapper.updateEntityFromDto(productRequest,product);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product is not found"));
        product.setActive(false);
        productRepository.save(product);
    }

    @Override
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product is not found"));
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getALlActiveProducts() {
        List<ProductResponse> productResponses = new ArrayList<>();
        List<Product> products = productRepository.findByActiveTrue();
        for( Product product : products) {
           productResponses.add(productMapper.toResponse(product));
        }
        return productResponses;
    }
}
