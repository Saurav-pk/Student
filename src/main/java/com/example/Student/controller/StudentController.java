package com.example.Student.controller;

import com.example.Student.dto.request.CreateStudentRequestDto;
import com.example.Student.dto.request.UpdateStudentRequestDto;
import com.example.Student.dto.response.StudentResponseDto;
import com.example.Student.entity.Student;
import com.example.Student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "createStudent")
    public StudentResponseDto createStudent(@RequestBody CreateStudentRequestDto createStudentRequestDto) {
        Student createdStudent = studentService.createStudent(createStudentRequestDto);
        return convertToStudentResponseDto(createdStudent);
    }

    @GetMapping(value = "listStudents")
    public List<StudentResponseDto> findAllStudents() {
        List<Student> students = studentService.findAllStudents();
        return students.stream()
                .map(this::convertToStudentResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentResponseDto findStudentById(@PathVariable Long id) {
        Student student = studentService.findStudentById(id);
        return convertToStudentResponseDto(student);
    }

    @PutMapping("update/{id}")
    public StudentResponseDto updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequestDto updateStudentRequestDto) {
        Student updatedStudent = studentService.updateStudent(id, updateStudentRequestDto);
        return convertToStudentResponseDto(updatedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    private StudentResponseDto convertToStudentResponseDto(Student student) {
        if (student != null) {
            StudentResponseDto responseDto = new StudentResponseDto();
            responseDto.setId(student.getId());
            responseDto.setName(student.getName());
            responseDto.setAge(student.getAge());
            return responseDto;
        }
        return null;
    }
}
