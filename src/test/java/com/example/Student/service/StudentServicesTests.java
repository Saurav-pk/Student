package com.example.Student.service;

import com.example.Student.dto.request.StudentRequestDto;
import com.example.Student.dto.response.StudentResponseDto;
import com.example.Student.entity.Student;
import com.example.Student.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServicesTests {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;


    @Test
    void testFindStudentById() {
        Integer studentId = 1;
        Student expectedStudent = new Student(studentId, "John Doe", 21);
        when(studentRepository.findById(Long.valueOf(studentId))).thenReturn(Optional.of(expectedStudent));
        Student result = studentService.findStudentById(Long.valueOf(studentId));
        verify(studentRepository).findById(Long.valueOf(studentId));
        assertEquals(expectedStudent, result);
    }
    @Test
    void testCreateStudent(){

        Student student = Student.builder()
                .name("Saurav")
                .age(22).build();
       StudentRequestDto studentRequestDto = StudentRequestDto.builder()
                .name("Saurav")
                .age(22).build();
        when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        Student savedStudent = studentService.createStudent(studentRequestDto);
        Assertions.assertThat(savedStudent).isNotNull();
    }

    @Test
    void testFindAllStudents() {
        Student student1 = new Student();
        Student student2 = new Student();
        List<Student> studentList = Arrays.asList(student1, student2);
        when(studentRepository.findAll()).thenReturn(studentList);
        StudentResponseDto<List<Student>> result = studentService.findAllStudents();
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    void testUpdateStudent() {
        Long id = 1L;
        Student existingStudent = new Student(Math.toIntExact(id), "John Doe", 25);
        StudentRequestDto requestDto = new StudentRequestDto("Updated Name", 30);
        when(studentRepository.findById(id)).thenReturn(Optional.ofNullable(existingStudent));
        when(studentRepository.save(Mockito.any(Student.class))).thenAnswer(invocation -> {
            Student updatedStudent = invocation.getArgument(0);
            return updatedStudent;
        });
        Student updatedStudent = studentService.updateStudent(id, requestDto);
        verify(studentRepository).findById(id);
        verify(studentRepository).save(existingStudent);
        assertNotNull(updatedStudent);
        assertEquals(requestDto.getName(), updatedStudent.getName());
        assertEquals(requestDto.getAge(), updatedStudent.getAge());
    }

    @Test
    void testDeleteStudent() {
        Long id = 1L;
        doNothing().when(studentRepository).deleteById(id);
        studentService.deleteStudent(id);
        verify(studentRepository, times(1)).deleteById(id);
    }
}
