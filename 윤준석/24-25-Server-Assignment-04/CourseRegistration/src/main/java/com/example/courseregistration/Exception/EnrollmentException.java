package com.example.courseregistration.Exception;

public class EnrollmentException extends RuntimeException {
    public EnrollmentException(String message) {
        super(message);
    }

    public static EnrollmentException notFound(String message) {
        return new EnrollmentException(message);
    }

    public static EnrollmentException alreadyExists(String message) {
        return new EnrollmentException(message);
    }
}
