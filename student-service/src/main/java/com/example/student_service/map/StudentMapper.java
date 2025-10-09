package com.example.student_service.map;

import com.example.student_service.dto.StudentRequest;
import com.example.student_service.dto.StudentResponse;
import com.example.student_service.dto.StudentResponse;
import com.example.student_service.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toEntity(StudentRequest studentRequest);
    StudentResponse toResponse(Student student);
    void updateEntityFromDto(StudentRequest studentRequest, @MappingTarget Student student);

}
