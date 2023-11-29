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
public class StudentServicesTests {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void student_test_createStudent_returnsStudentDto(){

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
    public void testFindAllStudents() {
        // Arrange
        Student student1 = new Student();
        Student student2 = new Student();
        List<Student> studentList = Arrays.asList(student1, student2);

        // Mocking the behavior of the repository
        when(studentRepository.findAll()).thenReturn(studentList);

        // Act
        StudentResponseDto<List<Student>> result = studentService.findAllStudents();

        // Assert
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void testUpdateStudent() {
        Long id = 1L;

        // Create a sample student
        Student existingStudent = new Student(Math.toIntExact(id), "John Doe", 25);

        // Create a StudentRequestDto for updating the student
        StudentRequestDto requestDto = new StudentRequestDto("Updated Name", 30);

        // Mock the behavior of the findById method
        when(studentRepository.findById(id)).thenReturn(Optional.ofNullable(existingStudent));

        // Mock the behavior of the save method
        when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> {
            Student updatedStudent = invocation.getArgument(0);
            return updatedStudent;
        });

        // Call the updateStudent method
        Student updatedStudent = studentService.updateStudent(id, requestDto);

        // Verify that findById and save methods were called with the expected arguments
        verify(studentRepository).findById(id);
        verify(studentRepository).save(existingStudent);

        // Assertions
        assertNotNull(updatedStudent);
        assertEquals(requestDto.getName(), updatedStudent.getName());
        assertEquals(requestDto.getAge(), updatedStudent.getAge());
    }

    @Test
    public void testDeleteStudent() {
        Long id = 1L;
        doNothing().when(studentRepository).deleteById(id);
        studentService.deleteStudent(id);
        verify(studentRepository, times(1)).deleteById(id);
    }
}
