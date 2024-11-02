package com.example.courseregistration.Repository;

import com.example.courseregistration.Domain.Course;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // 강의 코드로 강의 조회
    Optional<Course> findByCourseId(Long courseId);

    // 중복 확인
    boolean existsByCourseId(Long courseId);

    void deleteByCourseId(Long courseId);
}
