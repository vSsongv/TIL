# Font & Text 속성
> font와 text를 꾸밀 수 있는 css요소들을 다룬다.
## 🎀Font
- font에 관련한 css속성들이다.
- **font-size, font-family**는 반드시 포함되어야 한다.
- **font-size** : font의 size를 지정해준다.  **ex)font-size: 16px;**
- **font-style** : font를 기울여준다. **ex)font-style: italic;**
- **font-weight** : font의 weight나 boldness를 정해준다. 몇몇 폰트들은 normal 나 bold 일 때만 가능하다. **ex)font-weight: bold;** (직접 숫자로 입력 가능하다.)
- **font-family** : font 종류를 변경 가능하다. **ex) font-family: 'Times New Roman';** 종류가 여러가지라면 앞에서부터 브라우저가 지원하는 font를 선택한다.
- **line-height** : font의 위 아래 여백을 설정해준다. font의 종류에 상관 없이 균일성을 줄 수 있다. **ex)line-height: 2.0;**
- **shorthand** : 서로 다른 여러 가지 CSS 속성의 값을 지정할 수 있다. 이때 font-size, font-family는 **반드시 맨 뒤에 위치해야 한다.**
```
	/* 아래 코드는 완벽히 동일한 역할을 한다. */
	  .text {
        font-size: 20px;
            font-family: 'Times New Roman';
            font-weight: 100
            font-style: italic
      }
       /* shorthand로 작성된 코드 */
         .text {
            font: italic 100 20px 'Times New Roman';
         }
```         
- **letter-spacing** : 글자 사이의 간격을 조절한다. **ex)letter-spacing: 16px;**
- **word-spacing** : 단어와 단어 사이, 태그와 태그 사이의 거리를 설정한다. '%'값 또한 사용 가능하다.  **ex)letter-spacing: 16px;**
## 🎀Text
- 전체 text에 관련한 css속성들이다.
- **text-align** : **블록 요소**나 표의 칸 상자의 가로 정렬을 결정한다. 가로 길이를 가진 요소들에 조금 더 right, left, center로 나눌 수 있다. **ex)text-align: left;**
- **text-indent** : **블록 요소**의 앞 부분을 얼마나 띄울지 설정할 수 있다. 상속되는 속성이므로 부모 class에 설정해주면 자식 요소들에게 모두 적용된다. '%'값으로도 사용 가능하다. **ex)text-indent: 30px;**
- **text-transform** : 글씨의 형태를 변경할 수 있다. **uppercase(모두 대문자로 변경), lowercase(모두 소문자로 변경), full-width, capitalize(단어의 첫 글자만 대문자로 변경),** 
- **text-decoration** : 글씨의 장식(선) 색을 지정한다. **1.text-decoration-line**, **2.text-decoration-color**, **3.text-decoration-style**, **4.text-decoration-thickness**을 정할 수 있다.
  1. **text-decoration-line** : _default는 none으로, 얘를 선언해주지 않으면 아래 애들은 쓸 수 없다._ 선을 그릴 위치를 지정한다. 
     - underlin : 밑줄 
     - overline, : 윗줄
     - line-through : 취소선
     로 3가지를 사용할 수 있고, 동시에 사용 가능하다. 보통 text-decoration으로 쓴다.
  2. **text-decoration-style** : 선의 형태를 지정한다. default는 solid이다. 
     - solid : 잘 이어진 한 줄로 그린다.
     - double : 두 줄로 그린다.
     - dotted : 점선으로 그린다.
     - dashed : 좀 더 긴 점선으로 그린다.
     - wavy : 물결 무늬로 그린다.
     
      |코드|결과|
      |-|-|
      | span { color: blue text-decoration: line-through red 2px } | ![](https://images.velog.io/images/songjy377/post/a18f6b20-bc9f-4a58-931d-ea40d607eb6d/image.png) |
      | span { color: blue text-decoration: blakc line-through red wavy } | ![](https://images.velog.io/images/songjy377/post/20597420-2e6a-48ce-9085-205004bc81ae/image.png)

- **word-break**
  - 텍스트가 자신의 콘텐츠 박스 밖으로 넘어갈 때 박스 밖으로 나갈지, 줄바꿈을 할지 지정한다. 한중일 언어는 기본적으로 break-all속성이다.
  - 언어에 따라 기본값이 다르기 때문에 **break-all, keep-all**으로 설정이 가능하다.
break-all : 가로 크기에 맞게 자른다.
keep-all : overflow가 나도 무시한다.
