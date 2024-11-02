package com.example.courseregistration.Domain;

import com.example.courseregistration.Dto.Student.StudentRequestDto;
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
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "student_number")
    private Long studentNumber;

    private String name;

    @OneToMany(
            mappedBy = "student", //연관관계의 owner를 결정, 상대 엔티티 클래스의 필드 이름을 의미하게 됨
            fetch = FetchType.LAZY, //연관된 엔티티를 언제 로딩하는지 결정, LAZY속성으로 지연로딩 학생 엔티티 조회시 수강신청(Enrollment) 엔티티는 즉시 로드되는 것이 아니라 접근시에 로드됨
            cascade = CascadeType.ALL, //연관된 엔티티에 대해 어떤 상태변화가 전이될지 설정, ALL은 모든 상태 변화가 enrollment리스트에 있는 엔티티들에게도 전이되도록 정의(학생 삭제시 수강신청도 삭제됨)
            orphanRemoval = true //부모 엔티티와의 관계가 끊어진 자식 엔티티를 자동으로 삭제하는 옵션, DB관리에 용이
    )
    private List<Enrollment> enrollments = new ArrayList<>();

    // 학생 정보 업데이트 (학번은 변경하지 않음)
    public void update(StudentRequestDto dto) {
        this.name = dto.getName();
    }
} //POSIX New line 반영을 위한 마지막 줄 EOL
