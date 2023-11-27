package com.example.Student.exception;

import lombok.Getter;

@Getter
public class StudentNotFoundException extends RuntimeException{

    private final String message;

    public StudentNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
