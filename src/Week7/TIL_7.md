## 1. 오늘 배운 내용
- Spring Boot 기반으로 본격적인 REST API CRUD를 구현
- REST API에서 URL은 자원(Resource)을 표현, HTTP 메서드(GET, POST, PUT, DELETE)는 행동을 표현
    
    POST   /members/lions        → 아기사자 추가
    GET    /members/{name}       → 멤버 조회
    PUT    /members/lions/{name} → 아기사자 정보 수정
    DELETE /members/{name}       → 멤버 삭제
    
- `@RequestBody` : JSON → Java 객체 변환
- `@PathVariable` : URL 경로에서 값 추출
- `ResponseEntity` : 상태 코드까지 직접 제어
    - 생성 성공 → `201 Created`
    - 이름 중복 → `409 Conflict`
    - 잘못된 HTTP 메서드 요청 → `405 Method Not Allowed`
- CRUD 기능을 위해 `MemoryMemberRepository`에 `updateByName`, `deleteByName` 메서드를 추가

## 2. 핵심 정리 (나의 언어로)
REST API는 결국 URL로 누구를 조작할 건지 정하고, HTTP 메서드로 뭘 할지 표현하는 것. 즉 URL은 대상, HTTP 메서드는 행동

그리고 DTO 사용하는 이유는 요청 데이터 형식을 깔끔하게 관리할 수 있고, 내부 도메인 객체를 그대로 노출하지 않아도 되고, 역할별 필드를 명확하게 분리할 수 있기 때문. 도메인 객체를 그대로 노출하지 않고 요청용 DTO와 응답용 DTO를 따로 만들어서 API 계층과 내부 객체를 분리. 또 역할별 DTO를 나누는 이유는 Lion과 Staff가 서로 다른 필드(학번, 포지션)를 갖기 때문.

“MemoryMemberRepository를 그대로 사용한다”는 의미가 “메모리 저장 방식 유지(DB/JPA로 교체 X)”라는 뜻. 클래스명은 맞고 다른 틀린게 없는데 왜 implements 관련해서 빨간줄이 뜨는가 했는데 그냥 그대로 쓰고 있어서 틀렸던것. update, delete메서드 추가를 요구하고 있기에 updateByName이랑 deleteByName 추가 필요했던 것.(CRUD기능)

DTO에서 객체를 응답용 JSON으로 변환하기 위해 `static from()` 팩토리 메서드를 사용하는 이유를 알았음.

## 3. 결과 이미지(스크린샷)
1. Lion(아기사자) 등록
<img width="730" height="526" alt="스크린샷 2026-05-23 오후 7 07 14" src="https://github.com/user-attachments/assets/6fe83ad4-90f4-4a0f-9753-c4a677accd10" />
2. Staff(운영진) 등록
<img width="725" height="518" alt="스크린샷 2026-05-23 오후 7 12 35" src="https://github.com/user-attachments/assets/8ae0f704-726d-4212-8037-f809ffa174a8" />
3. 이름 중복 등록 시
<img width="719" height="510" alt="스크린샷 2026-05-23 오후 7 13 12" src="https://github.com/user-attachments/assets/2a1313b8-7f2b-43ba-98ac-7dca430bcb03" />
4. 단일 멤버 조회
<img width="727" height="515" alt="스크린샷 2026-05-23 오후 7 14 53" src="https://github.com/user-attachments/assets/95dbe7ae-3629-473d-a1d0-7b01c20aed7a" />
5. Lion 수정
<img width="724" height="520" alt="스크린샷 2026-05-23 오후 7 26 02" src="https://github.com/user-attachments/assets/0e71df16-5f5e-424b-9109-6e758aaab409" />
6. Staff 수정
<img width="722" height="533" alt="스크린샷 2026-05-23 오후 7 27 08" src="https://github.com/user-attachments/assets/d6cbccdb-e228-46fd-ab1d-684c93a2dce0" />
7. 멤버 삭제(204, 404)
<img width="726" height="521" alt="스크린샷 2026-05-23 오후 7 28 21" src="https://github.com/user-attachments/assets/55c20d12-5152-4a0c-b765-17862c0300fd" />
<img width="725" height="518" alt="스크린샷 2026-05-23 오후 7 28 39" src="https://github.com/user-attachments/assets/a9df050f-d46e-48b2-972a-3bfa0f25bfc2" />

## 4. 느낀점
이번 주차는 단순히 Spring 문법을 배우는 느낌보다, 실제 백엔드 서버가 어떻게 동작하는지를 처음으로 경험한 느낌이었다. 요청을 받고, 데이터를 저장하고, JSON으로 응답을 반환하는 흐름을 직접 구현하면서 REST API 구조에 대해 조금은 감이 오는 것 같다. 특히 Postman으로 직접 API를 테스트해보면서 HTTP 메서드와 URL의 일치가 중요하다는 것을 알았다. 404, 405, implements 오류, git push 충돌 등 다양한 에러를 맞닥뜨리면서 단순히 코드를 작성하는 것보다 에러를 읽고 원인을 추론하는 과정이 개발에서 굉장히 중요하다는 점을 느꼈다.
