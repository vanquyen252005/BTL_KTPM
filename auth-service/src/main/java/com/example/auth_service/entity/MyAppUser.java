package com.example.auth_service.entity;

import com.example.auth_service.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyAppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String verificationToken;
    private boolean isVerified;

    @Column(name = "reset_token")
    private String resetToken;
    @Enumerated(EnumType.STRING)
    private Role role;
}
