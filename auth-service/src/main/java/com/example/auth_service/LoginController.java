package com.example.auth_service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginController {
    @GetMapping("/login/success")
    public String loginSuccess(OAuth2AuthenticationToken authentication) {
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        if (roles.contains("ROLE_ADMIN")) {
            return "redirect:http://localhost:8082/dashboard/admin"; // admin-portal-service
        } else if (roles.contains("ROLE_STAFF")) {
            System.out.println("Toi la staff");
            return "redirect:http://localhost:8083/dashboard/staff"; // staff-portal-service
        } else if (roles.contains("ROLE_CUSTOMER")) {
            return "redirect:http://localhost:8084/dashboard/customer"; // customer-portal-service
        }
        return "redirect:/error";
    }
}
