### Modelmapper

- 화면으로부터 전달받은 DTO를 엔티티로 바꿔주고 엔티티를 DTO로 바꿔주는 작업이 복잡하기 때문에 이를 도와주는 역할.
- 서로 다른 클래스의 값을 필드의 이름과 자료형이 같으면 getter, setter를 통해 값을 복사해서 객체를 반환해준다.

### [application.properties](http://application.properties) 설정

- 설정이 validate이면 테스트 코드 실행 시 테이블이 자동으로 생성되지 않으므로 테스트 환경에서는 ddl-auto를 create로 설정한다.

`spring.jpa.hibernate.ddl-auto=validate`

### [application-test.properties](http://application.properties) 설정

`spring.jpa.hibernate.ddl-auto=create`

### Querydsl

- 조회 조건이 복잡할 경우 Querydsl를 이용해 조건에 맞는 쿼리를 동적으로 쉽게 생성할 수 있다.
- 비슷한 쿼리를 재활용할 수 있다는 장점이 있다.
- Interface를 구현하는 클래스는 ‘Impl’를 맨 뒤에 붙여주어야 정상적으로 작동한다.
- BooleanExpression이라는 where절에서 사용할 수 있는 값을 지원한다.

### Querydsl 결과 조회 method

| 메소드 | 기능 |
| --- | --- |
| QueryResults<T> fetchResults() | 조회 대상 리스트 및 전헤 개수를 포함하는 QueryResult 반환 |
| List<T> fetch() | 조회 대상 리스트 반환 |
| T fetchOne() | 조회 대상이 1건이면 해당 타입 반환,
조회 대상이 1건 이상이면 에러 발생. |
| T fetchFirst() | 조회 대상이 1건 또는 1건 이상이면 1건만 반환 |
| long fetchCount() | 해당 데이터 전체 개수 반환, count 쿼리 실행 |
1. **fetch()**
    - 리스트로 결과를 반환하는 방법. 만약에 데이터가 없으면 빈 리스트를 반환
    
    ```java
    List<Member> fetch = queryFactory
    		.selectFrom(member)
            	.fetch();
    ```
    
2. **fetchOne()**
    - 한 건을 조회할 때 사용,
    - 결과가 없을때는 null 반환, 결과가 둘 이상이면 NonUniqueResultException 반환
    
    ```java
    Member fetchOne = queryFactory
    										.selectFrom(member)
    										.fetch();
    ```
    
3. **fetchFirst()**
    - 처음의 한 건을 가져오고 싶을 때 사용. 주석에 있는 표현과 같은 표현
    
    ```java
    Member fetchFirst = queryFactory
    											.selectFrom(QMember.member)
    											//.limit(1).fetchOne()
    											.fetchFirst();
    ```
    
4. **fetchResults()**
    - 페이징을 위해 사용. 페이징을 위해서 total contents를 가져옴
    
    ```java
    QueryResults<Member> results = queryFactory
    		.selectFrom(member)
    		.fetchResults();
    ```
    
5. **fetchCount()**
    - count 쿼리를 날릴 수 있음
    
    ```java
    long count = queryFactory
    		.selectFrom(member)
    		.fetchCount();
    ```
