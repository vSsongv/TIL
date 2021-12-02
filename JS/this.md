# this
> this 개념에 대해 다룬다.(211203)

동작을 나타내는 메소드는 자신이 속한 객체의 상태, 즉 프로퍼티를 참조하고 변경할 수 있어야 한다. 이때 자신이 속한 객체의 프로퍼티를 참조하려면 **자신이 속한 객체를 카리키는 식별자를 참조할 수 있어야 한다.**
```js
function Circle(radius) {
  // 이 시점에는 생성자 함수 자신이 생성할 인스턴스를 가리키는 식별자를 알 수 없다.
  ???.radius = radius;
}

const circle = new Circle(5);
```

생성자 함수를 정의하는 시점에는 아직 인스턴스를 생성하기 이전이기 때문에 생성자 함수가 생성할 인스턴스를 가리키는 식별자를 알 수 없다. <br>
이를 위해서 자바스크립트는 `this`라는 특수한 식별자를 제공해, 자신이 속한 객체나 자신이 생성할 인스턴스를 가리킬 수 있도록 한다.

this는 자바스크립트 엔진에 의해 암묵적으로 생성되며, 함수를 호출하면 암묵적으로 함수 내부에 전달된다. 이후 this는 함수 내부에서 지역 변수처럼 사용할 수 있게 된다.

## ❓ `this`
> - `this`는 **자신이 속한 객체 또는 자신이 생성할 인스턴스를 가리키는** 자기 참조 변수(self-referencing variable)다. 
> - **`this`를 통해 자신이 속한 객체 또는 자신이 생성할 인스턴스의 프로퍼티나 메서드를 참조할 수 있다. **
> - `this`는 자바스크립트 엔진에 의해 암묵적으로 생성된다. 
> - **`this`가 가리키는 값, 즉 this 바인딩은 <span style="color:red">함수 호출 방식에 의해 동적으로 결정**</span>된다.

## 함수 호출 방식과 this 바인딩
> - this 바인딩은 **함수 호출 방식, 즉 함수가 어떻게 호출되었는지에 따라 동적으로 결정**된다.
><br>
>- 📌 함수를 호출하는 방식
>   1. 일반 함수 호출
>   2. 메서드 호출
>   3. 생성자 함수 호출
>   4. Function.prototype.apply/call/bind 메서드에 의한 간접 호출

### 🔰 일반 함수 호출
> - 기본적으로 this에는 전역 객체가 바인딩 된다. **어떠한 함수라도 일반 함수로 호출되면 this에 전역 객체가 바인딩된다.**

- 전역 함수는 물론, 중첩 함수 또한 일반 함수로 호출하면 함수 내부의 this에는 전역 객체가 바인딩된다.
```js
// 전역 함수
function foo() {
  console.log("foo's this: ", this); // window
  
  // 중첩 함수
  function bar() {
    console.log("bar's this: ", this); // window
  }
  //중첩 함수도 일반 함수로 호출되면 중첩 함수 내부의 this에는 전역 객체가 바인딩된다.
  bar();
}

foo();
```
- 다만 **strict mode가 적용된 일반 함수 내부의 this에는 undefined가 바인딩된다.** this는 객체의 프로퍼티나 메서드를 참조하기 위한 자기 참조 변수이기 때문에, 객체를 생성하지 않는 일반 함수에서는 의미가 없기 때문이다.
```js
function foo() {
  // strict mode 적용
  'use strict'; 
  
  console.log("foo's this: ", this); // undefined
  
  function bar() {
    console.log("bar's this: ", this); // undefined
  }
  
  bar();
}

foo();
```
- 콜백 함수 또한 일반 함수로 호출된다면 전역 객체가 바인딩된다.
```js
// 전역 변수
var value = 1;

const obj = {
  value: 100,
  foo() {
    console.log("foo's this: ", this); // {value: 100, foo: f}
    
    // 콜백 함수 내부의 this에는 전역 객체가 바인딩된다.
    setTimeout(function() {
      console.log("callback's this: this", this); // window
      console.log("callback's this.value: ", this.value); // 1
    }, 100);
  }
};

obj.foo();
```
- 하지만 외부 함수인 메소드의 this와 메소드의 헬퍼 역할을 하는 중첩 함수 또는 콜백 함수의 this가 다르다는 것은, 헬퍼 함수로서 동작하기 어렵게 만든다.
- 중첩, 콜백 함수의 this 바인딩을 메소드의 this바인딩과 일치시키기 위한 방법은 다음과 같다.
> ✔ 1. this 바인딩 할당
> ```js
> var value = 1;
> 
> const obj = {
>   value: 100,
>   foo() {
>     const that = this; //this를 that이라는 변수에 할당한다.
>     
>     setTimeout(function() {
>       console.log(that.value); // 100 -> 콜백 함수 내에서 that을 참조한다.
>     }, 100);
>   }
> };
> 
> obj.foo();
> ```
>
> - this 바인딩을 변수 that에 할당하면, 콜백 함수 내부에서는 this 대신 that을 참조할 수 > 있다.
> 
> ✔ 2. Function.prototype.bind()
> 
> - 또한 자바스크립트는 this를 명시적으로 바인딩할 수 있는 Function.prototype.bind > 메서드를 제공한다.
> ```js
> var value = 1;
> 
> const obj = {
>   value: 100,
>   foo() {
>     setTimeout(function() {
>       console.log(this.value); // 100
>     }.bind(this), 100); //명시적 바인딩.
>   }
> };
> 
> obj.foo();
> ```
> ✔ 3. 화살표 함수
> 
> - 화살표 함수를 사용해서 this 바인딩을 일치시킬 수 있다.
> ```js
> var value = 1;
> 
> const obj = {
>   value: 100,
>   foo() {
>     setTimeout(() => console.log(this.value), 100); // 100 -> 화살표 함수 내부의 > this는 상위 스코프의 this를 가리킨다.
>   }
> };
> 
> obj.foo();
> ```

### 🔰 메서드 호출
> - 메서드 내부의 this에는 메서드를 호출한 객체, 즉 메서드를 호출할 때 메서드 이름 앞의 마침표(.) 연산자 앞에 기술한 객체가 바인딩된다. 
> - **메서드를 호출한 객체에 바인딩된다.**

```js
const person = {
  name: 'Lee',
  getName() {
    return this.name;
  }
};

console.log(person.getName()); // Lee
```
- 메서드는 프로퍼티에 바인딩된 함수다. person 객체의 getName 프로퍼티가 가리키는 함수 객체는 person 객체에 포함된 것이 아니라 독립적으로 존재하는 별도의 객체다. getName 프로퍼티가 함수 객체를 가리키고 있을 뿐이다.

- 그렇기 때문에 getName 프로퍼티가 가리키는 함수 객체인 getName 메서드는 **다른 객체의 프로퍼티에 할당하는 것으로 다른 객체의 메서드가 될 수도 있고, 일반 변수에 할당해 일반 함수로 호출될 수도 있다.**
```js
const person = {
  name: 'Lee',
  getName() {
    return this.name;
  }
};

const anotherPerson = {
  name: 'Ssong'
};
// anotherPerson 객체에 할당
anotherPerson.getName = person.getName;

//getName을 호출한 객체는 anotherPerson 이므로 Kim이 나온다!
console.log(anotherPerson.getName()); 

// 변수에 할당
const getName = person.getName;
// 일반 함수로 호출 -> window.name과 같다.
//window.name의 기본값은 '' 이다.
console.log(getName()); // ''
```
![](https://images.velog.io/images/songjy377/post/4cc4a3aa-c4b9-4f2d-b563-c82c39e2bae8/image.png)
- 프로토타입 메소드 내부에서 사용된 this도 일반 메소드와 마찬가지로 해당 메소드를 호출한 객체에 바인딩된다. 

### 🔰 생성자 함수 호출
> - **생성자 함수 내부의 this에는 생성자 함수가 생성할 인스턴스가 바인딩된다.**
> - 만약 new 연산자와 함께 생성자 함수를 호출하지 않으면 생성자 함수가 아니라 일반 함수로 동작한다.
>```js
>function Circle(radius) {
>  this.radius = radius;
>  this.getDiameter = function () {
>    return 2 * this.radius;
>  };
>}
>
>// 생성자 함수로 호출
>const circle1 = new Circle(5);
>console.log(circle1.getDiameter()); // 10
>
>// 일반 함수로 호출
>const circle2 = Circle(10);
>console.log(circle2); // undefined
>```

### 🔰  `Function.prototype.apply/call/bind` 메서드에 의한 간접 호출
> - `apply`, `call`, `bind` 메서드는 Function.prototype의 메서드다. 즉, 이들 메서드는 모든 함수가 상속받아 사용할 수 있다.
> - `apply`, `call` 메서드는 this로 사용할 객체를 받아 함수를 호출한다.

```js
/*
@param thisArg - this로 사용할 객체
@param argsArray - 함수에게 전달할 인수 리스트의 배열 또는 유사 배열 객체
@returns 호출된 함수의 반환값
*/
Function.prototype.apply(thisArg[, argsArray])

/*
@param thisArg - this로 사용할 객체
@param arg1, arg2, ... - 함수에게 전달할 인수 리스트
@returns 호출된 함수의 반환값
*/

Function.prototype.call(thisArg[, arg1[, arg2[, ...]]])

function getThisBinding() {
  return this;
}

// this로 사용할 객체
const thisArg = { a: 1 };

console.log(getThisBinding()); // window

console.log(getThisBinding.apply(thisArg)); // { a: 1 }
console.log(getThisBinding.call(thisArg)); // { a: 1 }
```
### 💠 `apply`, `call` 
>- `apply`, `call`  메서드의 본질적인 기능은 **함수를 호출하는 것**이다. 
>- 함수를 호출하면서 **첫 번째 인수로 전달한 특정 객체를 호출한 함수의 this에 바인딩**한다.
>
>```js
>function getThisBinding() {
>  return this;
>}
>
>// this로 사용할 객체
>const thisArg = { a: 1 };
>//`apply` 메서드는 호출할 함수의 인수를 배열로 묶어 전달한다.
>console.log(getThisBinding.apply(thisArg, [1, 2, 3])); 
>// Arguments(3) [1, 2, 3 callee: f, Symbol.iterator: f]
>// { a: 1 }
>
>//`call` 메서드는 호출할 함수의 인수를 쉼표로 구분한 리스트 형식으로 전달한다.
>console.log(getThisBinding.call(thisArg, 1, 2, 3)); 
>// Arguments(3) [1, 2, 3 callee: f, Symbol.iterator: f]
>// { a: 1 }
>```
>- `apply`와 `call` 메소드의 대표적인 용도는 `arguments` 객체와 같은 유사 배열 객체에 배열 메소드를 사용하는 경우다. `arguments` 객체는 배열이 아니기 때문에 Array.prototype.slice 같은 배열의 메소드를 사용할 수 없으나 `apply`와 `call`을 이용하면 가능하다.
>```js
>function getThisBinding() {
>  return this;
>}
>
>// this로 사용할 객체
>const thisArg = { a: 1 };
>
>console.log(getThisBinding()); // window
>
>console.log(getThisBinding.apply(thisArg)); // { a: 1 }
>console.log(getThisBinding.call(thisArg)); // { a: 1 }
>```

### 💠 `bind` 
>- `bind` 메소드는  apply와 call 메서드와 달리 함수를 호출하지 않고 this로 사용할 객체만 전달한다.
>- 메소드의 this와 메소드 내부의 중첩 함수 또는 콜백 함수의 this가 불일치하는 문제를 해결하기 위해 유용하게 사용된다.
>```js
>const person = {
>    name: "Ssong",
>    foo(callback) {
>        //bind 메소드로 callback함수 내부의 this 바인딩을 전달
>        setTimeout(callback.bind(this), 100);
>    }
>};person.foo(function () {
>
>
>    console.log(`Hi, my name is ${this.name}.`);
>});
>```

### 📌 상황별 `this`가 가리키는 것

>📌 **<span style="color:orangered">객체 리터럴의 메소드로서 호출된 함수 내부의 this는 그 메소드를 호출한 객체를 의미한다.</span>**
>```js
>const Circle = {
>  radius: 5,
>  gerDiameter() {
>    //this는 circle을 가리킨다.
>    return this.radius;
>  }
>}
>```
>📌 **<span style="color:orangered">생성자함수 내부의 this는 생성자 함수가 생성할 인스턴스를 가리킨다.</span>**
>
>```js
>function Circle(radius) {
>    //this는 생성자 함수가 생성할 인스턴스를 가리킨다.
>    this.radius = radius;
>}
>
>Circle.prototype.getDiameter = function () {
>    //this는 생성자 함수가 생성할 인스턴스를 가리킨다.
>    return 2 * this.radius;
>};
>```
>
>📌 **<span style="color:orangered">일반 함수 내부의 this는 전역객체 window를 가리킨다.</span>**
>```js
>//this는 어디서든지 참조 가능하다.
>//전역에서 this는 전역 객체 window를 가리킨다.
>console.log(this) //window
>
>function square(number) {
>    //일반 함수 내부의 this는 전역객체 window를 가리킨다.
>    console.log(this); //window
>    return number * number; 
>}
>```
>📌 **<span style="color:orangered">인스턴스/프로토타입 메소드 내에서 this는 인스턴스를 가리킨다.</span>**

|함수 호출 방식|	this 바인딩
|---|---|
|일반 함수 호출|	전역 객체
|메서드 호출|	메서드를 호출한 객체
|생성자 함수 호출|	생성자 함수가 생성할 인스턴스
|apply/call/bind()에 의한 호출|	apply/call/bind() 메서드에 인수로 전달한 객체
_<모던 자바스크립트 deepdive를 읽고 정리한 내용입니다.>_