package com.example.student_service.serviceimpl;

import com.example.student_service.dto.StudentRequest;
import com.example.student_service.dto.StudentResponse;
import com.example.student_service.entity.Student;
import com.example.student_service.exception.StudentNotFoundexception;
import com.example.student_service.map.StudentMapper;
import com.example.student_service.repository.StudentRepository;
import com.example.student_service.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
    Student student = studentMapper.toEntity(studentRequest);
      return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    public StudentResponse updateStudent(StudentRequest studentRequest, Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundexception("Student is not found"));
        studentMapper.updateEntityFromDto(studentRequest,student);
        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
    Student student = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundexception("Student is not found"));
    student.setActive(false);
    studentRepository.save(student);
    }

    @Override
    public StudentResponse getStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundexception("Student is not found"));
        return studentMapper.toResponse(student);
    }

    @Override
    public List<StudentResponse> getALlActiveStudents() {
        List<StudentResponse> studentResponses = new ArrayList<>();
        List<Student> students = studentRepository.findByActiveTrue();
        for( Student student : students) {
            studentResponses.add(studentMapper.toResponse(student));
        }
        return studentResponses;
    }
}
