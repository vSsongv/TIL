# 스프레드 문법 & 디스트럭처링 할당
> 스프레드 문법과 디스트럭처링 할당에 대해 다룬다.(211207)

## ➿ 스프레드 문법
>- ES6에서 도입된 스프레드 문법(spread syntax) ... 는 하나로 뭉쳐 있는 여러 값들의 집합을 펼쳐서 **개별적인 값들의 목록**으로 만든다.

```js
   console.log(...[1, 2, 3]); //1 2 3
```
- **스프레드 문법을 사용할 수 있는 대상은 순회할 수 있는 이터러블에 한정된다.**
- ❗ **스프레드 문법의 결과는 값이 아니다. 즉 스프레드 문법은 값을 생성하는 연산자가 아니다. 따라서 스프레드 문법의 결과는 변수에 할당할 수 없다.**
```js
  const list = ... [1, 2, 3]; // ❌ SyntaxError
```

### ➿ 함수 호출문의 인수 목록에서 사용하는 경우
```js
const arr = [1, 2, 3];

Math.max(...arr); // 3
```

>📌 **Rest 파라미터 vs 스프레드 문법**
>- Rest 파라미터는 함수에 전달된 인수들의 목록을 배열로 전달받기 위해 매개변수 이름 앞에 ...을 붙이는 것이다. 스프레드 문법은 여러 개의 값이 하나로 뭉쳐 있는 배열과 같은 이터러블을 펼처서 개별적인 값들의 목록을 만드는 것이다. 따라서 Rest 파라미터와 스프레드 문법은 서로 반대의 개념이다.

### ➿ 배열 리터럴 내부에서 사용하는 경우
☑️ 2개의 배열을 1개의 배열로 결합할 수 있다.
```js
	const arr = [...[1, 2], ...[3, 4]];
	console.log(arr); // [1, 2, 3, 4]
```
☑️ `splice` 함수로 새로운 배열을 만들 때 활용할 수 있다.
```js
	const arr1 = [1, 4];
	const arr2 = [2, 3];
	// arr1.splice(1, 0, arr2); // 배열 자체가 들어갔다. [1, [2, 3], 4]
	arr1.splice(1, 0, ...arr2); // ES6 
	console.log(arr1); //배열 내부의 값들이 들어갔다. [1, 2, 3, 4]
```
☑️ 스프레드 문법을 이용해 배열을 _얕은 복사_할 수 있다.
```js
const origin = [1, 2];
const copy = [...origin];

console.log(copy); // [1, 2]
console.log(copy === origin); // false
```

### ➿ 이터러블을 배열로 변환
```js
function sum() {
  //이터러블이면서 유사 배열 객체인 arguments를 배열로 반환
  return [...arguments].reduce((pre, cur) => pre + cur, 0);
}

console.log(sum(1, 2, 3)); //6
```
- Rest 파라미터를 이용하는 것이 더 좋다. 
```js
//Rest 파라미터는 args는 함수에 전달된 인수들의 목록을 배열로 전달받는다.
const sum = (...args) => args.reduce((pre, cur) => pre + cur, 0);

console.log(sum(1, 2, 3,)); // 6
```
>❗ **이터러블이 아닌 유사 배열 객체는 스프레드 문법의 대상이 될 수 없다.**
>✔️ 그러나 ES6에서 도입된 `Array.from` 메서드를 사용하여 이터러블이 아닌 유사 배열 객체를 배열로 변경 할 수 있다.

### ➿ 객체 리터럴 내부에서 사용하는 경우
```js
// 객체 병합. 프로퍼티가 중복되는 경우 뒤에 위치한 프로퍼티가 우선권을 갖는다.
const merged = { ... { x:1, y:2 }, ... { y:10, z:3} };
console.log(merged); // { x:1, y:10, z:3 }

// 특정 프로퍼티 변경
const changed = { ... {x: 1, y: 2 }, y: 100 };
console.log(changed); // { x:1, y:100 }

// 프로퍼티 추가
const added = { ... {x:1, y:2}, z: 0};
console.log(added) // { x:1, y:2, z:0 }
```

## ✅ 디스트럭처링 할당
> - 구조화된 배열과 같은 이터러블 또는 객체를 비구조화 하여 1개 이상의 변수에 개별적으로 할당하는 것을 말한다. 
> - 배열과 같은 이터러블 또는 객체 리터럴에서 필요한 값만 추출하여 변수에 할당할 때 유용하다.

### ✅ 배열 디스트럭처링 할당
- **배열 디스트럭처링 할당의 대상(할당문의 우변)은 이터러블이어야 하며, 할당 기준은 인덱스다.**
```js
 const arr = [1, 2, 3];

 const [one, two, three] = arr; //1 2 3

 //요소 개수가 일치하지 않아도 된다.
 const [a, b] = arr; //1 2
 //기본값을 설정할 수 있다.
 const [c, d = 3] = [1]; //1 3

 //Rest 요소를 사용할 수 있다.
 const [x, ...y] = [1, 2, 3];
 console.log(x, y); 1 [2, 3]
```

### ✅ 객체 디스트럭처링 할당
- 프로퍼티 값만 추출하여 변수에 할당하고 싶을 때 유용하다.
- **할당 기준은 프로퍼티 키다**.
```js
const user = { firstName: 'Jinyoung', lastName: 'Song' };
// 이때 프로퍼티 키를 기준으로 디스트럭처링 할당이 이루어진다. 순서는 의미가 없다.
const { lastName, firstName } = user;

console.log(firstName, lastName); // Jinyoung Song

// 프로퍼티 키와 다른 이름으로 프로퍼티 값을 할당받을 수 있다.
const { lastName: ln, firstName: fn } = user;

console.log(ln, fn) //Jinyoung Song
```
- 변수에 기본값을 설정할 수 있다.
```js
const { firstName = 'Jinyoung', lastName } = { lastName = 'Ssong' }; //Jinyoung Ssong
```
- 배열의 요소가 객체인 경우 배열 디스트럭처링 할당과 객체 디스트럭처링 할당 혼용할 수 있다. 

```js
 const todos = [
     {id: 1, content: 'HTML', completed: true}
     {id: 2, content: 'JS', completed: false}
 ]

 //todo 배열의 두 번째 요소인 객체로부터 id 프로퍼티만 추출한다.
 const [, {id}] = todos;
 console.log(id); //2
```
- 중첩 객체인 경우 다음과 함께 사용한다.
```js
const user = {
    name: 'Ssong',
    address: {
        city: 'seoul'
    }
};

 //adress 프로퍼티 키로 객체를 추출하고 이 객체의 city 프로퍼티 키로 값을 추출한다.
 const { address: { city } } = user;
 console.log(city); //seoul

 //Rest 프로퍼티 ...을 사용할 수 있다.
 const { x, ...rest } = { x: 1, y: 2, z: 3 };
 console.log(x, rest) = 1, { y: 2, z: 3 }
```

_<모던 자바스크립트 deepdive를 읽고 정리한 내용입니다.>_