# let & const
>let과 const 키워드에 대해 다룬다.(211129)

## ✅ var 키워드로 선언한 변수의 문제점

### 🔰 변수 중복 선언 허용
- var 키워드로 선언한 변수는 중복 선언이 가능하다.
```js
	var x = 1;
	var y = 1;
	
	var x = 100; //var로 선언된 변수는 같은 스코프 내에서 중복 선언 허용
	var y; // 초기화문이 없는 변수 선언문은 무시
	
	console.log(x); // 100
	console.log(y); // 1
```
- 동일한 이름의 변수가 이미 선언되어 있는 것을 모르고 값을 할당했다면 의도치 않게 먼저 선언된 변수 값이 변경되는 부작용이 발생한다.
### 🔰 함수 레벨 스코프
- **var 키워드**로 선언한 변수는 오로지 **함수의 코드 블록만을 지역 스코프로 인정**한다. 
- 함수 외부에서 var 키워드로 선언한 변수는 코드 블록 내에서 선언해도 모두 전역 변수가 된다.
```js
	var x = 1;
	
	if(true) {
	  var x = 10; // 전역 변수, 중복 선언된 상태
	}
	
	console.log(x); // 10 -> 값이 변경됨.
```
- 함수 레벨 스코프는 전역 변수를 남발할 가능성을 높인다. 이로 인해 의도치 않게 전역 변수가 중복 선언되는 경우가 발생할 수 있다.

### 🔰 함수 레벨 스코프
- 변수 호이스팅에 의해 var 키워드로 선언한 변수는 변수 선언문 이전에 참조할 수 있다. 
- **단, 할당문 이전에 변수를 참조하면 언제나 undefined를 반환한다.**
```js
//런타임 이전에 변수는 선언되었지만, 할당되지 않았다.
console.log(foo); // undefined
//변수에 값을 할당
foo = 123;

console.log(foo); // 123

// 변수 선언은 런타임 이전에 실행된다.
var = foo;
```
- 변수 선언문 이전에 변수를 참조하는 것은 변수 호이스팅에 의해 에러를 발생시키지는 않지만 프로그램의 흐름상 맞지 않을뿐더러, 가독성을 떨어뜨리고 오류를 발생시킬 여지가 있다.

- var 키워드의 단점을 보완하기 위해 ES6에서는 새로운 변수 선언 키워드인 **let**과 **const**를 도입했다. 

## ✅ let 키워드
### 🔰 변수 중복 선언 금지
- let 키워드로 이름이 같은 변수를 중복으로 선언하면 문법 에러가 발생한다.
```js
let bar = 123;
let bar = 456; // ❌SyntaxError: Identifier 'bar' has already been declared
```

### 🔰 블록 레벨 스코프
- let 키워드로 선언한 변수는 함수를 비롯한 if, for, try/catch 등의 모든 코드 블록을 지역 스코프로 인정하는 블록 레벨 스코프를 따른다.
```js
	let foo = 1; // 전역 변수
	
	{
	  let foo = 2; // 지역 변수 -> 선언 가능.
	  let bar = 3; // 지역 변수
	  console.log(foo); // 2
	}
	
	console.log(foo); // 1
	console.log(bar); //❌ReferenceError: bar is not defined
```

### 🔰 변수 호이스팅
- let 키워드로 선언한 변수는 변수 호이스팅이 **발생하지 않는 것처럼** 동작한다.
```js
	console.log(foo); // ❌ReferenceError: foo is not defined 
	// 일시적 사각지대에서는 변수를 참조할 수 없다.
	
	let foo; // 초기화
	console.log(foo); // undefined;
	
	foo = 1; // 할당
	console.log(foo); // 1
```
- let 키워드로 선언한 변수는 선언과 초기화가 분리되어 진행된다. 
- 런타임 이전에 암묵적으로 선언 단계가 실행되고, 초기화 단계는 변수 선언문에 도달했을 때 실행된다.
- 변수 초기화 단계 이전까지는 let 키워드로 선언한 변수에 접근할 수 없다. 즉 _일시적 사각지대가 존재_한다.
- 📌 일시적 사각지대 : 스코프의 시작 지점부터 초기화 시작 지점까지 변수를 참조할 수 없는 구간
![](https://images.velog.io/images/songjy377/post/8ddc8fcf-4317-454a-88bc-523ad06ad9ed/image.png)

-❓ 그럼 let 키워드로 선언한 변수는 변수 호이스팅이 발생하지 않을까? -> **그렇지 않다.**
```js
let foo = 1; // 전역 변수

{
  console.log(foo); // ❌ReferenceError: Cannot access 'foo' before initialization
  let foo = 2; // 지역 변수
}
```
- 변수 호이스팅이 발생하지 않는다면 위 예제는 전역 변수 foo의 값을 출력해야 하지만, 호이스팅이 발생하기 때문에 ReferenceError가 발생한다.

- let과 const 를 포함한 모든 선언은 호이스팅된다. 
- 단지, 선언과 초기화가 분리되어 진행되기 때문에 호이스팅이 발생하지 않는 것처럼 동작하는 것이다.

## ✅ const 키워드
- const 키워드는 상수(constant)를 선언하기 위해 사용한다. 하지만 반드시 상수만을 위해 사용하지는 않는다.
### 🔰 선언과 초기화
- const 키워드로 선언한 변수는 반드시 선언과 동시에 초기화를 해야 한다.
```js
const x; // ❌ SyntaxError: Missing initalizer in const declaration
```
- const는 let과 마찬가지로 블록 레벨 스코프를 가지며, 변수 호이스팅이 발생하지 않는 것처럼 동작한다.
```js
{
  // 변수 호이스팅이 발생하지 않는 것처럼 동작한다.
  console.log(foo); // ReferenceError: Cannot access 'foo' before initailization
  const foo = 1;
  console.log(foo); // 1
}

// 블록 레벨 스코프를 갖는다.
console.log(foo); // ReferenceError: foo is not defined
```

### 🔰 재할당 금지
- **const 키워드로 선언한 변수는 재할당을 할 수 없다.**
```js
const foo = 1;
foo = 2; // ❌TypeError: Assignment to constant variable
```

>❓ 상수 
>- 상수는 재할당이 금지된 변수를 말한다. 
>- 상수는 상태 유지와 가독성, 유지보수의 편의를 위해 적극적으로 사용해야 한다.
```js
const TAX_RATE = 0.1; //0.1을 상수로서 사용하여 의미를 나타내준다.
let preTaxPrice = 100;
let afterTaxPrice = afterTaxPrice + (afterTaxPrice * TAX_RATE);
```

### 🔰 const 키워드와 객체
> - const 키워드로 선언된 변수에 원시 값을 할당한 경우에는 값을 변경할 수 없지만, **객체를 할당한 경우에는 값을 변경할 수 있다.**
> <span style="color:red">WHY?</span> -> const로 선언된 **변수**는 재할당이 불가능하다고 했다. 원시 값은 재할당 없이 변경할 수 있는 방법이 없지만, 객체는 재할당 없이도 직접 변경이 가능하기 때문이다.

```js
	const person = {
	  name: 'Lee'
	};
	//객체는 변경 가능한 값이다. 따라서 재할당 없이 변경이 가능하다.
	person.name = 'Kim';
	
	console.log(person); // { name: "Kim" }
```

## 📌 var vs. let vs. const
> - 변수를 선언할 때 기본적으로 const를 사용하고 let은 재할당이 필요한 경우에 한정하여 사용하는 것이 좋다. 
> - const 키워드를 사용하면 의도치 않은 재할당을 방지하기 때문에 좀 더 안전하다.
> - **ES6를 사용한다면 var 키워드는 사용하지 않는다.**
> - 재할당이 필요한 경우에 한정해 let 키워드를 사용한다. 이 때 변수의 스코프는 최대한 좁게 만든다.
> - 변경이 발생하지 않고 읽기 전용으로 사용하는 변수에는 const 키워드를 사용한다.