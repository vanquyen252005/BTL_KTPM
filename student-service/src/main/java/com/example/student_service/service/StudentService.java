package com.example.student_service.service;

import com.example.student_service.dto.StudentRequest;
import com.example.student_service.dto.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentRequest studentRequest);
    StudentResponse updateStudent(StudentRequest studentRequest,Long id);
    void deleteStudent(Long id);
    StudentResponse getStudent(Long id);
    List<StudentResponse>  getALlActiveStudents();

}
