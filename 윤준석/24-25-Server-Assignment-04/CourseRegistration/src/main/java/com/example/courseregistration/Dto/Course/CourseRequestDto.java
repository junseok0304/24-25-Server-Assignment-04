package com.example.courseregistration.Dto.Course;


import com.example.courseregistration.Domain.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequestDto {
    private Long courseId; // 강의 코드
    private String title; // 강의명
    private String professorName; // 교수명
    private int maxStudents; // 최대 신청 가능한 학생 수

    public Course toEntity() {
        return Course.builder()
                .courseId(getCourseId())
                .title(getTitle())
                .professorName(getProfessorName())
                .maxStudents(getMaxStudents())
                .build();
    }
}
