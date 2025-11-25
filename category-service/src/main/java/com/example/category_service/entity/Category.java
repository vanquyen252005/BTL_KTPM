package com.example.category_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @Column(nullable = false)
    private String categoryName;
    @Column(nullable = false)
    private LocalDate lastModifiedDateTime;
    @Column(length = 100)
    private String lastModifiedUser;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    @Column(nullable = false)
    private boolean active;
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.active = true;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
