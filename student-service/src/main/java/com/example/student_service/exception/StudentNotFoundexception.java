package com.example.student_service.exception;

public class StudentNotFoundexception extends RuntimeException{
    public StudentNotFoundexception(String message) {
        super(message);
    }
}
