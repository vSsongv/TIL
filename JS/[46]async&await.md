# async & await
> async와 await 개념에 대해 다룬다.(220102)

- ES6에서 도입된 코드 블록의 실행을 일시 중지했다가 필요한 시점에 재개할 수 있는 특수한 함수인 제너레이터를 사용해서 비동기 처리를 동기 처리처럼 동작하도록 했지만, ES8에 제너레이터보다 간단하고 가독성 좋게 동기 처리처럼 동작하도록 구현할 수 있는 **async/await**가 도입되었다.

- async/await은 **프로미스 기반으로 동작**하고, 프로미스의 `then/catch/finally` 메서드 없이 동기 처리처럼 프로미스가 처리 결과를 반환하도록 구현할 수 있다. 즉 비동기 처리 결과를 후속 처리 메서드 없이 동기 처리처럼 프로미스가 처리 결과를 반환하도록 할 수 있다.

### 🔰 `async`
- `async` 함수는 `async` 키워드를 사용해 정의하며, 언제나 프로미스를 반환한다.
- 명시적으로 프로미스를 반환하지 않더라도 암묵적으로 반환값을 `resolve`하는 프로미스를 반환한다.

```js
// 함수 선언문
async fuction foo(n) { return n; }
foo(1).then(v => console.log(v)); //1

// async 함수 표현식
const bar = async function (n) { return n; }
bar(2).then(v => console.log(v)); //2

// async 화살표 함수
const baz = async n => n;
bar(3).then(v => console.log(v)); //3

// async 메서드
const obj = {
  async foo(n) { return n; }
};
obj.foo(3).then(v => console.log(v)); //4

//async 클래스 메서드
class MyClass {
  async bar(n) { return n; }
}
const mc = new MyClass();
mc.bar(5).then(v => console.log(v));
```
- 클래스의 construct 메서드는 인스턴스를 반환해야 하지만 async 함수는 언제나 프로미스를 반환해야 한다.
```js
class MyClass {
  async constructor() { } //syntaxError
}
const mc = new MyClass();
```
### 🔰 `await`
- `await` 키워드는 프로미스가 `settled` 상태가 될 때까지 대기하다가, `settled` 상태가 되면 프로미스가 `resolve`한 처리 결과를 반환한다.
- **await 함수는 반드시 async 함수 내부에서 사용해야 하고, 반드시 프로미스 앞에서 사용해야 한다.**
```js
// Node.js 환경에서 window.fetch 함수를 사용하기 위함
const fetch = require('node-fetch');

const getGithubUserName = async id => {
  const res = await fetch(`https://api.github.com/users/${id}`);
  const { name } = await res.json();
  console.log(name); // jy Ssong
};

getGithubUserName('vSsongv');  
```
- `fetch` 함수가 비동기 처리가 완료될 때까지 대기하다가, 완료되면 `resolve`한 처리 결과가 `res` 변수에 할당된다. 
- `await` 키워드는 다음 실행을 일시 중지시켰다가 프로미스가 `settled` 상태가 되면 다시 재개하는 것이다.
- 모든 프로미스에 `await` 키워드를 사용할 때, 서로 연관이 없이 개별적으로 수행되는 비동기 처리라면 순차적으로 처리할 필요가 없다.

```js
async function foo() {
  const res = await new Promise.all([
    new Promise(resolve => setTimeout(() => resolve(1), 3000)),
    new Promise(resolve => setTimeout(() => resolve(3), 3000)),
    new Promise(resolve => setTimeout(() => resolve(2), 3000)),
  ]);

  console.log([a, b, c]); //[1,2,3]
}

foo(); //약 3초 소요된다.
```
- 그러나 다음의 `bar`함수같은 경우 비동기 처리의 함수 결과가 보장되어야 하므로 모든 프로미스에 `await` 키워드를 써서 순차적으로 처리해야 한다.
```js
async function bar() {
  const a = await new Promise(resolve => setTimeout(() => resolve(1), 3000));
  const b = await new Promise(resolve => setTimeout(() => resolve(a + 1), 3000));
  const c = await new Promise(resolve => setTimeout(() => resolve(b + 1), 3000));
  
  console.log([a, b, c]); //[1,2,3]
}

bar(); //약 6초 소요된다.
```

### 🔰 에러 처리
- 비동기 처리를 위한 전통적인 콜백 패턴은 에러 처리가 곤란하다는 점이 있었다.
```js
try {
  setTimeout(() => { throw new Error('Error!'); }, 1000);
} catch (e) {
  // 에러를 캐치하지 못한다.
  console.error('캐치한 에러', e);
}
```
- 하지만 `async/await`에서 에러 처리는 `try...catch` 문을 사용할 수 있다. 
- **프로미스를 반환하는 비동기 함수는 명시적으로 호출할 수 있기 때문에 호출자가 명확하다.**
```js
const fetch = require('node-fetch');

const foo = async () => {
  try {
    const wrongUrl = 'https://wrong.url';
    
    const response = await fetch(wrongUrl);
    const data = await reponse.json();
    console.log(data);
  } catch (err) {
    console.error(err); // TypeError: Failed to fetch
  }
};

foo();
```
- **`foo` 함수의 `catch` 문은 `HTTP` 통신에서 발생한 네트워크 에러뿐 아니라 `try` 코드 블록 내의 모든 문에서 발생한 에러까지 모두 캐치할 수 있다.**

- `catch` 문을 사용해 에러 처리를 하지 않으면 `async` 함수는 에러를 `reject`하는 프로미스를 반환하기 때문에, `Promise.prototype.catch` 메서드를 사용해 에러를 캐치할 수도 있다.
```js
const fetch = require('node-fetch');

const foo = async () => {
  const wrongUrl = 'https://wrong.url';
    
  const response = await fetch(wrongUrl);
  const data = await reponse.json();
  return data;
};

foo()
  .then(console.log)
  .catch(console.error); // TypeError: Failed to fetch
  ```