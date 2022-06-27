- Entity들은 대부분 다른 Entity 와 연관 관계를 맺고 있다.
- JPA는 entity에 연관 관계를 매핑해두고 해당 entity와 연관된 entity를 사용하여 좀 더 객체지향적으로 프로그래밍할 수 있도록 도와준다.

### 연관 관계 매핑 종류

1. 일대일(1:1) @OneToOne
2. 일대다(1:N) @OneToMany 
3. 다대일(N:1) @ManyToOne
4. 다대다(N:M) @ManyToMany

### 방향성

- 테이블에서의 관계는 항상 양방향이지만, Entity는 단방향과  양방향이 모두 존재함.

```java
@OneToOne
    @JoinColumn(name="member_id") //매핑할 외래키 지정
    private Member member;
```

### 일대일 단방향 매핑

```java
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="member_id")
    private Member member;

}
```

- JPA는 영속성 컨텍스트에 데이터를 저장 후 트랜잭션이 끝날 때 flush()를 호출하여 데이터베이스에 반영한다.
- JPA는 영속성 컨텍스트로부터 엔티티를 조회 후 영속성 컨텍스트에 엔티티가 없을 경우 데이터베이스를 조회한다.
- 엔티티를 조회할 때 해당 엔티티와 매핑된 엔티티도 한 번에 조회하는 것을 *즉시 로딩* 이라고 한다.
- OneToOne, ManyToOne으로 매필할 경우 **즉시 로딩을 기본 Fetch 전략으로 설정한다.**

### 다대일 단방향 매핑

```java
@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
```

- 한 명의 회원은 여러 번 주문을 할 수 있으므로 다대일 단방향 매핑.

- 양반향 연관 관계로 설정할 시, 누가 외래키를 관리할지를 정해야 한다.
    - 연관 관계의 주인은 외래키가 있는 곳으로 설정
    - 연관 관계의 주인이 외래키를 관리
    - 주인이 아닌 쪽은 연관 관계 매핑 시 mappeBy 속성의 값으로 연관 관계의 주인을 설정
    - 주인이 아닌 쪽은 읽기만 가능

> 다대다 매핑은 보통 사용하지 않음. 이유는 컬럼을 추가할 수 없기 때문.
> 

### 영속성 전이(cascade)

- 엔티티 상태를 변경할 때 해당 엔티티와 연관된 엔티티의 상태 변화를 전파시키는 옵션.
- 부모는 One에 해당하고 자식은 Many에 해당됨.
- 예를 들어 Order 엔티티가 삭제되었을 때 OrderEntity가 함께 삭제되거나, Order 엔티티를 저장할 때 Order Entity에 담겨있던 OrderItem 엔티티를 한꺼번에 저장할 수 있음.

| cascade 종류 | 설명 |
| --- | --- |
| PERSIST | 부모 엔티티가 영속화될 때 자식 엔티티도 영속화 |
| MERGE | 부모 엔티티가 병합될 때 자식 엔티티도 병합 |
| REMOVE | 부모 엔티티가 삭제될 떄 연관된 자식 엔티티도 삭제 |
| REFRESH | 부모 엔티티가 refresh 되면 연관된 자식 엔티티도 referesh |
| DETACH | 부모 엔티티가 detach되면 연관된 자식 엔티티도 detach |
| ALL | 부모 엔티티의 영속성 상태 변화를 자식 엔티티에 모두 전이 |

### 고아 객체 제거

- 부모 엔티티와 연관 관계가 끊어진 자식 엔티티를 고아 객체라고 함.
- 참조하는 곳이 하나일 때만 사용해야 함.

### 지연 로딩

- 즉시 로딩은 개발자로 하여금 어떤 쿼리가 어떻게 실행될지 예측할 수 없게 하므로 실무에서 사용하기 힘듬.

### Auditing을 이용한 엔티티 공통 속성 공통화

- Jpa에서는 Auditing 기능을 제공하여 엔티티가 저장 또는 수정될 때 자동으로 등록일, 수정일, 등록자, 수정자를 입력해 줌.

```java
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="member_id")
    private Member member;

}
```

```java
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="member_id")
    private Member member;

}
```
