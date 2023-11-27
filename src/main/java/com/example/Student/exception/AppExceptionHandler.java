package com.example.Student.exception;

import com.example.Student.dto.response.StudentResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({StudentNotFoundException.class})
    public ResponseEntity<StudentResponseDto> handleCustomException(RuntimeException e){

        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setSuccess(false);
        responseDto.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

}
