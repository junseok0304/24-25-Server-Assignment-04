package com.example.courseregistration.Repository;

import com.example.courseregistration.Domain.Enrollment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // 특정 학생의 수강신청 목록 조회 (학번)
    List<Enrollment> findByStudent_StudentNumber(Long studentNumber);

    // 학생이 특정 강의를 수강신청했는지 확인 (학번과 강의코드)
    Optional<Enrollment> findByStudent_StudentNumberAndCourse_Id(Long studentNumber, Long courseId);
}
