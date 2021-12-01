# 함수와 일급 객체
> 함수와 일급 객체의 정의, 함수 객체의 프로퍼티에 대해 정리하였다.(211130)

객체는 객체 리터럴 이외의 생성자 함수를 사용하여 객체를 생성할 수 있다.
## ✅ 일급 객체
- 다음 조건을 만족하는 객체를 일급 객체라 한다.
> 1. 무명의 리터럴로 생성할 수 있다. -> 런타임에 생성이 가능하다.
> 2. 변수나 자료구조(객체, 배열 등)에 저장할 수 있다.
> 3. 함수의 매개변수에 전달할 수 있다.
> 4. 함수의 반환값으로 사용할 수 있다.
- 자바스크립트의 함수는 위의 조건을 모두 만족하는 일급 객체이다.
```js
	// 함수는 무명의 리터럴로 생성 가능, 변수에 저장 가능
	const increase = function (num) {
	  return ++num;
	}
	
	const decrease = function (num) {
	  return --num;
	}
	
	// 객체에 저장 가능
	const predicates = { increase, decrease };
	
	// 매개변수로 전달 가능, 반환값으로 사용 가능
	function makeCounter(predicate) {
	  let num = 0;
	  
	  return function () {
	    num = predicate(num);
	    return num;
	  };
	}
	
	// 매개변수에게 함수 전달 가능
	const increaser = makeCounter(predicates.increase);
	console.log(increaser()); // 1
	console.log(increaser()); // 2
	
	const decreaser = makeCounter(predicates.decrease);
	console.log(decreaser()); // -1
	console.log(decreaser()); // -2
```

- 함수가 일급 객체라는 것은 **함수를 객체와 동일하게 사용할 수 있다는 것**이다. 객체는 값이기 때문에 함수는 값과 동일하게 취급할 수 있다.
- **함수는 값을 사용할 수 있는 어디서든지 리터럴로 정의할 수 있고, 런타임에 함수 객체로 평가된다.**
- 📌 일급 객체로서 함수가 가지는 가장 큰 특징은 일반 객체와 같이 **함수의 매개변수에 전달할 수 있으며, 함수의 반환값으로 사용할 수 있다는 것**이다.
> ❗ 일반 객체 vs 함수 객체
>- 일반 객체는 호출할 수 없지만 함수 객체는 호출을 할 수 있다. 
>- 함수 객체에는 일반 객체에는 없는 함수 고유의 프로퍼티를 가진다.

## ✅ 함수 객체의 프로퍼티
함수는 객체이기 때문에 프로퍼티를 가질 수 있다.

브라우저 콘솔에서 `console.dir` 메서드를 사용해서 함수 객체의 프로퍼티들을 살펴보면 다음과 같다.
```js
function square(number) {
  return number * number;
}

// 객체의 속성을 계층 구조로 출력한다.
console.dir(square);
```
![](https://images.velog.io/images/songjy377/post/39db0117-0389-4dbc-884c-8191572dac7a/image.png)
- `Object.getOwnPropertyDescriptors` 메소드로 모든 프로퍼티의 프로퍼티 어트리뷰트를 확인해보면 아래와 같다.
![](https://images.velog.io/images/songjy377/post/e81c3550-faf3-4102-a368-8a21ea6941be/image.png)
- `arguments`, `caller`, `length`, `name`, `prototype` 프로퍼티는 일반 객체에는 없는 함수 객체의 고유한 데이터 프로퍼티다.
- 그러나 `__proto__`는 함수 객체의 고유의 프로퍼티가 아니라 Object.prototype 객체의 프로퍼티를 상속받은것이다. (Object.prototype 객체의 프로퍼티는 모든 객체가 상속 받아 사용할 수 있다.)

### 🔰 arguments property
> - 함수 객체의 arguments 프로퍼티 값은 arguments 객체다. 
> - arguments 객체는 함수 호출 시 전달된 인수들의 정보를 담고 있는 순회 가능한 유사 배열 객체이며, 함수 내부에서 지역 변수처럼 사용된다. -> **즉 함수 외부에서는 참조할 수 없다.**

```js
function multiply(x, y) {
  console.log(arguments);
  return x * y;
}
console.log(multiply()); // [Arguments] {} NaN 
console.log(multiply(1)); // [Arguments] { '0': 1 } NaN -> 매개변수 개수 일치 안해도 가능
console.log(multiply(1, 2)); // [Arguments] { '0': 1, '1': 2 } 2
console.log(multiply(1, 2, 3)); // { '0': 1, '1': 2, '2': 3 } 2
```
- 함수를 정의할 때 선언한 매개변수는 함수 내부에서 변수와 동일하게 취급된다. 
- 함수가 호출되면 함수 내에서 암묵적으로 매개변수가 선언이 되고 **undefined로 초기화된 이후 인수가 할당**된다.
![](https://images.velog.io/images/songjy377/post/0b911a0f-03f0-42f4-9e11-e6e0a282a429/image.png)
- 📌 `arguments` 객체는 인수를 프로퍼티 값으로 가지고, 프로퍼티 키는 인수의 순서를 나타낸다. `arguments` 객체의 `callee` 프로퍼티는 호출되어 `arguments` 객체를 생성한 함수 자신을 가리키고, `length` 프로퍼티는 인수의 개수를 가리킨다.
- 자바스크립트는 함수의 매개변수(parameter)와 인수(argument)의 개수가 일치하는지 확인하지 않기 때문에, 함수가 호출되면 인수 개수를 확인하고 이에 따라 함수의 동작을 달리 정의할 필요가 있을 수 있다.

>📌 `arguments` 객체는 매개변수를 확정할 수 없는 **가변 인자 함수**를 구현할 때 유용하다.

```js
function sum() {
  let res = 0;
  //arguments 객체는 length 프로퍼티가 있는 유사 배열 객체이므로 for문을 통해 순회할 수 있다. 
  for(let i = 0 ; i < arguments.length ; i++) {
    res += arguments[i];
  }
  
  return res;
}

console.log(sum()); // 0
console.log(sum(1, 2)); // 3
console.log(sum(1, 2, 3)); // 6
```
- ❗ 유사 배열 객체는 배열이 아니므로 배열 메서드를 사용할 경우 에러가 발생한다. 배열 메서드를 사용하려면 간접 호출을 해야 하는 번거로움이 있다.

### 🔰 length property
> - length 프로퍼티는 함수를 정의할 때 선언한 매개변수의 개수를 가리킨다.

```js
function foo() {}
console.log(foo.length); // 0

function bar(x) {
  return x;
}
console.log(bar.lengh); // 1

function baz(x, y) {
  return x * y;
}
console.log(baz.length); // 2
```
- `arguments` 객체의 `length` 프로퍼티와 함수 객체의 `length` 프로퍼티의 값은 다를 수 있다는 점을 인지해야 한다. 
- **`arguments` 객체의 `length` 프로퍼티**는 **인자(argument) 의 개수**를 가리키고, **함수 객체의 `length` 프로퍼티**는 **매개변수(parameter) 의 개수**를 가리킨다.

### 🔰 name property

> - name 프로퍼티는 함수 이름을 나타낸다.
> - _ES6에서부터 정식 표준이 되었으며, ES5와 ES6에서 다르게 동작하기 때문에 주의해야 한다. 익명 함수 표현식의 경우 ES5에서는 빈 문자열을 값으로 갖지만 ES6에서는 함수 객체를 가리키는 식별자를 값으로 갖는다._

```js
// 기명 함수 표현식
var nameFunc = function foo() {};
console.log(nameFunc.name); // foo

// 익명함수 표현식
var anonymousFunc = function() {};
console.log(anonymousFunc.name); // ES5: "", ES6: anonoymousFunc

// 함수 선언문
function bar() {}
console.log(bar.name); // bar
```

### 🔰 `__proto__` 접근자 property

> - `__proto__` 프로퍼티는 `[[Prototype]]` 내부 슬롯이 가리키는 프로토타입 객체에 접근하기 위해 사용하는 접근자 프로퍼티다.
> - 객체 리터럴 방식으로 생성한 객체의 프로토타입 객체는 Object.prototype이다. 즉, 객체 리터럴 방식으로 생성한 객체는 프로토타입 객체인 **Object.prototype의 프로퍼티를 상속**받는다.

```js
const obj = { a: 1 };

console.log(obj.__proto__ === Object.prototype); // true

//객체 리터럴 방식으로 생성한 객체는 프로토타입 객체인 Object.prototype의 프로퍼티를 상속받는다.
//hasOwnProperty 메소드는 Object.prototype의 메소드다.
console.log(obj.hasOwnProperty('a')); // true
console.log(obj.hasOwnProperty('__proto__')); // false
console.log(obj.__proto__ === Object.prototype); // true
```

> ❓ `hasOwnProperty` : 인수로 전달받은 프로퍼티 키가 객체 고유의 프로퍼티 키인 경우에만 true를 반환하고 상속받은 프로토타입의 프로퍼티 키인 경우 false를 반환한다. 

### 🔰 prototype property
> - prototype 프로퍼티는 생성자 함수로 호출할 수 있는 함수 객체, 즉 **`constructor` 만이 소유하는 프로퍼티**다.

```js
// 함수 객체는 prototype 프로퍼티를 가진다.
(function () {}).hasOwnProperty('prototype'); // true

// 일반 객체는 prototype 프로퍼티를 가지지 않는다.
({}).hasOwnProperty('prototype'); // false
```
- prototype 은 함수가 객체를 생성하는 생성자 함수로 호출될 때 생성자 함수가 생성할 인스턴스의 프로토타입 객체를 가리킨다. [프로토타입 문서](https://velog.io/@songjy377/JS-Prototype)