package com.example.category_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CategoryResponse {

    private Long categoryId;
    private String categoryName;

    // Thông tin Audit
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}