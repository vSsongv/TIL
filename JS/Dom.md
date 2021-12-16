**DOM은 HTML 문서의 계층적 구조와 정보를 표현하며 이를 제어할 수 있는 API, 즉 프로퍼티와 메서드를 제공하는 트리 자료구조다.**

## 🌳 `Node`
### 🌿 `HTML`요소와 노드 객체
>HTML요소는 HTML 문서를 구성하는 개별적인 요소를 의미한다.
>![](https://images.velog.io/images/songjy377/post/7afda020-c807-4076-b6f2-f2c63926792e/image.png)
>
> HTML요소는 렌더링 엔진에 의해 파싱되어 DOM을 구성하는 요소 노드 객체로 변환된다. 이때 HTML 요소의 **어트리뷰트는 어트리뷰트 노드**로, **텍스트 콘텐츠는 텍스트 노드로 변환**된다.
>![](https://images.velog.io/images/songjy377/post/b21b10ac-6f4a-410f-82a6-727bf23688d6/image.png)
> - HTML요소는 중첩 관계를 갖는다. 즉 HTML 요소의 콘텐츠 영역에는 텍스트뿐만 아니라 다른 HTML요소도 포함할 수 있다.
> - **노드 객체들로 구성된 트리 자료구조를 DOM이라 한다.**
>```html
><!DOCTYPE html>
>
><head>
>    <meta charset="UTF-8">
></head>
>
><body>
>    <ul>
>        <li class="apple">Apple</li>
>        <li class="peach">Peach</li>
>        <li class="banana">Banana</li>
>    </ul>
></body>
><script src = "app.js"></script>
></html>
>```
>![](https://images.velog.io/images/songjy377/post/5009094d-ea1c-49ca-9aab-61b1c1cea284/image.png)

### 🌿 노드 객체의 타입
> - DOM은 노드 객체의 계층적인 구조로 구성된다. 노드 객체는 종류가 있고 상속 구조를 갖는다. 

<span style="color: blue">**문서 노드**</span>
- DOM트리의 최상위에 존재하는 root노드. **document 객체**를 가리킨다. 
- window의 document 프로퍼티에 바인딩되어 있다. -> window.document 또는 document로 참조할 수 있다.
- document 객체는 유일하며, 다른 노드에 접근하기 위한 진입점 역할을 담당한다.

<span style="color: blue">**요소 노드**</span>
- **HTML의 요소를 가리키는 객체.**
-  HTML 요소는 중첩에 의해 부자 관계를 가지며 이 부자 관계를 통해 정보를 구조화한다. 따라서 요소 노드는 문서의 구조를 표현한다고 할 수 있다.
- 어트리뷰트, 텍스트 노드의 부모 노드이기 때문에 이들에게 접근하려면 먼저 요소 노드에 접근해야 한다.

<span style="color: blue">**어트리뷰트 노드**</span>
- **HTML 요소의 어트리뷰트를 가리키는 객체**
- 부모 노드와 연결되어있지 않고 요소 노드에만 연결되어 있다. -> **요소 노드에 접근해야만 어트리뷰트 노드에 접근할 수 있다.**

<span style="color: blue">**텍스트 노드**</span>
- **텍스트 노드는 HTML 요소의 텍스트를 가리키는 객체**
- 문서의 정보를 표현한다고 할 수 있다.
- **텍스트 노드는 DOM tree의 최종단이다.**

### 🌿 노드 객체의 상속 구조
- DOM은 DOM API를 사용할 수 있다. 이를 통해 노드 객체는 자신의 부모, 형제, 자식을 탐색할 수 있으며, 자신의 어트리뷰트와 텍스트를 조작할 수도 있다. 
- DOM을 구성하는 노드 객체는 빌트인 객체가 아닌 브라우저 환경에서 추가적으로 제공하는 호스트 객체이다. 하지만 노드 객체도 자바스크립트 객체이기 때문에 `Object`를 상속받는다.

![](https://images.velog.io/images/songjy377/post/072dc09f-a58d-467b-9083-beba672a43b3/image.png)
- 모든 노드 객체는 `Object, EventTarget, Node` 인터페이스를 상속받는다. 이벤트와 관련된 기능은 `EventTarget` 인터페이스가 제공하고, 노드로서 트리 탐색이나 노드 정보 제공 등의 노드 관련 기능은 Node 인터페이스가 제공한다.
- 또한 문서 노드는 `Document, HTMLDocument` 인터페이스를 상속받고, 요소 노드는 `Element, HTMLElement` 인터페이스를 상속받는다. 어트리뷰트 노드는 `Attr`, 텍스트 노드는 `CharacterData` 인터페이스를 각각 상속받는다.
- 예를 들어, `input` 요소를 파싱하여 객체화한 `input` 요소 노드 객체는 `HTMLInputElement, HTMLElement, Element, Node, EventTarget, Object`의 prototype에 바인딩되어 있는 프로토타입 객체를 상속받는다. -> 즉 `input` 요소 노드 객체는 프로토타입 체인의 모든 프로토타입의 프로퍼티나 메서드를 상속받아 사용할 수 있다.
- 배열이 객체인 동시에 배열인 것처럼, input요소 노드 객체도 다음과 같이 다양한 특성을 갖는 객체이며, 이러한 특성을 나타내는 기능들을 상속을 통해 제공받는다.

|input 요소 노드 객체의 특성|프로토타입을 제공하는 객체|
|---|---|
|객체|`Object`|
|이벤트를 발생시키는 객체|`EventTarget`|
|트리 자료구조의 객체|`Node`|
|브라우저가 렌더링할수 있는 웹 문서 요를 표현하는 객체|`Element`|
|웹 문서의 요소 중에서 HTML 요소를 표현하는 객체|`HTMLElement`|
|HTML요소 중에서 input 요소를 표현하는 객체|`HTMLInputElement`|

## 🌳 요소 노드 취득
### 🌿 `Document.getElementById()`
> - 인수로 전달한 id 값을 갖는 하나의 요소 노드를 탐색해 반환한다. 
> - 만약 중복된 id 값을 갖는 요소가 여러 개가 있다면 첫 번째 요소 노드만 반환한다. 또한 해당되는 노드가 없다면 null을 반환한다.
>
>```js
><html>
>  <body>
>    <ul>
>      <li id="banana">Banana</li>
>    </ul>
>    <script>
>      //id값이 'banana'인 요소 노드를 탐색하여 반환한다.
>      const $elem = document.getElementById('banana');
>      $elem.style.color = 'red';
>    </script>
>  </body>
></html>
>```
> - HTML요소에 id 어트리뷰트를 부여하면 id값과 동일한 이름의 전역 변수가 암묵적으로 선언되고 해당 노드 객체가 할당되는 부수 효과가 있다. -> 단, id값과 동일한 이름의 전역 변수가 이미 선언되어 있으면 이 전역 변수에 노드 객체가 재할당되지 않는다.
>```js
><html>
>  <body>
>    <ul>
>      <li id="foo"></li>
>    </ul>
>    <script>
>      let foo = 1;
>      //전역 변수가 이미 있으므로 노드 객체가 재할당되지 않는다.
>      console.log(foo); //1
>    </script>
>  </body>
></html>
>```

### 🌿  `Document.getElementsByTagName()`
> - 인수로 전달한 태그 이름을 갖는 모든 요소 노드들을 탐색하여 반환한다. **HTMLCollection(live) 객체** 를 반환한다.
>```js
><html>
>  <body>
>    <ul>
>      <li id="banana">Banana</li>
>      <li id="orange">Orange</li>
>      <li id="peach">Peach</li>
>    </ul>
>    <script>
>      const $elems = document.getElementsByTagName('li');
>      [...$elems].forEach(elem => { elem.style.color = 'red'; });
>      
>      // 모든 요소 노드를 탐색하여 반환
>      const $all = document.getElementsByTagName('*');
>    </script>
>  </body>
></html>
---------------------------------------
><body>
>    <ul id="fruits">
>        <li>Apple</li>
>        <li>Banana</li>
>        <li>Peach</li>
>    </ul>
>    <ul>
>        <li>HTML</li>
>    </ul>
>    <script>
>        //DOM 전체에서 태그 이름이 li인 요소 노드를 모두 탐색하여 반환한다.
>        const $lisFronDoc = document.getElementsByTagName('li');
>        console.log($lisFronDoc); //HTMLCollection(4) [li, li, li, li]
>
>        //ul#fruits 요소의 자손 노드 중에서 태그 이름이 li인 요소 노드를 모두 탐색하여 반환한다.
>        const $fruits = document.getElementsById('fruits');
>        const $lisFromFruits = $fruits.getElementsByTagName('li');
>        console.log($lisFromFruits); //HTMLCollection(3) [li, li, li]
>    </script>
></body>
></html>
>```

### 🌿  `getElementsByClassName()`
> - 인수로 전달한 클래스 이름을 갖는 모든 요소 노드들을 탐색하여 반환한다. **HTMLCollection(live) 객체** 를 반환한다.
>```html
><body>
>    <ul>
>        <li id="fruits apple">Apple</li>
>        <li id="fruits banana">Banana</li>
>        <li id="fruits peach">Peach</li>
>    </ul>
>    <script>
>        //DOM 전체에서 class 이름이 'fruit'인 요소 노드를 모두 탐색하여 HTMLCollection 객체에 담아 반환한다.
>        const $elems = document.getElementsByClassName('fruit');
>        console.log($elems);
>
>        //class이름이 fruit apple인 요소 노드를 모두 탐색하여 HTMLCollection 객체에 담아 반환한다.
>        const $apple = document.getElementsByClassName('fruits apple');
>        $apple.style.color = 'blue'; 
>    </script>
></body>
>
></html>
>```

>**CSS 선택자**
>```css
>* {...} /* 전체 선택자 */
>p {...} /* 태그 선택자 */
>#id {...} /* id 선택자 */
>.class {...} /* class 선택자 */
>input[type=text] {...} /* attribute 선택자 */
>div p {...} /* 후손 선택자, div의 ㅏ식 요소 중 p요소를 모두 선택 */
>div > p {...} /* 자식 선택자, div의 자식 요소 중 p요소를 모두 선택 */
>p + ul {...} /* 인접 형제 선택자 p요소의 형제 요소 중에 p요소 바로 뒤에 ul 요소를 선택 */
>p ~ ul {...} /* 일반 형제 선택자 p요소의 형제 요소 중에 p요소 뒤에 위치하는 ul 요소를 모두 선택 */
>a:hover {...} /* 가상 클래스 선택자 hover 상태인 a 요소를 모두 선택 */
>p::before { content: essential...} /* 가상 요소 선택자 p요소의 콘텐츠 위치를 선택 */
>```

### 🌿 `querySelector()`
> - 인수로 전달한 CSS 선택자를 만족시키는 하나의 요소 노드를 탐색하여 반환한다.
>```html
>    <ul>
>        <li class="apple">Apple</li>
>        <li class="banana">Banana</li>
>        <li class="peach">Peach</li>
>    </ul>
>    <script>
>        //DOM 전체에서 class 어트리뷰트 값이 'peach'인 첫 번째 요소 노드를 탐색하여 반환한다.
>        const $elems = document.querySelector('.peach');
>        console.log($elems);
>    </script>
>```

### 🌿 `querySelectorAll()`
> - 인수로 전달한 CSS 선택자를 만족시키는 모든 요소 노드를 탐색하여 반환한다. **NodeList(non-live) 객체**를 반환한다.
>```
>    <script>
>        //ul 요소의 자식 요소인 li 요소를 모두 탐색하여 반환한다.
>        const $elems = document.querySelectorAll('ul > li');
>        console.log($elems); // NodeList(3) [li.apple, li.banana, li.peach]
>    </script>
>```

### 🌿 `matches()`
> - 인수로 전달한 CSS 선택자를 통해 특정 요소 노드를 취득할 수 있는지 확인한다. -> event 위임에 유용하다. [event 포스트](https://velog.io/@songjy377/JS-Event)
>```html
><body>
>    <ul id="fruits">
>        <li class="apple">Apple</li>
>        <li class="banana">Banana</li>
>        <li class="peach">Peach</li>
>    </ul>
>    <script>
>        //ul 요소의 자식 요소인 li 요소를 모두 탐색하여 반환한다.
>        const $apple = document.querySelector('.apple');
>        //$apple 노드는 #fruits > li.apple로 취득 가능하다.
>        console.log($apple.matches('#fruits > li.apple')); //true
>        //$apple 노드는 #fruits > li.banana로 취득 불가능하다.
>        console.log($apple.matches('#fruits > li.banana')); //false
>    </script>
></body>
>
></html>
>```

### 🌿 `HTMLCollection`
> `getElementsByClassName`, `getElementsByTagName` 메서드가 반환하는 노드 객체의 상태 변화를 실시간으로 반영하는 살아 있는 DOM 컬렉션 객체이다. 
```html
    <ul id="fruits">
        <li class="apple">Apple</li>
        <li class="banana">Banana</li>
        <li class="peach">Peach</li>
    </ul>
    <script>
        for (let i = $elems.length - 1; i >= 0; i++) {
            $elem[i].className = 'blue';
        }
    </script>
```
>위와 같은 경우에, `apple`의 색이 변화한 후 `$elem`에서 실시간으로 제거되면, 1번째 요소는 Banana가 아닌 Peach가 된다. 따라서 Banana의 색은 변하지 않는다.
>![](https://images.velog.io/images/songjy377/post/c362fe57-5b21-469f-8c8d-7957064c572e/image.png)

### 🌿 `NodeList`
> - `HTMLCollection`과 다르게 상태 변경을 실시간으로 반영하지 않고 과거의 정적 상태를 유지하는 non-live 객체로 동작한다.
> - forEach, item, entries, keys, values 메서드를 사용할 수 있다.
>```html
><body>
>    <ul id="fruits">
>        <li class="red apple">Apple</li>
>        <li class="red banana">Banana</li>
>        <li class="red peach">Peach</li>
>    </ul>
>    <script>
>        const $elems = document.querySelectorAll('.red');
>        // NodeList 객체는 NodeList.prototype.forEach 메서드를 상속받아 사용할 수 있다.
>        $elems.forEach((element) => element.className = 'blue');    </script>
></body>
>
></html>
>```
>![](https://images.velog.io/images/songjy377/post/4b644af9-4462-4ad6-9c17-e183d06810b3/image.png)

> 📌 **노드 객체의 상태 변경과 상관없이 안전하게 DOM collection을 사용하기 위해서는, HTMLCollection, NodeList 객체를 배열로 변환하여 사용하는 것이 좋다.**

## 🌳 노드 탐색
> - DOM 트리 상의 노드를 탐색할 수 있도록 Node, Element 인터페이스는 트리 탐색 프로퍼티를 제공한다.
> - Node 인터페이스에는 `parentNode, previousSibling, nextSibling, firstChild, lastChild, childNodes` 프로퍼티를 제공하고 Element 인터페이스에서는 `previousElementSibling, nextElementSibling, firstElementChild, lastElementChild, children` 프로퍼티를 제공한다.

### 🌿 자식 노드를 탐색하는 메서드
>|프로퍼티|설명|
>|---|---|
>`childNodes` |자식 노드를 모두 탐색하여 NodeList에 담아 반환한다. **요소 노드뿐만 아니라 텍스트 노드도 포함되어있을 수 있다.**
>`children` | 자식 노드를 모두 탐색하여 HTMLCollection에 담아 반환한다. **HTMLCollection에는 텍스트 노드가 포함되어있지 않다.**
>`firstChild` | **텍스트 노드이거나 요소 노드**인 첫 번째 자식 노드를 반환한다.
>`lastChild` | **텍스트 노드이거나 요소 노드**인 마지막 자식 노드를 반환한다.
>`firstElementChild` |첫 번째 자식 **요소 노드**를 반환한다.
>`lastElementChild` | 마지막 자식 **요소 노드**를 반환한다.
>`hasChild` | 자식 노드의 존재 여부를 boolean 값으로 반환한다. **텍스트 노드를 포함하여 자식 노드를 확인한다.**<br> ➡ 텍스트 노드가 아닌 요소 노드가 존재하는지 확인하려면
>`children.length` 나 `childElementCount` 를 사용하다.
>
>```js
><html>
>
><body>
>    <ul id="fruits">
>        <li class="apple">Apple</li>
>        <li class="banana">Banana</li>
>        <li class="orange">Orange</li>
>    </ul>
>    <script>
>        const $fruits = document.getElementById('fruits');
>
>        console.log($fruits.childNodes);
>        // NodeList(7) [text, li.apple, text, li.banana, text, li.orange. text]
>        console.log($fruits.children);
>        // HTMLCollection(3) [li.apple, li.banana, li.orange] 
>
>        console.log($fruits.firstChild); // #text
>        console.log($fruits.firstElementChild) // li.apple
>
>        console.log($fruits.lastChild); // #text
>        console.log($fruits.lastElementChild) // li.orange
>    </script>
></body>
>
></html>
>```

> - ❗ HTML 문서에서 space, tab, 개행 등의 공백 문자는 **공백 텍스트 노드** 를 생성한다. 따라서 노드를 탐색할 때는 공백 텍스트 노드를 고려해야 한다.

### 🌿 부모 노드를 탐색하는 메서드
> - `parentNode`. 부모 노드를 반환한다. 
>```html
><body>
>    <ul id="fruits">
>        <li class="apple">Apple</li>
>        <li class="banana">Banana</li>
>        <li class="orange">Orange</li>
>    </ul>
></body>
>
><script>
>    const $banana = document.querySelector('.banana');
>    console.log($banana.parentNode); //ul#fruits
></script>
>```

### 🌿 형제 노드를 탐색하는 메서드
>|프로퍼티 | 설명|
>|--|--|
>`previousSibling` |부모 노드가 같은 형제 중에서 자신의 이전 형제 노드를 탐색하여 반환한다.  **텍스트 노드이거나 요소 노드**일 수 있다.
>`nextSibling` |부모 노드가 같은 형제 중에서 자신의 다음 형제 노드를 탐색하여 반환한다.  **텍스트 노드이거나 요소 노드**일 수 있다.
>`previousElementSibling` |부모 노드가 같은 형제 중에서 자신의 이전 형제 노드를 탐색하여 반환한다.  **요소 노드만** 반환한다.
>`nextElementSibling` |부모 노드가 같은 형제 중에서 자신의 다음 형제 노드를 탐색하여 반환한다.  **요소 노드만** 반환한다.
>```html
><body>
>    <ul id="fruits">
>        <li class="apple">Apple</li>
>        <li class="banana">Banana</li>
>        <li class="orange">Orange</li>
>    </ul>
>    <script>
>        const $fruits = document.getElementById('fruits');
>        const { firstChild } = $fruits; //객체 디스트럭처링 할당
>        console.log(firstChild); //#text //첫번재 요소는 텍스트 노드
>
>        const { nextSibling } = firstChild;
>        console.log(nextSibling); //li.apple
>
>        const { firstElementChild } = $fruits;
>        console.log(firstElementChild); //li.apple -> firstElementChild는 요소 노드만 반환한다.
>    </script>
></body>
>```

## 🌳 노드 정보 취득


노드 조작
다음과 같은 DOM API를 이용해 새로운 노드를 생성해 DOM에 추가하거나 기존 노드를 삭제 또는 교체할 수 있다.

노드 생성
Document.createElement, Document.createTextNode, Document.createDocumentFragment

노드 삽입
Node.appendChild, Node.insertBefore

노드 복사
Node.cloneNode

노드 교체
Node.replaceChild

노드 삭제
Node.removeChild

DOM 조작을 통해 DOM에 새로운 노드가 추가되거나 삭제되면 리플로우와 리페인트가 발생하기 때문에, 복잡한 콘텐츠를 다루는 DOM 조작은 성능 최적화를 위해 주의해서 다루어야 한다.