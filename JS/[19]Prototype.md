# Prototype
> 프로토타입의 정적 프로퍼티, 정적 메소드 ,Object.create ,직접 상속 ,프로퍼티 섀도잉 ,프로토타입 체인 등을 다룬다.(211201)

>- JS는 명령형, 함수형, 프로토타입 기반 OOP를 지원하는 멀티 패러다임 프로그래밍 언어이다.
>- JS를 이루고 있는 거의 모든 것이 **객체** 다.

>📌 객체지향 프로그래밍
>- OOP는 실세계의 실체(사물이나 개념)을 인식하는 철학적 사고를 프로그래밍에 접목하려는 시도에서 시작한다.
- 실체는 **특징이나 성질을 나타내는 속성(property)** 을 가지고 있다. -> 이를 통해 특정한 실체를 구별할 수 있다.
> <br>
> - 예를 들어, 사람은 이름, 주소, 나이, 성별 등의 다양한 속성을 갖는다. 이때 _이름이 아무개이고 성별은 여성이며 나이는 20세인 사람_ 으로 속성을 구체적으로 표현하면 특정한 사람을 다른 사람과 구별하여 인식할 수 있다.
> - 이때 우리가 필요한 속성이 이름과 주소 속성일 때, 이렇게 다양한 속성 중에서 **프로그램에 필요한 속성만을 간추리는 것을 추상화**라고 한다.
> <br>
>- 프로그래머는 속성으로 표현된 객체 하나를 다른 객체와 구별할 수 있다.
>- **속성을 통해 여러 개의 값을 하나의 단위로 구성한 복합적인 자료구조를 객체**라고 한다.
>```js
>const Circle = {
>  radius = 5;
>  
>  getDiameter {
>    return 2 * this.radius;
>  }
>```
>- 위 코드에서 반지름은 **원의 상태를 나타내는 데이터**이며, **원의 지름을 구하는 것은 동작**이다.
>- 객체는 **상태 데이터와 동작을 하나의 논리적인 단위로 묶은 복합적인 자료구조**이다. 
>- 이때 객체의 <span style="color:blue">상태 데이터</span>를 <span style="color:blue">property</span>, <span style="color:green">동작</span>을 <span style="color:green">method</span>라고 한다.
>- 각 객체는 다른 객체와 메시지를 주고받거나 데이터를 처리할 수도 있다. 또는 다른 객체의 데이터나 메소드를 상속받을수도 있다.

## ✅ 프로토타입과 상속
- 상속(inheritance)은 어떤 객체의 프로퍼티 또는 메서드를 다른 객체가 상속받아 그대로 사용할 수 있는 것을 말한다.
- JS는 프로토타입을 기반으로 상속을 구현하여 불필요한 중복을 제거한다. -> 중복 제거는 개발 비용을 현저히 줄일 수 있는 잠재력이 있다.
```js
// 생성자 함수
function Circle(radius) {
  this.radius = radius;
  this.getDiameter = function () {
    return 2 * this.radius;
  }
};

const circle1 = new Circle(5); // 반지름이 5인 Circle 객체 생성
const circle2 = new Circle(10); // 반지름이 10인 Circle 객체 생성

console.log(circle1.getDiameter === circle2.getDiameter); // false

console.log(circle1.getDiameter()); // 10
console.log(circle2.getDiameter()); // 20
```

- 생성자 함수는 동일한 프로퍼티 구조를 갖는 객체를 여러 개 생성할 때 유용하다. 하지만 자세히 보면, 위의 Circle 생성자 함수에는 한 가지 문제가 있다.
- `Circle` 함수는 `radius` 프로퍼티와 `getDiameter` 메서드를 갖는다. `radius` 프로퍼티 값은 인스턴스마다 다르지만 `getDiameter` 메서드는 모든 인스턴스가 동일한 메서드를 사용하기 때문에, Circle 함수는 인스턴스를 생성할 때마다 getDiameter 메서드를 중복해서 생성하게 된다.

![](https://images.velog.io/images/songjy377/post/ed921fc7-2197-4184-a690-42f09893e128/image.png)
- 이렇게 동일한 메서드를 중복해서 소유하는 것은 메모리를 불필요하게 낭비하게 된다. 이 문제를 상속을 이용해 리펙토링을 할 수 있다.
```js
function Circle(radius) {
  this.radius = radius;
}

// Circle의 모든 인스턴스가 getDiameter를 공유해서 사용하도록 프로토타입에 추가한다.
Circle.prototype.getDiameter = function () {
  return 2 * this.radius;
}

const circle1 = new Circle(5);
const circle2 = new Circle(10);

// Circle의 모든 인스턴스는 하나의 getDiameter 메서드를 공유한다.
console.log(circle1.getDiameter === circle2.getDiameter); // true

console.log(circle1.getDiameter()); // 10
console.log(circle2.getDiameter()); // 20
```
![](https://images.velog.io/images/songjy377/post/9477b88c-cd4f-4525-bb64-201064b96d86/image.png)

> - **`circle1`과 `circle2` 인스턴스는 자신의 부모 객체 역할을 하는 `Circle.prototype` 에 대한 모든 프로퍼티와 메서드를 상속받는다.**
> - `getDiameter` 메서드는 단 하나만 생성되어 프로토타입인 `Circle.prototyp`e의 메서드로 할당되어 있다. 
> - `Circle` 생성자 함수가 생성하는 모든 인스턴스는 `getDiameter` 메서드를 상속받아 사용할 수 있다.
>- 즉, `radius` 프로퍼티만 개별적으로 가지고 있고 내용이 동일한 `getDiameter` 메서드는 상속을 통해 공유하여 사용하는 것이다.

> 📌 **프로토타입은 어떤 객체의 부모 역할을 하는 객체로서, 객체 간 상속을 구현하기 위해 사용된다.**

## ✅ 프로토타입 객체
> - Prototype(프로토타입)은 객체 간 상속을 구현하기 위해 사용된다.
> - 생성자 함수에 의해 생성된 각각의 객체에 공유 프로퍼티를 제공하기 위해 사용한다.

- 모든 객체는 `[[Prototype]]`이라는 내부 슬롯을 가진다. 
- 이 값은 null이거나, 프로토타입의 참조값이다.
- `[[Prototype]]`에 저장되는 프로토타입은 객체 생성 방식에 의해 결정된다. -> **객체가 생성될 때 객체 생성 방식에 따라 프로토타입이 결정**되고 `[[Prototype]]`에 저장된다.
- 예를 들어, 객체 리터럴에 의해 생성된 객체의 프로토타입은 `Object.prototype`이고, 생성자 함수에 의해 생성된 객체의 프로토타입은 생성자 함수의 `prototype` 프로퍼티에 바인딩되어있는 객체다.
- 📌**모든 객체는 하나의 프로토타입을 갖는다.**(`[[Prototype]]` 값이 null인 경우 제외) 그리고 **모든 프로토타입은 생성자 함수와 연결**되어 있다.
![](https://images.velog.io/images/songjy377/post/00ffb23d-52af-46b1-81c2-5d3789918057/image.png)
> 📌<span style="color:red">**중요**</span>📌
>- `[[Prototype]]` 내부 슬롯에는 직접 접근할 수 없지만, 위 그림처럼 `__proto__` 접근자 프로퍼티를 통해 자신의 프로토타입, 즉 자신의 `[[Prototype]]` 내부 슬롯이 자리키는 프로토타입에 접근할 수 있다.
> - 프로토타입은 자신의 `constructor` 프로퍼티를 통해 생성자 함수에 접근할 수 있고, 생성자 함수는 자신의 `prototype` 프로퍼티를 통해 프로토타입에 접근할 수 있다.

### 🔰 `__proto__` 접근자 프로퍼티
- 모든 객체는 `__proto__` 프로퍼티를 통해 자신의 프로토타입에 간접적으로 접근할 수 있다.
```js
	const person = { name: 'Lee' };
```
![](https://images.velog.io/images/songjy377/post/9ef83b3b-0c81-4f61-a27c-4446889250bf/image.png)
- 주황 박스로 표시한 것이 person 객체의 프로토타입인 Object.prototype이다. 즉,`__proto__` 접근자 프로퍼티를 통해 Object.prototype에 접근한 결과가 콘솔에 표시된 것이다.

📌 <span style="color:orange">**`__proto__`은 접근자 프로퍼티이다.**</span>

- 내부 슬롯은 프로퍼티가 아니므로, 직접적으로 접근하거나 호출할 수 없다. -> `__proto__` 접근자 프로퍼티를 통해 간접적으로만 접근할 수 있다.
- `__proto__` 접근자 프로퍼티는 getter, setter를 통해 내부 슬롯의 값(프로토타입 값)을 취득하거나 할당한다. 프로토타입에 접근하면 내부적으로 getter 함수인 `get__proto__`가 호출된다. `__proto__` 접근자 프로퍼티를 통해 새로운 프로토타입을 할당하면 setter 함수인 `set__proto__`가 호출된다.
```js
const obj = {};
const parent = { x: 1 };

// get__proto__가 호출되어 obj 객체의 프로토타입을 취득
obj.__proto__;

// set__proto가 호출되어 obj 객체의 프로토타입을 교체
obj.__proto__ = parent;

console.log(obj.x); // 1
```

📌 <span style="color:orange">**`__proto__` 접근자 프로퍼티는 상속을 통해 사용된다.**</span>

- `__proto__` 접근자 프로퍼티는 객체가 직접 소유하는 프로퍼티가 아니라 Object.prototype의 프로퍼티다. 모든 객체는 상속을 통해 `Object.prototype.__proto__` 접근자를 사용할 수 있다.

❗ Object.prototype는 모든 prototype의 최상위 객체이다. -> 모든 객체에 상속된다.

📌 <span style="color:orange">**`__proto__`접근자 프로퍼티를 사용해서 프로토타입에 접근하는 이유**</span>
- 아래 예제를 보자.

```js
const parent = {};
const child = {};
//child의 prototype을 parent로 설정
child.__proto__ = parent;
//parent의 prototype을 child로 설정
parent.__proto__ = child; // ❌ TypeError: Cyclic __proto__ value

```
- 위 예제에서는 서로가 서로의 프로토타입이 되는 비정상적인 프로토타입 체인이 만들어지기 때문에 에러가 발생한다.
- 즉 **상호 참조에 의해 프로토타입 체인이 생성되는 것을 방지하기 위해서다.** 
- 프로토타입 체인은 단방향 링크드 리스트로 구현되어야 한다. -> 순환 참조하는 프로토타입 체인이 만들어지면 프로토타입 체인 종점이 존재하지 않기 때문에 프로토타입 체인에서 프로퍼티를 검색할 때 무한 루프에 빠진다.
- 따라서 `__proto__`접근자 프로퍼티를 통해 프로토타입에 접근하고 교체하도록 구현되어 있다.

📌 <span style="color:orange">**`__proto__`접근자 프로퍼티를 코드 내에 직접 사용하지 말자.**</span>

- 모든 객체가 `__proto__`를 사용할 수 있는 것이 아니기 때문이다.
```js
	// obj는 프로토타입 체인의 종점이다.
	const obj = Object.create(null);
	
	// obj는 Object.__proto__를 상속받을 수 없다.
	console.log(Object.getPrototypeOf(obj)); // undefined
```
- 위와 같이 Object.prototype을 상속받지 않는 객체를 만들 수도 있기 때문에 `__proto__`를 사용할 수 없는 경우가 있다.
- 그렇기 때문에 `__proto__` 프로퍼티 대신 `Object.getPrototypeOf`메서드를 사용하고, 프로토타입을 교체하고 싶은 경우에는 `Object.setPrototypeOf` 메서드를 사용할 것을 권장한다.
```js
	const obj = {};
	const parent = { x: 1 };
	
	// obj 객체의 프로토타입을 취득
	Object.getPrototypeOf(obj);
	
	// obj 객체의 프로토타입을 교체
	Object.setPrototypeOf(obj, parent);
	
	console.log(obj.x); // 1
```
>`Object.getPrototypeOf`과 `Object.setPrototypeOf` 메서드는 `get Object.prototype.__proto__` 와 `set Object.prototype.__proto__`와 같다.

### 🔰 함수 객체의 prototype 프로퍼티
> - 함수 객체만이 소유하는 `prototype` 프로퍼티는 **생성자 함수가 생성할** 인스턴스의 프로토타입을 가리킨다.

```js
	//함수 객체는 prototype 프로퍼티를 소유한다.
	(function () {}).hasOwnProperty('prototype'); //true
	
	//일반 객체는 prototype 프로퍼티를 소유하지 않는다.
	({}).hasOwnProperty('prototype'); //false
```
- 생성자 함수로서 호출할 수 없는 함수, 즉 `non-constructor`인 화살표 함수와 ES6 메서드 축약 표현으로 정의한 메서드는 `prototype` 프로퍼티를 소유하지 않으며 생성하지도 않는다.
```js
	// 화살표 함수
	const Person = (name) => {
	  this.name = name;
	};
	
	console.log(Person.hasOwnProperty('prototype')); // false
	console.log(Person.prototype); // undefined
	
	const obj = {
	  // ES6 메서드 축약 표현으로 정의한 메서드
	  foo() {}
	}; 
	
	console.log(obj.foo.hasOwnProperty('prototype')); 	// false
	console.log(obj.foo.prototype); // undefined
```

- **모든 객체가 가지고 있는 `__proto__` 접근자 프로퍼티와 함수 객체만이 가지고 있는 `prototype` 프로퍼티는 결국 동일한 프로토타입을 가리킨다.**
- 하지만 사용하는 주체가 다르다.

|구분	|소유|	값|	사용 주체|	사용 목적
|--|--|--|--|--|
|`__proto__` 접근자 프로퍼티|	모든 객체|	프로토타입의 참조|	모든 객체|	객체가 자신의 프로토타입에 접근 또는 교체하기 위해 사용
|`prototype` 프로퍼티|	`constructor`|	프로토타입의 참조|	생성자 함수|	생성자 함수가 자신이 생성할 객체의 프로토타입을 할당하기 위해 사용

```js
// 생성자 함수
function Person(name) {
  this.name = name;
}

const me = new Person('Lee');

// me 객체의 생성자 함수는 Person이다.
//결국 Person.prototype와 me.__proto__는 동일한 프로토타입을 가리킨다.
console.log(Person.prototype === me.__proto__); // true
```
![](https://images.velog.io/images/songjy377/post/e8d9d6a9-8302-4068-939c-cbaff773d60a/image.png)

### 🔰 프로토타입의 constructor 프로퍼티와 생성자 함수
> - 모든 프로토타입은 constructor 프로퍼티를 갖는다. 이는 자신을 참조하고 있는 생성자 함수를 가리킨다.
> - **이 연결은 생성자 함수가 생성될 때, 즉 함수 객체가 생성될 때 이뤄진다.**
>```js
>// 생성자 함수
>function Person(name) {
>  this.name = name;
>}
>
>const me = new Person('Lee');
>
>// me 객체의 생성자 함수는 Person이다.
>console.log(me.constructor === Person); // true
>```
- **생성자 함수에 의해 생성된 인스턴스는 프로토타입의 constructor 프로퍼티에 의해 생성자 함수와 연결된다.**

## ✅ 리터럴 표기법에 의해 생성된 객체의 생성자 함수와 프로토타입

리터럴 표기법|	생성자 함수|	프로토타입
|---|---|---|
객체 리터럴|	Object|	Object.prototytpe
함수 리터럴|	Function|	Function.prototype
배열 리터럴|	Array|	Array.prototype
정규 표현식 리터럴|	RegExp|	RegExp.prototype

> - 리터럴 표기법에 의해 생성된 객체의 경우 **프로토타입의 constructor 프로퍼티가 가리키는 생성자 함수가 반드시 객체를 생성한 생성자 함수라고 단정할 수는 없다.**

- 객체 리터럴에 의해 생성된 함수는 Object 생성자 함수가 생성한 객체가 아니다.
- 리터럴 표기법에 의해 생성된 객체도 상속을 위해 **프로토타입이 필요**하다. -> 가상적인 생성자 함수를 갖는다.
- **프로토타입과 생성자 함수는 단독으로 존재할 수 없고 언제나 쌍으로 존재한다.**

## ✅ 프로토타입의 생성 시점
> - **프로토타입은 생성자 함수가 생성되는 시점에 더불어 생성된다.** 
> - 생성자 함수는 사용자가 직접 정의한 **사용자 정의 생성자 함수**와 자바스크립트가 기본 제공하는 **빌트인 생성자 함수**로 구분할 수 있다.

### 🔰 사용자 정의 생성자 함수
> - 생성자 함수로서 호출할 수 있는 함수, 즉 constructor는 함수 정의가 평가되어 **함수 객체를 생성하는 시점에 프로토타입도 더불어 생성**된다.

```js
console.log(Person.prototype); // { constructor: f }
//생성자 함수 -> 선언문이므로 런타임 이전에 실행된다.
// 이때 프로토타입이 생성된다.
function Person(name) {
  this.name = name;
}
```
>📌 **사용자 정의 생성자 함수는 함수 객체가 생성되는 시점에 프로토타입도 더불어 생성되고, 생성된 프로토타입의 프로토타입은 언제나 Object.prototype이다.**
![](https://images.velog.io/images/songjy377/post/d4de2140-8f66-4a56-be2c-78e04cb2f45e/image.png)

- 생성자 함수로서 호출할 수 없는 함수, 즉 non-constructor는 프로토타입이 생성되지 않는다.
```js
//화살표 함수는 non-constructor 이다.
function Person = name => {
  this.name = name;
}

console.log(Person.prototype); // undefined
```

### 🔰 빌트인 정의 생성자 함수
> - `Object`, `String`, `Number`, `Function` 등과 같은 빌트인 생성자 함수도 함수가 생성되는 시점에 프로토타입이 생성된다. 
> - 모든 빌트인 생성자 함수는 전역 객체가 생성되는 시점에 생성된다. 
>📌 전역 객체는 코드가 실행되기 이전 단계에 자바스크립트 엔진에 의해 생성된다. -> Object도 전역 객체의 프로퍼티이며, 전역 객체가 생성되는 시점에 생성된다.
![](https://images.velog.io/images/songjy377/post/776eb235-3b7f-4618-9513-e5abb895430f/image.png)

📌 객체가 생성되기 이전에 생성자 함수와 프로토타입은 이미 객체화되어 존재한다. **생성자 함수 또는 리터럴 표기으로 객체를 생성하면 프로토타입은 생성된 객체의 `[[Prototype]]` 내부 슬롯에 할당된다.** -> 이로써 생성된 객체는 프로토타입을 상속받는다.

## ✅ 객체 생성 방식과 프로토타입의 결정
>- 객체는 다음과 같이 다양한 생성 방법이 있다.
>   - 객체 리터럴 
>   - Object 생성자 함수
>   - 생성자 함수
>   - Object.create 메서드
>   - 클래스(ES6)
>- 📌 공통점 : 추상 연산 `OrdinaryObjectCreate` 에 의해 생성된다.
> - `OrdinaryObjectCreate`는 필수적으로 자신이 생성할 객체의 프로토타입을 인수로 전달받고, 빈 객체를 생성한 후 인수 프로퍼티가 있으면 객체에 추가한다.
> - 그 인수 프로퍼티를 `[[Prototype]]` 내부 슬롯에 할당하고, 객체를 반환한다.


### 🔰 객체 리터럴에 의한 생성
>- 객체 리터럴에 의해 생성되는 객체의 프로토타입은 Object.prototype이다.
> - 이때 추상 연산 `OrdinaryObjectCreate`에 전달되는 프로토타입은 Object.prototype이다.
> - 객체 리터럴에 의해 생성된 객체는 Object.prototype을 상속받는다.
>```js
>const obj = { x: 1 };
>//객체 리터럴에 의해 생성된 obj객체는 Object.prototype을 상속받는다.
>//hasOwnProperty 메소드를 상속받았다.
>console.log(obj.constructor === Object); // true
>console.log(obj.hasOwnProperty('x')); // true
>```

### 🔰 Object 생성자 함수에 의한 생성
> - Object 생성자 함수에 의해 생성되는 객체의 프로토타입은 Object.prototype이다.
> - Object 생성자 함수에 의해 생성된 객체는 Object.prototype을 상속받는다.
>```js
>const obj = new Object();
>obj.x = 1;
>//Object.prototype을 상속받는다.
>console.log(obj.constructor === Object); // true
>>console.log(obj.hasOwnProperty('x')); // true
>```

> ❓ 객체 리터럴과 Object 생성자 함수에 의한 객체 생성 방식의 차이
> - 프로퍼티를 추가하는 방식이 다르다. 객체 리터럴 방식은 객체 리터럴 내부에 프로퍼티를 추가하지만 Object 생성자 함수 방식은 일단 빈 객체를 생성한 이후 프로퍼티를 추가해야 한다.

### 🔰 생성자 함수에 의한 생성
> - 생성자 함수에 의해 생성되는 객체의 프로토타입은 생성자 함수의 prototype 프로퍼티에 바인딩되어 있는 객체다.
> - 사용자 정의 생성자 함수와 더불이 생긴 prototype의 프로퍼티는 constructor뿐이다.

## ✅ 프로토타입 체인
> - **프로토타입 체인은 자바스크립트가 상속을 구현하는 매커니즘이다.**
> - 자바스크립트는 객체의 프로퍼티에 접근하려고 할 때 해당 프로퍼티가 없으면 자신의 부모 역할을 하는 프로토타입의 프로퍼티를 순차적으로 검색한다. 이를 프로토타입 체인이라 한다.
> - ex) Array.prototype.reduce -> reduce를 호출하는 주체는 instance이다.

```js
// 생성자 함수
function Person(name) {
  this.name = name;
}

// 프로토타입 메서드
Person.prototype.sayHello = function () {
  console.log(`Hi! My name is ${this.name}`);
};

const me = new Person('Lee');

// hasOwnProperty는 Object.prototype의 메서드다.
console.log(me.hasOwnProperty('name')); // true
```
- `Person`에 의해 생성된 `me` 객체는 `Object.prototype`의 메서드인 `hasOwnProperty`를 호출할 수 있다. 이는 `me` 객체가 `Person.prototype` 뿐만 아니라`Object.prototype`도 상속받았다는 것을 알 수 있다.
![](https://images.velog.io/images/songjy377/post/2402cc66-4a7b-457a-85b1-573d8eb93a75/image.png)
>- 프로토타입 체인의 최상위에 위치하는 객체는 언제나 Object.prototype이다. 따라서 모든 객체는 Object.prototype을 상속받는다.
>- Object.prototype은 프로토타입 체인의 종점이다.
>- Object.prototype의 프로토타입은 null이다.

>❓ 스코프 체인 vs 프로토타입 체인
>- 스코프 체인 : 식별자 검색을 위한 메커니즘
>- 프로토타입 체인 : 상속과 프로퍼티 검색을 위한 메커니즘
> - **스코프 체인과 프로토타입 체인은 서로 연관없이 별도로 동작하는 것이 아니라 서로 협력하여 식별자와 프로퍼티를 검색하는 데 사용된다.**

> 📌 메소드를 검색할 때, 프로퍼티를 참조할 때의 과정
> 1. 먼저 메소드를 호출한 객체에서 검색한다.
> 2. 만약 해당 메소드가 존재하지 않을 경우 프로토타입 체인을 따라(`[[Prototype]]` 내부 슬롯에 바인딩되어 있는 프로토타입으로 이동) 메소드를 검색한다.

## ✅ 오버라이딩과 프로퍼티 섀도잉
```js
const Person = (function () {
  // 생성자 함수
  function Person(name) {
    this.name = name;
  }
  
  // 프로토타입 메서드
  Person.prototype.sayHello = function () {
    console.log(`Hi! My name is ${this.name}`);
  };
  
  // 생성자 함수를 반환
  return Person;
}());

const me = new Person('Lee');

// 인스턴스 메서드
me.sayHello = function () {
  console.log(`Hi!, ${this.name}`);
};

// 인스턴스 메서드 호출
me.sayHello(); // Hi!, Lee
```
- 생성자 함수로 객체를 생성한 다음, 인스턴스에 동일한 이름의 메서드를 추가했다.
- **프로토타입의 프로퍼티와 같은 이름의 프로퍼티를 인스턴스에 추가하면 프로토타입 체인을 따라 프로토타입의 프로퍼티를 검색하여 덮어쓰는 것이 아니라, 인스턴스의 프로퍼티로 추가한다.**
- 이때 인스턴스 메서드 sayHello는 프로토타입 메서드 sayHello를 _오버라이딩(overriding)_ 했고 프로토타입 메서드 sayHello는 가려진다.

> - 📌 상속 관계에 의해 프로퍼티가 가려지는 현상을 **프로퍼티 섀도잉(property shadowing) **이라 한다.

> - 오버라이딩
>상위 클래스가 가지고 있는 메서드를 하위 클래스가 재정의하여 사용하는 방식
> - 오버로딩
>함수의 이름은 동일하지만 매개변수의 타입 또는 개수가 다른 메서드를 구현하고 매개변수에 의해 메서드를 구별하여 호출하는 방식이다. 자바스크립트는 오버로딩을 지원하지 않지만 arguments 객체를 사용하여 구현할 수는 있다.

이제 추가한 인스턴스 메서드 sayHello를 삭제해보자.
```js
delete me.sayHello;
//인스턴스에는 sayHello 메소드가 없으므로 프로토타입 메소드가 호출된다.
me.sayHello(); // Hi, My name is Lee
```
- 인스턴스 메서드 sayHello가 삭제되고, 프로토타입 메서드가 호출되었다.
- ❌ 하위 객체를 통해 프로토타입의 프로퍼티를 변경 또는 삭제하는 것은 불가능하다. 다시 말해 하위 객체를 통해 프로토타입에 get 액세스는 허용되나 set 액세스는 허용되지 않는다.
- 만약 프로토타입 메서드 sayHello를 삭제하는 경우에는 하위 객체인 인스턴스를 통해 접근하는 것이 아니라 **프로토타입에 직접 접근해서 삭제해야 한다.**
```js
delete Person.prototype.sayHello;

me.sayHello(); // TypeError: me.sayHello is not a function 
```
## ✅ 프로토타입의 교체
> - 프로토타입은 임의의 다른 객체로 변경할 수 있다. 이것은 부모 객체인 프로토타입을 동적으로 변경할 수 있다는 것을 의미한다. 
> - 프로토타입은 생성자 함수 또는 인스턴스에 의해 교체할 수 있다.

### 🔰 생성자 함수를 통한 교체
```js
const Person = (function () {
  // 생성자 함수
  function Person(name) {
    this.name = name;
  }
  
  // protototype 프로퍼티를 통해 프로토타입 교체
  Person.prototype = {
    sayHello() {
      console.log(`Hi! My name is ${this.name}`);
    }
  };
  
  return Person;
}());

const me = new Person('Lee');

// constructor 프로퍼티와 생성자 함수 간의 연결이 파괴된다.
console.log(me.constructor === Person); // false
// 프로토타입 체인을 따라 Object.prototype의 constructor 프로퍼티가 검색된다.
console.log(me.constructor === Object); // true
```
- Person.prototype에 객체 리터럴을 할당했다. Person 생성자 함수가 생성할 객체의 프로토타입을 객체 리터럴로 교체한 것이다.
- 프로토타입으로 교체한 객체 리터럴에는 constructor 프로퍼티가 없다. -> me 객체의 생성자 함수를 검색하면 Person이 아닌 Object가 나오게 된다. 그림으로 표현하면 다음과 같다.
![](https://images.velog.io/images/songjy377/post/c0dc7468-c421-4808-8ffa-fe7f7eabb49c/image.png)
- **프로토타입을 교체하면 constructor 프로퍼티와 생성자 함수 간의 연결이 파괴된다.**
- 연결을 되살리고 싶다면 교체한 객체 리터럴에 construct프로퍼티를 추가하면 된다.
```js
Person.prototype = {
  constructor: Person,
    sayHello() {
      console.log(`Hi! My name is ${this.name}`);
    }
  };
```

### 🔰 인스턴스에 의한 교체
- 프로토타입은 앞서 언급한 함수의 `__proto__` 프로퍼티를 통해 간접적으로 접근할 수 있다. 또한 `Object.getPrototypeOf`과 `Object.setPrototypeOf` 메서드를 사용해 프로토타입을 교체할 수 있다.
- `__proto__` 접근자 프로퍼티를 통해 프로토타입을 교체하는 것은 이미 생성된 객체의 프로토타입을 교체하는 것이다.
```js
function Person(name) {
  this.name = this.name;
}

const me = new Person('Lee');

// 프로토타입으로 교체할 객체
const parent = {
  sayHello() {
    console.log(`Hi! My name is ${this.name}`);
  }
};

// me 객체의 프로토타입을 parent 객체로 교체
Object.setPrototypeOf(me, parent);
// me.__proto__ = parent; 와 동일

me.sayHello(); // Hi! My name is Lee

// constructor 프로퍼티와 생성자 함수 간의 연결이 파괴된다.
console.log(me.constructor === Person); // false
//프로토타입 체인을 따라 Object.prototype의 constructor가 검색된다.
console.log(me.constructor === Object); // true
// 생성자 함수의 prototype 프로퍼티가 교체된 프로토타입을 가리키지 않는다.
console.log(Person.prototype === Object.getPrototypeOf(me)); // false
```
![](https://images.velog.io/images/songjy377/post/91f53aa4-f6cd-4bb6-8500-50fb4aaa708a/image.png)

>❓ 생성자 함수에 의한 프로토타입 교체와 인스턴스에 의한 프로토타입 교체의 차이점
>- 생성자 함수 : Person 생성자 함수의 prototype 프로퍼티가 교체된 프로토타입을 가리킨다.
> - 인스턴스 : Person 생성자 함수의 prototype 프로퍼티가 교체된 프로토타입을 가리키지 않는다.

>❌ **프로토타입은 직접 교체하지 않는 것이 좋다.**

## ✅ `instanceof` 연산자
> - instanceof 연산자는 좌변에 객체를 가리키는 식별자, 우변에 생성자 함수를 가리키는 식별자를 피연산자로 받는다. 만약 우변의 피연산자가 함수가 아닌 경우 TypeError가 발생한다.
>```js
> 객체 instanceof 생성자 함수
>```
> - 우변의 생성자 함수의 `prototype`에 바인딩된 객체가 좌변의 객체의 프로토타입 체인 상에 존재하면 true로 평가되고, 그렇지 않은 경우에는 false로평가된다.

```js
// 생성자 함수
function Person(name) {
  this.name = name;
}
const me = new Person('Ssong');

// Person.prototype과 Object.prototype이 me 객체의 프로토타입 체인 상에 존재
console.log(me instanceof Person); // true
// Object.prototype과 Object.prototype이 me 객체의 프로토타입 체인 상에 존재
console.log(me instanceof Object); // true
```
앞서 소개한 프로토타입을 교체했을 때의 예제를 다시 보자.
```js
// 생성자 함수
function Person(name) {
  this.name = name;
}
const me = new Person('Ssong');

//교체할 객체
const parent = {};

//교체
Object.setPrototypeOf(me, parent);
// Person 생성자 함수와 parent 객체는 연결되어 있지 않다.
console.log(Person.prototype === parent); // false
console.log(parent.constructor === Person); // false
//Person.prototype이 me 객체의 프로토타입 체인 상에 존재하지 않기 때문에 false
console.log(me instanceof Person); // false
//Object.prototype이 me 객체의 프로토타입 체인 상에 존재하므로 true로 평가된다.
console.log(me instanceof Object); // true!
```
- me 객체는 프로토타입이 교체되어 프로토타입과 생성자 함수 간의 연결이 파괴되었지만 Person 생성자 함수에 의해 생성된 인스턴스임에는 틀림이 없다. 그러나 `me instanceof Person`은 false로 평가된다.
- 이는 `Person.prototype`이 `me` 객체의 프로토타입 체인 상에 존재하지 않기 때문이다. -> parent 객체를 Person 생성자 함수의 prototype 프로퍼티에 바인딩하면 me instanceof Person은 true로 평가될 것이다.

> 📌 `instanceof` 연산자는 프로토타입의 `constructor` 프로퍼티가 가리키는 생성자 함수를 찾는 것이 아니라, **생성자 함수의 `prototype`에 바인딩된 객체가 프로토타입 체인 상에 존재하는지 확인한다.**

- `me instanceof Person` 은 `me` 객체의 프로토타입 체인 상에 `Person.prototype`에 바인딩된 객체가 존재하는지 확인한다.
- `me instanceof Object`의 경우도 마찬가지로 me 객체의 프로토타입 체인 상에 `Object.prototype`에 바인딩된 객체가 존재하는지 확인한다.
- 따라서 **생성자 함수에 의해**`constructor` 프로퍼티와 생성자 함수 간의 연결이 파괴되어도 생성자 함수의 `prototype`프로퍼티와 프로토타입 간의 연결은 파괴되지 않으므로 instanceof는 아무런 영향을 받지 않는다.
```js
const Person = (function () {
  // 생성자 함수
  function Person(name) {
    this.name = name;
  }
  
  // 프로토타입 교체
  Person.prototype = {
    sayHello() {
      console.log(`Hi! My name is ${this.name}`);
    }
  };
  
  return Person;
}());

const me = new Person('Lee');
// constructor 프로퍼티와 생성자 함수 간의 연결이 파괴되어도 instanceof는 아무런 영향을 받지 않는다.
console.log(me.constructor === Person); // false
// Person.prototype과 Object.prototype이 me 객체의 프로토타입 체인 상에 존재
console.log(me instanceof Person); // true
// Object.prototype과 Object.prototype이 me 객체의 프로토타입 체인 상에 존재
console.log(me instanceof Object); // true
```
## ✅ 직접 상속
### 🔰 `Object.create`에 의한 직접 상속
>- `Object.create` 메서드도 다른 객체 생성 방식과 마찬가지로 추상 연산 `OrdinaryobjectCreate`를 호출한다.
>```js
>/**
>* 지정된 프로토타입 및 프로퍼티를 갖는 새로운 객체를 생성하여 반환한다.
>* @param {Object} prototype - 생성할 객체의 프로토타입으로 지정할 객체
>* @param {Object} [propertiesObject] - 생성할 객체의 프로퍼티 키와 프로퍼티 디스크립터 객체로 이뤄진 객체 전달 (옵션)
>* @returns {Object} 지정된 프로토타입 및 프로퍼티를 갖는 새로운 객체
>*/
>Object.create(prototype, [propertiesObject])
>```

```js
// 프로토타입이 null인 객체 생성
let obj = Object.create(null);
// obj -> null. obj는 프로토타입의 체인의 종점에 위치
console.log(Object.getPrototypeOf(obj) === null); // true

// obj -> Object.prototype -> null
// obj = {}; 와 동일
obj = Object.create(Object.prototype);
// obj -> Object.prototype -> null
console.log(Object.getPrototypeOf(obj) === Object.prototype); // true


const myProto = { x: 10 };
//임의의 객체를 직접 상속받는다.
// obj -> myProto -> Object.prototype -> null
obj = Object.create(myProto);
console.log(obj.x);//10
console.log(Object.getPrototypeOf(obj) === myProto); // true


function Person(name) {
  this.name = name;
}
// obj -> Person.prototype -> Object.prototype -> null
obj = Object.create(Person.prototype);
obj.name = 'Ssong';
console.log(obj.name) //Ssong
console.log(Object.getPrototypeOf(obj) === Person.portotype); // true
```

> 📌**Object.create 메서드의 장점**
> 1. new 연산자가 없이도 객체를 생성할 수 있다.
> 2. 프로토타입을 지정하면서 객체를 생성할 수 있다.
> 3. 객체 리터럴에 의해 생성된 객체도 상속받을 수 있다.

```js
const person = {
    name: 'Ssong'
}

//person은 hasOwnProperty 메소드를 가지고있지 않지만 Object가 가지고있기 때문에 상속을 받아 true가 출력된다.
console.log(person.hasOwnProperty('name'));
//위 방식은 undefined일 수도 있기 때문에 아래처럼 접근하는것이 좋다.
Object.prototype.hasOwnProperty.call(person, 'name');
```

```js
//프로토타입이 null인 객체, 프로토타입 체인의 종점에 위치하는 객체를 생성한다.
const obj = Object.create(null);
obj.a = 1;

//obj는 Object.prototype의 빌트인 메소드를 사용할 수 없다.
console.log(person.hasOwnProperty('a'));
//Object.prototype의 빌트인 메소드는 객체로 직접 호출하지 않고 간접적으로 호출한다.
Object.prototype.hasOwnProperty.call(person, 'name');
```

### 🔰 객체 리터럴 내부에서 `__proto__`에 의한 직접 상속
> - ES6 에서는 객체 리터럴 내부에서 `__proto__`접근자 프로퍼티를 사용하여 직접 상속을 구현할 수 있다.
>```js
>const myProto = { x: 10 };
>
>const obj = {
>  y: 20,
>  //객체를 직접 상속받는다.
>  //obj -> myProto -> Object.prototype -> >null
>  __proto__: myProto
>};
> /*위 코드는 아래와 동일하다
> const obj = Object.create(myProto, { y: { value: 20, writable: true, enumerable: true, configrable: true }
});*/
>console.log(obj.x, obj.y); //10 20
>console.log(Object.getPrototypeOf(obj) === myProto); //true
>```


> ❗❗ 사실 상속보다는 함수형 프로그램으로 구현하는 것이 좋다.
>```js
>const createPerson = name => ({ name });
>
>const sayHi = ({ name }) => {
>    console.log(name);
>}
>
>sayHi(createPerson('Ssong'));
>```

## ✅ 정적 프로퍼티와 정적 메소드
> - 정적(static) 프로퍼티와 정적 메서드는 **생성자 함수로 인스턴스를 생성하지 않아도 참조와 호출을 할 수 있는** 프로퍼티와 메서드를 말한다.

```js
function Person(name) {
  this.name = name;
}

// 프로토타입 메서드
Person.prototype.sayHello = function () {
  console.log(`Hi! My name is ${this.name}`);
};

// 정적 프로퍼티
Person.staticProp = 'static prop';

// 정적 메서드
Person.staticMethod = function () {
  console.log('staticMethod');
}

const me = new Person('Lee');
//생성자 함수에 추가한 정적 프로퍼티/메소드는 생성자 함수로 참조/호출한다.
Person.staticMethod(); // staticMethod

//정적 프로퍼티/메소드는 생성자 함수가 생성한 인스턴스로 참조/호출할 수 없다.
//인스턴스로 참조/호출할 수 있는 프로퍼티/메소드는 프로토타입 체인 상에 존재해야 한다.
me.staticMethod(); // TypeError: me.staticMethod is not a function
```
- Person 생성자 함수 객체가 소유한 프로퍼티와 메서드를 정적 프로퍼티와 메서드라 한다. **정적 프로퍼티나 메서드는 생성자 함수가 생성한 인스턴스로 참조와 호출을 할 수 없다.**
![](https://images.velog.io/images/songjy377/post/191ac191-37b8-460e-9345-2838e9971a34/image.png)
- ❌ **정적 프로퍼티/메서드는 인스턴스의 프로토타입 체인에 속한 객체의 프로퍼티/메서드가 아니므로 인스턴스로 접근할 수 없다.**

- 만약 인스턴스/프로토타입 메소드 내에서 this를 사용하지 않는다면 그 메소드는 정적 메소드로 변경할 수 있다. -> 메소드 내에서 인스턴스를 참조할 필요가 없다면 정적 메소드로 변경하여도 동작한다.
- 프로토타입 메소드를 호출하려면 인스턴스를 생성해야 하지만 정적 메소드는 인스턴스를 생성하지 않아도 호출 가능하다.
```js
function foo() {}
  //프로토타입 메소드
  //this를 참조하지 않는 프로토타입 메소드를 정적 메소드로 변경하여도 동일한 효과를 얻을 수 있다.
Foo.prototype.x = function() {
  console.log('x');
};

const foo = new Foo();
//프로토타입 메소드를 호출하려면 인스턴스를 생성해야 한다.
foo.x(); //x
//정적 메소드
Foo.x = function () {
  console.log('x');
};
//정적 메소드는 인스턴스를 생성하지 않아도 호출할 수 있다.
Foo.x(); //x
```
## ✅ 프로퍼티 존재 확인
### 🔰 `in` 연산자
> - `in` 연산자는 객체 내에 특정 프로퍼티가 존재하는지 여부를 확인한다.
> ```js
> /*
> * key: 프로퍼티 키
> * object: 객체로 평가되는 표현식
>*/
> key in object
>```

```js
const person = {
  name: 'Lee',
  address: 'Seoul'
};

console.log('name' in person); // true
console.log('address' in person); // true
console.log('age' in person); //false
// true
```
- ❗ `in` 연산자는 확인 대상 객체의 프로퍼티뿐만 아니라 확인 대상 객체가 **상속받은 모든 프로토타입의 프로퍼티를 확인**하므로 주의가 필요하다.
```js
console.log('toString' in person); // true
//toString은 Object.prototype의 메서드이다.
```
- 📌ES6부터 도입된 `Reflect.has` 메소드를 사용할 수 있다.
```js
console.log(Reflect.has(person, 'name')); // true
```
### 🔰 `Object.prototype.hasOwnProperty` 메소드
> - `Object.prototype.hasOwnProperty` 메서드 또한 객체에 특정 프로퍼티가 존재하는지 확인할 수 있다.
> ```js
> const person = {
>   name: 'Lee'
> };
> 
> console.log(person.hasOwnProperty('name')); // true
> console.log(person.hasOwnProperty('age')); // false
> ```
>- `in` 연산자와는 다르게, 인수로 전달받는 프로퍼티 키가 **객체 고유의 프로퍼티 키인 경우에만 true**를 반환하고, **상속받은 프로토타입의 프로퍼티 키인 경우에는 false**를 반환한다.
>```js
>console.log(person.hasOwnProperty('toString')); // false
>```

## ✅ 프로퍼티 열거
### 🔰 `for...in` 문
> - `for ... in`문을 사용해 객체의 모든 프로퍼티 중에서 **프로퍼티 어트리뷰트 `[[Enumerable]]`의 값이 true인 프로퍼티를** 순회하며 열거할 수 있다.
> ```js
> for (변수선언문 in 객체) { ... } 
>```

```js
const person = {
  name: 'Ssong',
  address: 'Seoul'
};
// for ... in 문의 변수 prop에 person 객체의 프로퍼티 키가 할당된다.
for (const key in person) {
  console.log(key + ': ' + person[key]);
}

// name: Ssong
// address: Seoul
```
- ❗ `for...in` 문은 in 연산자처럼 순회 대상 객체의 프로퍼티뿐만 아니라 상속받은 프로토타입의 프로퍼티까지 열거한다. 안티패턴이다.
>- 하지만 위 예제의 경우 `toString`  프로퍼티는 열겨되지 않는다. -> `[[Enumeralbe]]`의 값이 false이기 때문이다.
> - ❗ 상속받은 프로토타입의 프로퍼티 어트리뷰트 `[[Enumeralbe]]`의 값이 false이면 열거하지 않는다.
> - **프로퍼티 키가 symbol인 프로퍼티는 열거하지 않는다.** `[sym]: 10`

 - 📌 따라서 Object.prototype.hasOwnProperty 메서드를 사용해서 **객체 자신의 프로퍼티인지 확인하는 추가 처리**가 필요하다.
```js
for (const key in person) {
  // 객체 자신의 프로퍼티인지 확인
  if (!person.hasOwnProperty(key)) continue; -> 프로토타입 체인을 따라가며 있는지 확인한다.(this 사용)
  if (Object.prototype.hasOwnProperty.call(person, key)) -> Object에서 바로 찾으므로 얘가 좀 더 낫지만, 가독성이 좋지 않다.
  console.log(key + ': ' + person[key]);
}
```
>- ❗ 순서를 보장하지 않는다. 하지만 대부분의 브라우저는 순서를 보장하고, 숫자인 프로퍼티 키에 대해서는 정렬을 실시한다.

`for ... in` 메소드는 상속받은 프로퍼티 키도 열거하므로, 객체 자신의 고유한 프로퍼티만 열거하기 위해서는 `Object.keys/values/entries` 메서드를 사용하는 것을 권장한다.

### 🔰 `Object.keys()`
> - 객체 자신의 열거 가능한 프로퍼티 키를 배열로 반환한다.
>```js
>const person = {
>  name: 'Lee',
>  address: 'Seoul',
>  __proto__: { age: 20 }
>};
>console.log(Object.keys(person)); // ["name", "address"]
>
>for (const key of person) { //TypeError: person is not >iterable
>    console.log(key);
>} //❌person은 iterable하지 않으므로 에러.
>
>for (const key of Object.keys(person)) {
>    console.log(key);
>} //⭕OK
>```

### 🔰 `Object.values()`
> - (ES8)객체 자신의 열거 가능한 프로퍼티 값을 배열로 반환한다.
>```js
>const person = {
>  name: 'Lee',
>  address: 'Seoul',
>  __proto__: { age: 20 }
>};
>console.log(Object.values(person)); // ["Lee", "Seoul"]
>```

### 🔰 `Object.entries()`
> - (ES8)객체 자신의 열거 가능한 프로퍼티 키와 값의 쌍의 배열을 배열에 담아 반환한다.
>```js
>const person = {
>  name: 'Lee',
>  address: 'Seoul',
>  __proto__: { age: 20 }
>};
>
>console.log(Object.entries(person)); // [["name", "Lee"], ["address", "Seoul"]]
>Object.entries(person).forEach(([key, value]) => console.log(key, value));
>/*
> name Lee
> address Seoul
>*/
>```
