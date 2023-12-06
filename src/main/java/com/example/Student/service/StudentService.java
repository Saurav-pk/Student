package com.example.Student.service;

import com.example.Student.dto.request.StudentRequestDto;
import com.example.Student.dto.response.StudentResponseDto;
import com.example.Student.entity.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(StudentRequestDto studentRequestDto);

    StudentResponseDto<List<Student>> findAllStudents();

    Student findStudentById(Long id);

    Student updateStudent(Long id, StudentRequestDto studentRequestDto);

    void deleteStudent(Long id);
}
