# CSS Basic
> CSS에 대한 기초 설명이다.(210928)
### CSS = Cascading Style Sheets 
### 🔵 CSS는 룰 기반 언어이고, css로 특정 요소, 특정 요소 집합의 스타일 규칙을 정의할 수 있다.
### 🔵 CSS 기본 구조
![](https://images.velog.io/images/songjy377/post/e349ac94-b677-4f14-a40b-f4b891f12c89/image.png)
- ✅ Selector(선택자) : 어떤 html요소를 꾸밀지 명시한다.
- ✅ declation block(선언 블록)'{}' : 내부의 여러 선언들을 작성한다.
- ✅ declation(선언) : property와 value로 이루어진 쌍
- ✅ 주석은 /*comment*/로 작성한다.

### 🔵 CSS을 HTML에 적용하는 방법
### 1.Embadded (내부 스타일)
- head tag내에 style block을 쓰는것
![](https://images.velog.io/images/songjy377/post/d42a4d7a-b8fd-4b9a-8f22-be4a171b0743/image.png)
### 2.Inline (인라인 스타일) - `사용 지양
- 딱 하나의 요소에만 style적용을 하는 것, style 속성을 사용한다. 딱 하나만 선택하는 것이므로 selector 없이 선언부만 필요하다.
- font-size
![](https://images.velog.io/images/songjy377/post/448db2d2-186c-4113-b198-a316ff2d0245/image.png)
### 3.External (외부 스타일) - `가장 좋은 방법
- 별도로 분리한 css 파일을 link 속성을 사용하여 연결해주는 방법.
![](https://images.velog.io/images/songjy377/post/2f2480f5-855d-4a34-b7e1-f5c9174f2c62/image.png)
### 🔵 Cascading 원칙
### 1.스타일 우선순위
- 동일한 스타일이라도 **선언된 곳**에 따라 우선순위가 정해진다.
    - 브라우저에 의해 정의된 스타일 < 개발자가 선언한 스타일 < 사용자가 구성한 스타일
- 적용 범위가 적을수록 우선시된다.
  - ex) tag < class < id < inline
- 소스코드의 순서가 뒤에 있으면 덮어쓴다.
### 2.스타일 상속
- **부모 요소에 있는 속성들은 자식 요소에게 전달된다.**
- 상속이 안되는 속성도 있다. ex)배경, 이미지, 배경 색 등
