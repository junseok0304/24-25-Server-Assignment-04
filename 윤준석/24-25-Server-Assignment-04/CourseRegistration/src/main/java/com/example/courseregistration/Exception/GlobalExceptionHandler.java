package com.example.courseregistration.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 학생 관련 예외 처리
    @ExceptionHandler(StudentException.class)
    public ResponseEntity<String> handleStudentException(StudentException ex) {
        HttpStatus status = ex.getMessage().contains("존재하지 않는") ? HttpStatus.NOT_FOUND : HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(ex.getMessage());
    }

    // 강의 관련 예외 처리
    @ExceptionHandler(CourseException.class)
    public ResponseEntity<String> handleCourseException(CourseException ex) {
        HttpStatus status = ex.getMessage().contains("존재하지 않는") ? HttpStatus.NOT_FOUND : HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(ex.getMessage());
    }

    // 수강신청 관련 예외 처리
    @ExceptionHandler(EnrollmentException.class)
    public ResponseEntity<String> handleEnrollmentException(EnrollmentException ex) {
        HttpStatus status = ex.getMessage().contains("존재하지 않는") ? HttpStatus.NOT_FOUND : HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(ex.getMessage());
    }
}
