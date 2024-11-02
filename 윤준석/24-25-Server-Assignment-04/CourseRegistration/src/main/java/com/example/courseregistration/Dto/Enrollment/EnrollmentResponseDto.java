package com.example.courseregistration.Dto.Enrollment;

import com.example.courseregistration.Domain.Enrollment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentResponseDto {

    private Long id; // 수강신청 PK
    private Long studentNumber; // 학생 학번
    private String studentName; // 학생 이름
    private Long courseId; // 강의 코드
    private String courseTitle; // 강의명
    private String professorName; // 교수명

    public static EnrollmentResponseDto from(Enrollment enrollment) {
        return EnrollmentResponseDto.builder()
                .id(enrollment.getId())
                .studentNumber(enrollment.getStudent().getStudentNumber())
                .studentName(enrollment.getStudent().getName())
                .courseId(enrollment.getCourse().getId())
                .courseTitle(enrollment.getCourse().getTitle())
                .professorName(enrollment.getCourse().getProfessorName())
                .build();
    }
}
