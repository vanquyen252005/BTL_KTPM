package com.example.product_service.controller;

import com.example.product_service.dto.Product.ProductRequest;
import com.example.product_service.dto.Product.ProductResponse;
import com.example.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public String listProducts(Model model) {
        List<ProductResponse> products = productService.getALlActiveProducts();
        model.addAttribute("products", products);
        return "Product/list";
    }

    // Hiển thị form thêm san pham
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("product", new ProductRequest());
        return "Product/form";
    }

    // Xử lý form thêm san pham
    @PostMapping
    public String addProduct(@Valid @ModelAttribute("product") ProductRequest productRequest,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Product/form";
        }
        productService.createProduct(productRequest);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ProductResponse product = productService.getProduct(id);
        ProductRequest request = new ProductRequest();
        request.setProductBuyingPrice(product.getProductBuyingPrice());
        request.setProductSellingPrice(product.getProductSellingPrice());
        request.setProductType(product.getProductType());
        model.addAttribute("product", request);
        model.addAttribute("productId", id);
        return "Product/edit-form";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @Valid @ModelAttribute("product") ProductRequest request,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Product/edit-form";
        }
        productService.updateProduct(request, id);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
