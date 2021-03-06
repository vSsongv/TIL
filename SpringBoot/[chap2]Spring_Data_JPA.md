### JPA

- Java Persistence API
- Java ORM(Object Relational Mapping) 기술에 대한 API 표준.

### JPA 사용 시 장점

1. 특정 DB에 종속되지 않음 - 추상화한 데이터 접근 계층을 제공하기 때문에 어떤 DB를 사용하는지 알려주면 얼마든지 DB를 변경할 수 있음.
2. 객체지향적 프로그래밍 - 비즈니스 로직에 집중할 수 있음
3. 생산성 향상 - 새로운 컬럼 추가시 테이블과 매핑된 클래스에 필드만 추가하면 되기 때문에 쉽게 관리 가능. SQL 문을 직접 작성하지 않음.

### JPA 사용 시 단점

1. 복잡한 쿼리 처리 - 통계 처리 같은 복잡한 쿼리를 사용할 경우는 SQL문을 사용하는게 나을 수 있음. 특정 DB에 종속된다는 단점이 생기기 때문에 JPQL을 지원함.
2. 성능 저하 위험 - 객체 간 매핑을 잘못했을 경우 성능 저하가 될 수 있음
3. 학습 시간 - 러닝커브가 긴 편

### JPA 동작 방신

- Entity
    - DB테이블에 대응하는 클래스.
    - item table을 만들고, [Item.java](http://Item.java) 클래스를 만들어서 @Entity 어노테이션을 붙이면 Entity가 됨.
    
- Entity manager
    - 영속성 컨텍스트에 접근하여 Entity에 대한 DB작업을 제공.
    - `find()` : 영속성 컨텍스트에서 엔티티를 검색하고 영속성 컨텍스트에 없을 경우 DB에서 데이터를 찾아 영속성 컨텍스트에 저장함.
    - `persist()` : 엔티티를 영속성 컨텍스트에 저장함.
    - `remove()` : 엔티티 클래스를 영속성 컨텍스트에서 삭제함.
    - `flush()` : 영속성 컨텍스트에 저장된 내용을 DB에 반영함.
    
    ### Entity ****매핑 관련 어노테이션****
    
    | 어노테이션 | 설명 |
    | --- | --- |
    | @Entity | 클래스를 엔티티로 선언, 반드시 기본 키 필요 |
    | @Table | 엔티티와 매핑할 테이블을 지정합니다. |
    | @Id | 테이블의 기본키에 사용할 속성을 지정합니다. |
    | @GeneratedValue | 주키의 값을 위한 자동 생성 전략을 명시하는데 사용합니다. |
    | @Column | 필드와 컬럼을 매핑하는 데 사용합니다. 해당 어노테이션 속성을 사용하면 다양한 제약 조건 추가 가능(문자열 최대 저장 길이 등) |
    | @Lob | BLOB (바이너리 데이터를 DB 외부에 저장하기 위한 타입(이미지, 사운드, 비디오 같은 멀티미디어 데이터를 다룰 때 사용할 수 있습니다), CLOB (문자형 대용량 파일을 저장 하는데 사용하는 데이터 타입)
    을 타입매핑 합니다. |
    | @CreationTimestamp | insert시 시간 자동 저장합니다. |
    | @Enumerated | enum 타입을 매핑합니다. |
    | @Transient | 해당 필드 데이터베이스 매핑을 무시합니다. |
    | @Temporal | 날짜 타입을 매핑할 때 사용합니다. |
    | @CreateDate | 엔티티가 생성되어 저장될 때 시간을 자동으로 저장합니다. |
    | @LastModifiedDate | 조회한 엔티티의 값을 변경할 때 시간을 자동 저장합니다. |

### JpaReposity

| 메소드 | 기능 |
| --- | --- |
|  \<S extends T> save(S entity) | 엔티티 저장 및 수정 |
| void delete(T entity) | 엔티티 삭제 |
| count() | 엔티티 총 개수 반환 |
| Iterable\<T> findAll() | 모든 엔티티 조회 |

### Query Method

```sql
find + (entity 이름) + By + 변수이름

findByItemName

OrderBy + 속성명 + Asc(오름차순) / Des(내림차순) 로 정렬 가능.
```

### @Query annotation

- 쿼리 조건이 많아지면 메소드 이름이 굉장히 길어지기도 함. 이런 문제점에 대응하여 Qurey annotaion 이용!
- JPQL이라는 객체지향 쿼리 언어 이용.

### Querydsl

- Query annotation도 컴파일 시점에는 에러를 발견할 수 없다는 단점이 있다.

| 메소드 | 기능 |
| --- | --- |
|  List\<T> fetch() | 조회 결과 리스트 반환 |
| T fetchOne | 조회 대상이 1건인 경우 제네릭으로 지정한 타입 반환 |
| T fetchFirst() | 조회 대상 중 1건만 반환 |
| Long fetchCount() | 조회 대상 개수 반환 |
| QueryResult\<T> fetchResults() | 조회한 리스트와 전체 개수를 포함한 QueryResults 반환 |

### QueryDslPredicateExcutor

| 메소드 | 기능 |
| --- | --- |
| long count(Predicate) | 조건에 맞는 데이터의 총 개수 반환 |
| boolean exist(Predicate) | 조건에 맞는 데이터 존재 여부 반환 |
| Iterable findAll(Predicate) | 조건에 맞는 모든 데이터 반환 |
| Page\<T> findAll(Predicate, Pageble) | 조건에 맞는 정렬된 데이터 반환 |
| Iterable findAll(Predicate, Sort) | 조건에 맞는 정렬된 데이터 반환 |
| T findOne(Predicate) | 조건에 맞는 데이터 1개 반환 |
