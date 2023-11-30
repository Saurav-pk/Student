package com.example.Student.controller;

import com.example.Student.dto.request.StudentRequestDto;
import com.example.Student.dto.response.StudentResponseDto;
import com.example.Student.entity.Student;
import com.example.Student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    void testCreateStudent() {
        StudentRequestDto requestDto = new StudentRequestDto("John Doe", 21);
        Student createdStudent = new Student(1, "John Doe", 21);
        when(studentService.createStudent(requestDto)).thenReturn(createdStudent);
        ResponseEntity<StudentResponseDto> response = studentController.createStudent(requestDto);
        verify(studentService).createStudent(requestDto);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    void testFindAllStudents() {
        List<Student> students = Arrays.asList(new Student(1, "John", 22), new Student(2, "Jane", 23));
        when(studentService.findAllStudents()).thenReturn(new StudentResponseDto<>(students));
        List<StudentResponseDto> response = studentController.findAllStudents();
        verify(studentService).findAllStudents();
        assertEquals(2, response.size());
    }

    @Test
    void testFindStudentById() {
        Integer studentId = 1;
        Student student = new Student(studentId, "John", 21);
        when(studentService.findStudentById(Long.valueOf(studentId))).thenReturn(student);
        StudentResponseDto response = studentController.findStudentById(Long.valueOf(studentId));
        verify(studentService).findStudentById(Long.valueOf(studentId));
        assertEquals("John", response.getName());
    }

    @Test
    void testUpdateStudent() {
        long studentId = 1L;
        StudentRequestDto requestDto = new StudentRequestDto("Updated John", 23);
        Student updatedStudent = new Student((int) studentId, "Updated John", 23);
        when(studentService.updateStudent(studentId, requestDto)).thenReturn(updatedStudent);
        StudentResponseDto response = studentController.updateStudent(studentId, requestDto);
        verify(studentService).updateStudent(studentId, requestDto);
        assertEquals("Updated John", response.getName());
    }

    @Test
    void testDeleteStudent() {
        long studentId = 1L;
        studentController.deleteStudent(studentId);
        verify(studentService).deleteStudent(studentId);
    }
}
