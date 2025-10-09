package com.example.student_service.controller;

import com.example.student_service.dto.StudentRequest;
import com.example.student_service.dto.StudentResponse;
import com.example.student_service.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
private final StudentService studentService;
    @GetMapping
    public String listStudents(Model model) {
        List<StudentResponse> students = studentService.getALlActiveStudents();
        model.addAttribute("students", students);
        return "list";  // Tệp: templates/student/list.html
    }

    // Hiển thị form thêm sinh viên
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new StudentRequest());
        return "form";  // Tệp: templates/student/form.html
    }

    // Xử lý form thêm sinh viên
    @PostMapping
    public String addStudent(@Valid @ModelAttribute("student") StudentRequest studentRequest,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "form";
        }
        studentService.createStudent(studentRequest);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        StudentResponse student = studentService.getStudent(id);
        StudentRequest request = new StudentRequest();
        request.setFullName(student.getFullName());
        request.setEmail(student.getEmail());
        request.setPhone(student.getPhone());
        request.setGender(student.getGender());
        request.setAddress(student.getAddress());
        request.setDateOfBirth(student.getDateOfBirth());
        model.addAttribute("student", request);
        model.addAttribute("studentId", id);
        return "edit-form";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute("student") StudentRequest request,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-form";
        }
        studentService.updateStudent(request, id);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
