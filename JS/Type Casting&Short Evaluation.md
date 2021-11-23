# 타입 변환과 단축 평가

> 타입 변환과 단축 평가에 대해 다룬다.(211124)

## ❔ 타입 변환

> 자바스크립트의 모든 값은 타입이 있다. 개발자는 타입을 변경할 수 있다.
>
> - 개발자가 **의도적으로** 값의 타입을 변환하는 것을 **명시적 타입 변환** 또는 **타입 캐스팅(type casting)** 이라 한다.
> - 개발자의 **의도와는 상관없이** 표현식을 평가하는 도중에 **자바스크립트 엔진에 의해** 암묵적으로 타입이 자동변환되기도 한다.
> - 이를 **암묵적 타입 변환** 또는 **타입 강제 변환**이라 한다.

- 두 변환 모두 원시 값을 직접 변경하는 것은 아니다.
- 타입 변환이란 기존 원시 값을 사용해 다른 타입의 새로운 원시 값을 생성하는 것이다.

```js
var x = 10;
var str = x + '';

console.log(typeof x, x); //number 10 -> 타입 변환이 이루어진 값이 재할당되지는 않았음.
```

- 위 코드에서 JS 엔진은 x변수의 숫자 값을 바탕으로 새로운 문자열 값 '10'을 생성하고 이것으로 표현식 '10' + ''을 평가한다.
- 이때 암묵적으로 변화한 '10'은 x변수에 재할당되지 않는다. -> 암묵적 타입 변환은 기존 변수 값을 전환해 새로운 type의 값을 만들어 **단 한번**사용하고 버린다.

## ✅ 암묵적 타입 변환

> 자바스크립트 엔진은 표현식을 평가할 때 개발자의 의도와는 상관없이 코드의 문맥을 고려해 암묵적으로 데이터 타입을 강제 변환할 때가 있다.
>
> - 암묵적 타입 변환 시에는 _문자열, 숫자, boolean등 원시 타입_ 중 하나로 자동 변환한다.

### 🔰string

```js
// 피연산자가 모두 문자열 타입이어야 하는 문맥
'1' +
  (2)[(10, 11)] + // '12'
  ''; // '10, 11'
```

- 위 코드의 + 연산자는 피연산자 중 하나 이상이 문자열이므로 문자열 연결 연산자로 동작한다.
- 문자열 연결 연산자의 피연산자들은 모두 문자열 타입이어야 한다. -> JS 엔진은 문자열이 아닌 피연산자를 문자열로 변환한다.
- symbol type은 string으로 변환이 안 된다.
- **template literal에서도 표현식의 평가 결과를 문자열 타입으로 암묵적으로 타입 변환한다.**

```js
`1+1=${1 + 1}`; // "1+1=2"
```

### 🔰 number

```js
	// 피연산자가 모두 숫자 타입이어야 하는 문맥
	1 - '1' // 0
	1 * '10' // 10
	1 / '
```

- 산술 연산자의 역할은 숫자 값을 만드는 것이므로 산술 연산자의 모든 피연산자는 모두 숫자 타입이어야 한다.
  `'1' > 0 //true`
- 비교 연산자 또한 피연산자의 크기를 비교하므로 모든 피연산자는 모두 숫자 타입이어야 한다.
- `+`단항 연산자는 피연산자가 숫자 값이 아니면 숫자 타입의 값으로 암묵적 타입 변환을 수행한다.

```js
//string type
+'0' + // 0
  '1' + // 1
  //boolean type
  true + // 1
  false + // 0
  //null type
  null + //0
  //undefined type
  undefined; //NaN
```

- symbol type은 number 변환이 안 된다.

### 🔰 boolean

- JS 엔진은 if, for문 과 같은 조건식의 평가 결과를 boolean으로 암묵적 타입 변환한다.
  ❗ boolean 값으로 평가되어야 할 문맥에서 boolean값이 아닌 값을 Truthy 값은 true로, Falsy 값은 false로 암묵적 타입 변환한다.
  > 📌 false로 평가되는 Falsy 값
  > false, undefined, null, 0, -0, NaN, ''(빈 문자열)

## ✅ 명시적 타입 변환

> 개발자의 의도에 따라 명시적으로 타입을 변경하는 방법은 다양하다. 표준 빌트인 생성자 함수(String, Number, Boolean) 호출하는 방법, 빌트인 메서드를 사용하는 방법 등이 있다.

### 🔰 string

1. String 생성자 함수를 new 연산자 없이 호출하는 방법

```js
String(1); // "1"
String(true); // "true"
```

2. Object.prototype.toString 메서드를 사용하는 방법

```js
(1).toString(); // "1"
true.toString(); // "true"
```

3. 문자열 연결 연산자를 이용하는 방법

```js
1 + ''; // "1"
true + ''; // "true"
```

### 🔰 number

1. Number 생성자 함수를 new 연산자 없이 호출하는 방법

```js
Number(-1); // -1
Number(true); // 1
```

2. parseInt, parseFloat 함수를 사용하는 방법(문자열만 변환 가능)

```js
parseInt('0'); // 0
parseFloat('10.53'); // 10.53
```

❗ parseInt는 문자열만 변환 가능하다. boolean값인 true는 NaN으로 출력된다. `parseInt(true) // NaN`

3. \+ 단항 산술 연산자를 이용하는 방법

```js
+'0'; // 0
+true; // 1
```

4. \* 산술 연산자를 이용하는 방법

```js
'0' * 1; // 0
true * 1; // 1
```

### 🔰 boolean 타입으로 변환

1. Boolean 생성자 함수를 new 연산자 없이 호출하는 방법

```js
Boolean('x'); // true
Boolean(''); // false
Boolean(NaN); // false
Boolean(1); // true
Boolean(undefined); // false
```

2. ! 부정 논리 연산자를 두 번 사용하는 방법

```js
!!'x'; // true
!!'false'; // false
!!1; //true
!!null; // false
```

## ✅ 단축 평가

- 논리합/논리곱 연산자는 좌항에서 우항으로 평가가 진행되고, 언제나 2개의 피연산자 중 한쪽으로 평가된다.
- &&(논리곱)은 두 개의 피연산자 모두 true일 때 true를 반환한다.
  `'Cat' && 'Dog'
- 위 식에서 두 피연산자 모두 true이기 때문에, 논리 연산의 결과를 결정하는 두 번째 피연산자인 'DOG'를 반환한다.
- ||(논리곱)일 경우 하나만 true여도 true이기 때문에 'Cat'을 반환한다.

> - 논리 연산자는 피연산자를 타입 변환하지 않고 그대로 반환한다. 이를 단축 평가라 한다.
> - 단축 평가는 표현식을 평가하는 도중에 평가결과가 확정된 경우 나머지 평가 과정을 생략하는 것을 말한다.
>
> | 단축 평가 표현식    | 평가 결과 |
> | ------------------- | --------- |
> | true \|\| anything  | true      |
> | false \|\| anything | anything  |
> | true && anything    | anything  |
> | false && anything   | false     |

- 단축 평가를 사용하면 if문을 대체할 수 있다.

```js
var done = true;
var message = '';
// if문
if (done) message = '완료';
// 단축 평가
message = done && '완료';
console.log(message); // 완료

var done = false;
var message = '';
// if문
if (!done) message = '미완료';
// 단축 평가로
message = done || '미완료';
console.log(message); // 미완료
```

- 삼항 연산자도 if...else 문을 대체할 수 있다.

```js
var done = true;
var message = '';
// if문
if (done) message = '완료';
else message = '미완료';
console.log(message); // 완료
// 삼항 연산자
message = done ? '완료' : '미완료';
console.log(message); // 완료
```

### 🔰 단축 평가의 유용성

1. **객체를 가리키는 기대하는 변수가 null 또는 undefined가 아닌지 확인하고 프로퍼티를 참조할 때**

> - 객체는 key, value로 구성된 집합이므로 null또는 undefined를 참조하는 경우 typeerror가 발생한다.
>
> ```js
> var elem = null;
> var value = elem.value; // ❌ TypeError: >Cannot read property 'value' of null
> ```

- 이때 단축 평가를 사용할 수 있다.

```js
var elem = null;
// elem이 null이나 undefined와 같은 Falsy 값이면 elem으로 평가
// elem이 Truthy 값이면 elem.value로 평가
var value = elem && elem.value; // null
```

2. **함수 매개변수에 기본값을 설정할 때**

> - 함수를 호출할 때 인수를 전달하지 않으면 매개변수에는 undefined가 할당된다. 이때 단축 평가를 사용해 매개변수의 기본값을 설정하면 undefined로 인해 발생할 수 있는 에러를 방지할 수 있다.
>
> ```js
> function getStringLength(str) {
>   str = str || '';
>   return str.length;
> }
>
> //ES6의 기본값 설정
> function getStringLength(str = '') {
>   str = str || '';
>   return str.length;
> }
> getStringLength(); // 0
> getStringLength('hi'); // 2
> ```

### 🔰 옵셔널 체이닝(optional chaining) 연산자

> - ES6에서 도입된 옵셔널 체이닝 연산자 `?.` 는 좌항의 피연산자가 null 또는 undefined인 경우 undefined를 반환하고, 그렇지 않으면 우항의 프로퍼티 참조를 이어간다.

```js
var elem = null;
var value = elem?.value;
console.log(value); // undefined
```

❗ **좌항 피연산자가 false로 평가되는 Falsy 값이라도 null 또는 undefined가 아니면 우항의 프로퍼티 참조를 이어간다.**

```js
var str = '';
var length = str && str.length;
console.log(length); // '' -> length를 참조하지 못한다.

var str = '';
var length = str?.length;
console.log(length); // 0 ->좌항이 false값이지만 null, undefined가 아니므로 우항의 프로퍼티를 참조한다.

let user1 = {
  firstName: 'Violet',
};

let user2 = null; // user2는 권한이 없는 사용자라고 가	정해봅시다.

let key = 'firstName';

alert(user1?.[key]); // Violet -> 값이 있으므로 참조
alert(user2?.[key]); // undefined
```

### 🔰 null 병합 연산자

> - ES11에서 도입된 null 병합 연산자 `??` 는 좌항의 피연산자가 null또는 undefined인 경우 우항의 피연산자를 반환하고, 그렇지 않으면 좌항의 피연산자를 반환한다.
> - 변수에 기본값을 설정할 때 유용하다.

```js
var foo = null ?? 'default string';
console.log(foo); // "default string"
```

❗ **||를 사용한 단축 표현의 경우, 0이나 ''값이 기본값으로 유효하게 되면 문제가 될 수 있다. **
❗ **그러나 null병합 연산자는 좌항 피연산자가 false로 평가되는 Falsy 값이라도 null 또는 undefined가 아니면 좌항의 프로퍼티 참조를 이어간다.**

```js
var foo = '' ?? 'default string';
console.log(foo); // ""
```
