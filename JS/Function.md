# 함수(Function)
> 함수에 대해 다룬다.(211125)

# ✅ 함수(Function)
> - 일련의 과정을 문으로 구현하고 코드 블록으로 감싸서 하나의 실행 단위로 정의한 것.
> - 입력을 전달받는 변수를 _매개변수_, 입력을 _인수_, 출력을 _반환값_이라 한다. 
> - 함수가 여러 개 존재할 수 있으므로 식별자인 함수 이름을 사용할 수 있다.
![](https://images.velog.io/images/songjy377/post/cecdf10b-c366-4b03-819f-4d624bdee71a/image.png)
> - 함수는 _함수 정의_를 통해 생성한다.
> - 인수를 매개변수를 통해 전달하여 함수의 실행을 명시적으로 지시해야 한다. 이를 _함수 호출_이라 한다.

### ❓ 함수 사용 이유
- 실행 시점을 개발자가 결정할 수 있고 재사용이 가능하다. -> **코드의 재사용**이라는 측면에서 매우 유용하다.
- **유지보수의 편의성**을 높이고 **코드의 신뢰성**을 높이는 효과가 있다. 
- 식별자를 붙일 수 있으므로 함수의 내부 코드를 이해하지 않고도 함수의 역할을 파악할 수 있도록 할 수 있다. -> **코드의 가독성 향상**

## ✅ 함수 리터럴
- JS의 함수는 객체 타입의 값이다. 
- 함수 리터럴은 function 키워드, 함수 이름, 매개변수 목록, 함수 몸체로 구성된다.
```js
	var multi = function(x,y) {
	  return x * y;
	}; //함수 리터럴을 변수 multi에 할당
```
- 함수는 일반 객체와는 다르게 **호출할 수 있다.**
> <함수 리터럴의 구성 요소>
>
> |구성 요소| 설명 |
> |---|---|
> |함수 이름| - 함수 이름은 식별자다. 따라서 식별자 네이밍 규칙을 준수해야 한다. <br> - 함수 이름은 함수 몸체 내에서만 참조할 수 있는 식별자다. <br> - 함수 이름은 생략 가능하다. 이름이 있는 함수를(기명 함수), 없는 함수를(무명 함수/익명 함수)라 한다. |
> |매개변수 목록| - 0개 이상의 소괄호로 감싸고 쉼표로 구분한다. <br> - 각 매개변수에는 함수를 호출할 때 지정한 인수가 순서대로 할당된다. 즉 **매개변수 목록은 순서에 의미가 있다.** <br> - 매개변수는 함수 내에서 변수로 취급된다. 따라서 네이밍 규칙을 따라야 한다.|
> |함수 몸체| - 함수가 호출되었을 때 일괄적으로 실행할 문들을 하나의 실행 단위로 정의한 코드 블록이다. <br> - 함수 몸체는 **함수 호출에 의해** 실행된다.|

## ✅ 함수 정의
> - 함수 정의란 매개변수와 실행할 문들, 반환값을 지정하는 것을 말한다.
> - 함수를 정의하는 방식은 4가지가 있다.
> 1. 함수 선언문
>```js
>function sum(x,y) {
>  return x + y;
>}
>```
> 2. 함수 표현식
>```js
>var sum = function(x,y) {
>  return x + y;
>};
>```
> 3. Function 생성자 함수
>```js
> var sum = new Function('x', 'y', return x + y');
>```
> 4. 화살표 함수
>```js
> var sum = (x, y) => x + y;
>```

### 🔰 함수 선언문
- 리터럴과 형태가 동일하다. <span style="color:red">BUT</span>함수 리터럴은 함수 이름을 생략할 수 있지만 함수 선언문은 함수 이름을 생략할 수 없다.
```js
	function (x, y) {
	  return x + y;
	} // SyntaxError
```
- **표현식이 아닌 문이다.** 그러나 변수에 할당하는 것처럼 보이는 이유는 JS엔진이 **함수 리터럴로 해석하는 경우**와 **함수 선언문으로 해석하는 경우**가 있기 때문이다.
- 변수에 할당하거나 피연산자로 사용하는 경우 함수 리터럴로 해석한다. -> 아니면 함수 선언문으로 해석한다.
```js
	function foo() { console.log('foo'); } 
	//기명 함수 리터럴을 단독으로 사용했기 때문에 함수 선언문으로 해석됨.

	(function tmp() { console.log('tmp'); })	
	//피연산자로 사용했기 때문에 함수 리터럴 표현식으로 해석된다. 
      	//-> 피연산자는 *값*으로 평가될 수 있는 표현식이어야 한다.
	//함수 선언식은 *문*이기 때문에 피연산자로 사용할 수 없다.
```
- 함수 선언문으로 생성된 함수 foo()는 호출할 수 있다.  
`foo() -> ⭕`
- 리터럴 표현식으로 생성된 tmp는 호출할 수 없다. `tmp() -> ❌` -> <span style="color:red">WHY?</span>

> ❗❗ tmp를 호출할 수 없는 이유
>- 함수 리터럴에서, _함수 이름은 함수 몸체 내에서만 참조할 수 있는 식별자다_ 라고 했다. 이는 외부에서 함수 이름으로 함수를 참조할 수 없으므로 호출할 수 없다는 것을 의미한다.
>
> ❓❓ foo는 호출할 수 있는 이유
> - 함수 선언문으로 정의된 함수 또한 식별자를 선언한 적도 없고 할당한 적도 없다. 그런데 어떻게 실행되는 걸까?
> -> JS엔진이 암묵적으로 생성한 식별자이다.
- JS엔진은 함수 선언문을 해석해 함수 객체를 생성한다.  이때 함수 객체를 참조할 수 있는 식별자가 필요하다.
- 따라서 JS엔진은 암묵적으로 함수 이름과 동일한 이름의 식별자를 생성하고, 함수 객체를 할당한다.
- 함수는 함수 이름이 아닌 **함수 식별자로 호출한다.**

### 🔰 함수 표현식
- JS의 함수는 일급 객체다. 따라서 변수에 할당할 수 있다.
- **함수 리터럴로 생성한 함수 객체를 변수에 할당할 수 있다.** 이러한 정의 방식은 함수 표현식이라고 한다.
- 함수 표현식은 **표현식인 문** 이다.
```js
	var sum = function(x, y) {
	  return x + y;
	};
```
- 위에서 언급했지만 함수는 함수 식별자로 호출한다.
```js
	var sum = function sumNumbers(x,y) {
	  return x + y;
	};
```
- 함수 이름은 sumNumbers이고, 식별자는 sum이다.
- 위 코드에서 sumNumbers로는 함수를 호출할 수 없다. `sumNumbers(2,3) -> ❌ReferenceError`

### 📌 함수 호이스팅
- 아래 코드를 보자.
```js
	var res = square(5); //문제 없음
	//선언문
	function square(number) {
	  return number * number;
	}
	
	var res = square(5); // TypeError: square is not a function
	//표현식
	var square = function(number) {
	  return number * number;
	}
```
- ✔ 변수 호이스팅처럼, 함수 역시 호이스팅이 존재한다. (변수 호이스팅과의 차이점은, 변수 호이스팅은 undifined로 초기화되는 반면, 함수 호이스팅은 객체로 초기화되어 호출이 가능하다.)
- ✔ **함수 선언문으로 정의한 함수는 함수 선언문 이전에 호출할 수 있다.** -> 암묵적으로 생성된 식별자에 함수 객체로 초기화되기 때문.
- ✔ 자바스크립트 엔진이 스크립트가 로딩되는 시점에 바로 초기화하고 이를 VO(variable object)에 저장한다. 즉, **함수 선언, 초기화, 할당이 한번에 이루어진다**. 
- ✔ **함수 표현식으로 정의한 함수는 함수 선언문 이전에 호출할 수 없다.** -> 변수 선언은 런타임 이전에 실행되지만, 할당문이 런타임 이후 평가되기 때문이다. 즉 **변수 호이스팅만 발생한다.**

### 🔰 화살표 함수
- ES6에서 도입된 정의 방법. 화살표 함수는 **항상 익명 함수로 정의한다.**
```js
	var sum = (x, y) => x + y;
```
- 생성자 함수로 사용할 수 없고, 기존 함수와 this 바인딩 방식이 다르고, prototype 프로퍼티가 없으며 arguments 객체를 생성하지 않는다.

### 🔰 Function 생성자 함수
- function 생성자 함수에 매개변수 목록과 함수 몸체를 문자열로 전달하면서 new 연산자와 함께 호출하면 함수 객체를 생성해서 반환한다.
```js
	var sum = new Function('x', 'y', return x + y);
```
- function 생성자 함수로 함수를 생성하는 방법은 바람직하지 않다. -> 클로저를 생성하지 않는 등, 3다른 방법으로 정의된 함수들과 다르게 동작하기 때문.

## ✅ 함수 호출
>- 함수는 함수를 가리키는 식별자와 한 쌍의 소괄호인 함수 호출 연산자로 호출한다. 함수 호출 연산자 내에는 0개 이상의 인수를 쉼표로 구분해서 나열한다.
>- 함수를 호출하면 현재의 흐름을 중단하고 호출된 함수로 실행 흐름을 옮긴다.
>- 이때 매개변수에 인수가 순서대로 할당되고 함수 몸체의 문들이 실행되기 시작한다.

### 🔰 매개변수와 인수
> - 함수의 작업 실행을 위해 외부에서 함수 내부로 값을 전달할 필요가 있는 경우, 매개변수를 통해 인수를 전달한다. 
> - 인수는 값으로 평가될 수 있는 표현식이어야 한다.
>
>```js
>var sum = function(x,y) {
>  return x + y;
>};
>
>//인수 1과 2가 매개변수 x, y에 순서대로 할당되고 함수 몸체의 문들이 실행된다.
>var result = sum(1, 2);
>```

### 💠 매개변수(parameter)
> - 매개변수는 함수를 정의할 때 선언하며, 함수 몸체 내부에서 변수와 동일하게 취급된다.
> - 함수 몸체 외부에서 참조할 수 없다. -> 매개변수의 scope는 함수 내부이다.
> - 함수가 호출되면 함수 몸체 내에서 undefined로 초기화된 이후 인수가 순서대로 할당된다.
> - 매개변수의 개수와 인수의 개수가 달라도 에러를 발생시키지 않는다. -> 인수가 부족해서 할당되지 않은 값은 undefined다.
>
>```js
>var sum = function(x,y) {
>  return x + y;
>};
>
>//매개변수 y에 전달할 인자가 없다. 
>//따라서 2 + undefined와 같으므로 NaN이 반환된다.
>console.log(sum(2));
>```
> - 매개변수의 개수가 초과되면 무시한다. -> 그냥 버려지는 것은 아니고, 암묵적으로 arguments 객체의 프로퍼티로 보관된다.

### 💠 인수(argument)
>- JS는 동적 타입 언어이므로 매개변수의 타입을 사전에 지정할 수 없다.
>```js
>var sum = function(x,y) {
>  return x + y;
>};
```
>console.log(sum(2)); //NaN
>console.log(sum('a', 'b')); //'ab'
>```
> - 위 코드의 경우 에러가 발생하지 않음을 확인할 수 있다.
>- 따라서 적절한 인수가 전달되었는지 확인할 필요가 있다.

- ES6에서 도입된 _매개변수 기본값_ 을 사용하면 인수 체크 및 초기화를 간소화할 수 있다. -> 인수를 전달하지 않았을 경우와 undefined를 전달한 경우에만 유효하다.
```js
	var sum = function(x = 0,y = 0) {
	  return x + y;
	};
    
	console.log(sum()); //기본값으로 인해 0이 반환된다.
```

### 💠 매개변수의 최대 개수
- 매개변수는 적을 수록 좋다.
- 이상적인 함수는 한 가지 일만 하도록 하고, 최대한 작게 만들어야 한다.
- 만약 매개변수가 3개 이상이어야 한다면 하나의 매개변수를 선언하고 객체를 인수로 전달하는 것이 유리하다.
```js
	// ajax 메소드에 객체를 인수로 전달하는 예
	$.ajax ({
	  method: 'POST',
	  url: '/user',
	  data: { id: 1, name: 'Lee' },
	  cache: false
	});
```
- 객체를 인수로 사용하는 경우 키만 정확히 지정하면 순서를 신경쓰지 않아도 된다. -> 명시적 의미를 갖는 프로퍼티 키를 사용하게 되므로 가독성이 좋아진다.

### 🔰 반환문
> - 호출 표현식은 return 키워드가 반환한 표현식의 평가 결과로 평가된다. -> 반환값으로 평가된다.
> -반환문은 함수 내에서만 사용할 수 있다.
> - 반환문은 두 가지 역할을 한다.
>   1. 함수의 실행을 중단하고 함수를 빠져나간다.
>   2. return 키워드 뒤에 오는 표현식을 평가해 반환한다. -> 지정해주지 않으면 undefined 반환
> - 반환문은 생략할 수 있다. -> 마지막 문까지 실행한 후 undefined 반환
>- return 키워드와 반환값 사이에 줄바꿈이 있으면 안된다.
>```js
>var sum = function(x = 0,y = 0) {
>	 return //;이 자동 추가된다.
>     x + y; //무시된다.
>};
>    
>console.log(sum()); //undefined
>```

## ✅ call by reference와 외부 상태 변경
- 매개변수 또한 원시 값, 객체에 따라 call by value, call by reference로 동작한다.
```js
	function changeVal(primitive, obj) {
	  primitive += 100;
	  obj.name = 'Kim';
	  obj.gender = 'female';
	}
	
	var num = 100;
	var obj = {
	  name: 'Lee',
	  gender: 'male'
	};
	
	console.log(num); // 100
	console.log(obj); // Object {name: 'Lee', 	gender: 'male'}
	
	changeVal(num, obj);
	
	console.log(num); // 100 -> 원시 값이므로 값 변경 불가
	console.log(obj); // Object {name: 'Kim', 	gender: 'female'} -> 객체이므로 값 변경됨
```
- ❗ 객체는 외부에서 함수 몸체 내부로 전달한 참조 값에 의해 원본 객체가 변경되는 부수 효과가 발생한다.
- **함수가 외부 상태를 변경하면 상태 변화를 추적하기 어려워진다.**
- 해결 방안 중 하나로는 _불변 객체_ 를 만드는 것이다. -> 원시 값처럼 동작하게 하기
- 이렇게 객체의 상태 변경을 막으려면 _방어적 복사_를 통해 deep copy를 실행하여 재할당을 하는 방법이 있다.

## ✅ 다양한 함수의 형태
### 🔰 즉시 실행 함수(IIFE)
- (Immediately Invoke Function Expression) 
- 함수 정의와 동시에 즉시 호출되는 함수. -> 단 한번만 호출되며 다시 호출할 수 없다.
- 함수선언문은 자바스크립트 엔진에 의해 함수 몸체를 닫는 중괄호 뒤에 ;가 자동 추가된다. -> **즉시 실행 함수는 ()로 묶어준다.**
```js
// 기명 즉시 실행 함수(named immediately-invoked function expression)
(function myFunction() {
  var a = 3;
  var b = 5;
  return a * b;
}());

// 익명 즉시 실행 함수(immediately-invoked function expression)
(function () {
  var a = 3;
  var b = 5;
  return a * b;
}());

// 즉시 실행 함수도 일반 함수처럼 값을 반환할 수 있고 인수를 전달할 수도 있다.
res = (function (a, b) {
  return a * b;
}(3, 5)); //15 
```
### 🔰 재귀 함수(recursive function)
- 자기 자신을 호출하는, 재귀 호출을 수행하는 함수를 말한다.
```js
	function fibonacci(n) {
	  if (n < 2) return n; //탈출 조건
     	//함수 내부에서의 fibobacci는 함수 이름이다. 
      	//외부에서 호출할 때는 반드시 식별자로 호출해야 한다.
	  return fibonacci(n - 1) + fibonacci(n - 2); 
	}
```
- 재귀 함수는 자신을 무한히 연쇄 호출하므로 호출을 멈출 수 있는 **탈출 조건**을 반드시 만들어야 한다. 
- 탈출 조건이 없는 경우, 함수가 무한 호출되어 stackoverflow 에러가 발생한다.
- **반복문보다 재귀 함수를 통해 보다 직관적으로 이해하기 쉬운 구현이 가능한 경우에만 한정적으로 적용하는 것이 바람직하다.**

### 🔰 중첩 함수(nested function)
>- 함수 내부에 정의된 함수를 _중첩 함수(nested function)_또는 _내부함수(Inner function)_ 라 한다.
> - 일반적으로 중첩 함수는 자신을 포함하는 외부 함수를 돕는 헬퍼 함수의 역할을 한다.
> - 중첩 함수를 포함하는 함수를 _외부 함수_ 라 부른다.
> - 중첩 함수는 **외부 함수 내에서만 호출할 수 있다.**
> - 내부 함수는 외부 함수의 변수에 접근할 수 있다.
> - 하지만 외부 함수는 내부 함수의 변수에 접근할 수 없다.
```js
function parent(param) {
  var parentVar = param;
  //중첩 함수
  function child() {
    var childVar = 'lee';
    console.log(parentVar + ' ' + childVar); // Hello lee -> 외부 함수의 변수를 참조할 수 있다.
  }
  child();
  console.log(parentVar + ' ' + childVar);
  // Uncaught ReferenceError: childVar is not defined -> 중첩 함수 변수 참조 불가❌
}
parent('Hello');
```

### 🔰 콜백 함수(Callback function)
>- 매개변수를 통해 다른 함수의 내부로 전달되는 함수.
>- 콜백 함수를 전달받은 함수를 **고차 함수**라 한다.
>```js
>	function repeat(n, f) {
>	   for(var i = 0; i < n; i++) {
>	 	f(i) //i를 전달하면서 f를 호출
>	   }	
>	}
>
>	var logAll = function(i) {
>		console.log(i)
>	}
>
>	repeat(5, logAll); //반복 호출할 함수를 인수로 전달한다.
>```
> - **고차 함수는 콜백 함수를 자신의 일부분으로 합성한다.** -> 콜백 함수는 외부에서 주입되기 때문에 조금 더 자유롭게 교체가 가능하다.
>- 고차 함수는 매개변수를 통해 전달받은 콜백 함수의 호출 시점을 결정해서 호출한다. -> **콜백 함수는 고차 함수에 의해 호출된다.**
>- 고차 함수는 콜백 함수에 인수를 전달할 수 있다. -> **콜백 함수 자체를 고차 함수에 전달해야 한다.**

- 콜백 함수가 고차 함수 내부에만 호출된다면 콜백 함수를 익명 함수 리터럴로 정의하면서 곧바로 고차 함수에 전달하는 것이 일반적이다.
```js
	repeat(5, function(i) {
      if(i % 2) console.log(i);
    }); //1 3
```
- 이때 콜백 함수로 전달된 리터럴은 고차 함수가 호출할 때마다 함수 객체를 생성한다. -> 다른 곳에서도 호출할 필요가 있거나 자주 호출된다면 함수 외부에서 콜백 함수를 정의한 후 참조를 전달하는 것이 효율적이다.
```js
	//logAll 함수는 단 한 번만 생성된다.
	var logAll = function(i) {
		console.log(i)
	}
    
    //고차 함수에 함수 참조를 전달한다.
    repeat(5, logAll);
```
- 📌**콜백 함수는 비동기 처리에 활용되는 중요한 패턴이다.**
```js
	//버튼을 클릭하면 콜백 함수 실행
	var button = document.getElementById('myButton');
	button.addEventListener('click', function() {
      		console.log('button clicked!');
    	});
	//1초 후에 메세지 출력
	setTimeout(function () {
	  console.log('1초 후 출력된다.');
	}, 1000);
```

### 🔰 순수 함수(pure function) 
> - 부수 효과가 없는 함수

- 동일한 인수가 전달되면 언제나 동일한 값을 반환하는 함수.
- 어떤 외부 상태에도 의존하지 않고 매개변수를 통해 전달된 인수에게만 의존해 반환값을 만든다.
```js
	var cnt = 0;
	
	function increase(n) {
            return ++n;
    	}

	cnt = increase(cnt); //결과값을 재할당해서 상태 변경
	console.log(cnt) //1
```

### 🔰 비순수 함수(impure function)
> - 외부 상태를 변경하는 부수 효과가 있는 함수

- 외부 상태에 의존하여, 외부 상태에 따라 반환값이 달라진다.
```js
	var cnt = 0;
	
	function increase() {
            return ++cnt; //외부 상태 변경
    	}
	// 외부 상태(cnt)를 변경하므로 상태 변화 추적 어려움.
	increase();
	console.log(cnt) //1
```

📌 함수가 외부 상태를 변경하게 되면 상태 변화 추적이 어려워진다. -> 순수 함수를 지양하자.
📌 함수형 프로그래밍은 부수 효과를 최소화해서 **불변성**과 **안정성**을 지향하는 프로그래밍 패러다임이다.