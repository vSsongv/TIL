# Closure
> Closure의 개념과 그 활용 내용을 정리했다. (211209)

MDN에서는 클로저(Closures)를 이렇게 정의하고 있다.

>_클로저란 함수와 그 함수가 선언된 렉시컬 환경과의 조합이다._

내가 이해한 대로 문장을 바꿔 보자면, 

>**클로저란 클로저가 선언된 렉시컬 환경(상위 스코프)과의 조합이다.**

참 난해하다. 렉시컬 환경이란 무엇이고, 그럼 클로저란 대체 무엇일까?

### ✅ 렉시컬 스코프
>- **JS 엔진은 함수를 어디에 정의했는지에 따라 상위 스코프를 결정한다.** 렉시컬 환경의 _"외부 렉시컬 환경에 대한 참조"_ 에 저장할 참조값, **즉 상위 스코프에 대한 참조는 함수 정의가 평가되는 시점에 함수가 정의된 환경(위치)에 의해 결정**된다. 이것이 바로 렉시컬 스코프다.
>```js
>var x = 1;
>const y = 2;
>
>function foo() {
>  const x = 10;
>  bar();
>}
>
>function bar() {
>  console.log(x);
>}
>
>foo(); // 1
>bar(); // 1
>```
- foo 와 bar 함수는 전역에서 정의된 전역 함수다. 함수의 상위 스코프는 함수를 어디서 정의했는지에 따라 결정되기 때문에, **foo와 bar 함수의 상위 스코프는 전역이다.
**

### ✅ 함수 객체의 내부 슬롯 `[[Environment]]`
> - **함수는 자신이 정의된 환경, 즉 상위 스코프를 기억해야 한다.** 
> - **이를 위해 함수는 내부 슬롯 `[[Environment]]`에 자신이 정의된 환경, 즉 상위 스코프의 참조를 저장한다. **
> - 이때 자신의 내부 슬롯 `[[Environment]]`에 저장된 현재 실행 중인 실행 컨텍스트의 렉시컬 환경이 바로 상위 스코프이다.
> - **자신이 존재하는 한 렉시컬 환경의 참조를 기억한다.**
>```js
>var x = 1;
>const y = 2;
>
>function foo() {
>  const x = 10;
>  bar();
>}
>//함수 bar는 자신의 상위 스코프, 전역 렉시컬 환경을 [[Environment]]에 저장하여 기억한다.
>function bar() {
>  console.log(x);
>}
>
>foo(); // 1
>bar(); // 1
>```

### ✅ 클로저와 렉시컬 환경
> - 보통 내부 함수는 외부 함수가 죽을 때 죽는다.
> - **외부 함수 보다 중첩 함수가 더 오래 유지되는 경우 중첩 함수는 이미 생명 주기가 종료한 외부 함수의 변수를 참조할 수 있다. 이러한 중첩 함수를 클로저라고 부른다.**

```js
const x = 1;

function outer() {
  const x = 10;
  const inner = function () { console.log(x); };
  return inner;
}
//outer를 호출하면 중첩 함수 inner를 반환한다.
//그리고 outer함수의 실행 컨텍스트는 실행 컨텍스트 stack에서 pop되어 제거된다.
const innerFunc = outer();
innerFunc(); // 10
```
- `outer` 함수를 호출하면 `outer` 함수는 중첩 함수 `inner`를 반환하고 생명 주기를 마감한다. `outer` 함수의 지역 변수 x는 더는 유효하지 않는다는 것이다.
- 하지만 `innerFunc()`;에서 보면 `outer` 함수의 지역 변수 x가 다시 부활이라도 한 듯이 값을 10으로 출력하는 것을 볼 수 있다.
- **함수는 자신의 상위 스코프를 기억한다. `inner` 함수는 자신이 정의된 위치에 따라 결정된 상위 스코프를 저장한다.**
- `outer` 함수는 평가될 때 전역 렉시컬 환경을 상위 스코프로서 저장한다. 그리고 중첩 함수 `inner`는 함수 표현식으로 정의했기 때문에 런타임에 평가된다. **`inner` 함수는 `outer` 함수의 렉시컬 환경을 상위 스코프로서 저장한다.**
- outer 함수의 실행이 종료되면 `outer` 함수의 생명 주기는 종료되어 실행 컨텍스트 스택에서 제거되지만 **`outer` 함수의 렉시컬 환경까지 소멸하는 것은 아니다.**
- `outer` 함수의 렉시컬 환경은 `inner` 함수가 참조하고 있고 `inner` 함수는 전역 변수 `innerFunc`가 참조하고 있어 가비지 컬렉션의 대상이 되지 않는다. -> 가비지 컬렉터는 누군가가 참조하고 있는 메모리 공간을 함부로 해제하지 않는다. 따라서 x 변수에 접근이 가능하여 10이 출력된다.

> 📌 <span style="color:orangered">**inner 함수를 우리는 클로저라 부른다.**</span>

### 그림필요

>❓ 그럼 상위 스코프를 기억하는 함수, 즉 모든 함수는 클로저일까?
>✔️ **상위 스코프의 어떤 식별자도 참조하지 않는 함수는 클로저가 아니다.**

```js
function foo() {
  const x = 1;
  
  // bar 함수는 클로저일까?
  function bar () {
    const y = 2;
    console.log(y); // 상위 스코프의 식별자를 참조하지 않는다.
  }
  
  return bar;
}

const bar = foo();
bar(); // 2
```
- 상위 스코프의 어떤 식별자도 참조하지 않는 경우, 대부분의 모던 브라우저는 최적화를 통해 상위 스코프를 기억하지 않는다. 참조하지도 않는 식별자를 기억하는 것은 메모리 낭비이기 때문이다.
- **그렇기 때문에 bar 함수는 클로저라 할 수 없다.**

>❓ 그럼 상위 스코프의 식별자를 참조하고 있는 함수는 모두 클로저일까?
>✔️ **외부 함수보다 먼저 소멸되는 함수는 클로저라 할 수 없다.**

```js
function foo() {
  const x = 1;
  
  // bar 함수는 클로저였지만 곧바로 소멸한다.
  function bar () {
    console.log(x);
  }
  
  bar();
}

foo(); // 1
```
- 중첩 함수 bar는 상위 스코프의 식별자를 참조하고 있기 때문에 클로저다. 하지만 외부 함수 foo의 외부로 함수 bar가 반환되지 않는다. **즉, 함수 bar는 함수 foo보다 생명 주기가 짧다.**
- **때문에 함수 bar는 클로저라 하지 않는다.**

>❓ 그럼 무엇을 클로저라 부르는 것일까?
>✔️ **상위 스코프의 식별자를 참조하고, 외부 함수보다 더 오래 유지되는 경우의 중첩 함수**를 클로저라 부른다.

> 📌 클로저에 의해 참조되는 상위 스코프의 변수를 <span style="backgroundColor:orange">**자유 변수**</span>라고 부른다.


### ✅ 클로저의 활용
> - 클로저는 상태를 안전하게 변경하고 유지하기 위해 사용한다. '
> - 즉, 상태가 의도치 않게 변경되지 않도록 상태를 안전하게 은닉하고 특정 함수에게만 상태 변경을 허용하기 위해 사용한다.

- 아래 코드는 함수가 호출될 때마다 호출된 횟수를 누적해 출력하는 카운터 예제이다.

```js
// 카운트 상태 변수
let num = 0;

const increase = function () {
  //카운트 상태를 1만큼 증가시킨다.
  return ++num;
};

console.log(increase()); // 1
console.log(increase()); // 2
console.log(increase()); // 3
```
- 카운트 상태는 전역 변수를 통해 관리되고 있기 때문에 언제든지 누구나 접근할 수 있고 변경할 수 있다. 결국 변수 num의 값이 변경되면 이는 오류로 이어진다.

- 👍🏻 상태를 유지할 수 있도록 클로저를 사용해보자.
```js
const increase = (function () {
  let num = 0;
  // 클로저
  return {
    increase() {
    	return ++num;
  	},
    decrease() {
      return num > 0 ? --num : 0;
    }
  };
}());

console.log(increase()); // 1
console.log(increase()); // 2

console.log(decrease()); // 1
console.log(decrease()); // 0
```
- 즉시 실행 함수가 호출되고 즉시 실행 함수가 반환한 함수가 increase 변수에 할당된다. increase 변수에 할당된 함수는 자신이 정의된 위치에 의해 결정된 상위 스코프인 즉시 실행 함수의 렉시컬 환경을 기억하는 클로저다.

- **즉시 실행 함수는 한 번만 실행되기 때문에** increase가 호출될 때마다 num 변수가 재차 초기화될일은 없다. 또한 num 변수는 외부에서 직접 접근할 수 없는 은닉된 private 변수이기 때문에 의도치 않은 변경을 걱정할 필요가 없다.

✔️ **위 예제를 생성자 함수로 표현한 코드**
```js
const Counter = (function () {
  let num = 0;
  function Counter() {
  }
  
  Counter.prototype.increase = function() {
    return ++num;
  };
  Counter.prototype.decrease = function() {
    return num > 0 ? --num : 0;
  };
  
  return Counter;
}());

const counter = new Counter();
```

- 즉시 실행 함수 내에서 선언된 num 변수는 인스턴스를 통해 접근할 수 없으며, 즉시 실행 함수 외부에서도 접근할 수 없는 은닉된 변수다.

- `increase, decrease`는 프로토타입을 통해 상속되는 프로토타입 메서드이지만, 자신의 함수 정의가 평가되어 함수 객체가 될 때 실행 중인 실행 컨텍스트인 즉시 실행 함수 실행 컨텍스트의 렉시컬 환경을 기억하는 클로저다.

- 따라서 즉시 실행 함수의 자유 변수 num을 참조할 수 있다. **즉, num 변수의 값은 `increase,decrease` 메서드만이 변경할 수 있다.**

📌 함수형 프로그래밍에서 클로저를 활용하는 예제
```js
function makeCounter(predicate) {
  // 카운트 상태를 유지하기 위한 자유 변수
  let counter = 0;
  
  // 클로저를 반환
  return function () {
    counter = predicate(counter);
    return counter;
  };
}

// 보조 함수
function increase(n) {
  return ++n;
}

// 보조 함수
function decrease(n) {
  return --n;
}
//함수로 함수를 생성한다.
const increaser = makeCounter(increase);
console.log(increaser()); // 1
console.log(increaser()); // 2

//  increaser 함수와 별개의 렉시컬 환경을 갖기 때문에 카운터 상태가 연동하지 않는다.
const decreaser = makeCounter(decrease);
console.log(decreaser()); // -1
console.log(decreaser()); // -2
```
- `makeCounter` 함수가 반환하는 함수는 `makeCounter` 함수의 스코프에 속한 couter 변수를 기억하는 클로저다.

- **`makeCounter` 함수를 호출해 함수를 반환할 때 반환된 함수는 자신만의 독립적인 렉시컬 환경을 갖는다.** 함수를 호출하면 그때마다 새로운 `makeCounter` 함수 실행 컨텍스트의 렉시컬 환경이 생성되기 때문이다.

- ❌ 즉, 전역 변수 `increaser`와 `decreaser`는 별개의 렉시컬 환경을 갖기 때문에 카운터 상태가 연동되지 않는다.

- 👉🏻 만약 독립된 카운터가 아니라 증감이 가능한 카운터를 만들려면 렉시컬 환경을 공유하는 클로저를 만들어야 한다. 즉 makeCounter함수를 두 번 호출하지 말아야 한다.

```js
const counter = (function () {
  // 카운트 상태 변수
  //즉시 실행 함수의 렉시컬 환경이 단 하나기 때문에, counter함수는 단 하나이다.
  let counter = 0;
  
  // 클로저를 반환
  return function (predicate) {
    counter = predicate(counter);
    return counter;
  };
}());

// 보조 함수
function increase(n) {
  return ++n;
}

// 보조 함수
function decrease(n) {
  return --n;
}

console.log(counter(increase)); // 1
console.log(counter(increase)); // 2
//자유 변수를 공유한다.
console.log(counter(decrease)); // 1
console.log(counter(decrease)); // 0
```

### 캡슐화와 정보 은닉
> - 캡슐화(encapsulation)는 **객체의 상태를 나타내는 프로퍼티와 프로퍼티를 참조하고 조작할 수 있는 동작인 메서드를 하나로 묶는 것**을 말한다. 
> - 캡슐화는 객체의 특정 프로퍼티나 메서드를 감출 목적으로 사용하기도 하는데 이를 **정보 은닉**이라 한다.
> - 정보은닉은 **외부에 공개할 필요가 없는 구현의 일부를 외부에 공개되지 않도록 감추어 적절치 못한 접근으로부터 객체의 상태가 변경되는 것을 방지해 정보를 보호하고, 객체 간의 상호 의존성, 즉 결합도를 낮추는 효과가 있다.** -> 서로가 서로를 모르게 하라.
>```js
>fuction Person(name, age) {
>  this.name  = name; // public
>  let _age = age; // private
>}
>
>//프로토타입 메소드
>Person.prototype.sayHi = function () {
>  console.log(`${this.name} , ${_age}.`);
>};
>const me = new Person('Ssong', 24);
>console.log(me.name); //Ssong
>console.log(me._age); //undefined -> Person 생성자 함수의 지역 변수 _age를 참조할 수 없다.
```

- ❓ 어떻게 해야 _age를 참조할 수 있을까?
- ✔️ 즉시 실행 함수를 사용
```js
const Person = (function () {
  let _age = 0; // private
  
  function Person(name, age) {
    this.name = name; // public
    _age = age;
  }
  //프로토타입 메소드
  Person.prototype.sayHi = function () {
    console.log(`${this.name} , ${_age}.`);
  };
  return Person;
}());
```
- Person 생성자 함수와 `sayHi` 메서드는 이미 종료되어 소멸한 즉시 실행 함수의 지역 변수 `_age`를 참조할 수 있는 클로저다.
- 그러나 생성자 함수로 여러 개의 인스턴스를 생성할 경우 `_age` 변수의 상태가 유지되지 않는다.

```js
const me = new Person('Ssong', 24);
me.sayHi(); //Hi! My name is Ssong. I am 24.
const you= new Person('Hana', 21);
you.sayHi(); //Hi! My name is Hana. I am 21.
me.sayHi(); //Hi! My name is Ssong. I am 21. 
//_age 값이 변경된다.
```
- 이는 `sayHi`메소드가 즉시 실행 함수가 호출될 때 생성되어 즉시 실행 함수의 렉시컬 환경을 참조하고 있으므로, 어떤 인스턴스에서 호출하여도 하나의 동일한 상위 스코프를 사용하게 된다. 그렇기 때문에 `_age`변수의 상태가 유지되지 않는다. -> JS는 정보 은닉을 완전하게 지원하지 않는다.

### ✅ 자주 발생하는 실수
- 👎🏻 바르게 동작하지 않는 코드
```js
var funcs = [];

for (var i = 0; i<3; i++) {
  funcs[i] = function () {return i; };
}

for (var j = 0; j<funcs.length; j++) {
  console.log(funcs[j]());
}
기대 출력값 : 0, 1, 2
실제 출력값 : 3, 3, 3
```
❗ for문의 변수 선언문에서 선언한 var 키워드 i 변수는 함수 레벨 스코프를 갖기 때문에 전역변수 이므로 전역 변수 i를 참조하여 i의 값 3이 출력된다.

👍🏻 클로저를 활용하여 바르게 동작하는 코드
```js
var funcs = [];

for (var i = 0; i< 3; i++) {
  funcs[i] = (function (id) {
    return function () {
      return id;
    };
  }(i));
}

for (var j = 0; j < funcs.length; j++) {
  console.log(funcs[j]()); //0, 1, 2
}
```
- i를 인수로 받아 매개변수 id에 할당한 후 중첩 함수를 반환하고 종료된다. 매개 변수 id는 즉시 실행 함수가 반환한 중첩 함수의 상위 스코프에 존재하기 때문에 자유 변수가 되어 그 값이 유지된다.

👍🏻 let을 사용하여 해결한 코드
```js
const funcs = [];

for(let i = 0; i < 3; i++) {
  func[i] = function () { return i; };
}

for(let i = 0; i < funcs.length; i++) {
  console.log(funcs[i]()); // 0 1 2
}
```
- for문이 반복될 때마다 독립적인 렉시컬 환경을 생성하여 식별자의 값을 유지한다.
- let이나 const 키워드를 사용하는 반복문은 코드 블록을 반복 실행할 때마다 새로운 렉시컬 환경을 생성하여 반복할 당시의 상태를 저장한다.

👍🏻 함수형 프로그래밍 기법인 고차 함수를 사용하여 해결한 코드

```js
// 배열의 요소로 추가된 함수들은 모두 클로저다
const funcs = Array.from(new Array(3), (_, i) => () => i);

funcs.forEach(f => console.log(f())); // 0 1 2
```
