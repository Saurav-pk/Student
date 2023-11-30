package com.example.Student.controller;

import com.example.Student.dto.request.StudentRequestDto;
import com.example.Student.dto.response.StudentResponseDto;
import com.example.Student.entity.Student;
import com.example.Student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/students")
public class StudentController {


    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping(value = "createStudent")
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody StudentRequestDto studentRequestDto) {
        Student createdStudent = studentService.createStudent(studentRequestDto);
        StudentResponseDto responseDto = convertToStudentResponseDto(createdStudent);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "listStudents")
    public List<StudentResponseDto> findAllStudents() {
        StudentResponseDto<List<Student>> studentResponseDto = studentService.findAllStudents();
        List<Student> students = studentResponseDto.getData();
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
    public StudentResponseDto updateStudent(@PathVariable Long id, @RequestBody StudentRequestDto studentRequestDto) {
        Student updatedStudent = studentService.updateStudent(id, studentRequestDto);
        return convertToStudentResponseDto(updatedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    private StudentResponseDto convertToStudentResponseDto(Student student) {
        if (student != null) {
            StudentResponseDto responseDto = new StudentResponseDto();
            responseDto.setId(Long.valueOf(student.getId()));
            responseDto.setName(student.getName());
            responseDto.setAge(student.getAge());
            return responseDto;
        }
        return null;
    }
}
