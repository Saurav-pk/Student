package com.example.Student.service;

import com.example.Student.dto.request.CreateStudentRequestDto;
import com.example.Student.dto.request.UpdateStudentRequestDto;
import com.example.Student.entity.Student;
import com.example.Student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(CreateStudentRequestDto createStudentRequestDto) {
        Student student = new Student();
        student.setName(createStudentRequestDto.getName());
        student.setAge(createStudentRequestDto.getAge());
        return studentRepository.save(student);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Long id, UpdateStudentRequestDto updateStudentRequestDto) {
        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent != null) {
            existingStudent.setName(updateStudentRequestDto.getName());
            existingStudent.setAge(updateStudentRequestDto.getAge());
            return studentRepository.save(existingStudent);
        }

        return null;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
