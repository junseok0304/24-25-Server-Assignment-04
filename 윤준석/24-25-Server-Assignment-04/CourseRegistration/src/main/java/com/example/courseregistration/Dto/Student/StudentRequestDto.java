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
public class StudentRequestDto {
    private Long id;
    private Long studentNumber;
    private String name;

    public Student toEntity() {
        return Student.builder()
                .studentNumber(studentNumber) // 생성 시에만 학번을 설정
                .name(name)
                .build();
    }
}
