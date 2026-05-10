# 1. 오늘 배운 내용

이번 5주차 미션에서는 4주차에 만들었던 멤버 관리 시스템을 리팩토링하며 **IoC/DI와 레이어 분리 구조**를 학습했다.

기존에는 main메서드 하나가 UI 출력, 데이터 저장, 중복 검사 같은 로직을 모두 처리하고 있었는데, 이번에는 이를 역할별로 분리하였다.

- Main : 사용자 입력 및 출력(UI)
- Service : 중복 검사와 같은 비즈니스 로직
- Repository : 멤버 저장 및 조회

이렇게 레이어를 분리하며 강한 결합 대신 느슨한 결합 구조를 경험했다.

또한 findAll() 메서드를 통해 이전처럼 main이 직접 members리스트를 관리하지 않고, Repository에게 전체 데이터를 요청하는 방식으로 변경하였다.

추가로 인터페이스 기반 설계를 적용하며 MemoryMemberRepository와 MockMemberRepository 두 개의 구현체를 만들었다.

둘 다 MemberRepository 인터페이스를 구현하도록 설계하여, Service는 실제 저장소가 무엇인지 몰라도 동일한 방식으로 동작할 수 있도록 만들었다.

마지막으로 Step1에서는 new를 통해 직접 객체를 생성했지만, Step2에서는 this 생성자를 사용해 의존성을 주입하는 방식으로 변경했다.

# 2. 핵심 정리 (나의 언어로)

**인터페이스를 쓰는 이유**

Service는 저장소가 Memory인지 Mock인지 알 필요가 없다. 둘 다 MemberRepository라는 공통 규칙을 따르기만 하면, Service 코드 한 줄 안 바꾸고 저장소를 갈아끼울 수 있다.

**DI (의존성 주입)란**

Service가 쓸 Repository를 스스로 new로 만들면, 나중에 바꾸려면 Service코드를 직접 열어서 수정해야 한다. 반면 생성자로 외부에서 주입받으면, main에서 어떤 구현체를 넘길지만 결정하면 된다. Service는 그냥 받아서 쓰면 끝.

**IoC (제어의 역전)**

원래라면 객체가 직접 필요한 객체를 만든다. 즉, 객체 스스로 제어. 근데 DI구조에서는 객체가 직접 안 만들고 객체 생성과 연결 관리 권한이 외부로 넘어감. Service가 "나 Repository필요하니까 내가 직접 만들게"였는데, 이제는 "누군가 줄 테니까 나는 그냥 받을게"가 된 것.(DI는 IoC를 구현하는 방법 중 하나)

# 3. 결과 이미지(스크린샷)
(step 1)
<img width="1280" height="832" alt="스크린샷 2026-05-10 오후 2 13 33" src="https://github.com/user-attachments/assets/4637c818-6594-4c58-9a38-8ee26d2d055a" />
<img width="1280" height="832" alt="스크린샷 2026-05-10 오후 2 13 42" src="https://github.com/user-attachments/assets/1248ef6b-6c36-4b58-8aa5-8faf9d78d62f" />
<img width="1280" height="832" alt="스크린샷 2026-05-10 오후 2 14 10" src="https://github.com/user-attachments/assets/c6ba0ccb-1bc4-4548-b1db-7c2808ddb614" />
<img width="1280" height="832" alt="스크린샷 2026-05-10 오후 2 14 18" src="https://github.com/user-attachments/assets/563625fa-8bb7-4f6f-9730-8336c893f90c" />
<img width="1280" height="832" alt="스크린샷 2026-05-10 오후 2 14 21" src="https://github.com/user-attachments/assets/b2ac12f9-6835-40f6-a632-87c335ebae1f" />

(step 2)
<img width="1280" height="832" alt="스크린샷 2026-05-10 오후 2 10 39(s2)" src="https://github.com/user-attachments/assets/fd188b03-2aea-4c3a-bd26-580bda64c6b1" />
<img width="1280" height="832" alt="스크린샷 2026-05-10 오후 2 10 43(s2)" src="https://github.com/user-attachments/assets/11b14020-2c1d-4f5b-bcba-a958a636db28" />

# 4. 느낀점

이번 미션은 단순히 기능 구현보다 “왜 구조를 나누는지”를 이해하는 과정이었다.

처음에는 Repository, Service, Interface를 왜 굳이 분리하는지 체감이 잘 되지 않았지만, MockMemberRepository를 만들고 실제 저장소와 교체해보면서 Service 코드를 수정하지 않아도 동작이 달라지는 것을 보고 인터페이스 기반 설계의 장점을 직접 느낄 수 있었다.

또한 existsByName에서 s를 빼먹고 existByName으로 썼다가 오류가 났었다. 그렇지만 이런 오탈자 실수도 결국 직접 겪어야 나중에 덜 틀리게 되는 것 같다.
