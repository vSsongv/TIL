# Timer
> JS의 timer 함수와, timer 함수를 이용한 debounce, throttle에 대해 다룬다. (211227)

## ⏲️ Timer 함수
일정 시간이 지난 후에 함수를 호출하고 싶을 때, 즉 함수 호출을 예약하려면 타이머 함수를 사용한다. 
이를 호출 스케줄링이라고 한다.
`setTimeout, setInterval ` 로는 타이머를 생성하고, `clearTimeout, clearInterval` 으로는 타이머를 제거할 수 있다. 타이머 함수는 ECSMAScript에 정의된 빌트인 함수가 아닌, 호스트 객체이다.

### ⌚ `setTimeout`
- `setTimeout` 함수는 두 번째 인수로 전달받은 시간(ms, 1/1000초)으로 **단 한 번** 동작하는 타이머를 생성한다. 이후 타이머가 만료되면 첫 번째 인수로 전달받은 콜백함수가 호출된다.
```js
// 1초 후 콜백 함수 호출
setTimeout(() => console.log('Hi!'), 1000);
```
- 콜백 함수에 전달해야할 인수가 존재하는 경우 세 번째 이후의 인수로 전달할 수 있다. (단, IE9 이하에서는 콜백 함수에 인수를 전달할 수 없다.)
```js
// 1초 후 콜백 함수 호출
// 콜백 함수에 'Lee'가 인수로 전달된다.
setTimeout(name => console.log(`Hi! ${name}.`), 1000, 'Lee');
```
- 두 번째 인수(delay)를 생략하면 기본값 0이 지정된다.
```js
setTimeout(() => console.log('Hello!'));
```
- `setTimeout` 함수는 생성된 타이머를 식별할 수 있는 고유한 타이머 id를 반환한다. 타이머 id는 <span style="color:blue">브라우저 환경</span>인 경우에는 <span style="color:blue">숫자</span>이고, <span style="color:#00CC00">Node.js 환경</span>인 경우에는 <span style="color:#00CC00">객체</span>다.

### ⌚ `clearTimeout`
- `setTimeout` 함수가 반환한 타이머 id를 `clearTimeout` 함수의 인수로 전달해 타이머를 취소할 수 있다.
```js
// 고유한 타이머 id를 반환된다.
const timerId = setTimeout(() => console.log('Hi!'), 1000);
// 고유 id를 clearTimeout 함수에 인수로 전달하면 타이머가 취소된다.
clearTimeout(timeout);
```

### ⌚ `setInterval` / `clearInterval`
- `setInterval` 함수는 두 번째 인수로 전달받은 시간으로 **반복 동작**을 하는 타이머를 생성한다. 
- 이후 타이머가 취소될 때까지 타이머가 만료될 때마다 첫 번째 인수로 전달받은 콜백 함수를 반복 호출한다.
- `setInterval` 함수 역시 생성된 타이머를 식별할 수 있는 고유한 타이머 id를 반환하며, `clearInterval` 함수의 인수로 전달해 타이머를 취소할 수 있다.
```js
let count = 1;

const timeoutId = setInterval(() => {
  console.log(count); // 1 2 3 4 5
  if(count++ === 5) clearInterval(timeoutId);
}, 1000);
```

## ⏲️ debounce & throttle
scroll이나 resize, input, mousemove 같은 이벤트는 짧은 시간 간격으로 연속해서 발생하기 때문에, 이러한 이벤트에 이벤트 핸들러를 바인딩할 경우 과도하게 호출되어 성능에 문제가 생길 수 있다.

디바운스와 스로틀은 짧은 시간 간격으로 연속해서 발생하는 이벤트를 그룹화해서 과도한 이벤트 핸들러의 호출을 방지하는 방법이다. 타이머 함수를 사용해 구현할 수 있다.

### ⌚ debounce(디바운스)
- 짧은 시간 간격으로 이벤트가 연속해서 발생하면, 이벤트 핸들러를 호출하지 않다가 **일정 시간이 경과한 이후에 이벤트 핸들러가 한 번만** 호출되도록 한다. 
- 입력이 끝날때까지 무한정 대기한다.

```html
<!DOCTYPE html>
<html>
<body>
  <input type="text">
  <div class="msg"></div>
  <script>
    const $input = document.querySelector('input');
    const $msg = document.querySelector('.msg');
    
    const debounce = (callback, delay) => {
      let timerId;
      // debounce 함수는 timerId를 기억하는 클로저를 반환한다.
      // delay 시간이 경과하기 이전에 이벤트가 발생하면 이전 타이머를 취소하고, 새로운 타이머를 재설정한다.
      // 즉 delay보다 짧은 간격으로 이벤트가 발생하면 callback은 호출되지 않는다.
      return event => {
    	if (timerId) clearTimeout(timerId);
    	timerId = setTimeout(callback, delay, event);
      };
    };
    
    // debounce 함수가 반환하는 클로저가 이벤트 핸들러로 등록된다.
    // 300ms 동안 이벤트가 발생하지 않으면 한 번 호출된다.
    $input.oninput = debounce(e => {
      $msg.textContent = e.target.value;
    }, 300);
  </script>
</body>
</html>
```

- debound는 **resize 이벤트 처리**나 input에 입력한 값으로 ajax 요청을 하는 **자동완성 처리**, **버튼 중복 클릭 방지 처리** 등에 사용된다.

- 아래 예시 코드는 입력이 진행되다가, 사용자가 입력을 멈추었을 때 ajax를 요청하도록 한 예시이다.

<iframe height="300" style="width: 100%;" scrolling="no" title="Debouncing keystrokes Example" src="https://codepen.io/jaehee/embed/JwKMGw?default-tab=html%2Cresult" frameborder="no" loading="lazy" allowtransparency="true" allowfullscreen="true">
  See the Pen <a href="https://codepen.io/jaehee/pen/JwKMGw">
  Debouncing keystrokes Example</a> by jaeheekim (<a href="https://codepen.io/jaehee">@jaehee</a>)
  on <a href="https://codepen.io">CodePen</a>.
</iframe>

&lt;출처: https://webclub.tistory.com/607>

### ⌚ throttle(스로틀)
- 짧은 시간 간격으로 이벤트가 연속해서 발생하더라도, 일정 시간 간격으로 이벤트 핸들러가 최대 한 번만 호출되도록 한다.
- 입력이 시작되면 일정 주기를 계속 실행한다.
```html
<!DOCTYPE html>
<html>
<head>
  <style>
    .container {
      width: 300px;
      height: 300px;
      background-color: rebeccapurple;
      overflow: scroll;
    }
    .content {
      width: 300px;
      height: 1000vh;
    }
  </style>
</head>
<body>
  <div class="container">
    <div class="content"></div>
  </div>
  <div>
    스로틀 이벤트 핸들러가 scroll 이벤트를 처리한 횟수:
    <span class="throttle-count">0</span>
  </div>
  <script>
    const $container = document.querySelector('.container');
    const $throttleCount = document.querySelector('.throttle-count');
    
    const throttle = (callback, delay) => {
      let timerId;
      // delay 시간이 경과하기 전에 이벤트가 발생하면 아무것도 하지 않다가 delay 시간이 경과했을 때 이벤트가 발생하면 콜백 함수를 호출하고 새로운 타이머를 재설정한다. 
      // 즉, delay 시간 간격으로 콜백 함수가 호출된다.
      return event => {
    	if (timerId) return;
    	timerId = setTimeout(() => {
    	  callback(event);
    	  timerId = null;
    	}, delay, event);
      };
    };
    
    let throttleCount = 0;
    // throttle 함수가 반환하는 클로저가 이벤트 핸들러로 등록된다.
    $container.addEventListener('scroll', throttle(() => {
      $throttleCount.textContent = ++throttleCount;
    }, 100));
  </script>
</body>
</html>
```
- throttle은 **scroll 이벤트 처리**나 **무한 스크롤 UI** 구현 등에 사용된다.

### ✅ Lodash, Underscore 라이브러리
- Lodash, Underscore 라이브러리에서 debounce와 throttle 함수를 제공한다.
```js
import { debounce } from 'lodash';

_.debounce(func, [wait=0], [options={}])

document.getElementById('input').onkeyup = _.debounce(function() {
  document.getElementById('debounced').innerHTML = 'Debounced message!';
}, 500);

---------------------------------------------------------------------

_.throttle(func, [wait=0], [options={}])

window.onscroll = _.throttle(function() {
  var doc = document.body;
  if (doc.scrollTop > doc.scrollHeight - 1000) {
	appendCards(10);
  }
}, 500);
```