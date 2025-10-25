package com.example.borrower_service.dto;

import com.example.borrower_service.entity.Borrower;
import com.example.product_service.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowRequest {
    private Borrower borrower;
    private Product product;
    private int quantity;
}
