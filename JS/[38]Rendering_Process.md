# 브라우저의 렌더링 과정
> Replow, Repaint, async, defer, CSSOM, DOM과 같은 브라우저의 렌더링 과정에 대해 다룬다.(211212)

❓ 브라우저는 HTML, CSS, JS로 작성된 텍스트 문서를 어떻게 보여주는 걸까?

간단히 말하면 다음과 같다.

🧩 **요청과 응답**
- HTML, CSS, JS, Image 등의 렌더링에 필요한 리소스를 요청하고 서버로부터 응답을 받는다.

🧩 **HTML/CSS 파싱과 렌더 트리 생성**
- 응답받은 HTML과 CSS를 파싱해 DOM과 CSSOM을 생성하고 이들을 결합해 렌더 트리를 생성한다.

🧩 **JS 파싱과 실행**
- 응답받은 JS를 파싱해 AST를 생성하고 바이트 코드로 변환해 실행한다. 이때 JS는 DOM API를 통해 DOM이나 CSSOM을 변경할 수 있다. 변경된 DOM과 CSSOM은 다시 렌더 트리로 결합된다.

🧩 **리플로우(Reflow)와 리페인트(Repaint)**
- 렌더 트리를 기반으로 HTML 요소의 위치와 크기를 계산하고 브라우저 화면에 HTML 요소를 페인팅한다.

![](https://images.velog.io/images/songjy377/post/9657ccaf-f4db-4b51-9795-4a9b1b66f183/image.png)

<span style="color:logray">그림출처: [poiemaweb](https://poiemaweb.com/)</span>

### ✅ 요청과 응답
> 브라우저의 핵심 기능은 필요한 리소스를 서버에 요청하고 서버로부터 응답받아 브라우저에 시각적으로 렌더링하는 것이다.

- 브라우저의 주소창에 URL을 입력하고 엔터 키를 누르면 URL의 호스트 이름이 DNS를 통해 IP주소로 변환되고, 이 IP주소를 갖는 서버에게 요청을 전송한다.

- 예를 들어, 브라우저 주소창에 https://velog.io 을 입력하고 엔터 키를 누르면 해당 velog 서버로 전송되고, 서버는 이 요청에 대해 암묵적으로 정적 파일 index.html 파일을 클라이언트로 응답한다.

- 또한 https://velog.io 를 로드하면 index.html 뿐만 아니라 css, js, image 등의 파일들도 응답된 것을 확인할 수 있는데, 렌더링 엔진은 index.html을 파싱하는 도중에 css, js, image 등의 외부 리소스를 로드하는 태그 등을 만나면 **HTML 파싱을 일시 중단하고 해당 리소스 파일을 서버로 요청하기 때문이다.**

> ❓ **HTTP**
> - HTTP는 브라우저와 서버가 통신하기 위한 프로토콜이다. HTTP/2는 다중요청/응답이 가능하다.

### ✅ HTML 파싱과 DOM 생성
> DOM은 HTML문서를 파싱한 결과물이다. [DOM 포스트](https://velog.io/@songjy377/JS-DOM)

- 서버가 응답한 HTML 문서는 문자열로 이루어진 **순수한 텍스트**다. 
- 이를 브라우저에 시각적인 픽셀로 렌더링하려면 HTML 문서를 브라우저가 이해할 수 있는 자료구조로 변환해 메모리에 저장해야 한다.

📔 **Byte 응답**
>- 서버에 존재하던 HTML 파일이 브라우저의 요청에 의해 응답된다. 이때 서버는 HTML 문서를 바이트(2진수) 형태로 응답한다. ex) 101101001011 ...

📕 **문자열 변환**
> - 응답된 바이트 형태의 HTML 문서는 meta 태그의 charset 어트리뷰트에 의해 지정된 인코딩 방식으로 문자열로 변환된다.  
>```html
><html><head><meta charset="UTF-8"> ... </html>
>```

📙 **Token 분해**
> - 문자열로 변환된 HTML 문서를 읽어 들여 문법적 의미를 갖는 최소 단위인 토큰들로 분해한다.
>```js
>{
>  startTag: 'html',
>  contents: {
>    startTag: 'head',
>    contents: { ... },
>    ...
>  endTag: 'html'
>}
>```

📒 **Node 생성**
> - 각 토큰들을 객체로 변환해 DOM을 구성할 노드들을 생성한다. 
> - 토큰의 내용에 따라 문서 노드, 요소 노드, 어트리뷰트 노드, 텍스트 노드가 생성된다.

📘 **DOM 구성**
> - 모든 노드들을 트리 자료 구조로 구성한다. 
> - 이 노드들로 구성된 트리 자료구조를 **DOM(Document Object Model)** 이라 부른다.
>![](https://images.velog.io/images/songjy377/post/7ddad0c4-1c78-4281-83fc-73af617b3003/image.png) 그림출처: https://bit.ly/2WochoN

### ✅ CSS 파싱과 CSSOM 생성
>- 렌더링 엔진은 HTML을 처음부터 한 줄씩 순차적으로 파싱해 DOM을 생성해 나간다. DOM을 생성해 나가다가 **CSS를 로드하는 link 태그나 style 태그를 만나면 DOM 생성을 일시 중단**한다.
>
>- link 태그의 href 속성에 지정된 CSS 파일을 서버에 요청해 응답되면 렌더링 엔진은 HTML과 동일한 해석과정(바이트→문자→토큰→노드→CSSOM)을 거쳐 CSS를 파싱해 **CSSOM(CSS Object Model)** 을 생성한다. style 태그 내의 CSS 또한 파싱하여 CSSOM을 생성한다.
>
>![](https://images.velog.io/images/songjy377/post/c533f41b-ddcc-4a4f-8cec-49fad41db8a1/image.png) 그림출처: https://bit.ly/2WochoN
>
>- CSS파싱을 완료하면 다시 HTML을 파싱한다.

### ✅ 렌더 트리 생성
> - **생성된 DOM과 CSSOM은 렌더링을 위해 렌더 트리>(render tree) 로 결합된다.**
> - 렌더트리는 브라우저 화면에 렌더링되는 노드만으로 구성된다. (meta 태그나 script 태그같은 브라우저 화면에 렌더링되지 않는 노드나, display: none 같은 CSS에 의해 표시되지 않는 노드들은 포함되지 않는다.)
>![](https://images.velog.io/images/songjy377/post/45cd7688-f7ea-4539-8a07-ae05522fe0f2/image.png) 그림출처: https://bit.ly/3iQ3ovQ
- 완성된 렌더 트리는 각 HTML 요소의 레이아웃(위치와 크기)를 계산하는 데 사용되고, 브라우저 화면에 픽셀을 렌더링하는 페인팅 처리에 입력된다.
![](https://images.velog.io/images/songjy377/post/f4b598cb-5885-4957-b4b4-0af8d21fc9aa/image.png)

- 브라우저 렌더링 과정은 반복해서 실행될 수 있다. 
- 아래의 경우 리페인팅과 레이아웃 계산이 재차 실행된다. ❗ **최대한 리렌더링이 발생하지 않도록 해야 한다.**
    1. JS에 의한 노드 추가 또는 삭제
    2. 브라우저 창의 리사이징에 의한 view port 변경
    3. HTML요소의 레이아웃 변경을 발생시키는 width/height, padding, margin, border, display, position 등의 스타일 변경

### ✅ JS 파싱과 실행
> - JS 엔진은 JS 코드를 파싱하여 AST(Abstract Syntax Tree)를 생성한다.

- CSS 파싱 과정과 마찬가지로 렌더링 엔진은 HTML을 파싱해 DOM을 생성해 나가다가, JS 파일을 로드하거나 JS 콘텐츠를 담은 script 태그를 만나면 DOM 생성을 일시 중단한다.
- 자바스크립트 파싱과 실행은 브라우저 렌더링 엔진이 아닌 자바스크립트 엔진이 처리한다. 
![](https://images.velog.io/images/songjy377/post/f16aa826-534a-4b57-b3d5-408a51c6b674/image.png)

<span style="color:#9A30AE">**토크나이징**</span>
- 단순한 문자열인 JS 소스코드를 문법적 의미를 갖는 코드의 최소 단위인 토큰으로 분해한다.

<span style="color:#9A30AE">**파싱**</span>
- 토큰들의 집합을 구문분석하여 AST를 생성한다. 이는 토큰의 문법적 의미와 구조를 반영한 트리 구조의 자료구조다.

<span style="color:#9A30AE">**바이트코드 생성과 실행**</span>
- AST는 바이트코드로 변환되고 인터프리터에 의해 실행된다. 

### ✅ `Replow, Repaint`
>- DOM은 HTML요소와 스타일을 변경할 수 있는 프로그래밍 인터페이스로서 DOM API를 제공한다. 
> - DOM API로 인해 DOM이나 CSSOM이 변경될 경우, 변경된 DOM과 CSSOM은 다시 렌더 트리로 결합되고 변경된 렌더트리를 기반으로 레이아웃과 페인트 과정을 거쳐 브라우저의 화면에 다시 렌더링한다. 이를 **`Reflow`(리플로우)**, **`Repaint`(리페인트)** 라 한다.
![](https://images.velog.io/images/songjy377/post/4a809ddb-3936-44d4-969f-b441b7b86871/image.png)

- `Reflow`는 레이아웃 계산을 다시 하는 것을 말하며, 노드 추가/삭제, 요소의 위치 변경, 윈도우 리사이징 등 레이아웃에 영향을 주는 경우에 한하여 실행된다. 
    - `Reflow`가 일어나는 대표 속성들
    - `position, width, height, margin, padding, border, border-width, font-size, font-weight, line-height, text-align, overflow`

- `Repaint`는 재결합된 렌더 트리를 기반으로 다시 페인트를 하는 것을 말한다.
    - `Repaint`가 일어나는 대표 속성
    - `background, color, text-decoration, border-style, border-radius`

- 기존 요소에 변경 사항이 생겼다고 해서 항상 Reflow-Repaint가 일어나는 것은 아니고, 레이아웃에 영향이 미치지 않는 단순한 색상 변경 같은 변경사항은 Reflow 수행 없이 바로 Repaint만 수행하게 됩니다. (Reflow가 일어나면 반드시 Repaint는 일어난다.)

### ✅ JS코드의 위치
> - JS코드에 의해 파싱이 중단되는 것을 생각해 보면, body요소의 가장 아래에 JS를 위치시키는 것이 좋다.
>
>- ❓ WHY?
>    1. DOM이 완성되지 않은 상태에서 JS가 DOM을 조작하면 에러가 발생할 수 있다.
>    2. JS 로딩/파싱/실행으로 인해 HTML요소들의 렌더링에 지장받는 일이 발생하지 않아 로딩 시간이 단축된다.
 
### ✅ `async` 어트리뷰트
> - HTML 파싱과 외부 JS 파일의 로드가 비동기적으로 진행된다. JS 파싱과 실행은 JS파일의 로드가 완료된 직후 진행되며, 이때 HTML 파싱이 중단된다.
>- 여러 개의 script에 `async` 어트리뷰트를 지정하면 script 태그의 순서와는 상관없어 로드가 완료된 JS부터 실행되므로 순서가 보장되지 않는다. -> 순서 보장이 필요한 script tag에는 사용하지 않아야 한다.
> ![](https://images.velog.io/images/songjy377/post/dc4f491a-c636-4561-9f46-0c9ef326120e/image.png)

### ✅ `defer` 어트리뷰트
> - HTML 파싱과 외부 JS 파일의 로드가 비동기적으로 진행된다. JS 파싱과 실행은 HTML 파싱 완료된 직후(DOM 생성이 완료된 직후) 진행된다.
> - DOM 생성이 완료된 이후 실행되어야 할 JS에 유용하다.
> ![](https://images.velog.io/images/songjy377/post/64ae8031-47a2-4528-983c-a4bb3db72267/image.png)
