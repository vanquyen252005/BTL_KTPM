package com.example.staff_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;

    @Column(unique = true, nullable = false)
    private String staffCode;

    private String fullName;
    private String email;
    private String phoneNumber;
    private String department;
    private String status;
}