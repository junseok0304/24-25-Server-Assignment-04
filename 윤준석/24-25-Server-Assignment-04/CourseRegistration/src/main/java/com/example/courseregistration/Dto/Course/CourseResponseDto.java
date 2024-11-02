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
public class CourseResponseDto {
    private Long courseId;
    private String title;
    private String professorName;
    private int maxStudents;

    public static CourseResponseDto from(Course course) {
        return CourseResponseDto.builder()
                .courseId(course.getCourseId())
                .title(course.getTitle())
                .professorName(course.getProfessorName())
                .maxStudents(course.getMaxStudents())
                .build();
    }
}
