package com.example.auth_service;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


@Controller
public class LoginController {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/login/success")
    public String loginSuccess(OAuth2AuthenticationToken authentication) throws ParseException {
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());

        String accessTokenValue = client.getAccessToken().getTokenValue();

        JWT jwt = JWTParser.parse(accessTokenValue);
        Map<String, Object> claims = jwt.getJWTClaimsSet().getClaims();

        Map<String, Object> resourceAccess = (Map<String, Object>) claims.get("resource_access");
        Map<String, Object> clientAccess = (Map<String, Object>) resourceAccess.get("ktx-client");
        List<String> roles = (List<String>) clientAccess.get("roles");

        System.out.println("Extracted roles: " + roles);

        if (roles.contains("ADMIN")) {
            return "redirect:http://localhost:8082/dashboard/admin";
        } else if (roles.contains("STAFF")) {
            return "redirect:http://localhost:8083/dashboard/staff";
        } else if (roles.contains("CUSTOMER")) {
            return "redirect:http://localhost:8084/dashboard/customer";
        }

        return "redirect:/access-denied";
    }
}