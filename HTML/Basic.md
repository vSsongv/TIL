## 💻Web 기초 개념
>HTML 기초 개념들에 대해 다룬다.(210927)
### ❔Web Standard(웹 표준성이란?)
* 모든 브라우저에서 공통으로 적용되는 문법으로, 보통 HTML5를 의미한다. 
- 웹에서 사용되는 기술의 표준적인 사용 규격.
- W3C에서 만듬. (JS는 ECMA)
### 🔰Web 표준 기술
- HTML, CSS, JS, Dom(Document Object Model)
### ❓Web Accessibility(웹 접근성이란?)
- **장애를 가진 사람**, **장애를 갖지 않은 사람** **모두**에게 **동등하게** 웹에 접근할 수 있게 하는 것. (_스크린리더, 화면 돋보기, 음성 인식, 키보드 오버레이 등이 포함된다._)
  - 일시적으로 키보드나 마우스를 사용하지 못하는 상황이라던지, 밝은 햇빛이나 소리를 듣기 힘든 환경에 있어 상황적 제약을 겪는 사람
  - 팔이 부러지거나 안경을 잃어버려서 "일시적인 장애"를 겪는 사람
- <span style="color: red">이런 기능이 잘 되어있는 사이트가 **접근성이 좋은 사이트**라고 한다.</span>
- 접근성이 좋은 개발을 하는 것이 중요함.
### ❔Cross Browsing(웹 호환성이란?)
- 웹 브라우저 버전, 종류와 관계없는 웹사이트 접근
- 동작, 레이아웃, 플러그인을 호환시키는 것.

## 📃HTML 기초
### HTML = Hypertext Markup Language
### 🎫DOCTYPE
- 문서형 정의(DTD)
>이전 버전의 HTML(HTML2~HTML4)은 SGML(Standard Generalized Markup Language)에 기반을 두어 만들어졌기 때문에 DTD 참조가 필요하며, 이 때문에 DOCTYPE 선언을 하려면 공개 식별자와 시스템 식별자가 포함된 긴 문자열을 작성해야 한다.
[출처](https://webdir.tistory.com/40)
- 어떤 HTML 버전으로 작성되었는지 미리 선언해서 올바른 내용을 표시할 수 있도록 해주는 것.
```
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
```
- \- 는 업계 표준을 의미, +는 W3C표준을 의미

**1. HTML 4.01**
>버전  :  HTML 4.01 Strict
>설명  :  strict 단어 뜻대로 웹 표준을 엄격하게 지키는 버전. center, font를 포함한 14가지 태그를 사용하지 않음.

>버전 : HTML 4.01 Transitional
>설명 : 아직까지도 많이 쓰이며 프레임을 제외한 모든 태그 사용이 가능

>버전  : HTML 4.01 Frameset
>설명  :  프레임 관련 태그까지 모두 사용 가능 

**2. XHTML**
- HTML 버전을 미러링하는 계열 XML 마크 업 언어의 일부

>버전  :  XHTML 1.0 Strict
>설명  :   웹 표준을 엄격히 지키는 버전

>버전  :  XHTML 1.0 Transitional
>설명  :  역시 여전히 쓰이며 기존 웹 페이지와의 호환성을 위해 쓰인다

>버전  :  XHTML 1.0 Frameset
>설명  : 마찬가지 프레임 관련 태그 사용가능

<span style="color: red">**3. HTML5**</span>
>버전 :  HTML5
>설명  :  현재 사용되고 있는 HTML 버전. 
> 선언부가 매우 간단해졌다. 웹표준을 지키며 디자인을 위한 14개의 태그가 삭제되었다. 대형 웹사이트 들은 대부분 HTML5로 갈아탔다. 

### ✅HTML과 XHTML의 차이
|HTML|XHTML|
|----|-----|
|대소문자 구분 안함|대소문자 구분|
|종료 태그 없어도 됨|종료 태그 필수|
|속성 값 따옴표 없어도 가능|속성 값은 반드시 따옴표 안 `<p align="center">`|

### 🔰Tag
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
### 🔲Block / 🔳Inline
- 🔲 **Block level** 요소는 언제나 새로운 줄에서 시작하고, 좌우 양쪽으로 최대한 늘어나 **가능한 모든 너비**를 차지한다.
- Block 요소 종류
> address, article, aside, audio, blockquote, canvas, dd, div, dl, fieldset, figcaption, figure, footer, form, h1, h2, h3, h4, h5, h6, header, hgroup, hr, noscript, ol, output, p, pre, section, table, ul, video
- 🔳 **Inline level** 요소는 줄의 어느 곳에서나 시작할 수 있고, 바로 이전 요소가 끝나는 지점부터 시작하여 **요소의 내용까지만** 차지한다. 인라인 요소안에 다른 인라인 요소가 포함될 수 있다.
- Inline은 Block요소 안에 포함 가능하다.
- Inline 요소 종류
>a, abbr, acronym, b, bdo, big, br, button, cite, code, dfn, em, i, img, input, kbd, label, map, object, q, samp, small, script, select, span, strong, sub, sup, textarea, tt, var

![block](https://images.velog.io/images/songjy377/post/b373cdfb-e71d-45b5-9f2f-d8ea86cf0bac/bb.jpg)

### Content Categories
![](https://images.velog.io/images/songjy377/post/46333756-1bce-426f-98e5-96b06c4b3217/image.png)
- Metadata Content : 문서의 메타데이터(다른 문서를 가리키는 링크 등을 나타내는 요소)
- Flow Content : 웹 페이지상의 메타데이터를 제외한 모든 요소
- Section Content : 구획을 나눌 때 사용
- Heading Content : 섹션의 제목과 관련된 요소들
- Phrasing Content : 문단에서 text를 markUp할 때 사용
- Embedded Content : 이미지나 비디오 등 외부 소스를 가져오거나 삽입할 때 사용되는 요소
- Interactive Content : 사용자와의 상호작용을 위한 컨텐츠 요소