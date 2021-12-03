# 빌트인 객체와 전역 객체
> 빌트인 객체와 전역 객체, 래퍼 객체와 다양한 빌트인 전역 함수, 빌트인 전역 프로퍼티, 암묵적 전역 등에 대해 다룬다.(211202)

## ✅ JS 객체의 분류
- 표준 빌트인 객체
표준 빌트인 객체는 ECMAScript 사양에 정의된 객체로 애플리케이션 전역의 공통 기능을 제공한다. 별도의 선언 없이 전역 변수처럼 언제나 참조할 수 있다.
- 호스트 객체
호스트 객체는 ECMAScript 사양에 정의되어 있지 않지만 자바스크립트 실행 환경에서 추가로 제공하는 객체를 말한다.
- 사용자 정의 객체
사용자 정의 객체는 표준 빌트인 객체와 호스트 객체처럼 기본 제공되는 객체가 아닌 사용자가 직접 정의한 객체를 말한다.

## ✅ 표준 빌트인 객체
> - ECMAScript 사양에 정의된 객체로 애플리케이션 전역의 공통 기능을 제공한다. JS 실행 환경과 관계없이 별도의 선언 없이 전역 변수처럼 언제나 참조할 수 있다.
> - Math, Reflect, JSON 을 제외한 표준 빌트인 객체는 모두 인스턴스를 생성할 수 있는 **[생성자 함수 객체](https://velog.io/@songjy377/JS-%EC%83%9D%EC%84%B1%EC%9E%90-%ED%95%A8%EC%88%98%EC%97%90-%EC%9D%98%ED%95%9C-%EA%B0%9D%EC%B2%B4-%EC%83%9D%EC%84%B1)**다.
> - 생성자 함수 객체인 표준 빌트인 객체는 프로토타입 메서드와 정적 메서드를 제공한다.
> - 생성자 함수 객체가 아닌 표준 빌트인 객체는 정적 메서드만 제공한다.

```js
const strObj = new String('Lee'); // String {"Lee"}
console.log(typeof strObj); // object

const numObj = new Number(123); // Number {123}
console.log(typeof numObj); // object

const func = new Function('x', 'return x * x'); // f anonymous(x) { return x * x }
console.log(typeof func); // object

const regExp = new RegExp(/ab+c/i); // /ab+c/i
console.log(typeof regExp); // object

const date = new Date(); // Mon Jul 05 2021 14:12:22 GMT+0900 (대한민국 표준시)
console.log(typeof date); // object

//표준 빌트인 객체가 생성한 인스턴스의 프로토타입은 표준 빌트인 객체의 prototype 프로퍼티에 바인딩된 객체다.
const strObj = new String('Ssong'); 
```
- prototype 프로퍼티에 바인딩된 객체는 다양한 기능의 빌트인 프로토타입 메서드를 제공한다.
```js
const numObj = new Number(1.5); // Number {1.5}
//toFixed는 Number의 프로토타입 메소드다.
// 소수점 자리를 반올림하여 그 결과를 반환한다.
console.log(numObj.toFixed()); // 2
//isInteger Number의 프로토타입 메소드다.
// 정수인지 검사하여 그 결과를 반환한다.
console.log(Number.isInteger(0.5)); // false
```

## ✅ 원시값과 래퍼 객체
```js
	const str = 'hello';
	console.log(str.length); // 5
```
- 원시값은 객체가 아니므로 프로퍼티나 메서드를 가질 수 없는데도 원시값인 문자열이 마치 객체처럼 동작한다. -> 이는 원시값인 문자열, 숫자, 불리언 값의 경우에 마침표 또는 대괄호 표기법으로 접근하면 자바스크립트 엔진이 일시적으로 원시값을 연관된 객체로 변환해주기 때문이다.
- 원시값을 객체처럼 사용하면 암묵적으로 연관된 객체를 생성해서, 생성된 객체로 프로퍼티에 접근하거나 메서드를 호출한다. 

>- 이처럼 문자열, 숫자, boolean 값에 대해 **객체처럼 접근하면** 생성되는 임시 객체를 래퍼 객체(wrapper object)라 한다.

- 래퍼 객체의 처리가 종료되면 식별자가 원시값을 갖도록 되돌리고, 래퍼 객체는 가비지 컬렉션의 대상이 된다.
```js
	const str = 'hello';
	//래퍼 객체가 생성되고 name 프로퍼티가 동적 추가된다.
	str.name = 'Ssong';
	//위에서 생성된 래퍼 객체는 처리가 종료되었으므로 가비지 컬렉션의 대상이 되었다.
	//아래 문에 의해 새로운 래퍼 객체가 생겼다. -> 새로운 객체이므로 name 프로퍼티가 존재하지 않는다.
	console.log(str.name); //undefined 
```

- 이처럼 래퍼 객체에 의해 문자열, 숫자, 불리언, 심볼은 마치 객체처럼 사용할 수 있고 표준 빌트인 객체의 프로토타입 메서드 또는 프로퍼티를 참조할 수 있다. _(null과 undefined는 래퍼 객체를 참조하지 않는다.)_
- 그렇기 때문에 `String`, `Number`, `Boolean` 생성자 함수를 new 연산자와 함께 호출하여 문자열, 숫자, 불리언 인스턴스를 생성할 필요가 없으며 권장하지도 않는다.

## ✅ 전역 객체
> - 전역 객체(global object)는 **코드가 실행되기 이전 단계에 자바스크립트 엔진에 의해 어떤 객체보다도 먼저 생성되는 특수한 객체**이며, **어떤 객체에도 속하지 않은 최상위 객체**다. 
> - 브라우저 환경에서는 window가 전역 객체를 가리키지만 Node.js 환경에서는 global이 전역 객체를 가리킨다.

>❗ 전역 객체가 최상위 객체라는 것은 프로토타입 상속 관계상에서 최상위 객체라는 의미가 아니다. 전역 객체는 다른 객체의 프로퍼티가 아니고, 객체의 계층적 구조상 표준 빌트인 객체와 호스트 객체를 프로퍼티로 소유한다는 것을 말한다.

### 🔰 전역 객체의 특징
1. 전역 객체는 개발자가 의도적으로 생성할 수 없다. 즉, 전역 객체를 생성할 수 있는 생성자 함수가 제공되지 않는다.
2. 전역 객체의 프로퍼티를 참조할 때 window를 생략할 수 있다.
```js
	// 문자열 'F'를 16진수로 해석하여 10진수로 변환
	window.parseInt('F', 16); // 15
	
	parseInt('F', 16); // 15
	
	window.parseInt === parseInt; // true
```
3. 전역 객체는 Object,String,Number과 같은 모든 표준 빌트인 객체를 프로퍼티로 가지고 있다.
4. 자바스크립트 실행 환경에 따라 추가적으로 프로퍼티와 메서드를 갖는다.
5. var 키워드로 선언한 전역 변수와 선언하지 않은 변수에 값을 할당한 암묵적 전역, 그리고 전역 함수는 전역 객체의 프로퍼티가 된다.
```js
	//선언하지 않은 변수에 값을 암묵적 전역.
	//bar는 전역변수가 아니라 전역 객체의 프로퍼티가 된다.
	bar = 2;
	console.log(window.bar); //2
```
6. let이나 const 키워드로 선언한 전역 변수는 전역 객체의 프로퍼티가 아니다. -> window.foo와 같이 접근할 수 없다.
7. 브라우저 환경의 모든 자바스크립트 코드는 하나의 전역 객체 window를 공유한다.

### 🔰 빌트인 전역 프로퍼티
> - 빌트인 전역 프로퍼티는 전역 객체의 프로퍼티를 의미한다. 주로 애플리케이션 전역에서 사용하는 값을 제공한다.

### <span style="color:#27D600">Infinity</span>
>- 무한대를 나타내는 숫자값을 갖는다.
>```js
>console.log(Infinity); // Infinity
>// 양의 무한대
>console.log(3/0); // Infinity
>// 음의 무한대
>console.log(-3/0); // -Infinity
>// Infinity는 숫자값이다.
>console.log(typeof Infinity); // number
>```

### <span style="color:#27D600">NaN</span>
> - `NaN` 프로퍼티는 숫자가 아님(Not-a-Number)을 나타내는 숫자값 `NaN`을 갖는다. `NaN` 프로퍼티는 `Number.NaN` 프로퍼티와 같다.
>```js
>console.log(NaN); // NaN
>console.log(Number('xyz')); // NaN
>console.log(1 * 'string'); // NaN
>// NaN는 숫자 타입이다.
>console.log(typeof NaN); // number
>```

### <span style="color:#27D600">undefined</span>
>- 원시 타입 undefined를 값으로 갖는다.
>```js
>console.log(window.undefined); // undefined
>
>var foo;
>console.log(foo); // undefined
>console.log(typeof undefined); // undefined
>```

### 🔰 빌트인 전역 함수

>- 빌트인 전역 함수는 애플리케이션 전역에서 호출할 수 있는 빌트인 함수로서 전역 객체의 메서드다.

### <span style="color:#5F66F4">_eval_</span>
> - eval 함수는 자바스크립트 코드를 나타내는 문자열을 인수로 전달받는다. 
> - 전달받은 **문자열 코드가 표현식이라면** eval 함수는 문자열 코드를 **런타임에 평가**하여 값을 생성한다.
> - 전달받은 인수가 **표현식이 아닌 문이라면** eval 함수는 **문자열 코드를 런타임에 실행**한다.
>```js
>// 표현식인 문
>eval('1 + 2;'); // 3
>// 표현식이 아닌 문
>eval('var x = 5;'); // undefined
>
>console.log(x); // 5
>// 객체 리터럴은 반드시 괄호로 둘러싼다.
>const o = eval('({ a: 1 })');
>console.log(o); // { a: 1 }
>//여러 개의 문으로 이루어져 있다면 모든 문을 실행한 후 마지막 결과값을 반환한다.
>eval('1 + 2; 3 + 4;') //7
>```
> ❗ eval 함수를 통해 실행되는 코드는 자바스크립트 엔진에 의해 최적화가 수행되지 않으므로 처리 속도가 느리다. 따라서 eval 함수의 사용은 금지해야 한다.
  
### <span style="color:#5F66F4">isFinite</span>
> - 전달받은 인수가 정상적인 유한수인지 검사하여 **유한수이면 true**를 반환하고, **무한수이면 false**를 반환한다. 
> - 인수의 타입이 숫자가 아닌 경우, 숫자로 타입변환 후 검사를 수행한다. NaN으로 평가되는 값이면 false를 반환한다.
>```js
>isFinite(0); // true
>isFinite('10'); // true
>isFinite(null); // true (null을 숫자 타입변환하면 0)
>isFinite(Infinity); // false
>isFinite('Hello'); // false
>```

### <span style="color:#5F66F4">isNaN</span>
> - 전달받은 인수가 **NaN인지 검사**하여 boolean 타입으로 반환한다. 인수의 타입이 숫자가 아닌 경우 숫자로 타입변환후 검사를 수행한다.
>```js
>isNaN(NaN); // true
>isNaN(10); // false
>isNaN(''); // false ('' => 0)
>isNaN('blabla'); // false ('' => 0)
>isNaN(undefined); // false ('' => 0)
>isNaN({}); // false
>```

### <span style="color:#5F66F4">parseFloat</span>
> - 전달받은 문자열 인수를 부동 소수점 숫자, 즉 **실수로 해석**하여 반환한다.
>```js
>parseFloat('3.14'); // 3.14
>//공백으로 구분된 문자열은 첫번째 문자열만 반환한다.
>parseFloat('34 45 66') //34 parseInt도 동일
>parseFloat('I am 24'); // 첫 번째 문자열을 숫자로 변환할 수 없다면 NaN을 반환한다. parseInt도 동일
>parseFloat(' 60 ') //앞뒤 공백은 무시한다. parseInt도 동일
>```

### <span style="color:#5F66F4">parseInt</span>
> - 전달받은 문자열 인수를 **정수로 해석**하여 반환한다. 
> - **문자열이 아니면 문자열로 반환한 다음, 정수로 해석**하여 반환한다. 
> - **두 번째 인수로 진법을 나타내는 기수를 전달할 수 있다. 기수를 생략하면 10진수로 해석하여 반환한다.**
>```js
>parseInt('10'); // 10
>parseInt('10.34') //10
>parseInt(15) //15
>parseInt(15.67) //15
>parseInt('10', 2); // 2 , '10'을 2진수로 해석하고 10진수 정수로 반환
- 10진수 숫자를 해당 기수의 문자열로 변환하고 싶을 때는 `Numer.prototype.toString`메소드를 사용한다.
```js
>cosnt x = 15;
>
>x.toString(2) //'1111'
>x.toString(8) //'17`
>ParseInt(x.toString(8). 8) //문자열 17을 8진수로 해석하고 그 결과를 10진수 정수로 반환한다.
>//16진수 리터럴 0xf를 16진수로 해석하고 10진수 정수로 그 결과를 반환한다.
>ParseInt('0xf') //15
> //하지만 2진수 8진수는 제대로 해석하지 못한다.
>ParseInt('0b12') //0 -> 0이후가 무시된다.
>ParseInt('1A0') //1 -> 10진수로 해석할 수 없는 A이후의 문자는 모두 무시된다.
>ParseInt('1A0') //1 -> 10진수로 해석할 수 없는 A이후의 문자는 모두 무시된다.
>```
  
### <span style="color:#5F66F4">encodeURI/decodeURI</span>
> - `encodeURI` 함수는 URI를 문자열로 전달받아 인코딩을, `decodeURI` 함수는 인코딩된 URI를 인수로 전달받아 디코딩한다. 
>- URI(Uniform Resource Identifier)는 인터넷에 있는 자원을 나타내는 주소를 말한다. URI의 하위 개념으로 URL, URN이 있다.
>![](https://images.velog.io/images/songjy377/post/772217f8-a899-47e9-b75b-6b36257f2a6e/image.png)
>```js
>const uri = 'http://example.com?name=홍길동&job=programmer';
>
>const enc = encodeURI(uri);
>console.log(enc); // http://example.com?name=%ED%99%8D%EA%B8%B8%EB%8F%99&job=programmer
>
>const dec = decodeURI(enc);
>console.log(dec); // http://example.com?name=홍길동&job=programmer
>```

### <span style="color:#5F66F4">encodeURIComponent/decodeURIComponent</span>
> - `encodeURIComponent` 함수는 URI 구성 요소를 인수로 전달받아 인코딩한다. 인수로 전달된 문자열을 쿼리 스트링의 일부로 간주하여, 쿼리스트링 구분자로 사용되는 =, ?, & 까지 인코딩한다.
> - _반면 `encodeURI` 함수는 매개변수로 전달된 문자열을 완전한 URI 전체라고 간주해 쿼리 스트링 구분자는 인코딩하지 않는다._
> - `decodeURIComponent` 함수는 매개변수 전달된 URI 구성 요소를 디코딩한다.
>```js
>const uriComp = '홍길동&job=programmer';
>let enc = encodeURIComponent(uriComp);
console.log(enc); // %ED%99%8D%EA%B8%B8%EB%8F%99%26job%3Dprogrammer
>let dec = decodeURIComponent(enc);
>console.log(dec); // 홍길동&job=programmer
>enc = encodeURI(uriComp);
>console.log(enc); // %ED%99%8D%EA%B8%B8%EB%8F%99&job=programmer
>dec = decodeURI(enc);
>console.log(dec); // 홍길동&job=programmer
>```

### 🔰 암묵적 전역
> - 선언되지 않은 변수가, JS엔진이 전역 객체의 프로퍼티로 동적 생성하여 전역 변수처럼 동작하는 것.
>```js
>var x = 10; // 전역 변수
>function foo () {
> //선언하지 않은 식별자에 값을 할당
>  y = 20; //window.y = 20;
>}
>foo();
>
> //선언하지 않은 식별자 y를 전역에서 참조할 수 있다.
>console.log(x + y); // 30
>```
> - foo 함수 내의 y는 선언하지 않은 식별자다. 선언하지 않았음에도 식별자 y는 마치 선언된 전역 변수처럼 동작한다.
> - ❗ y는 변수 선언 없이 단지 전역 객체의 프로퍼티로 추가되었을 뿐이다. 따라서 **y는 변수가 아니다.**
>```js
>console.log(y); // 변수가 아니므로 호이스팅이 발생하지 않는다.
>var x = 10; // 전역 변수
>function foo () {
> //선언하지 않은 식별자에 값을 할당
>  y = 20; //window.y = 20;
>}
>foo();
>
> delete y; //프로퍼티이므로 삭제 가능하다.
>```