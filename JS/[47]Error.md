# Error
> Error에 대해 다룬다.(220103)

- 에러는 언제나 발생할 수 있다. 발생한 에러에 대해 대처하지 않고 방치하면 프로그램은 강제 종료된다. 에러가 발생하지 않는 코드를 작성하는 것은 불가능에 가깝기 때문에, 작성한 코드에는 언제나 **예외적인 상황이 발생할 수 있다는 것을 전제**하고 이에 대응하는 코드를 작성하는 것이 중요하다.

### ⛔ `try... catch... finally` 문
```js
try {
  // 실행할 코드(에러가 발생할 가능성이 있는 코드)
}
catch(error) {
  // try 코드 블럭에서 에러가 발생하면 이 코드 블럭의 코드가 실행된다.
  // error에는 try 코드 블럭에서 발생한 Error 객체가 전달된다.
}
finally {
  //에러 발생과 상관없이 반드시 한 번 실행된다.
}
```
- `try... catch... finally` 문을 실행하면 먼저 `try` 문이 실행된다.
- 이때 try 코드 블럭에 포함된 문에서 에러가 발생하면 발생한 에러는 `catch`문의 error 변수에 전달되고 `catch`  코드 블럭이 실행된다. **error 변수는 catch 코드 블록에서만 유효하다.**
- `finally` 코드 블럭은 에러 발생과 상관없이 반드시 한 번 실행된다.
- `try... catch... finally` 문으로 에러를 처리하면 프로그램이 강제 종료되지 않는다.                 

### ⛔ Error 객체
- Error 생성자 함수는 에러 객체를 생성한다. Error 생성자 함수에는 에러를 설명하는 에러 메시지를 인수로 전달할 수 있다.
```js
const error = new Error('invalid');
```
- 에러 객체는 `message`와 `stack`프로퍼티를 갖는다. `message` 프로퍼티는 에러 메시지를, `stack` 프로퍼티는 에러를 발생시킨 콜 스택의 호출 정보를 나타내는 문자열이 담긴다.
- 자바스크립트는 Error 생성자 함수를 포함해 7가지의 에러 객체를 생성할 수 있는 Error 생성자 함수를 제공한다.

|생성자 함수|인스턴스|
|:--:|:--:|
|Error|일반적인 에러 객체|
|SyntaxError|JS문법에 맞지 않는 문을 해석할 때 발생하는 에러 객체|
|TypeError|피연산자 또는 인수의 데이터 타입이 유효하지 않을 때 발생하는 에러 객체|
|ReferenceError|참조할 수 없는 식별자를 참조했을 때 발생하는 에러 객체|
|RangeError|숫자값의 허용 범위를 벗어낫을 때 발생하는 에러 객체|
|URIError|encodeURI 또는 decodeURI 함수에 부적절한 인수를 전달했을 때 발생하는 에러 객체|
|EvalError|eval 함수에서 발생하는 에러 객체|

- 에러 코드를 실행하면 다음과 같다.
```js
1 @ 1; // SyntaxError: Invalid or unexpected token
foo(); // ReferenceError: foo is not defined
null.foo; // TypeError: Cannot read property 'foo' of null
new Array(-1); // RangeError: Invalid array length
decodeURIComponent('%'); // URIError: URI malformed
```

### ⛔ `throw` 문
- Error 생성자 함수로 에러 객체를 생성해보자.
```js
try {
  new Error('something wrong');
} catch (error) {
  console.log(error.message); // 값이 출력되지 않는다.
}
```
- 위의 코드를 실행시켜보면 catch 블록의 코드가 실행되지 않는다. **에러 객체를 생성한다고 에러가 발생하는 것이 아니다.**

- 에러를 발생시키려면 try 코드 블록에서 `throw` 문으로 에러 객체를 던져야 한다.
```js
try {
  throw new Error('something wrong');
} catch (error) {
  console.log(error.message); // 'something wrong'
}
```
- 에러를 던지면 `catch` 문의 에러 변수가 생성되고 던져진 에러 객체가 할당되고, `catch`문이 실행되기 시작한다.

### ⛔ 에러의 전파
- 에러는 호출자(caller) 방향으로 전파된다. 즉 콜 스택의 아래 방향으로 전파된다.
```js
const foo = () => {
  throw Error('foo에서 발생한 에러');
}

const bar = () => {
  foo();
};

const baz = () => {
  bar();
};

try {
  baz();
} catch (err) {
  console.error(err);
  /*
  Error: foo에서 발생한 에러
    at foo (<anonymous>:2:9)
    at bar (<anonymous>:6:3)
    at baz (<anonymous>:10:3)
    at <anonymous>:14:3
  */
}
```
- `baz` 함수를 호출하면 `bar` 함수가 호출되고, 이후 `foo` 함수가 호출되며 `foo` 함수는 에러를 `throw` 한다. 이때 `foo` 함수가 `throw`한 에러는 `catch` 블록에서 출력된 결과와 같이 호출자에게 전파되서 전역에서 캐치된다.
- 이처럼 throw된 에러를 캐치하지 않으면 호출자 방향으로 전파된다. 
- **throw된 에러를 어디에도 캐치하지 않으면 프로그램은 강제 종료된다.**

- 여기서 주의할 점은 **비동기 함수 `setTimeout`이나 `Promise`의 콜백 함수는 호출자(caller)가 없다는 것이다.** 
- `setTimeout`이나 `Promise`의 콜백 함수는 태스크 큐나 마이크로태스크 큐에 일시 저장되었다가 콜 스택이 비면 이벤트 루프에 의해서 콜 스택으로 푸시되어 실행된다.

- 이때 콜 스택에 푸시된 콜백 함수의 실행 컨텍스트는 콜 스택의 가장 하부에 존재하게 되기 때문에, 에러를 전파할 호출자가 존재하지 않는다.
```js
try {
  setTimeout(function() {
    foo(); // ReferenceError: foo is not defined (프로그램 종료)
  }, 1000);
} catch (error) {
  console.log(error); // 출력되지 않는다.
}
```
- `setTimeout`나 `Promise`의 콜백 함수의 예외를 잡으려면, 반드시 함수 내부에 `try...catch`를 구현해야 한다.
```js
setTimeout(function() {
  try {
    foo();
  } catch(error) {
     console.log(error); // ReferenceError: foo is not defined
  }
}, 1000);
```