### ❓ Thymeleaf

- 화면을 동적으로 만들기 위한 템플릿 엔진.
- 서버에서 요청이 올 때마다 html을 만들어주기 때문에 SSR 방식이다.

```java
@Controller
@RequestMapping(value="/thymeleaf")
public class ThymeleafExController {
    @GetMapping(value = "/ex1")
    public String thymeleafEx1(Model model){
        model.addAttribute("data", "타임리프 예제입니다.");
        return "thymeleafEx/ex1";
    }
```

- `http://localhost/thymeleaf/ex1` 에 접속하면 thymeleafEx 폴더 아래의 ex1.html이 나오게 된다.

### ❓ Spring Boot Devtools

- Automatic Restart  : vs code의 Go Live 같은 기은
- Live Reload : 정적 자원 수정 시 새로고침 없이 바로 적용할 수 있다.
- Property Defaults : Thymeleaf는 기본적으로 성능을 향상시키기 위해서 캐싱 기능을 사용함

### Tymeleaf 예제

```java
// Controller file
@GetMapping(value = "/ex2")
    public String thymeleafEx2(Model model) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemNm("테스트상품");
        itemDto.setItemDetail("상품상세설명");
        itemDto.setPrice(1000);
        itemDto.setRegTime(LocalDateTime.now());

        model.addAttribute("itemDto", itemDto);
        return "thymeleafEx/ex2";
    }
```

**th:text 예제**

```html
// html file
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>상품 데이터 예제</h1>
<div>상품명 : <span th:text="${itemDto.itemNm}"}></span></div>
<div>상품상세설명 : <span th:text="${itemDto.itemDetail}"}></span></div>
<div>상품등록인 : <span th:text="${itemDto.regTime}"}></span></div>
<div>상품가격 : <span th:text="${itemDto.price}"}></span></div>
</body>
</html>
```

**th:each 예제**

```java
<tr th:each="itemDto, status: ${itemDtoList}">
// 반복문 활용 가능
```

**th:if, th:unless, th:switch, th:case 예제**

```java
<tr th:each="itemDto, status: ${itemDtoList}">
        <td th:if="${status.even}" th:text="짝수"></td>
        <td th:unless="${status.even}" th:text="홀수"></td>
// status에는 반복에 대한 정보가 존재함. 

<td th:switch="${status.even}">
                    <span th:case="true">짝수</span>
                    <span th:case="false">홀수</span>
                </td>
// 위와 같이 switch문으로도 작성 가능.
```

**Parameter 전달**

```java
<a th:href="@{/thymeleaf/ex6(param1 = '파라미터 데이터1', param2 = '파라미터 데이터2')}">파라미터 전달</a>
//위와 같이 parameter를 전달하고

<div th:text="${param1}"></div>
<div th:text="${param2}"></div>
//이렇게 받음
```

### Tymeleaf 페이지 레이아웃

**th:replace** 

- 해당 속성이 선언된 html tag를 다른 html 파일로 치환
```java
<div th:replace="fragments/header::header"></div>
```
