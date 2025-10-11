# 스프링 계층형 아키텍처

: `Browser <- (DTO) -> Controller <- (DTO) -> Service <- (DTO) -> DAO <- (Entitiy) -> DB`

- **Controrller**
    : HTTP 요청과 응답을 주고받는 계층, 특정 endpoint로 요청을 가장 먼저처리함, DTO(Data Transfer Object)를 사용하여 서비스 계층과 데이터를 주고받음

- **Service**
    : 애플리케이션의 비즈니스 로직이 담기는 계층, 레포지토리 계층과 소통함(엔티티 또는 DTO를 통해서), 컨트롤러와 레포지토리 사이의 중간다리 역할
    - **service 계층의 비즈니스 로직 :**
        - ex) member 데이터 수정하려면 member를 조회하고 member 데이터 수정해야함. but 조회를 했는데 member가 존재하지 않으면 오류발생 또는 수정하는 과정에서 입력 데이터가 문제가 있으면 오류발생
        - ex) memberA가 하나의 메서드안에서 3가지 행동을 실행할때 중간에서 오류가 나면 앞의 행동들도 실행이 안됨
        -> spring에서는 모든 행동이 실행되거나 안되거나로 동작 -> 더 이상 쪼갤 수 없는 특징 (= 원자성을 가진다)
    - **원자성을 보장하려면?** -> 트랜잭션 단위로 처리해야함 -> 서비스 게층 메서드 위에 `@transactional`을 붙이면 됨

- **DAO**
- **DB**
- **DTO**

---

# 패키지 구조: 계층형 vs 도메인형

* **계층형 구조** : 애플리케이션을 기능별로 나눈다. (Controller는 Controller에, Service는 service에 넣기)
* **도메인형 구조** : 도메인 관련 모든 클래스를 포함시킴.
    -   특정 도메인의 코드를 한 곳에 넣으면 코드 탐색이 쉬움
    -   유지보수 용이
    -   새로운 도메인 추가해도 다른곳에 영향없음
    -> 스터디는 도메인형 구조로 진행

---

# 스프링 기본 개념

* **스프링(Spring)** : 대표적인 Java 백엔드 애플리케이션 프레임워크, 객체지향 원리를 잘 지키게 해줌
* **스프링 부트(Spring Boot)** : 스프링 프레임워크를 쉽고 편하게 쓸 수 있게 돕는 도구

### 스프링 애플리케이션 구조

-> 스프링안에 내장서버인 Tomcat이 있고 Tomcat에서 먼저 HTTP Request를 받음. 그 다음 요청이 Spring container로 들어가서..

### 스프링 컨테이너와 빈(Bean)

* **스프링 컨테이너** : 스프링 빈 저장소, 어플리케이션 컨텍스트라고도 함.
* **스프링 빈** : 어플리케이션 전역에서 사용할 공용 객체, 스프링 컨테이너(공용 창고)에 빈을 저장, 필요한 빈을 컨테이너에서 받아 사용함. 필요한 빈은 스프링 프레임워크가 자동으로 가져다 줌. 빈을 요구하는 객체 역시 스프링 빈.
* **빈 등록 방법** : 설정 파일 작성(수동 등록), 컴포넌트 스캔(자동 등록)이 있음.

    - **컴포넌트 스캔**
        1) 컴포넌트 스캔(`@ComponentScan`) : 어떤 클래스들이 Spring Bean인지 찾아서 등록
        2) 컴포넌트 (`@Component`) : 이 클래스가 Spring Bean이야!라고 표기
        -> 빈으로 등록하고 싶은 클래스에 `@Component`를 붙이면 된다

### 의존성 주입(Dependency Injection, DI)

: 내가 의존하는 객체를 직접 생성하지 않고 밖에서 주입 받는 것
-> 매번 필요한 객체를 생성하는 대신, 생성해둔 객체를 사용하므로 메모리를 효율적으로 사용가능

* **의존성 주입 방법** :
    1.  생성자 주입
    2.  필드 주입
    3.  수정자 주입 (세터 주입)

* **생성자 주입 방법** :
    1.  안전하게 `final`로 선언 : \[final: 변수에 한 번만 값을 할당할 수 있게 하여 변경을 막음]
    2.  생성자에 `@Autowired`을 사용하면, 생성자를 통해 빈을 주입함
    3.  만약 생성자 하나만 있다면, `@Autowired`를 생략할 수 있음

* **생성자 주입 방법(간단 ver)** :
    1.  필요한 의존성을 `final` 키워드를 사용해 추가
    2.  `@RequiredArgsConstructor`를 사용해 생성자를 추가

---

# <실습내용>

### Controller 구현

com.example.shop에 member라는 패키지를 추가하고 그 안에 MemberController 클래스를 추가
-> `@Controller`와 `@ResponseBody`, `@RequiredArgsConstructor` 추가
-> `@Controller`는 MemberController가 Controller라는걸 명시
-> `@ResponseBody`는 메서드 반환값을 HTTP 응답 본문에 직접 작성하게 해줌
-> `@RequiredArgsConstructor`는 모든 필드값을 파라미터로 받는 생성자를 생성, 비슷한게 NoArgsConstructor(파라미터가 없는 디폴트 생성자를 생성하는 어노테이션)도 있음.
-> `@RequestMapping("/members")` 는 공통된 endpoint(여기선 /members)가 있을때 사용

#### API 명세서 작성해보기
등록, 조회, 상세조회, 수정, 삭제의 api를 만들어보기

**1. 회원 등록(createMember):**
- `@PostMapping` 사용. endpoint는 `/members`
- `@RequestBody` : 클라이언트가 보낸 JSON 데이터를 `MemberCreateRequest` 객체로 변환
- `ResponseEntity.created(...)` : 회원 생성이 성공했다는 의미로 HTTP 상태 코드 201 Created를 반환. `URI.create(...)`를 통해 응답 헤더에 생성된 회원의 상세 조회 URI를 포함시킴

**2. 모든 회원 리스트 조회(getMember):**
- `@GetMapping` : HTTP `GET` 요청을 처리하며, 주로 데이터를 조회할 때 사용
- `ResponseEntity.ok(members)` : 조회에 성공했다는 의미로 HTTP 상태 코드 200 OK와 함께 조회된 회원 목록(member)을 응답 본문에 담아 반환

**3. 개별 회원 상세 조회(getMember) :**
- `@PathVariable Long memberId` : URL 경로에 포함된 `{memberId}` 값을 Long 타입의 `memberId` 파라미터로 받음

**4. 회원 정보 수정 (updateMember) :**
- `@PatchMapping("/{memberId}")` : HTTP `PATCH` 요청을 처리하며, 리소스의 일부만 수정할 때 사용
- `ResponseEntity.ok().build()` : 수정이 성공적으로 처리되었음을 알리는 200 OK 상태 코드를 반환

**5. 회원 삭제(deleteMember) :**
- `@DeleteMapping("/{memberId}")` : HTTP `DELETE` 요청을 처리하며, 리소스를 삭제할 때 사용
- `ResponseEntity.noContent().build()` : 삭제가 성공적으로 이루어져 더 이상 보여줄 콘텐츠가 없다는 의미의 204 No Content 상태 코드를 반환

---

### Service 계층 구현
1.  생성자 주입
2.  `@transactional`
3.  `(readOnly = true)` 옵션
    -> 조회만 하는 경우, 트랜잭션 내에서 데이터가 변경되지 않도록 readOnly 속성을 활성화

-   **`@Service`** : 이 클래스가 비즈니스 로직을 담당하는 서비스 계층의 컴포넌트임을 스프링에게 알려줌
-   **`private final MemberRepository memberRepository`** : 데이터베이스에 접근하는 창고 관리인 역할을 하는 `MemberRepository`를 의존성 주입받음. 실제 데이터 저장, 조회, 수정, 삭제는 이 Repository를 통해 이루어짐
-   **`@Transactional`** : 이 어노테이션이 붙은 메서드는 하나의 트랜잭션 단위로 묶임. 메서드 내의 모든 작업이 성공적으로 끝나야만 실제 데이터베이스에 반영되며, 중간에 오류가 발생하면 모든 작업이 원상 복구됨.