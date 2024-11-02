package com.example.courseregistration.Dto.Student;

import com.example.courseregistration.Domain.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponseDto {

    private Long studentNumber;
    private String name;

    public static StudentResponseDto from(Student student) {
        return StudentResponseDto.builder()
                .studentNumber(student.getStudentNumber())
                .name(student.getName())
                .build();
    }
}
