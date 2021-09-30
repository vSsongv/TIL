# Embedded & Metadata Contents
> 이미지, 비디오 등을 포함하는 embedded contents와 metadata contentds에 대해 다룬다.(210928)
## 📷 Embedded Contents
>이미지나 비디오 등 외부 소스를 가져오거나 삽입할 때 사용되는 요소

### &lt;iframe> tag
- 현재 문서 안에 다른 html 페이지를 삽입하기 위해 사용된다.

### 🖼️ &ltimg> tag
```
<img class="fit-picture"
     src="/media/cc0-images/grapefruit-slice-332-332.jpg"
     alt="Grapefruit slice atop a pile of other slices">
     
<img class="aaa" width="100" heigh="150">
```
- 문서에 이미지를 넣을 때 사용한다. 
- **src 특성** : 필수이며, 포함하고자 하는 이미지로의 경로를 지정해준다.
- **alt 특성** : 이미지의 텍스트 설명이다. 필수는 아니지만, 스크린 리더가 alt의 - 값을 읽어 사용자에게 이미지를 설명하므로, **접근성 차원에서 매우 유용**하다. 또한 네트워크 오류, 콘텐츠 차단, 죽은 링크 등 이미지를 표시할 수 없는 경우에도 이 속성의 값을 대신 보여준다.
- 절대경로는 모두가 접근 가능하고, 상대경로는 그렇지 않다.(내 컴퓨터에만 있는 사진인 경우)
- width, height 속성을 통해 크기 제어 가능하다.
- **srcset 특성** : 반응형 이미지를 만들 수 있다. 

```
<img src="imag/large.png"
	 stcset="imag/small.png 300w, 
     		 imag/medium.png 450w, > 화면이 450보다 커지면 medium 이미지로 나옴 
             imag/large.png 600w"> > 화면이 600보다 커지면 large 이미지로 나옴 
```
- size 속성을 통해 이미지 크기를 고정할 수 있다.
```
<img src="imag/large.png"
	 stcset="imag/small.png 300w, 
     		 imag/medium.png 450w, 
             imag/large.png 600w"
      sizes="(min-width: 600px) 600px, > 화면이 600보다 커지기 전까지는 300으로 고정
      		 (min-width: 450px) 450px, > 화면이 450보다 커지기 전까지는 300으로 고정
             300px"> 
```
### 웹에서 사용하는 image 유형

Type | Abbreviation | MIME type | File extension(s) | Summary|
|-|-|-|-|-|
bitmap | JPEG | image/jpeg | .jpg, .jpeg, .jfif, .pjpeg, .pjp | 정지 이미지의 손실 압축에 적합하다.(현재 가장 많이 사용됨.) |
bitmap | PNG | image/png | .png | 원본 이미지를 보다 정확하게 보여주거나 투명도가 필요한 경우 선호됨. |
bitmap | GIF | image/gif | .gif | 여러 장의 이미지로 이루어진 애니메이션 표현 가능. |
bitmap | WEBP | image/webp | .webp | 품질, 압축률 등이 우수하지만 지원 브라우저가 제한적. |
vector | SVG | image/svg+xml | .svg | 다양한 크기로 정확하게 그려야 할 때 사용됨. bitmap 이미지에 비해 확대했을 때 이미지가 깨지지 않는다.
                         vector 이미지 / bitmap 이미지 차이
![](https://images.velog.io/images/songjy377/post/57583d01-e835-422f-baaa-4ab79c97725c/image.png)

### 🎥 &lt;video> tag
```
<video src="/media/cc0-videos/flower.mp4">
</video>

<video controls width="250">

    <source src="/media/cc0-videos/flower.mp4"
            type="video/mp4">
</video>
```
- 비디오를 삽입할 때 사용한다.
- src 특성은 선택이다. source 태그를 사용할 수 있기 때문. 포함하고자 하는 비디오의 경로를 지정해준다.
- **controls 속성** : 소리 조절(volume), 동영상 탐색(seek), 일시 정지(pause)/재시작(resume)을 할 수 있는 컨트롤러를 제공한다
- **autoplay 속성** : 부울 속성(boolean)으로, 해당 속성이 지정된 경우 비디오가 데이터 로드를 완료하기 위해 중지하지 않고 재생할 수 있는 가장 빠른 시점에 재생되기 시작한다. 그러나 갑자기 소리가 있는 동영상이 재생되면 UX적으로 좋지 않을 수 있기 때문에 **muted**속성과 함께 사용해서 바로 재생되게 할 수 있다.
- width, height 속성을 통해 크기 제어 가능하다.
- **poster 속성** : 사용자가 동영상을 재생하거나 탐색하기 전까지 보여지는 썸네일을 설정할 수 있다.

### 🔊 &lt;audio> tag
- **src 특성** : 한 개 이상의 source 특성을 사용해 오디오 소스를 지정할 수 있으며, 가장 적절한 소스를 브라우저가 고릅니다.
- **controls 속성**, **autoplay 속성**, **muted** 속성은 동일하다.

## 📘 Metadata Contents
>데이터를 설명하는 데이터(책의 저자, 번역가, 사진이 찍힌 날짜, 장소 등)

### 📌 &lt;title> tag
- 브라우저의 제목 표시줄이나 페이지 탭에 보이는 문서 제목을 정의한다.
- 단어로만 이루어진 title은 피해야 한다.
- 상세하게 설명을 넣어 작성하는 것이 좋다.

### 📌 &lt;meta> tag
- 다른 메타관련 요소로 나타낼 수 없는 메타데이터를 나타냅니다.
```
<meta name="viewport">
```
- <**name 속성**>
- name과 content 특성을 함께 사용하면 문서의 메타데이터를 이름-값 쌍으로 제공할 수 있습니다. name은 이름, content는 값을 담당합니다.

- &lt;표준 메타데이터 이름>
   - application-name: 웹 페이지에서 구동 중인 애플리케이션의 이름.
   - author: 문서 저작자.
   - description: 페이지에 대한 짧고 정확한 요약. Firefox, Opera 등 여러 브라우저는 즐겨찾기 페이지의 기본 설명 값으로 description 메타데이터를 사용합니다.
   - generator: 페이지를 생성한 소프트웨어의 식별자.
   - keywords: 페이지의 콘텐츠와 관련된, 쉼표로 구분한 키워드 목록.
   - referrer: 문서에서 시작하는 요청의 HTTP Referer 헤더를 아래 표와 같이 통제합니다.
 - <**viewport 속성**>
 - viewport : 전체 브라우저에서 웹페이지를 볼 수 있는 영역
 - 브라우저에서 보이는 화면을 모바일에서 보이게 하기 위해 사용한다.
### 📌 &lt;link> tag
- 외부 리소스의 관계를 명시한다. 예를 들어, css나 icon을 html에 추가할 때 사용한다.
```
  	<link href="main.css" rel="stylesheet"> -외부 스타일시트 연결
  	<link rel="icon" href="favicon.ico"> -아이콘
```
 - MIME type
 html이 어떤 파일인지 인지하도록 하기 위해(파일 경로를 인지하지 못하는 경우를 위해) type을 명시해주는 것. 
```
   	text/plain, text/html, image/jpeg, image/png
```
### 📌 &lt;script> tag
- 외부 javascript 코드를 불러올 때 사용한다.
- 보통 body의 가장 마지막에 위치시키는 것을 권장한다.

	&lt;script src="javascript.js">&lt;/script>
