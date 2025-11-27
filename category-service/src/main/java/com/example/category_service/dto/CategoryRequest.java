package com.example.category_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequest {

    @NotBlank(message = "Tên danh mục không được để trống")
    private String categoryName;

    @Size(max = 500, message = "Mô tả không được quá 500 ký tự")
    private String description;

}