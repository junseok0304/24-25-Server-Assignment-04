package com.example.courseregistration.Exception;

public class CourseException extends RuntimeException {
    public CourseException(String message) {
        super(message);
    }

    // 중복된 강의 예외처리
    public static CourseException alreadyExists() {
        return new CourseException("이미 존재하는 강의입니다.");
    }

    // 강의가 존재하지 않는 경우 예외처리
    public static CourseException notFound() {
        return new CourseException("존재하지 않는 강의입니다.");
    }
}
