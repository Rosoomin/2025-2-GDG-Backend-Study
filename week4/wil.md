# ERD (Entity - Relationship Diagram)

## ERD란
ERD(Entity - Relationship Diagram) = 데이터 청사진

- **Entity(개체)**: 데이터를 가진 대상  
  예) 회원, 상품, 주문 내역 등은 어떤 속성과 데이터를 가지고 있는지 표현  
- **Relation(관계)**: 개체 사이의 연관성  
  예) 어떤 회원이 어떤 상품을 주문했는지 나타냄  

즉, 개체-관계 중심의 모델링 기법을 **ER Model**이라 한다.

---

## 핵심 용어

### 1. 엔티티(Entity)
관리해야 할 데이터의 주체  
예시:  
- 회원(Member)  
- 상품(Product)  
- 주문(Order)

---

### 2. 속성(Attribute)
각 엔티티가 가지는 구체적인 정보  
속성 = 필드 = 컬럼  
예시:  
- Member : id, name, address  
- Product : name, price, stock

---

### 3. 기본 키 (Primary Key)
고유하게 식별하는 데 사용되는 하나 이상의 컬럼(필드)  
각 엔티티의 식별자 역할을 함  
예시:  
- Member : `member_id`  
- Product : `product_id`  
- Order : `order_id`  
Member 엔티티의 id, name, address 중 id가 PK이다.

---

### 4. 외래 키 (Foreign Key)
다른 테이블의 PK를 참조(저장)하는 속성(칼럼)  
테이블 간 연결 고리 역할  

예시:  
- Member 테이블 → `member_id` (PK)  
- Order 테이블 → `order_id` (PK)  
- Order 테이블 안의 `member_id`가 FK가 된다.

---

### 5. 관계 (Relation)
개체 사이의 연관성 또는 업무 규칙  
실제로는 테이블 또는 외래 키(FK)로 구현된다.  

관계 종류  
- 1:1 (일대일)  
- 1:N (일대다)  
- N:M (다대다)

---

## 관계 예시

### 일대다 (1 : N)
한 명의 회원은 여러 개의 주문 내역을 가질 수 있다.  
즉, **Member(1) : Order(N)** 관계이다.

---

### 다대다 (N : M)
예시: Student(학생), Course(강의)  
- 한 명의 학생은 여러 개의 강의를 수강할 수 있음  
- 하나의 강의도 여러 명의 학생이 수강할 수 있음  

외래 키만으로는 해결 불가능하다.  
학생 한 명당 한 강의만 듣거나, 강의 하나에 한 명만 수강하게 되는 문제가 발생한다.  

**해결책:**  
중간 테이블(Enrollment)을 도입하여 `student_id`, `course_id`를 외래 키로 가진다.  
이를 통해 다대다 관계를 **1:N + N:1** 구조로 분해한다.

---

## 식별 관계 vs 비식별 관계

| 구분 | 설명 |
|------|------|
| 식별 관계(Identifying) | 강한 연관 관계. 관계 대상의 PK를 자신의 PK로도 사용 |
| 비식별 관계(Non-Identifying) | 느슨한 연관 관계. 관계 대상의 PK를 자신의 FK로만 사용 |

보통 실제 설계에서는 **비식별 관계**를 선택한다.

---

## ORM (Object - Relational Mapping)

객체(Object)와 관계형 데이터베이스(Relational DB)의 데이터를 자동으로 매핑하는 기술이다.  
JPA는 ORM 프레임워크의 대표적인 예시이며,  
SQL을 직접 작성하지 않아도 엔티티 클래스를 통해 테이블 생성 및 데이터 조작이 가능하다.

---

## 엔티티(Entity)

자바와 데이터베이스가 소통하는 단위이다.  
엔티티 클래스를 정의하면,  
JPA가 이를 참고하여 **테이블 생성 SQL문**을 자동으로 작성하고 실행한다.  
따라서 SQL 작성 시간을 단축할 수 있다.

---
<실습>
1. 엔티티 클래스
- @Entity, @Id 어노테이션 필요
- Id값을 자동 생성 : @GeneratedValue를 사용
- @Column으로 컬럼 명, 컬럼 타입 등을 지정

2. 외래키 (FK)
-엔티티 객체를 필드로 넣은 후 @JoinColumn, @ManyToOne 사용
- @ManyToOne 에서 fetch 속성은 LAZY로 지정
- '엔티티 객체'를 필드로 지정 -> ORM이 외래키로 알아서 처리
@JoinColumn : FK 칼럼 정보를 명시
@ManyToOne : 해당 외래키로 생기는 연관관계 종류를 나타내는 어노테이션

3. 엔티티 생성자
- 보통 id 필드를 제외하고 생성
- JPA가 엔티티를 사용하려면 인자 없는 생성자가 필요 -> @NoArgsConstructor 어노테이션으로 만들 수 있음
- access 속성을 통해 접근 제한자를 protected로 설정 -> JPA는 사용가능, 외부 사용 차단
- 추가로 엔티티 객체에 @Getter를 추가 -> 모든 필드에 getter 생성

