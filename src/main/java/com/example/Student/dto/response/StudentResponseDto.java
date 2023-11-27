package com.example.Student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {

    private Long id;

    private String name;

    private int age;

    private boolean success;

    private String message;

}
