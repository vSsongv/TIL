# Global Attribute
> html의 전역 속성들에 대해 다룬다.
## 🌎전역 속성(Global Attribute)
- 어떤 태그에도 전부 다 사용할 수 있는 속성
### 🚩 id
- 문서 전체에서 유일한 고유식별자. 원하는 특정 요소를 고르거나, 스크립트 및 스타일 적용 시 특정 요소를 식별하기 위해 사용된다. id는 영어로 시작해야 하고, 공백이 없어야 한다.
### 🚩 class
- id와 마찬가지로 식별자로 사용됨, 그러나 중복이 가능하고(같은 class로 요소들을 묶을 수 있다.) 공백으로 서로 다른 클래스를 구분한다.
```
  <p class="note editorial">Above point sounds a bit obvious. Remove/rewrite?</p>
  <p class="note">[Lights go up and wind blows; Caspian enters stage right]</p>
```
### 🚩 style
- 하나의 요소에 스타일을 적용할 떄 사용. **style은 별도의 파일에 정의하여 사용하는 것이 좋다.**
```
<div style="background: #ffe7e8; border: 2px solid #e66465;">
    <p style="margin: 15px; line-height: 1.5; text-align: center;">
        Well, I am the slime from your video<br>
        Oozin' along on your livin' room floor.</p>
</div>
``` 
![](https://images.velog.io/images/songjy377/post/89421e35-ea78-4907-bfbe-18c500cf9db3/image.png)
### 🚩 hidden
- 요소를 화면에서 숨기고자 할 때 사용한다. 브라우저는 hidden 속성을 설정한 요소를 렌더링 하지 않는다.
- <span style = "color:red">참고: hidden 특성을 가진 요소의 CSS display 속성 값을 변경하면 특성으로 인한 동작을 재정의한다.. 예컨대 display: flex를 지정한 요소는 hidden 특성이 존재하더라도 화면에 보이게 된다.</span>
### 🚩 title
- 요소와 관련된 추가 정보를 제공하는 텍스트를 나타낸다.
- 요소에 마우스를 올리면 추가 정보를 보여준다.
### 🚩 lang
- 요소 내의 수정 불가한 텍스트의 언어와, 수정 가능한 텍스트가 사용해야 하는 언어를 정의한다.
- **웹 접근성을 높일 수 있는 수단**이다. 스크린리더가 태그를 보고 언어를 선택할 수 있기 때문이다.
```
  <p lang="en-GB">This paragraph is defined as British English.</p>
  <p lang="fr">Ce paragraphe est défini en français.</p>
```
### 🚩 data
- js에서 정보가 필요할 때, 정보를 변수화할 때 이용한다.
```
<article>
	data-cloumns="3"
    data-index="10"
    data-paren="car"
</article>
``` 
### 🚩 draggable
- 요소의 드래그 가능 여부를 나타내는 열거형 특성으로, 네이티브 브라우저 동작 방식과 HTML Drag and Drop API 모두 통제한다. true / false값으로 조절 가능하다. js에서 사용할 수 있다.
