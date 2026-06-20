## 1. 오늘 배운 내용

### 1) 전역 예외 처리

기존에는 Service에서 `null`을 반환하고 Controller에서 직접 예외 상황을 처리했다.

이번 주차에서는 `@RestControllerAdvice`와 `@ExceptionHandler`를 사용해 예외 처리 로직을 한 곳으로 모았다.

또한 `MemberNotFoundException`, `AssignmentNotFoundException`, `DuplicateMemberException`과 같은 커스텀 예외를 만들어 사용했다.

### 2) Spring Data JPA 쿼리 메서드

SQL을 직접 작성하지 않고 메서드 이름만으로 조회 기능을 구현할 수 있다는 것을 배웠다.

```
List<Member>findByPart(String part);
```

```
booleanexistsByName(Stringname);
```

```
List<Assignment>findByTitleContaining(String keyword);
```

Spring Data JPA가 메서드 이름을 분석해 자동으로 SQL을 생성한다.

### 3) Optional과 orElseThrow()

기존에는 null 체크를 직접 수행했다.

```
if(member==null)
```

이번에는

```
.orElseThrow(...)
```

를 사용하여 값이 없을 경우 즉시 예외를 발생시키도록 변경했다.

코드 길이가 줄어들고 의도가 명확해졌다.

### 4) 검색 API 구현

기존 CRUD API 외에 검색 기능을 추가했다.

```
GET /members?part=프론트엔드
```

```
GET /assignments/search?keyword=spring
```

쿼리 파라미터를 사용해 조건에 맞는 데이터만 조회하도록 구현했다.

### 5) 프론트엔드와 백엔드 통신

제공된 프론트엔드 코드를 프로젝트에 연결하고 실제 브라우저 화면에서 API를 테스트했다.

JavaScript의 `fetch()`가 백엔드 API를 호출하고 JSON 데이터를 주고받는 과정을 확인했다.

또한 HTTP 통신 로그를 통해 요청 URL, HTTP 메서드, 상태 코드, Request Body를 직접 확인했다.

## 2. 핵심 정리 (나의 언어로)

이번 과제를 통해 "예외 처리는 Controller에서 if문으로 처리하는 것이 아니라 Service에서 예외를 던지고 전역에서 관리하는 것"이라는 점을 이해했다.

기존에는 Service에서 `null`을 반환하고 Controller에서 직접 예외 상황을 확인해야 했다. 하지만 예외를 발생시키고 `GlobalExceptionHandler`가 처리하도록 변경하면서 Controller는 정상 요청 처리에만 집중할 수 있게 되었다.

또한 JPA Repository는 단순히 save(), findAll()만 사용하는 것이 아니라 메서드 이름만으로도 다양한 조회 기능을 만들 수 있다는 것을 배웠다.

프론트엔드와 백엔드가 JSON 데이터를 주고받으며 통신하는 과정도 직접 확인했다. 브라우저에서 버튼을 클릭하면 JavaScript의 `fetch()`가 API를 호출하고, 백엔드가 JSON 응답을 반환하며, 그 결과가 화면에 렌더링된다는 전체 흐름을 경험할 수 있었다.

## 3. 결과 이미지(스크린샷)
(1) 존재하지 않는 멤버 조회
<img width="1280" height="832" alt="스크린샷 2026-06-20 오후 4 16 32" src="https://github.com/user-attachments/assets/c95ccab2-b5b6-48d8-b322-3847d47c1054" />
(2) 중복 이름으로 멤버 등록
<img width="1280" height="832" alt="스크린샷 2026-06-20 오후 4 16 55" src="https://github.com/user-attachments/assets/90b425d7-b4bd-4a8d-bbed-5374aa4491e4" />
(3) 존재하지 않는 과제 수정
<img width="1280" height="832" alt="스크린샷 2026-06-20 오후 4 18 39" src="https://github.com/user-attachments/assets/85942c0c-90c3-484e-83ab-fffc216de0c1" />
(4) 전체 과제 조회
<img width="1280" height="832" alt="스크린샷 2026-06-20 오후 4 19 13" src="https://github.com/user-attachments/assets/cac776b1-1456-4e56-8697-e89760d4671d" />
(5) 파트별 멤버 필터링
<img width="1280" height="832" alt="스크린샷 2026-06-20 오후 4 19 59" src="https://github.com/user-attachments/assets/e8cce74d-5c10-4c84-87de-921af7431ade" />
(6) 과제 제목 검색
<img width="1280" height="832" alt="스크린샷 2026-06-20 오후 4 20 21" src="https://github.com/user-attachments/assets/f6338457-dd89-4dd2-9d14-1574acfae2ce" />
(7) 멤버 등록(아기사자/운영진)
<img width="1280" height="832" alt="스크린샷 2026-06-20 오후 4 36 17" src="https://github.com/user-attachments/assets/a6f0163c-3c0a-4d12-acbe-82bac9a36e7b" />
(8) 멤버 수정/삭제
<img width="1280" height="832" alt="스크린샷 2026-06-20 오후 4 36 51" src="https://github.com/user-attachments/assets/fd4d2f43-2b42-46e2-a3ef-f1ca847a8ea1" />
(9) 과제 등록 + 과제 조회(전체/멤버별/단건)
<img width="1280" height="832" alt="스크린샷 2026-06-20 오후 4 39 12" src="https://github.com/user-attachments/assets/54d82f4c-e37c-4c79-8bd0-750f590c3053" />
(10) 과제 제목 검색
<img width="1280" height="832" alt="스크린샷 2026-06-20 오후 4 39 35" src="https://github.com/user-attachments/assets/36338657-55d2-4da4-a58e-f087a5c18941" />
(11) 과제 수정/삭제
<img width="1280" height="832" alt="스크린샷 2026-06-20 오후 4 39 58" src="https://github.com/user-attachments/assets/8f7cee5d-3d80-48b1-9a8e-dfc7dde1c267" />
<img width="1280" height="832" alt="스크린샷 2026-06-20 오후 4 40 31" src="https://github.com/user-attachments/assets/65e5bcfc-4379-4de4-97b1-391a25f47de7" />


## 4. 느낀 점

이번 과제는 단순히 기능을 추가하는 것보다 실제 서비스 구조에 한 단계 더 가까워진 경험이었다.

특히 기존에 `null`을 반환하던 코드를 예외 처리 방식으로 리팩토링하면서 Service, Controller, GlobalExceptionHandler의 역할이 명확하게 분리되는 것을 확인할 수 있었다. 처음에는 왜 굳이 예외를 만들어야 하는지 이해되지 않았지만, 기능이 늘어날수록 예외 처리 코드를 한 곳에서 관리하는 것이 훨씬 효율적이라는 점을 체감했다.

또한 Swagger로만 테스트하던 API를 실제 프론트엔드 화면과 연결해 보면서 백엔드 API가 실제 서비스에서 어떻게 사용되는지 이해할 수 있었다. 특히 HTTP 통신 로그를 통해 요청 URL, 메서드, 상태 코드, Request Body를 직접 확인하며 REST API의 동작 방식을 더 구체적으로 이해할 수 있었다.
