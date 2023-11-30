package com.example.Student.service;

import com.example.Student.dto.request.StudentRequestDto;
import com.example.Student.dto.response.StudentResponseDto;
import com.example.Student.entity.Student;
import com.example.Student.exception.StudentNotFoundException;
import com.example.Student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        return studentRepository.save(student);
    }

    public StudentResponseDto<List<Student>> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        return new StudentResponseDto<>(students);
    }

    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student does not exist"));
    }

    public Student updateStudent(Long id, StudentRequestDto studentRequestDto) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student does not exist"));
        if (existingStudent != null) {
            existingStudent.setName(studentRequestDto.getName());
            existingStudent.setAge(studentRequestDto.getAge());
            return studentRepository.save(existingStudent);
        }
        return null;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

