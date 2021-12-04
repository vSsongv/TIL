# Shallow Copy & Deep Copy
> Shallow Copy와 Deep Copy에 대해 조사해 보았다.(211205)

❓ 개발을 하다 보면 객체를 복사해서 사용해야 할 일들이 생긴다. 이때 분명 복사한 값만 변경했음에도 origin 객체의 값도 변경된 상황들이 발생한다. 

-> 객체가 얕은 복사가 되었기 때문이다. 지금부터 얕은 복사, 깊은 복사에 대해 알아보자.

- JS에서 타입은 값을 변경할 수 없는 원시 타입과 값 변경이 가능한 객체 타입으로 나뉜다.
a라는 객체를 b라는 객체에 할당하는 경우 어떤 일이 일어날까? 
```js
let a = {};
let b = a;
```
b에는 a가 가지고 있던 **객체의 참조값**이 복사되므로, 즉 두 a,b 모두 같은 객체를 바라보게 된다. -> **이를 얕은 복사라 한다.**

## 🖨️ shallow copy (얕은 복사)
>- 얕은 복사란 객체를 복사할 때, 복사 대상의 변수와 복사된 변수가 모두 같은 참조를 가리키고있는 것을 말한다. 
>- 객체안에 객체가 있을 경우 한개의 객체라도 원본 객체를 참조하고 있다면 이를 얕은 복사라고 한다.

```js
const person = {
  age: 24,
  name: {
    first: 'Ssong',
    last: 'Jinyoung'
  }
};
```
위와 같은 코드가 있다고 할 때, 얕은 복사를 진행해 보자.
### 📃 대입 연산자 `=`
> - 가장 기본적인 얕은 복사이다. person2의 값을 변경하면 person의 값도 동시에 변경된다.

```js
const person2 = person;

person2.age = 30;
person2.name.first = 'Kim';

console.log(person.age); // 30 
console.log(person2.age); // 30
```
### 📃`Object.assign()`
- `Object.assign(target, ...sources))` 은 만약 객체 내부의 단순 값의 경구  깊은 복사를 할 수 있다. 
- 그러나 객체 내부의 객체까지는 깊은 복사를 진행하지 못한다
```js깊은 복사(deep copy)
const obj = { gender: 'female' };
const person2 = Object.assign(obj, person);

person2.age = 30;
person2.name.first = 'Kim';

console.log(person.age); // 24 -> 깊은 복사가 진행되어 값이 변경되지 않았다.
console.log(person2.age); // 30
console.log(person.name.first); // Kim -> 객체 내부의 객체이므로 값이 변경되었다.
console.log(person2.name.first); // Kim
```

### 📃 ES6 Spread Operator
- spread operator로 얕은 복사를 할 수 있다.
- Object.assign과 동일하게 내부 객체까지는 복사하지 못한다.
```js
const person2 = { ...person };

person.age = 25;
person.name.first = 'Hana';

console.log(person.age); // 24 -> 깊은 복사가 진행되어 값이 변경되지 않았다.
console.log(person2.age); // 30
console.log(person.name.first); // Kim -> 객체 내부의 객체이므로 값이 변경되었다.
console.log(person2.name.first); // Kim
```
## 🖨️ deep copy (깊은 복사)
> - 깊은 복사는 객체안에 객체가 있을 경우에도 원본과의 참조가 완전히 끊어진 객체를 말한다.

```js
const person = {
  age: 24,
  name: {
    first: 'Ssong',
    last: 'Jinyoung'
  }
};
```
위와 같은 코드가 있다고 할 때, 깊은 복사를 진행해 보자.
### 📃 `JSON.parse(JSON.stringify(object))`
> - `JSON.stringify` 함수는 자바스크립트 객체를 JSON 문자열로 변환한다.
> - `JSON.parse`는 JSON 문자열을 자바스크립트 객체로 변환한다.
> -  객체를 문자열로 변환한 후 다시 객체로 변환해주므로, 기존의 연결되어 있던 참조가 사라진다.
- ❗ 단 문제점이 존재한다.
  - 속도가 느리다.
  - 함수, Data 객체, 정규식 등의 데이터는 복사되지 않는다.

```js
const person2 = JSON.parse(JSON.stringify(person));

person .age = 25;
person .name.first = 'Hana';

console.log(person2); // { age: 25, name: { first: "Hana", last: "Jinyoung" }};
console.log(person); // { age: 20, name: { first: "Ssong", last: "Jinyoung" }, sayHello: f  };
```

### 📃 `Lodash - clonedeep`
> - lodash 모듈의 cloneDeep() 메소드를 이용하여 객체의 깊은 복사가 가능하다. 
> - **가장 손쉽게 객체의 깊은 복사를 할 수 있는 방법**이다.

```js
const person2 = lodash.cloneDeep(person);

person .age = 25;
person .name.first = 'Hana';

console.log(person === person2); //false
console.log(person.x === person2.x); //false -> 완전히 다른 객체이기 때문.
```

📌 의도치 않은 원본을 수정하지 않을 수 있도록 deep copy를 잘 사용하도록 하자.
