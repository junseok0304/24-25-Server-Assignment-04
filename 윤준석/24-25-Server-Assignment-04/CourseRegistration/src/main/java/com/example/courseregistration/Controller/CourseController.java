package com.example.courseregistration.Controller;

import com.example.courseregistration.Dto.Course.CourseRequestDto;
import com.example.courseregistration.Dto.Course.CourseResponseDto;
import com.example.courseregistration.Service.CourseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    // 강의 정보 등록
    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody CourseRequestDto dto) {
        courseService.createCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("강의 정보가 등록되었습니다.");
    }

    // 강의 정보 수정
    @PutMapping("/{courseId}")
    public ResponseEntity<String> updateCourse(
            @PathVariable Long courseId,
            @RequestBody CourseRequestDto dto) {
        courseService.updateCourse(courseId, dto);
        return ResponseEntity.ok("강의 정보가 성공적으로 수정되었습니다.");
    }

    // 강의번호로 강의 삭제
    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourseByCourseId(@PathVariable Long courseId) {
        courseService.deleteCourseByCourseId(courseId);
        return ResponseEntity.ok("강의 정보가 성공적으로 삭제되었습니다.");
    }

    // 강의번호로 강의 조회
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponseDto> getCourseByCourseId(@PathVariable Long courseId) {
        CourseResponseDto response = courseService.getCourseByCourseId(courseId);
        return ResponseEntity.ok(response);
    }

    // 모든 강의 조회
    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        List<CourseResponseDto> response = courseService.getAllCourses();
        return ResponseEntity.ok(response);
    }
}
