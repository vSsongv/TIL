# 비동기 프로그래밍
> JS에서의 동기, 비동기 처리를 알아본다.(211227)

[실행 컨텍스트](https://velog.io/@songjy377/JS-%EC%8B%A4%ED%96%89-%EC%BB%A8%ED%85%8D%EC%8A%A4%ED%8A%B8)에서 공부했듯이 함수의 실행 순서는 실행 컨텍스트 스택으로 관리하고, 함수가 실행되려면 함수 실행 컨텍스트가 실행 컨텍스트 스택에 푸시되어야 한다.

**JS 엔진은 단 하나의 실행 컨텍스트 스택을 갖기 때문에, 함수를 실행할 수 있는 창구가 단 하나밖에 없다.**

즉 JS 엔진은 한 번에 하나의 태스크만 실행할 수 있는 **싱글 스레드 방식으로 동작**한다. 싱글 스레드 방식은 한 번에 하나의 태스크만 실행할 수 있기 때문에 처리에 시간이 걸리는 태스크를 실행하는 경우 블로킹이 발생한다.

## 🔧 동기 처리
- **현재 실행 중인 태스크가 종료할 때까지 다음 태스크가 대기하는 방식**을 동기(synchronous) 처리라고 한다.
- 태스크가 순서대로 **하나씩** 처리되므로 순서가 보장된다는 장점은 있지만, 그 태스크가 종료할 때까지 이후 태스크들이 실행되지 않는다는 단점이 있다.
```js
// sleep 함수는 delay 시간이 경과한 이후에 콜백 함수를 호출한다.
function sleep(func, delay) {
  const delayUntil = Date.now() + delay;
  while(Date.now() < delayUntil);
  func();
}

function foo() {
  console.log('foo');
}

function bar() {
  console.log('bar');
}
// sleep 함수는 3000초 이상 실행된다.
sleep(foo, 3000);
// bar함수는 sleep 함수의 실행이 종료된 이루에 호출되므로 3초 이상 블로킹된다.
bar();
```
![](https://images.velog.io/images/songjy377/post/4111ebd3-e1b8-4c83-9ad1-ae559391ab76/image.png)

## 🛠️ 비동기 처리
- **현재 실행 중인 태스크가 종료되지 않아도 다음 태스크를 곧바로 실행하는 방식**을 비동기(asynchronous) 처리 라고 한다.
- 현재 실행 중인 태스크가 종료되지 않은 상태라 해도 다음 태스크를 곧바로 실행하기 때문에 블로킹이 발생하지 않는다는 장점이 있지만, 태스크의 실행 순서가 보장되지 않는 단점이 있다.
- 위 예제를 타이머 함수를 사용해 수정해보면,
```js
function foo() {
  console.log('foo');
}

function bar() {
  console.log('bar');
}

setTimeout(foo, 3000);

bar();
```
- `setTimeout` 함수는 앞선 `sleep` 함수와 달리, `setTimeout` 함수 이후의 태스크를 블로킹하지 않고 곧바로 실행한다.
![](https://images.velog.io/images/songjy377/post/a34cf621-95d1-41d5-810e-a0ddb7f89010/image.png)
✅ `setTimeout`, `setInterval`, HTTP 요청, 이벤트 핸들러(_커스텀 이벤트 디스패치, click, blur, focus 제외_)는 비동기 처리 방식으로 동작한다.

❓ JS는 싱글 스레드라고 했는데, 어떻게 비동기로 처리되는 것일까?

## 🚥 태스크 큐와 이벤트 루프
- JS 엔진은 크게 2개의 영역으로 구분할 수 있다.

✅ **콜 스택**
- **실행 컨텍스트 스택이 바로 콜 스택이다**. 함수를 호출하면 함수 실행 컨텍스트가 순차적으로 콜 스택에 푸시되어 순차적으로 실행된다.
- JS 엔진은 단 하나의 콜 스택을 사용하기 때문에 최상위 실행 컨택스트(실행 중인 컨택스트)가 종료되어 스택에서 제거되기 전까지는 다른 어떤 태스크도 실행되지 않는다.

✅ **힙**
  - 힙은 **객체가 저장되는 메모리 공간**이다. 콜 스택의 요소인 실행 컨텍스트는 힙에저장된 객체를 참조한다. 
  - 객체는 런타임에 메모리 공간을 동적 할당하므로, 객체가 저장되는 공간인 힙은 구조화되어 있지 않다.
  
![](https://images.velog.io/images/songjy377/post/caf0ffb4-eb4a-49b6-a323-e7be9d31a695/image.png)

> 비동기 처리에서 소스코드의 평가와 실행을 제외한 모든 처리는 **브라우저, 또는 Node.js가 담당한다.**

- 예를 들어, 비동기 방식으로 동작하는 콜백 함수의 평가와 실행은 JS 엔진이 담당하지만, **호출 스케줄링을 위한 타이버 설정과 콜백 함수의 등록은 브라우저, 또는 Node.js가 담당한다.**
👉🏻 이를 위해 브라우저는 **태스크 큐와 이벤트 루프를 제공한다.**

🚨 **태스크 큐(task queue/event queue/callstack queue)**
- `setTimeout`이나 `setInterval`같은 비동기 함수의 콜백 함수나 이벤트 핸들러가 일시적으로 보관되는 영역이다.

🚨 **이벤트 루프(event loor)**
- JS의 동시성을 지원하는 것이 **이벤트 루프**이다.
- 이벤트 루프는 브라우저에 내장되어 있는 기능 중 하나다.
- 이벤트 루프는 콜 스택에 현재 실행 중인 실행 컨텍스트가 있는지, 태스크 큐에 대기 중인 함수가 있는지 반복해서 확인한다.
- 만약 **콜 스택이 비어있고, 태스크 큐에 대기 중인 함수가 있다면 이벤트 루프는 순차적으로(FIFO) 태스크 큐에 대기 중인 함수를 콜 스택으로 이동시킨다.**

즉, 위쪽 코드에서 `setTimeout` 함수의 타이머가 완료되면 콜백 함수를 태스크 큐에 등록하는 처리는 자바스크립트 엔진이 아니라 **브라우저가 실행한다**. 자바스크립트 엔진은 단순히 태스크가 요청되면 콜 스택을 통해 요청된 작업을 순차적으로 실행할 뿐이다.

**setTimeout의 콜백 함수는 태스크 큐에 푸시되어 대기하다가 콜 스택이 비게 되면, 콜 스택에 푸시되어 실행하게 된다.**

아래 코드를 보자.

```js
function func1() {
  console.log('func1');
  func2();
}

function func2() {
  setTimeout(function() {
    console.log('func2');
  }, 0); // 0초로 설정

  func3();
}

function func3() {
  console.log('func3');
}

func1(); // ?
```
- `setTimeout` 함수는 비동기 함수이기 때문에, 지연 시간
(delay)을 0초로 설정해도 `func1 func2 func3`의 순서로 출력되지 않는다.

- 지연 시간은 0이지만 콜 스택이 비어야 호출되므로 약간의 시간차가 발생할 수 있기 때문이다. 즉, 정확히 지연 시간 후에 호출된다는 보장은 없다.

- `func1` 함수가 호출되면 콜 스택에 쌓인다. `func1` 함수는 `func2` 함수를 호출해 `func2` 함수가 콜 스택에 쌓이고 `setTimeout` 함수가 호출된다. 

- 브라우저는 타이머를 설정하고 타이머의 만료를 기다린다. 이 타이머가 만료되면 tick event가 발생한다. 이때 브라우저는 `setTimout`의 콜백함수를 태스크 큐에 푸시한다.

- 즉 `setTimeout`의 콜백 함수는 즉시 실행되지 않고 지정 대기 시간만큼 기다리다가, 시간이 끝나면 태스크 큐로 이동한 후 콜 스택이 비어졌을 때 콜 스택으로 이동되어 실행된다. 즉, 위의 예제의 결과는 `func1 func3 func2` 순으로 출력된다.

![](https://images.velog.io/images/songjy377/post/3fd208f7-7fc1-49dc-b358-7418fda32063/event-loop.gif) <출처 [poiemaweb](https://poiemaweb.com/js-async)>

> 즉, JS엔진은 싱글 스레드이지만 브라우저는 멀티 스레드이다. JS와 브라우저는 협업하여 비동기 함수를 실행한다. 

- 브라우저는 자바스크립트 엔진 외에도 렌더링 엔진과 Web API를 제공하는데, Web API에는 DOM API와 타이머 함수, HTTP 요청(Ajax)과 같은 비동기 처리를 포함한다.
