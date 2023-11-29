package com.example.Student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto<T> {

    private Long id;

    private String name;

    private int age;

    private boolean success;

    private String message;

    private T data;

    public StudentResponseDto(T data) {
        this.data = data;
    }

}
