package com.example.courseregistration.Controller;

import com.example.courseregistration.Dto.Student.StudentRequestDto;
import com.example.courseregistration.Dto.Student.StudentResponseDto;
import com.example.courseregistration.Service.StudentService;
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
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // 학생 정보 등록
    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody StudentRequestDto dto) {
        studentService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("학생이 성공적으로 등록되었습니다.");
    }

    // 학번으로 학생 조회
    @GetMapping("/{studentNumber}")
    public ResponseEntity<StudentResponseDto> getStudentByStudentNumber(
            @PathVariable Long studentNumber) {
        StudentResponseDto student = studentService.findByStudentNumber(studentNumber);
        return ResponseEntity.ok(student);
    }

    // 학번으로 학생 정보 수정
    @PutMapping("/{studentNumber}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable Long studentNumber,
            @RequestBody StudentRequestDto dto) {

        dto = StudentRequestDto.builder()
                .studentNumber(studentNumber)
                .name(dto.getName())
                .build();

        StudentResponseDto updatedStudent = studentService.updateStudent(dto);
        return ResponseEntity.ok(updatedStudent);
    }

    // 학번으로 학생 정보 삭제
    @DeleteMapping("/{studentNumber}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentNumber) {
        studentService.deleteByStudentNumber(studentNumber);
        return ResponseEntity.ok("학생이 성공적으로 삭제되었습니다.");
    }

    // 모든 학생 조회
    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        List<StudentResponseDto> students = studentService.findAllStudents();
        return ResponseEntity.ok(students);
    }
}
