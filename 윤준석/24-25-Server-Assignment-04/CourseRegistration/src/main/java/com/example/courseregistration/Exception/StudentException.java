// StudentException.java
package com.example.courseregistration.Exception;

public class StudentException extends RuntimeException {
    public StudentException(String message) {
        super(message);
    }

    // 중복된 학생 예외처리
    public static StudentException alreadyExists() {
        return new StudentException("이미 존재하는 학생입니다.");
    }

    // 학생이 존재하지 않는 예외처리
    public static StudentException notFound() {
        return new StudentException("존재하지 않는 학번입니다.");
    }

    // 동일한 이름 예외처리
    public static StudentException sameName() {
        return new StudentException("이미 동일한 이름입니다.");
    }
}
