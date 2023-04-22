- project setting
    
    - src/main/java package 아래 java 소스코드
    
    - resource 아래 html, css, js 정적 리소스 저장
    
    - thymeleaf는 view를 main/resources/templates에서 찾음.
    
    - src/test/java 아래 test code 작성
    
    - @RestController
    
    	- Restful API를 조금 더 쉽게 만들어주는 annotation.
    	- Controller + ResponseBody(Java)
    
    - @GetMapping
    
    	- 클라 요청을 처리할 URL 매핑

### Lombok

- getter/setter, ToString과 같은 반복적인 자바 코드를 자동으로 생성해줌

| 어노테이션 | 기능 |
| --- | --- |
| @Getter/Setter | 코드를 컴파일할 때 속성들에 대한 Getter/Setter 메소드 생성 |
| @ToString | toString() 메소드 생성 |
| @ToString(exclude={”변수명”}) | 원하지 않는 속성을 제외한 toString() 메소드 생성 |
| @NonNull | 해당 변수가 null 체크, NullPointerException 예외 발생 |
| @EqualsAndHashCode | equals()와 hashCode() 메소드 생성 |
| @Builder | 빌더 패턴을 이용한 객체 생성 |
| @NoArgsConstructor | 파라미터가 없는 기본 생성자 생성 |
| @AllArgsConstructor | 모든 속성에 대한 생성자 생성 |
| @RequiredArgsConstructor | 초기화되지 않은 Final, @NonNull 어노테이션이 붙은 필드에 대한 생성자 생성 |
| @Log | log 변수 자동 생성 |
| @Value | 불변(immutable) 클래스 생성 |
| @Data | @ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArg-sConstructor를 합친 어노테이션 |
