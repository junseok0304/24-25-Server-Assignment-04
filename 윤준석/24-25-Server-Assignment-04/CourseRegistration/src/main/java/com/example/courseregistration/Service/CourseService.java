package com.example.courseregistration.Service;

import com.example.courseregistration.Domain.Course;
import com.example.courseregistration.Dto.Course.CourseRequestDto;
import com.example.courseregistration.Dto.Course.CourseResponseDto;
import com.example.courseregistration.Exception.CourseException;
import com.example.courseregistration.Repository.CourseRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    // 새로운 강의 생성
    @Transactional
    public CourseResponseDto createCourse(CourseRequestDto dto) {
        if (courseRepository.existsByCourseId(dto.getCourseId())) {
            throw CourseException.alreadyExists();
        }
        Course course = courseRepository.save(dto.toEntity());
        return CourseResponseDto.from(course);
    }

    // 강의코드로 조회
    @Transactional(readOnly = true)
    public CourseResponseDto getCourseByCourseId(Long courseId) {
        Course course = courseRepository.findByCourseId(courseId)
                .orElseThrow(CourseException::notFound);
        return CourseResponseDto.from(course);
    }

    // 모든 강의 조회
    @Transactional(readOnly = true)
    public List<CourseResponseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseResponseDto::from)
                .collect(Collectors.toList());
    }

    // 강의 정보 업데이트
    @Transactional
    public CourseResponseDto updateCourse(Long courseId, CourseRequestDto dto) {
        Course course = courseRepository.findByCourseId(courseId)
                .orElseThrow(CourseException::notFound);

        course.update(dto.getTitle(), dto.getProfessorName(), dto.getMaxStudents());
        return CourseResponseDto.from(course);
    }

    // 강의 삭제
    @Transactional
    public void deleteCourseByCourseId(Long courseId) {
        if (!courseRepository.existsByCourseId(courseId)) {
            throw CourseException.notFound();
        }
        courseRepository.deleteByCourseId(courseId);
    }
}
