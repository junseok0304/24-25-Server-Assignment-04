package com.example.courseregistration.Repository;

import com.example.courseregistration.Domain.Student;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // 학번으로 학생 조회
    Optional<Student> findByStudentNumber(Long studentNumber);

    // 학번 중복 여부 확인
    boolean existsByStudentNumber(Long studentNumber);

    // 학번으로 학생 삭제
    void deleteByStudentNumber(Long studentNumber);
}
