package com.example.auth_service.controller;

import com.example.auth_service.Service.EmailService;
import com.example.auth_service.entity.MyAppUser;
import com.example.auth_service.repository.MyAppUserRepository;
import com.example.auth_service.utils.JwtTokenUtil;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RegistrationController {
    @Autowired
    private MyAppUserRepository myAppUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;


    @PostMapping(value = "/req/signup", consumes = "application/json")
    public ResponseEntity<String> createUser(@RequestBody MyAppUser user){

        MyAppUser existingAppUser = myAppUserRepository.findByEmail(user.getEmail());

        if(existingAppUser != null){
            if(existingAppUser.isVerified()){
                return new ResponseEntity<>("User Already exist and Verified.",HttpStatus.BAD_REQUEST);
            }else{
                String verificationToken = JwtTokenUtil.generateToken(existingAppUser.getEmail());
                existingAppUser.setVerificationToken(verificationToken);
                myAppUserRepository.save(existingAppUser);
                //Send Email Code
                emailService.sendVerificationEmail(existingAppUser.getEmail(), verificationToken);
                return new ResponseEntity<>("Verification Email resent. Check your inbox",HttpStatus.OK);
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String verificationToken =JwtTokenUtil.generateToken(user.getEmail());
        user.setVerificationToken(verificationToken);
        myAppUserRepository.save(user);
        //Send Email Code
        emailService.sendVerificationEmail(user.getEmail(), verificationToken);

        return new ResponseEntity<>("Registration successfully ! Please Verify your Email", HttpStatus.OK);
    }
}
