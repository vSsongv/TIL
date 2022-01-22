# ES6 함수의 추가 기능
> ES6 함수의 추가 기능에 대해 다룬다.(211207)

## ✅ 함수의 구분
> ES6 이전까지 함수는 별다른 구분 없이 다양한 목적으로 사용되었다.
> - 사용 목적에 따라 명확히 구분되지 않는다. 
>- **ES6 이전의 모든 함수는 일반함수로서 호출할 수 있는 것은 물론 생성자 함수로서 호출할 수 있다.** 
>- ES6 이전의 모든 함수는 callable 이면서 constructor다.
>
>이로 인해 ES6 이후에는 함수를** 사용 목적에 따라 _일반 함수, 메서드, 화살표 함수_ 세 가지 종류로 명확히 구분했다.**
>
>ES6 함수의 구분|	constructor|prototype|	super|	arguments
|--|--|--|--|--|
일반 함수|	O|	O|	X|	O
메서드|	X|	X|	O|	O
화살표 함수|	X|	X|	X|	X

## ✅ 메서드
> - ES6 사양에서 메서드는 **메서드 축약 표현으로 정의된 함수만을** 의미한다. ES6 사양에서 정의한 메서드는 인스턴스를 생성할 수 없는 non-constructor다. 따라서 ES6 메서드는 생성자 함수로서 호출할 수 없다.

```js
const obj = {
  x: 1,
  
  // 메서드
  foo() { 
    return this.x;
  },
  
  // 메서드가 아닌 일반 함수
  bar: function() {
    return this.x;
  }
};

console.log(obj.foo()); // 1
console.log(obj.bar()); // 1
```
- ES6 메서드는 인스턴스를 생성할 수 없는 `non-constructor`이기 때문에, 생성자 함수로서 호출할 수 없고 프로토타입 또한 생성하지 않는다.

```js
new obj.foo(); // TypeError: obj.foo is not a constructor
new obj.bar(); // bar {}
```
- 📌 **ES6 메서드는 자신을 바인딩한 객체를 가리키는 내부 슬롯 `[[HomeObject]]`를 갖는다.**
- `super` 키워드는 `[[HomeObject]]`를 사용하여 수퍼클래스의 메소드를 참조하므로 ES6 메서드는 `super` 키워드를 참조할 수 있다. 
- ES6 메서드가 아닌 함수는 `super` 키워드를 사용할 수 없다.
```js
const base = {
  name: 'Lee',
  sayHi() {
    return `H! ${this.name}`;
  }
};

const derived = {
  __proto__: base,
  
  // 메서드
  sayHi() {
    return `${super.sayHi()}. how are you doing?`;
  },
  
  // 일반 함수
  sayHi: function () {
    // SyntaxError. 'super' keyword unexpected here
    return `${super.sayHi()}. how are you doing?`;
  }
}
```
> - ES6의 메서드는 본연의 기능인 super를 추가하고 의미적으로 맞지 않는 기능인 constructor는 제거했기 때문에, ES6 이전의 메서드 정의 방식은 사용하지 않는 것이 좋다.

## ➡️ 화살표 함수
> - 화살표 함수(arrow function)는 function 키워드 대신 화살표(`=>`)를 사용하여 기존의 함수 정의 방식보다 간략하게 함수를 정의할 수 있다.
> - 한 줄 짜리 함수를 작성할 때 유용.

### ⬆️ 화살표 함수 정의
 ✳️ <span style="color:#00CE11">**함수 정의**</span>
> - 화살표 함수는 함수 선언문으로 정의할 수 없고 함수 표현식으로 정의해야 한다.
>```js
>const arrow = (x, y) => x + y;
>```

✳️ <span style="color:#00CE11">**매개변수 선언**</span>
> - 매개변수가 여러 개인 경우 소괄호 `()` 안에 매개변수를 선언한다. 
>```js
> const arrow = (x, y) => { ... };
>```
> - 한 개인 경우 소괄호 `()`를 생략할 수 있다.
>```js
> const arrow = x => { ... };  
>```
>- 매개변수가 없는 경우 소괄호 `()`를 생략할 수 없다.
> ```js
> const power = x => x ** 2;
> power(2); // 4
> ```

✳️ <span style="color:#00CE11">**함수 몸체 정의**</span>
> - 함수 몸체가 하나의 문으로 구성된다면 `{}`를 생략할 수 있다. 
> - 함수 몸체가 여러 개의 문으로 구성된다면 함수 몸체를 감싸는 중괄호를 생략할 수 없다. 반환값이 있다면 명시적으로 반환해야 한다.
>```js
> const power x => x ** 2; //하나의 문인 경우
>
> const sum = (a, b) => {
>     const result = a + b;
>     return result; //반환값
> }
>```
> - 함수 몸체 내부의 문이 표현식이 아닌 문이라면 에러가 발생한다.
> ```js
> const arrow = () => const x = 1; // ❌ SyntaxError
>```
> - 객체 리터럴을 반환하는 경우 객체 리터럴을 소괄호 ()로 감싸 주어야 한다. 감싸지 않으면 객체 리터럴의 중괄호를 함수 몸체를 감싸는 중괄호로 잘못 해석한다.
> ```js
> const create = (id, content) => ({ id, content });
> ```
> - 화살표 함수도 즉시 실행 함수로 사용할 수 있다.
>```js
>const person = (name => ({
>  sayHi() { return name; }
>}))('Ssong');
>
>console.log(person.sayHi()); // Cho
>```

### ⬇️ 화살표 함수 특징
☑️ <span style="color:blue">**화살표 함수는 인스턴스를 생성할 수 없는 non-constructor다.**</span> 
>```js
const Foo = () => {};
>
>new Foo(); // TypeError: Foo is not a onstructor
>```
>- 인스턴스를 생성할 수 없기 때문에 prototype 프로퍼티도 없고 프로토타입도 생성하지 않는다.

☑️ <span style="color:blue">**중복된 매개변수 이름을 선언할 수 없다.**</span> 
>```js
>// 일반 함수
>function normal(a, a) {
>  return a + a;
>}
>console.log(normal(1, 2)); // 4
>
>// 화살표 함수
>const arrow = (a, a) => a + a;
>// SyntaxError: Duplicate parameter name not allowed in this context
>```

☑️ <span style="color:blue">**화살표 함수는 함수 자체의 `this, arguments, super, new.target` 바인딩을 갖지 않는다.**</span> 
>- 화살표 함수는 함수 내부에서 `this, super, arguments`를 참조하면, 스코프 체인을 통해 **상위 스코프의 `this, super, arguments`를 참조**한다.

### 🔰 this
- 일반 함수로서 호출되는 모든 함수 내부의 this는 전역 객체를 가리킨다. 
```js
var value = 1;

const obj = {
  value: 100,
  foo() {
    console.log("foo's this: ", this); // {value: 100, foo: f}
    
    // 콜백 함수
    setTimeout(function() {
      console.log("callback's this: this", this); // window
      console.log("callback's this.value: ", this.value); // 1
    }, 100);
  }
};

obj.foo();
```

- 메서드 내부에서 `setTimeout` 함수에 전달된 콜백 함수의 `this`에는 전역 객체가 바인딩이 된다. 그렇기 때문에 `this.value`는 obj 객체의 value 프로퍼티가 아닌 전역 객체의 `window.value` 프로퍼티를 참조한다.

>💡 화살표 함수는 이런 콜백 함수의 this와 외부 함수의 this의 불일치 문제를 해결할 수 있다.


>- 화살표 함수는 함수 자체의 this 바인딩을 갖지 않는다. 
>- 따라서 화살표 함수 내부에서 `this`를 참조하면 상위 스코프의 `this`를 그대로 참조한다. 이를 **`lexical this`**라 한다. 이는 마치 렉시컬 스코프와 같이 화살표 함수의 **this가 함수가 정의된 위치에 의해 결정된다는 것을 의미한다.**

- <span style="color:#910099">**화살표 함수가 중첩되어 있다면** 상위 화살표 함수에도 this바인딩이 없으므로 스코프 체인 상에서 **가장 가까운 상위 함수 중에서 화살표 함수가 아닌 함수의 this를 참조한다.**</span>
```js
//중첩 함수 foo의 상위 스코프는 즉시 실행 함수다.
//따라서 화살표 함수 foo의 this는 상위 스코프인 즉시 실행 함수의 this를 가리킨다.
(function () {
    const foo = () => () => console.log(this);
    foo()();
}).call({ a: 1 }); //{a:1}
```
- <span style="color:#910099">**화살표 함수가 전역 함수라면 화살표 함수의 this는 전역 객체를 가리킨다.**</span>
```js
const foo = () => console.log(this);
foo(); // window
```

- <span style="color:#910099">**클래스 필드에 할당한 인스턴스 프로퍼티의 화살표 함수의 this는 클래스가 생성할 인스턴스를 참조한다. 이때 클래스 필드에 할당한 화살표 함수는 인스턴스 함수가 되므로 좋지 않다.**</span>

```js
class Person = {
  constructor() {
    this.name = 'Lee';
    //클래스가 생성한 인스턴스(this)의 sayHi 프로퍼티에 화살표 함수를 할당한다.
    // 따라서 sayHi 프로퍼티 인스턴스 프로퍼티
    this.sayHi = () => console.log(`Hi, ${this.name}`);
  }
}
```

- <span style="color:#910099">**프로퍼티에 할당한 화살표 함수도 스코프 체인 상에서 가장 가까운 화살표 함수가 아닌 함수의 this를 참조한다.**</span>
```js
const counter = {
  num: 1,
  increase: () => ++this.num
};

console.log(counter.increase()); // ?
```
❓ 프로퍼티에 할당한 화살표 함수도 스코프 체인 상에서 가장 가까운 상위 함수 중에서 화살표 함수가 아닌 함수의 `this`를 참조한다. 즉 `counter.increase()` 값은  화살표 함수의 상위 스코프인 전역 객체를 가리켜, NaN이 출력된다.

❌ <span style="color:#910099">**화살표 함수는 함수 자체의 this 바인딩을 갖지 않기 때문에 화살표 함수 내부의 this를 교체할 수 없다.**</span>

❌ <span style="color:#910099">**메서드를 화살표 함수로 정의하는 것은 피해야 한다. 메서드를 정의할 때는 ES6 메서드 축약 표현으로 정의한 ES6 메서드를 사용하는 것이 좋다.**</span>
>```js
>// Bad
>const person = {
>  name: 'Ssong',
>};
>
>Object.prototype.sayHi = () => console.log(`Hi ${this.name}`);
>
>person.sayHi(); // Hi undefined
>
>// Good
>const person = {
>  name: 'Ssong',
>};
>
>Object.prototype.sayHi = function() {
>  console.log(`Hi ${this.name}`);
>};
>
>person.sayHi(); // Hi Ssong
>```

❌ <span style="color:#910099">**프로토타입 객체의 프로퍼티를 동적 추가할 때는 일반 함수를 할당해라.**</span>



## ✅ super
> - 화살표 함수는 함수 자체의 super 바인딩을 갖지 않는다. 따라서 화살표 함수 내부에서 super를 참조하면 this와 마찬가지로 상위 스코프의 super를 참조한다.
>```js
>class Base {
>  constructor(name) {
>    this.name = name;
>  }
>  
>  sayHi() {
>    return `Hi! ${this.name}`;
>  }
>}
>
>class Derived extends Base {
> //화살표 함수의 super는 상위 스코프인 constructor의 super를 가리킨다.
>  sayHi = () => `${super.sayHi()} how are you doing?`;
>}
>
>const derived = new Derived('Lee');
>console.log(derived.sayHi()); // Hi! Lee how are you doing?
>```
> - `super`는 ES6 메서드 내에서만 사용할 수 있다. 화살표 함수 sayHi는 상위 스코프인 `constructor super` 바인딩을 참조해 `super` 바인딩을 갖지 않아도 에러가 발생하지 않는다.

## ✅ arguments
> - 화살표 함수는 함수 자체의 `arguments` 바인딩을 갖지 않는다. 따라서 화살표 함수 내부에서 `arguments`를 참조하면 `this`와 마찬가지로 상위 스코프의 `arguments`를 참조한다. 따라서 화살표 함수로 가변 인자 함수를 구현해야 할 때는 반드시 Rest 파라미터를 사용해야 한다.
>```js
>(function () {
>    const foo = () => console.log(arguments);
>    foo(3, 4); // [Arguments] { '0': 1, '1': 2 }
>}(1, 2));
>
>
>const foo = () => console.log(arguments);
>foo(1, 2); // ReferenceError: arguments is not defined
>```
>- 즉시 실행 함수 안에서 화살표 함수 foo는 상위 스코프인 즉시 실행 함수의 `arguments`를 가리킨다. 
>- 또한 전역 함수로 선언한 화살표 함수 foo는 상위 스코프인 전역의 `arguments`를 가리켜 에러를 발생시킨다. 전역에는 `arguments` 객체가 존재하지 않기 때문이다.
> - ❗ 화살표 함수는 자신에게 전달된 인수 목록을 확인할 수 없으므로, **화살표 함수로 가변 인자 함수를 구현해야 한다면 반드시 Rest 파라미터를 사용해야 한다.**

## ✅ Rest 파라미터
> - Rest 파라미터(나머지 매개변수)는 매개변수 이름 앞에 세개의 점 ...을 붙여서 정의한 매개변수를 말하며, **함수에 전달된 인수들의 목록을 배열로 전달받는다.**
> 📌** ES6에서는 rest 파라미터를 사용하여 가변 인자 함수의 인수 목록을 배열로 직접 전달받을 수 있다.**
>
>✔️ 일반 매개변수와 Rest 파라미터는 함께 사용할 수 있다.
>```js
>function foo(...rest) {
>  console.log(rest); // [1, 2, 3, 4, 5]
>}
>
>foo(1, 2, 3, 4, 5);
>```
>✔️ **Rest 파라미터는 반드시 마지막 파라미터이어야 한다.**
>```js
>function foo(param, ...rest) {
>    console.log(param); // 1
>    console.log(rest); // [2, 3, 4, 5]
>}
>
>foo(1, 2, 3, 4, 5);
>```
>❗ **Rest 파라미터는 단 하나만 선언할 수 있다.**
>```js
>function foo(...rest1, ...rest2) { }
>
>foo(1, 2, 3, 4, 5);
>// SyntaxError: Rest parameter must be last formal parameter
>```
>✔️ Rest 파라미터는 함수 객체의 length 프로퍼티에 영향을 주지 않는다.
>```js
>function foo(...rest) { }
>console.log(foo.length); // 0
>
>function baz(x, y, ...rest) { }
>console.log(baz.length); // 2
>```

## ✅ 매개변수 기본값
> - 매개변수 기본값을 사용하면 함수 내에서 수행하던 인수 체크 및 초기화를 간소화 할 수 있다.
>```js
>function sum (x = 0, y = 0) {
>  return x + y;
>}
>
>console.log(sum(1, 2)); // 3
>console.log(sum(1)); // 1
>
>console.log(sum.length); // 0
>```
>❗ 단, 매개변수 기본값은 매개변수에 인수를 전달하지 않은 경우와 `undefined`를 전달한 경우에만 유효하다.
>```js
>function logName(name = 'Lee') {
>    console.log(name);
>}
>
>logName();          // Lee
>logName(undefined); // Lee
>logName(null);      // null
>```
>❌ 앞서 살펴본 Rest 파라미터에는 기본값을 지정할 수 없다.
>```js
>function foo(...rest = []) {
>    console.log(rest);
>}
>// SyntaxError: Rest parameter may not have a default initializer
>```
> ❗ 근데, 매개변수도 안 넘겼는데 뭔가가 출력되는건 조금 이상하다! 에러를 발생시켜서 _너 인수 넘겨줘야 해!_ 라고 알리는 것이 좋다.

_<모던 자바스크립트 deepdive를 읽고 정리한 내용입니다.>_
