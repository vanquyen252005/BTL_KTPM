package com.example.base_domains.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowerEvent {
    private String message;
    private String borrowerID;
    private String productID;
    private int quantity;
}
