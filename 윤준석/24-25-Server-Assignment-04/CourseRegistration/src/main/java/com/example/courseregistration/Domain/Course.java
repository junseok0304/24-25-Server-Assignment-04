package com.example.courseregistration.Domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(accessLevel = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "course_id")
    private Long courseId; // 강의코드

    private String title; // 강의명

    @Column(name = "professor_name")
    private String professorName; // 교수명

    @Column(name = "max_students")
    private Integer maxStudents; // 최대 신청 가능한 학생 수

    @OneToMany(
            mappedBy = "course",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Enrollment> enrollments = new ArrayList<>();

    public void update(String title, String professorName, Integer maxStudents) {
        this.title = title;
        this.professorName = professorName;
        this.maxStudents = maxStudents;
    }
}
