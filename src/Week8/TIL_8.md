## 1. 오늘 배운 내용
- MySQL과 Spring Boot를 연동하기 위해 `application.properties`에서 datasource 설정을 진행했다.
- JPA(Java Persistence API)를 사용하여 객체를 DB 테이블과 연결하는 방법을 학습했다.
- `@Entity`, `@Id`, `@GeneratedValue`, `@Enumerated` 같은 JPA 핵심 어노테이션의 역할을 이해했다.
- 기존의 MemoryRepository 방식 대신 `JpaRepository`를 사용하여 DB 기반 CRUD를 구현했다.
- DTO를 생성용(Create), 수정용(Update), 응답용(Response)으로 분리하여 사용하는 구조를 익혔다.
- Swagger를 사용해 API를 직접 테스트하고 HTTP 상태 코드(`200`, `201`, `204`)의 의미를 확인했다.
- Hibernate가 실행 중 자동으로 SQL을 생성하고 DB와 연결되는 과정을 콘솔 로그로 확인했다.
- MySQL Workbench에서 실제 테이블 생성 및 데이터 저장 여부를 직접 검증했다.

## 2. 핵심 정리 (나의 언어로)
이번 주차의 가장 큰 차이는 이전까지는 `ArrayList` 같은 메모리에 데이터를 저장했다면, 이번에는 MySQL에 데이터를 저장했다는 점이다. 원래 DB를 사용하려면 SQL을 직접 문자열로 작성해야 하지만, JPA를 사용하면 `memberRepository.save(member)` 같은 코드만으로 Hibernate가 자동으로 SQL을 생성해준다. 실제로 콘솔에서 `insert into member ...` 같은 SQL이 자동 출력되는 것도 확인했다.
`@Entity`는 “이 클래스가 DB 테이블이다”라고 알려주는 역할이고, 클래스 안에 작성한 필드들은 DB 컬럼으로 자동 생성된다. 또한 이전까지는 Lion과 Staff를 상속 구조로 관리했지만, 이번에는 `RoleType` enum 하나로 역할을 구분했다. 관계형 데이터베이스에는 Java의 상속 개념이 없기 때문에, 실무에서는 역할 정도의 차이는 enum과 단일 테이블 구조로 단순하게 관리하는 경우가 많다. 그래서 DB에는 `LION`, `STAFF`처럼 저장하고, 사용자에게 보여줄 때만 `아기사자`, `운영진`처럼 변환해서 응답하도록 구현했다.
또한 DTO를 Create, Update, Response로 나누면서 “DB에 저장되는 객체(Entity)”와 “사용자 요청 및 응답 데이터(DTO)”는 역할이 다르다는 것도 이해하게 되었다. 특히 `MemberResponse.from(member)` 같은 팩토리 메서드를 사용해 Entity를 Response DTO로 변환하는 구조를 처음 경험했다. 즉, Member 객체를 그대로 사용자에게 보내는 것이 아니라, 필요한 정보만 담아서 응답용 객체로 변환하는 과정을 DTO 내부에서 처리하도록 만든 것이다.
그리고 `ddl-auto=create`는 실행할 때마다 테이블을 새로 생성하고, `show-sql=true`는 Hibernate SQL을 콘솔에 출력하며, `format_sql=true`는 SQL을 보기 좋게 정렬해주는 설정이라는 것도 배웠다.

## 3. 결과 이미지(스크린샷)
1. Lion(아기사자) 등록
<img width="1280" height="832" alt="스크린샷 2026-05-30 오후 10 17 20" src="https://github.com/user-attachments/assets/2ca2d0e2-4fbb-44b4-9fe1-6f48bc5d9511" />
2. Staff(운영진) 등록
<img width="1280" height="832" alt="스크린샷 2026-05-30 오후 10 18 17" src="https://github.com/user-attachments/assets/8efd3b18-1025-4fb0-b14f-2a9a48d1c50e" />
3. ID로 단일 멤버 조회
<img width="1280" height="832" alt="스크린샷 2026-05-30 오후 10 19 05" src="https://github.com/user-attachments/assets/a145b920-581e-44c4-a2b4-e6c7670ef26e" />
4. Lion 수정
<img width="1280" height="832" alt="스크린샷 2026-05-30 오후 10 20 53" src="https://github.com/user-attachments/assets/3d89043d-4c34-46b8-9f77-98a93d6bcfdb" />
5. 멤버 삭제
<img width="1280" height="832" alt="스크린샷 2026-05-30 오후 10 25 12" src="https://github.com/user-attachments/assets/bf955215-be4e-4956-af85-96748a68c193" />
6. 콘솔 SQL 출력 예시
<img width="925" height="854" alt="스크린샷 2026-05-30 오후 10 26 35" src="https://github.com/user-attachments/assets/94bbd476-ae54-45e7-bfe3-ad655995215b" />
7. MySQL Workbench 확인
<img width="1280" height="832" alt="스크린샷 2026-05-30 오후 10 31 52" src="https://github.com/user-attachments/assets/42156cba-6ce5-4a44-b580-045ccfbed733" />


## 4. 느낀 점
이번 주차는 지금까지 했던 내용 중 가장 어려웠다. 이전까지는 객체지향 구조와 메모리 기반 CRUD 정도였다면, 이번에는 JPA, MySQL, Entity, DTO, Repository, HTTP 상태 코드까지 한 번에 연결되면서 흐름 자체를 이해하는 데 시간이 오래 걸렸다. 특히 단순히 코드를 따라치는 게 아니라 “왜 메모리 저장소 대신 JpaRepository를 쓰는지”, “왜 DTO와 Entity를 분리하는지”를 이해하려고 하다 보니 더 복잡하게 느껴졌다. 하지만 Swagger에서 API가 정상 동작하고, Hibernate SQL이 콘솔에 출력되고, MySQL Workbench에서 실제 데이터가 저장되는 것을 직접 확인하니까 지금까지 만들었던 기능들이 실제 백엔드 시스템처럼 연결된 느낌이 들어 신기했다.
