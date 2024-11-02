package com.example.courseregistration.Service;

import com.example.courseregistration.Domain.Student;
import com.example.courseregistration.Dto.Student.StudentRequestDto;
import com.example.courseregistration.Dto.Student.StudentResponseDto;
import com.example.courseregistration.Exception.StudentException;
import com.example.courseregistration.Repository.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    // 새로운 학생 생성
    @Transactional
    public void create(StudentRequestDto dto) {
        Long studentNumber = dto.getStudentNumber();
        if (studentRepository.existsByStudentNumber(studentNumber)) {
            throw StudentException.alreadyExists();
        }
        studentRepository.save(dto.toEntity());
    }

    // 학번으로 학생 조회
    @Transactional(readOnly = true)
    public StudentResponseDto findByStudentNumber(Long studentNumber) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(StudentException::notFound);
        return StudentResponseDto.from(student);
    }

    // 학번으로 학생 정보 업데이트
    @Transactional
    public StudentResponseDto updateStudent(StudentRequestDto dto) {
        Long studentNumber = dto.getStudentNumber();
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(StudentException::notFound);

        // 동일한 이름인 경우 예외 처리 후 기존과 다르면 반영
        if (student.getName().equals(dto.getName())) {
            throw StudentException.sameName();
        }
        student.update(dto);
        return StudentResponseDto.from(student);
    }

    // 학번으로 학생 삭제
    @Transactional
    public void deleteByStudentNumber(Long studentNumber) {
        if (!studentRepository.existsByStudentNumber(studentNumber)) {
            throw StudentException.notFound();
        }
        studentRepository.deleteByStudentNumber(studentNumber);
    }

    // 모든 학생 조회
    @Transactional(readOnly = true)
    public List<StudentResponseDto> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(StudentResponseDto::from)
                .toList();
    }
}
