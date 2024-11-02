package com.example.courseregistration.Controller;

import com.example.courseregistration.Dto.Enrollment.EnrollmentRequestDto;
import com.example.courseregistration.Dto.Enrollment.EnrollmentResponseDto;
import com.example.courseregistration.Service.EnrollmentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // 수강신청 정보 등록
    @PostMapping
    public ResponseEntity<String> createEnrollment(@RequestBody EnrollmentRequestDto dto) {
        enrollmentService.createEnrollment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("신청이 완료되었습니다!");
    }

    // 수강신청 정보 삭제
    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable Long enrollmentId) {
        enrollmentService.deleteEnrollment(enrollmentId);
        return ResponseEntity.ok("신청이 취소되었습니다!");
    }

    // 모든 수강신청 조회
    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDto>> getAllEnrollments() {
        List<EnrollmentResponseDto> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    // 학번으로 수강신청내역 조회
    @GetMapping("/student/{studentNumber}")
    public ResponseEntity<List<EnrollmentResponseDto>> getEnrollmentsByStudentNumber(@PathVariable Long studentNumber) {
        List<EnrollmentResponseDto> enrollments = enrollmentService.getEnrollmentsByStudentNumber(studentNumber);
        return ResponseEntity.ok(enrollments);
    }

    // 수강신청 순번으로 신청내역 조회
    @GetMapping("/{enrollmentId}")
    public ResponseEntity<EnrollmentResponseDto> getEnrollmentById(@PathVariable Long enrollmentId) {
        EnrollmentResponseDto enrollment = enrollmentService.getEnrollmentById(enrollmentId);
        return ResponseEntity.ok(enrollment);
    }
}
