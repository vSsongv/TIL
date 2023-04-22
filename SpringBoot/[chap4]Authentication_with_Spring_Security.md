- 인증
    - 웹에서 해당 리소스에 대해 작업을 수행할 수 있는 주체인지 확인하는 것
- 인가
    - 인증 과정 이후 접근 권한을 가지게 되는 것.

```java
<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf}">
```

- Spring Security를 이용하기 위해서는 기본적으로 CSRF를 방어하기 위해 모든 post방식의 데이터 전송에는 CSRF 토큰 값이 있어야 함.
    - CSRF(Cross Site Request Forgery) : 사용자가 자신의 의지와 상관없이 해커가 의도한 대로 CRUD의 행위를 하도록 하는 공격.
- CSRF토큰은 서버에서 허용한 요청이 맞는지 확인하기 위한 토큰.
- 사용자의 세션에 임의의 값을 저장하여 요청마다 그 값을 포함하여 전송하면 서버에서 세션에 저장된 값과 요청이 온 값이 일치하는지 확인하여 CSRF를 방어함.

### UserDetailService

- UserDetailService 인터페이스는 DB에서 회원 정보를 가져오는 역할을 담당합니다.
- loadUserByUsername() : 회원 정보를 조회하여 사용자의 정보와 권한을 갖는 UserDetails 인터페이스를 반환합니다.

### UserDetail

- 회원의 정보를 담기 위해서 사용하는 인터페이스

`thymeleaf-extras-springsecurity5`  라이브러리를 통해 ADMIN인 경우에만 특정 메뉴가 보이도록 할 수 있다.

```java
<li class="nav-item" sec:authorize="hasAnyAuthority('ROLE_ADMIN')">
    <a class="nav-link" href="/admin/item/new">상품 등록</a>
</li>
```
