# Likelion Member Manager

멋쟁이사자처럼 멤버 및 과제 관리 시스템입니다.
Spring Boot와 JPA를 활용하여 멤버와 과제를 관리하고, 프론트엔드와 연동하여 CRUD 기능을 제공합니다.

---

## 기술 스택

| 기술                      | 버전                |
| ----------------------- | ----------------- |
| Java                    | 17                |
| Spring Boot             | 3.x               |
| Spring Data JPA         | 3.x               |
| MySQL                   | 8.x               |
| Gradle                  | 8.x               |
| Swagger(OpenAPI)        | springdoc-openapi |
| HTML / CSS / JavaScript | ES6               |

---

## 실행 방법

### 1. 프로젝트 클론

```bash
git clone <repository-url>
cd Week10
```

### 2. MySQL 실행

MySQL 서버를 실행한 뒤 데이터베이스를 생성한다.

```sql
CREATE DATABASE likelion;
```

### 3. application.properties 설정

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/likelion
spring.datasource.username=root
spring.datasource.password=비밀번호

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. 프로젝트 실행

```bash
./gradlew bootRun
```

실행 후 접속

```text
http://localhost:8080
```

Swagger

```text
http://localhost:8080/swagger-ui.html
```

---

# API 목록

## Member API

| Method | URI                  | 설명        |
| ------ | -------------------- | --------- |
| GET    | /members             | 전체 멤버 조회  |
| GET    | /members?part={part} | 파트별 멤버 조회 |
| GET    | /members/{id}        | 단일 멤버 조회  |
| POST   | /members/lions       | 아기사자 등록   |
| POST   | /members/staffs      | 운영진 등록    |
| PUT    | /members/lions/{id}  | 아기사자 수정   |
| PUT    | /members/staffs/{id} | 운영진 수정    |
| DELETE | /members/{id}        | 멤버 삭제     |

---

## Assignment API

| Method | URI                                   | 설명          |
| ------ | ------------------------------------- | ----------- |
| POST   | /members/{memberId}/assignments       | 과제 등록       |
| GET    | /members/{memberId}/assignments       | 특정 멤버 과제 조회 |
| GET    | /assignments                          | 전체 과제 조회    |
| GET    | /assignments/{assignmentId}           | 단일 과제 조회    |
| GET    | /assignments/search?keyword={keyword} | 과제 제목 검색    |
| PUT    | /assignments/{assignmentId}           | 과제 수정       |
| DELETE | /assignments/{assignmentId}           | 과제 삭제       |

---

# 예외 처리

전역 예외 처리(GlobalExceptionHandler)를 적용하였다.

| 예외                          | 상태 코드 |
| --------------------------- | ----- |
| MemberNotFoundException     | 404   |
| AssignmentNotFoundException | 404   |
| DuplicateMemberException    | 409   |

에러 응답 예시

```json
{
  "message": "멤버를 찾을 수 없습니다."
}
```

---

# 프로젝트 구조

```text
src
└── main
    ├── java
    │   └── com.example.week10
    │       ├── member
    │       │   ├── controller
    │       │   ├── service
    │       │   ├── repository
    │       │   ├── domain
    │       │   └── dto
    │       │
    │       ├── assignment
    │       │   ├── controller
    │       │   ├── service
    │       │   ├── repository
    │       │   ├── domain
    │       │   └── dto
    │       │
    │       └── global
    │           ├── exception
    │           └── dto
    │
    └── resources
        ├── static
        │   ├── index.html
        │   ├── css
        │   └── js
        └── application.properties
```

---

# 주요 기능

### 멤버 관리

* 아기사자 등록
* 운영진 등록
* 멤버 조회
* 파트별 필터링
* 멤버 수정
* 멤버 삭제

### 과제 관리

* 과제 등록
* 전체 과제 조회
* 멤버별 과제 조회
* 과제 제목 검색
* 과제 수정
* 과제 삭제

### 예외 처리

* 존재하지 않는 멤버 조회 시 404 반환
* 존재하지 않는 과제 조회 시 404 반환
* 중복 멤버 등록 시 409 반환

### 프론트엔드 연동

* Fetch API를 통한 REST API 호출
* HTTP 통신 로그 확인
* 전역 예외 처리 결과를 화면에 표시
* 멤버/과제 CRUD UI 제공

```
```
