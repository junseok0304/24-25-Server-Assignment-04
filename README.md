# Spring Data JPA를 활용한 수강신청 시스템 구현하기
<img width="808" alt="스크린샷 2024-11-02 오후 7 05 25" src="https://github.com/user-attachments/assets/1c171d38-3b89-498e-a2f0-304839a132e1">

# Description

- ERD 그림 (상단 첨부) 

-Student CRUD
POST - /students (학생 정보 등록) C
GET - /students/{studentNumber} (학생 정보 조회) R
PATCH - /students/{studentNumber} (학생 정보 수정) U
DELETE - /students/{studentNumber} (학생 정보 삭제) D
GET - /students (학생 정보 리스트 조회) R

-Course CRUD
POST - /courses (강의 정보 등록) C
GET - /courses/{courseId} (강의 정보 조회) R
PATCH - /courses/{courseId} (강의 정보 수정) U
DELETE - /courses/{courseId} (강의 정보 삭제) D
GET - /courses(강의 정보 리스트 조회) R

-Enrollment CRUD
POST - /enrolments (수강 신청 정보 등록) C
GET - /enrolments/{enrolmentId} (수강 신청 조회) R
DELETE - /enrolments/{enrolmentId} (수강 신청 삭제) D
GET - /enrolments (수강 신청 리스트) R

# Important content

- 전체적인 흐름중에 잘못된 부분이나 효율적이지 않은 부분있으면 지적 부탁드립니다!
- Exception 작성과 관련하여 처음 시도해보는 방법으로 예외처리를 구현해보았는데, 본 방법 외에
더 작성 측면에서 간편한 방법이 있다면 소개 부탁드립니다!

# Question

### Q1.
StudentController.java 의 학생 정보 삭제 부분에서

- 수정 전 
ResponseEntity.status(HttpStatus.NO_CONTENT).body("학생이 성공적으로 삭제되었습니다.")
- 수정 후
return ResponseEntity.ok("학생이 성공적으로 삭제되었습니다.");

수정 전 코드는 포스트맨에서 204 코드만 표출되고 메시지는 반환이 안되는 것을 확인했습니다.

204 상태코드는 NO_CONTENT 속성을 가지므로 
204 코드를 사용할 경우에는 작성해둔 메시지 반환이 안되는 것이 정상 으로 이해헀는데 맞을까요?
해당 부분을 200 OK코드로 변경 후에는 바로 해결(작성해둔 메시지 정상적으로 반환)되긴 했습니다.

### Q2.

<img width="868" alt="스크린샷 2024-11-02 오후 7 05 00" src="https://github.com/user-attachments/assets/338a5c78-3697-4c9f-99c9-0b8b9e67bf16">
현재 상황과 비슷한 meme...

예외처리 코드를 작성 하다 보니 한 개의 API기능(학생 정보 등록) 만 해도
많은 예외들을 처리해줘야 한다는 것을 느꼈습니다. 
(삭제하려는 학생이 없거나, 이미 등록된 학생을 등록하려고 한다던지 등등등)
서비스 구현 수준(현업 또는 프로젝트 개발시)에서는 이러한 예외처리에 대해서 미리 설정하고 대응하는 
절차같은게 따로 있나요? (미리 예외처리를 사전에 기획하고 기능정의서에 작성되어있다던지..)
아니면 서비스 테스트(QA과정 이라고 하나요)를 거치면서 예외에 대한 부분들을 찾고, 수정(반영)하는 
방식이 더 많은지 도 궁금합니다!


# Reference
스터디세션 코어님 강의자료
JPA 다대일 일대다 관계 정리 벨로그
Spring Boot에서 전역 예외 처리 활용하기
GlobalExceptionHandler를 통한 예외 처리 구현
