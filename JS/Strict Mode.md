# Strict Mode
> strict mode에 대해 정리하였다.(211130)

## ✅ Strict Mode
- ES5부터 추가된 strict mode는 자바스크립트 언어의 문법을 좀 더 엄격히 적용하여, 오류를 발생시킬 가능성이 높거나 자바스크립트 엔진의 최적화 작업에 문제를 일으킬 수 있는 코드에 대해 명시적인 에러를 발생시킨다.

## ✅ Strict Mode의 적용
> - strict mode를 적용하려면 전역의 선두 또는 함수 몸체의 선두에 use strict;를 추가한다.

```js
  'use strict'; // 전역의 선두에 추가
  
  function foo() {
    x = 10; // ReferenceError: x is not defined
  }
  foo();
```
- 전역의 선두에 추가하면 스크립트 전체에 적용된다.

```js
  function foo() {
    'use strict'; // 함수의 선두에 추가
  
    y = 10; // ReferenceError: y is not defined
  }
  foo();
```
- 코드의 선두에 use strict; 를 위치시키지 않으면 strict mode가 제대로 동작하지 않는다.
```js
  function foo() {
    x = 10; // 에러를 발생시키지 않는다.
    'use strict';
  }
  foo();
```
### ❌ 전역에 적용하는 것은 피하자
> - 전역에 적용한 strict mode는 스크립트 단위로 적용된다.

```html
<html>
  <body>
    <script>
      'use strict';
    </script>
    <script>
      x = 1; // 에러가 발생하지 않는다.
      console.log(x); // 1
    </script>
    <script>
      'use strict';
      y = 1; // ReferenceError: y is not defined
      console.log(y);
    </script>
  </body>
</html>
```
- 스크립트 단위로 적용된 strict mode는 다른 스크립트에 영향을 주지 않고 해당 스크립트에 한정되어 적용된다.
- strict mode 스크립트와 non-strict mode 스크립트를 혼용하는 것은 오류를 발생시킬 수 있다. 특히 외부 서드파티 라이브러리를 사용하는 경우 라이브러리가 non-strict mode인 경우도 있기 때문에 전역에 strict mode를 적용하는 것은 바람직하지 않다.
- 이러한 경우에는 즉시 실행 함수로 스크립트 전체를 감싸서 스코프를 구분하고 즉시 실행 함수의 선두에 strict mode를 적용한다.
```js
(function() {
  //즉시 실행 함수의 선두에 strict mode 적용
  'use strict';
  
  // do something ...
}());
```
### ❌ 함수 단위로 적용하는 것은 피하자
> - strict mode는 즉시 실행 함수로 감싼 스크립트 단위로 적용하는 것이 바람직하다.

- 앞서 말한 바와 같이 함수 단위로도 strict mode를 적용할 수 있다. 그러나 어떤 함수는 strict mode를 적용하고 어떤 함수는 strict mode를 적용하지 않는 것은 바람직하지 않을 뿐만 아니라 모든 함수에 일일이 strict mode를 적용하는 것은 번거롭다.
- 또한 strict mode가 적용된 함수가 참조할 함수 외부의 컨텍스트에 strict mode를 적용하지 않는다면 이 또한 문제가 발생할 수 있다.
```js
(function() {
  // non-strict mode
  var let = 10; // 에러가 발생하지 않는다.
  
  function foo() {
    'use strict';
    
    let = 20; // SyntaxError: Unexpected strict mode reserved word
  }
  
  foo();
}());
```

## ✅ Strict Mode가 발생시키는 에러
### 🔰 암묵적 전역
> - 선언하지 않은 변수를 참조하면 `ReferenceError`가 발생한다.
>```js
>(function() {
>  'use strict';
>  
>  x = 1;
>  console.log(x); // ReferenceError: x is not >defined
>}());
>```

### 🔰 변수, 함수, 매개변수의 삭제
> - delete 연산자로 변수, 함수, 매개변수를 삭제하면 `SyntaxError`가 발생한다.
>```js
>(function() {
>  'use strict';
>  
>  var x = 1;
>  delete x; // SyntaxError: Delete of an unqualified identifier in strict mode
>  
>  function foo(a) {
>    delete a; // SyntaxError: Delete of an unqualified identifier in strict mode
>  }
>  
>  delete foo; // SyntaxError: Delete of an unqualified identifier in strict mode
>}());
>```

### 🔰 매개변수 이름의 중복
> - 중복된 매개변수 이름을 사용하면 `SyntaxError`가 발생한다.
>```js
>(function() {
>  'use strict';
>  
>  // SyntaxError: Duplicate parameter name not allowed in this context
>  function foo(x, x) {
>    return x + x;
>  }
>  
>  console.log(foo(1, 2));
>}());
>```

### 🔰 with문의 사용
> - with문을 사용하면 `SyntaxError`가 발생한다. with문은 전달된 객체를 스코프 체인에 추가한다. 
> - with문은 객체 이름을 생략할 수 있어서 코드가 간단해지는 효과가 있지만 성능과 가독성이 나빠지는 문제가 있다.
>```js
>(function() {
>  'use strict';
>  
>  // SyntaxError: Duplicate parameter name not allowed in this context
>  function foo(x, x) {
>    return x + x;
>  }
>  
>  console.log(foo(1, 2));
>}());
>```

## ✅ Strict Mode 적용에 의한 변화
### 🔰 일반 함수의 this
> - strict mode에서 함수를 일반 함수로서 호출하면 `this`에 `undefined` 가 바인딩된다. 생성자 함수가 아닌 일반 함수 내부에서는 this를 사용할 필요가 없기 때문이다.
>```js
>(function() {
>  'use strict';
>  
>  function foo() {
>    console.log(this); // undefined
>    // non-strict mode에서는 window 전역 객체를 가리킨다.
>  }
>  
>  foo();
>  
>  function Foo() {
>    console.log(this); // Foo
>  }
>  
>  new Foo();
>}());
>```

### 🔰 arguments 객체
> - strict mode에서는 매개변수에 전달된 인수를 재할당하여 변경해도 `arguments` 객체에 반영되지 않는다.
>```js
>(function (a) {
>'use strict';
>
>    // 매개변수에 전달된 인수를 재할당하여 변경
>    a = 2;
>
>    // 변경된 인수가 arguments 객체에 반영되지 않는다.
>    console.log(arguments); // { 0: 1, length: 1 }
>    // non-strict mode에서는 { 0: 2, length: 1 }
>}(1));
>```

### ❓ Strict Mode를 꼭 사용해야 할까?
- ES6에서 추가된 클래스와 모듈을 사용하면 자동으로 use strict가 적용된다. 그렇기 때문에 이 둘을 사용하고 있다면 스크립트에 strict mode를 사용할 필요가 없다.
- 코드를 클래스와 모듈을 사용해 구성한다면 strict mode를 사용하지 않아도 되지만, 그렇지 않은 경우에는 사용해서 잠재적인 오류를 발생시키지 않도록 하는 것이 좋다.