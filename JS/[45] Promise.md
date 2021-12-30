## 🚨 콜백 패턴의 단점
아래 코드를 보자.

```js
let g = 0;

setTimeout(() => { g = 100; }, 0);

console.log(g); // 0
```

- setTimeout 함수의 콜백 함수에서 상위 스코프의 변수에 값을 할당했다. 결과는 100이 나와야 할 것 같지만 결과는 0이 나온다.

- `setTimeout` 함수를 호출하면 콜백 함수를 호출 스케줄링한 다음, 타이머 id를 반환하고 즉시 종료된다. `setTimeout` 함수의 콜백 함수는 `setTimeout` 함수가 종료된 이후에 호출되기 때문에, 콜백 함수에서 상위 스코프의 변수에 할당하면 기대한 대로 동작하지 않는다.

또 다른 예시를 보자. 아래 코드는 `get`요청을 위한 코드이다.
```js
const get = url => {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', url);
  xhr.send();
  // onload 이벤트 핸들러는 비동기로 동작한다.
  xhr.onload = () => {
    if (xhr.status === 200) {
      //이는 get 함수의 반환문이 아니다.
      return JSON.parse(xhr.response);
    }
  };
};

const response = get('https://jsonplaceholder.typicode.com/posts/1');
console.log(response); // undefined
```
- get 함수가 호출되면 `XMLHttpRequest` 객체를 생성하고 HTTP 요청을 전송한다. `xhr.onload` 이벤트 핸들러 프로퍼티에 이벤트 핸들러를 바인딩하고 종료한다. 로그에는 어떤 값이 출력될까?

- `xhr.onload` 이벤트 핸들러 프로퍼티에 바인딩한 이벤트 핸들러의 반환문은 `get` 함수의 반환문이 아니다. `get` 함수는 반환문이 생략되었기 때문에 암묵적으로 undefined를 반환한다.

- 비동기 함수는 함수 내부에 비동기로 동작하는 코드를 포함한 함수를 말한다. **비동기 함수를 호출하면 함수 내부의 비동기로 동작하는 코드가 완료되지 않았다 해도 기다리지 않고 즉시 종료된다. 즉 비동기 함수 내부의 비동기로 동작하는 코드는 비동기 함수가 종료된 이후에 완료된다.**

- 따라서 비동기 함수는 **상위 스코프의 변수에 할당할 수도 없고, 비동기 처리 결과를 외부에 반환할 수 없다.**

- 비동기 함수의 처리 결과에 대한 후속 처리는 비동기 함수 내부에서 수행해야 한다. 즉, 비동기 처리 결과에 대한 후속 처리를 수행하는 콜백 함수를 비동기 함수에 전달해야 한다.

```js
const get = url => {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', url);
  xhr.send();
  
  xhr.onload = () => {
    if (xhr.status === 200) {
      // 서버의 응답을 콜백 함수에 인수로 전달하면서 호출하여 응답에 대한 후속 처리를 한다.
      successCallback(JSON.parse(xhr.response));
    }
    else failureCallback(xhr.status);
  };
};

const response = get('https://jsonplaceholder.typicode.com/posts/1');
```

### 🚩 콜백 헬
다음의 예제를 보자.
```js
const get = (url, callback) => {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', url);
  xhr.send();
  
  xhr.onload = () => {
    if (xhr.status === 200) {
      callback(JSON.parse(xhr.response));
    }
  };
};

const url = 'https://jsonplaceholder.typicode.com';

get(`${url}/posts/1`, ({ userId }) => {
  console.log(userId); // 1
  
  get(`${url}/users/${userId}`, ({ userInfo }) => {
    console.log(userInfo); // {id: 1, name: "Leanne Graham", ...}
  });
});
```
- GET 요청을 통해 서버로부터 id가 1인 post응답을 취득하고 이 데이터를 사용하여 또다시 GET 요청을 한다.

- **처리 결과에 대한 후속 처리를 수행하는 비동기 함수가 또다시 비동기 함수를 호출해야 한다면, 콜백 함수 호출이 중첩되어 복잡도가 높아지는 현상이 발생한다. 이를 콜백 헬(callback hell)이라 한다.**
```js
get('/step1', a => {
  get('/step2/${a}', b => {
    get('/step3/${b}', c => {
      get('/step4/${c}', d => {
        console.log(d);
      });
     });
   });
 });
 ```
- 위의 예제가 콜백 헬이 발생하는 전형적인 사례다. 콜백 헬은 가독성을 나쁘게 하고 실수를 유발하는 원인이 된다.

### 🚩 에러 처리의 한계
- 또 다른 콜백 패턴의 문제점으로, 에러 처리가 곤란하다는 문제점이 있다.
```js
try {
  setTimeout(() => { throw new Error('Error!'); }, 1000);
} catch (e) {
  console.error('캐치한 에러', e);
}
```
- try 코드 블록 내에서 호출한 `setTimeout` 함수는 1초 후에 콜백 함수가 실행되도록 설정하고, 이후 콜백 함수는 에러를 발생시킨다. 하지만 이 에러는 catch 코드 블록에서 캐치되지 않는다.

- `setTimeout은` 비동기 함수이기 때문에 콜백 함수가 호출되는 것을 기다리지 않고 즉시 종료되어서 콜 스택에서 제거된다. 즉, `setTimeout` 함수의 콜백 함수가 실행될 때 `setTimeout` 함수는 이미 콜 스택에서 제거된 상태다. 

- **에러는 호출자 방향으로 전파된다.** `setTimeout` 함수의 콜백 함수를 호출한 것은 `setTimeout` 함수가 아니다. 그렇기 때문에 콜백 함수가 발생시킨 에러는 catch 블록에서 캐치되지 않는다.

## 🅿️ 프로미스의 생성
> - ES6에서는 프로미스(Promise) 를 도입해, 전통적인 콜백 패턴이 가진 단점을 보완하고 비동기 처리 시점을 보다 명확하게 표현할 수 있다.
> - `Promise` 생성자 함수를 new 연산자와 함께 호출하면 `Promise` 객체를 생성한다. `Promise`는 호스트 객체가 아닌 ECMAScript 사양에 정의된 표준 빌트인 객체다.
```js
const promise = new Promise((resolve, reject) => {
  // 프로미스 함수의 콜백 함수 내에서 비동기 처리를 수행한다.
  if(/* 비동기 처리 성공 */) {
     resolve('result');
  } else { /* 비동기 처리 실패 */
     reject('failure reason');
  }
});
```
- `Promise` 생성자 함수는 비동기 처리를 수행할 콜백 함수를 인수로 전달받는데, 이 콜백 함수는 `resolve`와 `reject` 함수를 인수로 전달받는다. 
- **비동기 처리가 성공하면 콜백 함수의 인수로 전달받은 `resolve` 함수를 호출하고, 처리가 실패하면 `reject` 함수를 호출한다.**

- 프로미스는 현재 비동기 처리가 어떻게 진행되고 있는지를 나타내는 상태 정보를 갖는다.

|프로미스의 상태 정보|의미|상태 변경 조건|
|:---:|:---:|:---:|
|`pending`|비동기 처리가 아직 수행되지 않은 상태|프로미스가 생성된 직후 기본 상태|
|`fulfilled`|비동기 처리가 수행된 상태(성공)|`resolve` 함수 호출|
|`rejected`|비동기 처리가 수행된 상태(실패)|`reject` 함수 호출|
|`settled`|	`pending`이 아닌 상태로 비동기 처리가 수행된 상태(성공/실패)|resolve 또는 reject 함수가 호출된 상태|

- 프로미스는 `pending` 상태에서 `settled` 상태로 변화할 수 있다. 하지만 일단 `settled`상태가 되면 다른 상태로 변화할 수 없다.

- 생성된 직후의 프로미스는 기본적으로 `pending` 상태이고, 이후 비동기 처리가 수행되면 비동기 처리 결과에 따라 프로미스의 상태가 변경된다.

![](https://images.velog.io/images/songjy377/post/09a01fb2-57fd-46b8-92fd-54ea8a05217a/image.png)

>- **처리 성공** : `resolve` 함수를 호출해 프로미스를 `fulfilled` 상태로 변경하고 처리에 대한 결과 값을 갖는다.
>
>![](https://images.velog.io/images/songjy377/post/d36f0c1f-dd3f-4cac-90f1-994a234bebc5/image.png)

> - **처리 실패** : `reject` 함수를 호출해 프로미스를 `rejected` 상태로 변경한다. 마찬가지로 처리 결과를 값으로 갖는다.
>
> ![](https://images.velog.io/images/songjy377/post/719abfe7-f4e4-4ee8-9278-aa3db3d50eda/image.png)

> **프로미스는 비동기 처리 상태와 처리 결과를 관리하는 객체다.**

## 🅿️ 프로미스의 후속 처리 메서드 
- 비동기 처리 결과에 대한 후속 처리는 `then`, `catch`, `finally`를 사용하여 수행한다.
- **프로미스의 비동기 처리 상태가 변화하면, 후속 처리 메서드에 인수로 전달한 콜백 함수가 선택적으로 호출된다.**

### Ⓜ️ `Promise.prototype.then`()

- then 메서드는 두 개의 콜백 함수를 인수로 받는다. 
- <span style="color:blue">**첫 번째 콜백 함수**</span>는 비동기 처리가 성공했을 때 호출되는 <span style="color:blue">**성공 처리 콜백 함수**</span>이고, 
- <span style="color:orange">**두 번째 콜백 함수**</span>는 비동기 처리가 실패했을 때 호출되는 <span style="color:orange">**실패 처리 콜백 함수**</span>다.

```js
new Promise(resolve => resolve('fulfilled'))
	.then(v => console.log(v), e => console.error(e)); // fulfilled

new Promise((_, reject) => reject(new Error('rejected')))
	.then(v => console.log(v), e => console.error(e)); // Error: rejected
```
- **then 메서드는 언제나 프로미스를 반환한다.** then 메서드의 콜백 함수가 프로미스를 반환하면 그 프로미스를 그대로 반환하고, 콜백 함수가 프로미스가 아닌 값을 반환하면 그 값을 암묵적으로 `resolve` 또는 `reject`하여 프로미스를 생성해 반환한다.

### Ⓜ️ `Promise.prototype.catch`()

- catch 메서드는 **한 개의 콜백 함수를 인수로 전달받는다.** 
- catch 메서드의 콜백 함수는 프로미스가 `rejected` 상태인 경우만 호출된다. then 메서드와 마찬가지로 **언제나 프로미스를 반환한다.**
```js
new Promise((_, reject) => reject(new Error('rejected')))
	.catch(e => console.error(e)); // Error: rejected

// catch 메서드를 호출하면 내부적으로 then을 호출한다. 위 예제는 내부적으로 다음과 같이 처리된다.
new Promise((_, reject) => reject(new Error('rejected')))
	.then(undefined, e => console.error(e)); // Error: rejected
```
### Ⓜ️ `Promise.prototype.finally`()

- finally 메서드는 **한 개의 콜백 함수를 인수로 전달받는다.** 
- finally 메서드의 콜백 함수는 **프로미스의 성공이나 실패와 상관없이 무조건 한 번 호출**된다. 
- 따라서 프로미스의 상태와 상관없이 **공통적으로 수행해야 할 처리 내용이 있을 때 유용하다.**
- finally 메서드도 **언제나 프로미스를 반환한다.**
```js
new Promise(() => {})
	.finally(() => console.log('finally')); // finally
```

> 프로미스를 이용하면 에러를 문제 없이 처리할 수 있다. catch 메서드를 사용하자. 
```js
promiseAjax('https://jsonplaceholder.typicode.com/todos/1')
  .then(res => console.xxx(res))
  .catch(err => console.error(err)); // TypeError: console.xxx is not a function
```

## 🅿️ 프로미스 체이닝
- `then`, `catch`, `finally` 후속 처리 메서드는 언제나 프로미스를 반환하기 때문에 연속적으로 호출할 수 있다. 이를 **프로미스 체이닝(promise chaining)**이라 한다.
```js
const url = 'https//jsonplaceholder.typicode.com';

const promiseGet = url => {
  return new Promise((resolve, reject) => {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', url);
    xhr.send();
  
    xhr.onload = () => {
      if (xhr.status === 200) {
        resolve(JSON.parse(xhr.response));
      } else {
        reject(new Error(xhr.status));
      }
  	};
  });
};

promiseGet(`${url}/posts/1`)
  //콜백 함수가 반환한 프로미스 반환
  .then(({ userId }) => promiseGet(`${url}/users/${userId}`))
  // 콜백 함수가 반환한 값을 resolve한 프로미스 반환
  .then(userInfo => console.log(userInfo))
  //에러가 발생하지 않으면 호출되지 않는다.
  .catch(err => console.error(err));  
```

- 프로미스는 프로미스 체이닝을 통해 비동기 처리 결과를 전달받아 후속 처리를 하기 때문에 비동기 처리를 위한 콜백 패턴에서 발생하던 콜백 헬이 발생하지 않는다. 다만 프로미스도 콜백 패턴을 사용하기 때문에 콜백 함수를 사용하지 않는 것은 아니다.

- ES8에서 도입된 async/await을 통해 후속 처리 메서드 없이 프로미스가 처리 결과를 반환하도록 구현할 수 있다. [async/await 포스트](https://velog.io/@songjy377/JS-%EC%A0%9C%EB%84%88%EB%A0%88%EC%9D%B4%ED%84%B0%EC%99%80-asyncawait)

## 🅿프로미스의 정적 메서드
- Promise는 주로 생성자 함수로 사용되지만, 함수도 객체이기 때문에 메서드를 가질 수 있다.

### Ⓜ️ `Promise.resolve` / `Promise.reject`

- 인수로 전달받은 값을 resolve/reject 하는 프로미스를 각각 생성한다. **이미 존재하는 값을 래핑해 프로미스를 생성하기 위해 사용**한다.
```js
const resolvedPromise = Promise.resolve([1, 2, 3]);
resolvedPromise.then(console.log); // [1, 2, 3]

const rejectedPromise = Promise.reject(new Error('Error!'));
rejectedPromise.catch(console.log); // Error: Error!
```
위 예제는 다음 예제와 동일하게 동작한다.
```js
const resolvedPromise = new Promise(resolve => resolve([1, 2, 3]));
resolvedPromise.then(console.log); // [1, 2, 3]

const rejectedPromise = new Promise((_, reject) => reject(new Error('Error!')));
rejectedPromise.catch(console.log); // Error: Error!
```

### Ⓜ️ `Promise.all`

- 프로미스를 요소로 갖는 배열 등의 이터러블을 인수로 전달받는다. 전달받은 모든 프로미스가 모두 fulfilled 상태가 되면 모든 처리 결과를 배열에 저장해 새로운 프로미스를 반환한다. **여러 개의 비동기 처리를 모두 병렬 처리할 때 사용**한다.
```js
const requestData1 = () => 
	new Promise(resolve => setTimeout(() => resolve(1), 3000));
const requestData2 = () => 
	new Promise(resolve => setTimeout(() => resolve(2), 2000));
const requestData3 = () => 
	new Promise(resolve => setTimeout(() => resolve(3), 1000));

// 3개의 비동기 처리를 병렬로 처리
Promise.all([requestData1(), requestData2(), requestData3()])
	.then(console.log) // [1, 2, 3] => 약 3초 소요
  .catch(console.error);
```
- 위 예제의 경우 모든 처리에 걸리는 시간은 가장 늦게 `fulfilled` 상태가 되는 첫 번째 프로미스의 처리 시간인 3초보다 조금 더 소요된다. **`Promise.all` 메서드는 전달받은 모든 프로미스가 `fulfilled` 상태가 되면 종료하기 때문이다.**

- 이때 첫 번째 프로미스가 가장 나중에 `fulfilled` 상태가 되어도 `Promise.all`메서드는 첫 번째 프로미스가 `resolve`한 처리 결과부터 차례대로 배열에 저장해 그 배열을 `resolve`하는 새로운 프로미스를 반환한다. **처리 순서가 보장**되는 것이다.

- 단, 전달받은 배열의 프로미스가 **하나라도 `rejected` 상태가 되면 나머지 프로미스가 fulfilled 상태가 되는 것을 기다리지 않고 즉시 종료**한다.

- 만약 인수로 전달받은 이터러블의 요소가 프로미스가 아니라면 `resolve` 메서드를 통해 프로미스로 래핑한다.

### Ⓜ️ `Promise.race`

- 프로미스를 요소로 갖는 배열 등의 이터러블을 인수로 전달받는다. **가장 먼저 `fulfilled` 상태가 된 프로미스의 처리 결과를 `resolve`하는 새로운 프로미스를 반환**한다.
```js
Promise.race([ 
	new Promise(resolve => setTimeout(() => resolve(1), 3000)), 
	new Promise(resolve => setTimeout(() => resolve(2), 2000)), 
	new Promise(resolve => setTimeout(() => resolve(3), 1000))
])
  .then(console.log); // 3 
```
- 전달된 프로미스가 **하나라도 `rejected` 상태가 되면 에러를 reject하는 새로운 프로미스를 즉시 반환**한다.

### Ⓜ️ `Promise.allSettled`

- 프로미스를 요소로 갖는 배열 등의 이터러블을 인수로 전달받는다. 전달받은 프로미스가 **모두 비동기 처리가 수행된 상태인 `settled` 상태(`fulfilled`/`reject` 상태)가 되면 처리 결과를 배열로 반환**한다.
```js
Promise.allSettled([ 
	new Promise(resolve => setTimeout(() => resolve(1), 2000)), 
	new Promise((_, reject) => setTimeout(() => reject(new Error('Error!')), 1000))
]).then(console.log); 
/*
[
  {status: "fulfilled", value: 1},
  {status: "rejected", reason: Eror: Error! at <anonymouss>:3:54}
]
*/
```
- 반환한 배열에는 `fulfilled`나 `rejected` 상태와는 상관없이 모든 프로미스 처리 결과가 모두 담겨 있다.
- `fulfilled` 상태인 경우 status 프로퍼티와 value 프로퍼티를 갖는다. `rejected` 상태인 경우에도 status 프로퍼티와 reason 프로퍼티를 갖는다.

## ♉ 마이크로태스크 큐
다음의 예제를 보자.
```js
setTimeout(() => console.log(1), 0);

Promise.resolve().then(() => console.log(2));
```
- 1 -> 2의 순으로 출력될 것처럼 보이지만 2 -> 1의 순으로 출력된다. **프로미스의 후속 처리 메서드의 콜백 함수는 태스크 큐가 아니라 마이크로태스크 큐(microtask queue/job queue)**에 저장되기 때문이다.

- **마이크로태스크 큐는 태스크 큐보다 우선순위가 높다.** 
- 이벤트 루프는 콜 스택이 비면 먼저 마이크로태스크 큐에서 대기하고 있는 함수를 가져와서 실행한다. 이후 마이크로태스크 큐가 비면 태스크 큐에서 대기하고 있는 함수를 가져와 실행한다.

## ♉ `fetch`
- `fetch` 함수는 HTTP 요청 전송 기능을 제공하는 클라이언트 사이트 Web API이다.
- `fetch` 함수에는 HTTP 요청을 전송할 URL과 HTTP 요청 메서드, HTTP 요청 헤더, 페이로드 등을 설정한 객체를 전달한다.
```js
const promise = fetch(url, [, options])
```
- **HTTP 응답을 나타내는 Response 객체를 래핑한 Promise 객체를 반환한다.**
```js
fetch('http//js.test.com//todos/1').then(response => console.log(response));
```
- `Response.prototype` Response 객체에 포함되어 있는 HTTP 응답 몸체를 위한 다양한 메서드를 제공한다. 예를 들어 MIME 타입이 application/json인 HTTP 응답 몸체를 취득하려면 `Response.prototype.json` 메서드를 사용한다. 이는 response body를 역직렬화한다.

- ❗`fetch` 함수를 사용할 때는 에러 처리에 주의해야 한다. 
  - 404, Not Found 같은 HTTP 에러를 reject 하지 않고, ok의 상태를 false로 설정한 객체를 resolve 한다. 
  - 오프라인 등의 네트워크 장애나, CORS 에러에 의해 요청이 완료되지 못한 경우에만 프로미스를 reject 한다.
```js
const URL = 'https:://js.test.com/xxx/1';

// 부적절한 URL이 지정되었으므로 Not Found 에러가 발생한다.
fetch(URL).then(reponse => {
  if(!response.ok) throw new Error(response.statusText);
  return response.json();
}).then(todo => console.log(todo))
.catch(err=>console.error(err));
```

### ♐ `fetch`를 이용한 HTTP 전송 예제
```js
const request = {
  get(url) {
    return fetch(url);
  },

  post(url, payload) {
    return fetch(url, {
      method:'POST',
      headers: {'content-Type':'application/json'},
      body: JSON.stringify(payload)
    });
  },
  patch(url, payload) {
    return fetch(url, {
      method:'patch',
      headers: {'content-Type':'application/json'},
      body: JSON.stringify(payload)
    })},
    delete(url) {
      return fetch(url, {method: DELETE});
      } 
};

//get
request.get('http//js.test.com//todos/1')
  .then(response => {
    if(!response.ok) throw new Error(response.statusText);
    return response.json();
}).then(todos => console.log(todos))
.catch(err => console.log(err));

//post
request.post('http//js.test.com//todos/1', {
  userId: 1,
  title: 'JavaScript',
  completed: false
}).then(response => {
  if(!response.ok) throw new Error(response.statusText);
  return response.json();
}).then(todos => console.log(todos))
.catch(err => console.log(err));

//patch
request.patch('http//js.test.com//todos/1', {
  completed: true
}).then(response => {
  if(!response.ok) throw new Error(response.statusText);
  return response.json();
}).then(todos => console.log(todos))
.catch(err => console.log(err));

//delete
request.delete('http//js.test.com//todos/1', {
}).then(response => {
  if(!response.ok) throw new Error(response.statusText);
  return response.json();
}).then(todos => console.log(todos))
.catch(err => console.log(err));
```