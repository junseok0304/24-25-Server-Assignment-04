package com.example.courseregistration.Dto.Enrollment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentRequestDto {
    private Long studentNumber; // 수강 신청 할 학생의 학번
    private Long courseId;  // 수강 신청 할 강의 ID
}
