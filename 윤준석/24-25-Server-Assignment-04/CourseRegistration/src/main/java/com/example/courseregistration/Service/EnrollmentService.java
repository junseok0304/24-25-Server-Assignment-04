package com.example.courseregistration.Service;

import com.example.courseregistration.Domain.Course;
import com.example.courseregistration.Domain.Enrollment;
import com.example.courseregistration.Domain.Student;
import com.example.courseregistration.Dto.Enrollment.EnrollmentRequestDto;
import com.example.courseregistration.Dto.Enrollment.EnrollmentResponseDto;
import com.example.courseregistration.Exception.EnrollmentException;
import com.example.courseregistration.Repository.CourseRepository;
import com.example.courseregistration.Repository.EnrollmentRepository;
import com.example.courseregistration.Repository.StudentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    // 모든 수강신청 조회
    @Transactional(readOnly = true)
    public List<EnrollmentResponseDto> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(EnrollmentResponseDto::from)
                .collect(Collectors.toList());
    }

    // 수강신청 생성
    @Transactional
    public EnrollmentResponseDto createEnrollment(EnrollmentRequestDto dto) {
        Student student = studentRepository.findByStudentNumber(dto.getStudentNumber())
                .orElseThrow(() -> EnrollmentException.notFound("존재하지 않는 학생입니다."));
        Course course = courseRepository.findByCourseId(dto.getCourseId())
                .orElseThrow(() -> EnrollmentException.notFound("존재하지 않는 강의입니다."));

        if (enrollmentRepository.findByStudent_StudentNumberAndCourse_Id(student.getStudentNumber(), course.getId())
                .isPresent()) {
            throw EnrollmentException.alreadyExists("이미 수강신청한 강의입니다.");
        }

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .build();

        enrollmentRepository.save(enrollment);
        return EnrollmentResponseDto.from(enrollment);
    }

    // 특정 학생의 수강신청 목록 조회
    @Transactional(readOnly = true)
    public List<EnrollmentResponseDto> getEnrollmentsByStudentNumber(Long studentNumber) {
        return enrollmentRepository.findByStudent_StudentNumber(studentNumber).stream()
                .map(EnrollmentResponseDto::from)
                .collect(Collectors.toList());
    }

    // 특정 수강신청 조회
    @Transactional(readOnly = true)
    public EnrollmentResponseDto getEnrollmentById(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> EnrollmentException.notFound("존재하지 않는 수강신청입니다."));
        return EnrollmentResponseDto.from(enrollment);
    }

    // 수강신청 취소
    @Transactional
    public void deleteEnrollment(Long enrollmentId) {
        if (!enrollmentRepository.existsById(enrollmentId)) {
            throw EnrollmentException.notFound("존재하지 않는 수강신청입니다.");
        }
        enrollmentRepository.deleteById(enrollmentId);
    }
}
