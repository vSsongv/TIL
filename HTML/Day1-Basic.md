# First Day - Basic

## 💻Web 기초 개념
### ❔Web Standard(웹 표준성이란?)
* 모든 브라우저에서 공통으로 적용되는 문법으로, 보통 HTML5를 의미한다.
### ❓Web Accessibility(웹 접근성이란?)
- **장애를 가진 사람**, **장애를 갖지 않은 사람** 모두에게 **동등하게** 웹에 접근할 수 있게 하는 것. (_스크린리더, 화면 돋보기, 음성 인식, 키보드 오버레이 등이 포함된다._)
  - 일시적으로 키보드나 마우스를 사용하지 못하는 상황이라던지, 밝은 햇빛이나 소리를 듣기 힘든 환경에 있어 상황적 제약을 겪는 사람
  - 팔이 부러지거나 안경을 잃어버려서 "일시적인 장애"를 겪는 사람
- <span style="color: red">이런 기능이 잘 되어있는 사이트가 **접근성이 좋은 사이트**라고 한다.</span>
### ❔Cross Browsing(웹 호환성이란?)
- 웹 브라우저 버전, 종류와 관계없는 웹사이트 접근
- 동작, 레이아웃, 플러그인을 호환시키는 것.

## 📃HTML
### Tag
tag 사이에 content가 들어간다.
```
<open tag>content<close tag>
```
이미지, 줄바꿈 들 내용이 없는 경우 close tag는 딱히 필요가 없다. ex) br, hr, img, meta, input
```
<ul> - list를 생성하는 tag
<li> - list의 요소들.
주석 -> <!--comment-->
<기본 골격>
<html>
    <head>
    	content
    <\head>
    <body>
    	content
    <\body>
<\html>
```
### Block / Inline
Block level 요소는 언제나 새로운 줄에서 시작하고, 좌우 양쪽으로 최대한 늘어나 **가능한 모든 너비**를 차지한다.
Inline level 요소는 줄의 어느 곳에서나 시작할 수 있고, 바로 이전 요소가 끝나는 지점부터 시작하여 **요소의 내용까지만** 차지한다.
Inline은 Block요소 안에 포함 가능하다.
![block](https://images.velog.io/images/songjy377/post/b373cdfb-e71d-45b5-9f2f-d8ea86cf0bac/bb.jpg)

### Content Categories
![](https://images.velog.io/images/songjy377/post/46333756-1bce-426f-98e5-96b06c4b3217/image.png)
- Flow Content : 웹 페이지상의 메타데이처를 제외한 모든 요소
- Section Content : 구획을 나눌 때 사용
- Heading Content : 섹션의 제목과 관련된 요소들
- Phrasing Content : 문단에서 text를 markUp할 때 사용
- Embaded Content : 이미지나 비디오 등 외부 소스를 가져오거나 삽입할 때 사용되는 요소
- Interactive Content : 사용자와의 상호작용을 위한 컨텐츠 요소