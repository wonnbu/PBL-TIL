## 1. 오늘 배운 내용

- JPA의 연관관계 매핑을 사용하여 Member와 Assignment를 1:N 관계로 연결했다.
- `@ManyToOne`, `@OneToMany`, `@JoinColumn`, `mappedBy`의 역할을 학습했다.
- Assignment 엔티티를 새로 생성하고 Member와 연결되는 구조를 구현했다.
- AssignmentRepository를 생성하여 과제 정보를 DB에서 조회하고 저장하는 기능을 구현했다.
- Assignment 전용 DTO(Create, Update, Response)를 작성하여 요청과 응답을 분리했다.
- AssignmentService를 생성하여 과제 등록, 조회, 수정, 삭제 로직을 구현했다.
- `@Transactional`을 사용하여 데이터 변경 작업을 트랜잭션으로 처리하는 방법을 학습했다.
- AssignmentController를 생성하여 Assignment CRUD API를 구현했다.
- Swagger를 이용해 과제 등록 → 멤버별 과제 조회 → 단건 조회 → 수정 → 삭제까지 전체 API를 테스트했다.
- MySQL Workbench에서 assignment 테이블 생성 및 member_id 외래 키 저장 여부를 확인했다.

## 2. 핵심정리 (나의 언어로)

이번 주차의 핵심은 JPA의 연관관계와 트랜잭션이었다. 이전까지는 Member 엔티티 하나만 관리했다면, 이번에는 Assignment라는 새로운 엔티티를 만들고 Member와 연결했다.

Member 한 명은 여러 개의 Assignment를 가질 수 있기 때문에 1:N 관계를 사용했다. DB에서는 N쪽 테이블이 외래 키를 가지기 때문에 assignment 테이블에 member_id 컬럼이 생성된다. 그래서 JPA에서도 Assignment 쪽에 `@ManyToOne`을 붙이고 Member를 참조하도록 만들었다. 반대로 Member에서는 `@OneToMany(mappedBy = "member")`를 사용해 자신이 가진 과제 목록을 조회할 수 있도록 했다.

여기서 `mappedBy`는 "실제로 외래 키를 관리하는 주인은 Assignment의 member 필드다"라는 의미이다. 즉, Member도 Assignment 목록을 알고 있지만 실제 DB 관계를 관리하는 것은 Assignment이다. 그래서 DB에도 member 테이블에는 Assignment 관련 컬럼이 생기지 않고 assignment 테이블에만 member_id가 생성된다.

또한 이번 주차에서는 트랜잭션의 개념도 배웠다. 클래스 레벨에 `@Transactional(readOnly = true)`를 적용하면 기본적으로 조회 전용으로 동작하여 성능상 이점이 있다. 반대로 등록, 수정, 삭제처럼 데이터를 변경하는 메서드에는 `@Transactional`을 별도로 붙여 정상적으로 INSERT, UPDATE, DELETE가 수행되도록 만들었다.

## 3. 결과 이미지(스크린샷)
- 과제 등록
  <img width="1280" height="832" alt="스크린샷 2026-06-07 오후 8 56 34" src="https://github.com/user-attachments/assets/238d6c04-3eef-4899-b6b1-698ace242d85" />
- 존재하지 않는 멤버에게 과제 등록
  <img width="1280" height="832" alt="스크린샷 2026-06-07 오후 8 57 08" src="https://github.com/user-attachments/assets/9f55ffb7-21d3-4de0-b1ec-e18e301b62fe" />
- 멤버별 과제 목록 조회
  <img width="1280" height="832" alt="스크린샷 2026-06-07 오후 8 57 54" src="https://github.com/user-attachments/assets/093748f4-e990-45f5-84d0-ed4a6713dfc9" />
- 과제 단건 조회
  <img width="1280" height="832" alt="스크린샷 2026-06-07 오후 8 58 21" src="https://github.com/user-attachments/assets/d4c3ee8e-c15b-41eb-b17b-ac9f16a05b00" />
- 과제 수정
  <img width="1280" height="832" alt="스크린샷 2026-06-07 오후 8 59 22" src="https://github.com/user-attachments/assets/e6e048f9-d89e-4630-b6e5-fce0556b9783" />
- 과제 삭제
  <img width="1280" height="832" alt="스크린샷 2026-06-07 오후 9 19 30" src="https://github.com/user-attachments/assets/e807ef93-2ab0-4567-b0db-5200acc79721" />
- 콘솔 SQL 출력 예시
  <img width="1280" height="832" alt="스크린샷 2026-06-07 오후 9 02 51" src="https://github.com/user-attachments/assets/639c4600-f169-43ac-80db-1c5079ddb98b" />
- MySQL Workbench 확인
  <img width="1136" height="854" alt="스크린샷 2026-06-07 오후 9 00 39" src="https://github.com/user-attachments/assets/85f76a5c-bc8d-4d05-83d3-4411f4d96a49" />
  <img width="1136" height="854" alt="스크린샷 2026-06-07 오후 9 02 16" src="https://github.com/user-attachments/assets/fa4f1f0d-2fbc-4356-8f3e-1dd190617c2e" />

## 4. 느낀점

이번 주차는 단순히 API를 추가하는 것이 아니라 데이터베이스의 관계를 설계하는 과정이 포함되어 있어서 이전보다 훨씬 어렵게 느껴졌다. 특히 `mappedBy`, 외래 키, 연관관계의 주인 같은 개념은 처음에는 헷갈렸지만 Assignment 테이블에 member_id가 실제로 생성되는 것을 보고 이해가 되기 시작했다. 또한 `@Transactional`을 사용하면 여러 작업을 하나의 단위로 처리할 수 있다는 점과, JPA가 객체 변경만으로 자동 UPDATE를 수행하는 과정을 직접 확인하면서 ORM의 편리함을 체감할 수 있었다. Swagger와 MySQL Workbench를 함께 사용해 API 동작과 DB 저장 결과를 모두 확인해보니 단순히 코드를 작성하는 것이 아니라 실제 백엔드 서비스가 어떻게 동작하는지 조금 더 이해하게 된 것 같다.
