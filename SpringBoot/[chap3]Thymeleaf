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
