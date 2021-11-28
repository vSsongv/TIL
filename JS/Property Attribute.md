# Property Attribute
> Property Attribute에 대해 다룬다. (211129)

❓ 내부 슬롯과 내부 메소드
- ECMAScript 에서 사용하는 의사 프로퍼티와 의사 메소드.
- 이중 대괄호 ([[....]])로 감싼 이름들이다.
- 개발자가 직접적으로 접근할 수 없다.
- 모든 객체는 [[Prototype]] 이라는 내부 슬롯을 갖는다. -> \__proto__로 접근할 수 있다.

## ✅ 프로퍼티 어트리뷰트
> - 자바스크립트 엔진은 프로퍼티를 생성할 때 **프로퍼티의 상태를 나타내는 프로퍼티 어트리뷰트**를 기본값으로 자동 정의한다. 
> 프로퍼티의 상태란 프로퍼티의 값(value), 값의 갱신 가능 여부(writable), 열거 가능 여부(enumerable), 재정의 가능 여부(configurable)를 말한다. 
> - 프로퍼티 어트리뷰트는 자바스크립트 엔진이 관리하는 내부 상태 값인 내부 슬롯 [[Value]], [[Writeable]], [[Enumerable]], [[Configurable]]이다.

 📌 `Object.getOwnPropertyDescriptor` 메소드
   - 프로퍼티 어트리뷰트 정보를 제공하는 프로퍼티 디스크립터 객체를 반환한다. 
   - 첫 번째 매개변수에는 객체의 참조를 전달하고, 두 번째 매개변수에는 프로퍼티 키를 문자열로 전달한다.
   - 만약 존재하지 않는 프로퍼티나 상속받은 프로퍼티에 대한 프로퍼티 디스크립터를 요구하면 undefined가 반환된다.

```js
 cosnt person = {
	name: 'Ssong';
};
 
 console.log(Object.getOwnPropertyDescriptor(person, 'name'));
 // { value: 'Ssong', writable: true, enumerable: true, configurable: true }
```
- Object.getOwnPropertyDescriptors 메소드는 모든 프로퍼티의 프토퍼티 어트리뷰트 정보를 제공하는 프로퍼티 디스크립터 객체들을 반환한다.

## ✅ 프로퍼티의 종류
>- 프로퍼티는 데이터 프로퍼티와 접근자 프로퍼티로 구분할 수 있다.

### 🔰 데이터 프로퍼티(data property)
>  - 키와 값으로 구성된 일반적인 프로퍼티다.

|property attribute | property descriptor object property | 설명 |
|---|---|---|
|[[Value]]|value|- 프로퍼티 키를 통해 프로퍼티 값에 접근하면 반환되는 값<br> - 프로퍼티 키를 통해 프로퍼티 값을 변경하면 [[Value]]에 값을 재할당한다. 이때 프로퍼티가 없으면 동적으로 생성한다.|
|[[Writeable]]|writeable| - 프로퍼티의 변경 가능 여부를 나타내며 boolean값을 갖는다. <br> - 값이 false인 경우 해당 프로퍼티의 [[Value]] 의 값을 변경할 수 없는 읽기 전용 프로퍼티가 된다.
|[[Enumerable]]|enumerable| - 프로퍼티의 열거 가능 여부를 나타내며 boolean값을 갖는다. <br> - 값이 false인 경우 해당 프로퍼티는 for ...in 문이나 Object.keys 메소드 등으로 열거할 수 없다.
|[[Configurable]]|configurable| - 프로퍼티의 재정의 가능 여부를 나타내며 boolean값을 갖는다. <br> - 값이 false인 경우 해당 프로터피의 삭제, 어트리뷰트 값 변경이 금지된다.<br> - 단 [[Writeable]]이 true인 경우 [[Value]]의 변경과 [[Writeable]]를 false로 변경하는 것은 허용된다.

```js
const person = {
  name: 'Ssong'
};
//프토퍼티 어트리뷰트 정보를 제공하는 프로퍼티 디스크립터 객체 취득
console.log(Object.getOwnPropertyDescriptor(person,'name'));
// {value: "Ssong", writable: true, enumerable: true, configurable: true}
```
- [[Value]]의 값은 프로퍼티 값으로 초기화되고, [[Writeable]], [[Enumerable]], [[Configurable]]의 값들은 true로 초기화된다. -> 동적 추가해도 동일.

### 🔰 접근자 프로퍼티(accessor property)
> - 자체적으로는 값을 갖지 않고 다른 데이터 프로퍼티의 값을 읽거나 저장할 때 호출되는 접근자 함수로 구성된 프로퍼티다.
> getter, setter 함수라고도 부른다. 두 함수 모두 정의할 수도 있고 하나만 정의할 수도 있다.

|property attribute | property descriptor object property | 설명 |
|---|---|---|
|[[Get]]|get|- 접근자 프로퍼티를 통해 데이터 프로퍼티의 값을 읽을 때 호출되는 접근자 함수. <br> - 접근자 프로퍼티 키로 프로퍼티 값에 접근하면 [[Get]]의 값, 즉 getter함수가 호출되고 그 결과가 프로퍼티 값으로 반환된다.|
|[[Set]]|set| - 접근자 프로퍼티를 통해 데이터 프로퍼티의 값을 저장할 때 호출되는 접근자 함수. <br> - setter함수가 호출되고 그 결과가 프로퍼티 값으로 저장된다.|
|[[Enumerable]]|enumerable| - 프로퍼티의 열거 가능 여부를 나타내며 boolean값을 갖는다. <br> - 값이 false인 경우 해당 프로퍼티는 for ...in 문이나 Object.keys 메소드 등으로 열거할 수 없다.
|[[Configurable]]|configurable| - 프로퍼티의 재정의 가능 여부를 나타내며 boolean값을 갖는다. <br> - 값이 false인 경우 해당 프로터피의 삭제, 어트리뷰트 값 변경이 금지된다.<br> - 단 [[Writeable]]이 true인 경우 [[Value]]의 변경과 [[Writeable]]를 false로 변경하는 것은 허용된다.
```js
const person = {
    //데이터 프로퍼티
    firstName = 'Jinyoung',
    lastName = 'Ssong',

    //fullName은 접근자 함수로 구성된 접근자 프로퍼티다.
    //getter 함수
    get fullName() {
        return `${this.firstName} ${this.lastName}`;
    },

    //setter 함수
    set fullName(name) {
        [this.firstName, this.lastName] = name.split(' ');
    }
};

//데이터 프로퍼티를 통한 프로퍼티 값의 참조.
console.log(person.firstName + ' ' + person.lastName); //Jinyoung Song

//접근자 프로퍼티를 통한 프로퍼티 값의 저장
//fullName에 값을 지정하면 setter함수가 호출된다.
person.fullName = 'Hana Choi';
console.log(person); //{ firstName: 'Hana', lastName: 'Choi' }

//접근자 프로퍼티를 통한 프로퍼티 값의 참조
//fullName에 접근하면 getter함수가 호출된다.
console.log(person); //Hana Choi
```
- `firstName`과 `lastName`은 **데이터 프로퍼티**다. 
- getter/setter 함수의 이름인 `fullName`이 **접근자 프로퍼티**다.

> ❓ `fullName` getter 함수의 동작 과정
> 1. 프로퍼티 키가 유효한지 확인한다. 프로퍼티 키는 문자열 또는 symbol이어야 한다. "fullName"은 문자열이므로 유효하다.
> 2. 프로토타입 체인에서 프로퍼티를 겁색한다. person 객체에 fullName 프로퍼티가 존재한다.
> 3. 검색된 fullName의 프로퍼티가 데이터 프로퍼티인지, 접근자 프로퍼티인지 확인한다. 
> 4. getter함수를 호출하여 그 결과를 반환한다. 

### 📌 접근자 프로퍼티와 데이터 프로퍼티의 구별
```js
//일반 객체의 __proto__는 접근자 프로퍼티다.
Object.getOwnPropertyDescriptor(Object.prototype, '__prototype__');
//{get: f, set: f, enumerable: false, configration: true}

//함수 객체의 prototype은 데이터 프로퍼티다.
Object.getOwnPropertyDescriptor(function () { }, 'prototype');
//{value: {...}, writable: true, enumerable: false, configration: false}
```

## ✅ 프로퍼티 정의
> - 프로퍼티 정의란 새로운 프로퍼티를 추가하면서 프로퍼티 어트리뷰트를 명시적으로 정의하거나, 기존 프로퍼티의 프로퍼티 어트리뷰트를 재정의하는 것을 말한다. 
> - Object.defineProperty 메서드를 사용하면 프로퍼티의 어트리뷰트를 정의할 수 있다.

- 프로퍼티 디스크립터 객체의 프로퍼티를 생략했을 때의 기본값

|프로퍼티 디스크립터 객체의 프로퍼티|대응하는 프로퍼티 어트리뷰트| 생략했을 때의 기본값|
|---|---|---|
|value|	[[value]]|	undefined
|get|	[[Get]]|	undefined
|set|	[[Set]]|	undefined
|writable|	[[Writable]]|	false
|enumerable|	[[Enumerable]]|	false
|configurable|	[[configurable]]|	false

📌 Object.defineProperty : 한번에 하나의 프로퍼티만 정의할 수 있다.
📌 Object.defineProperties : 여러 개의 프로퍼티를 한 번에 정의할 수 있다.
```js
const person = {};

Object.defineProperties(person, {
    firstName: {
        value: 'Ssong',
        writable: true,
        enumetable: true,
        configrable: true
    },
    laseName: {
        value: 'Jin',
        writable: true,
        enumetable: true,
        configrable: true
    },
})
```

## ✅ 객체 변경 방지 
- 객체는 변경 가능한 값이므로 프로퍼티를 추가하거나 삭제할 수 있다.
- `Object.defineProperty` 또는 `Object.defineProperries` 메소드를 사용하여 재정의 할 수 있다.
- JS는 객체의 변경을 방지하는 여러 메소드를 제공한다. 
- 해당 메소드들은 객체의 변경을 금지하는 강도가 다르다.

구분|	메서드|	프로퍼티 추가|	프로퍼티 삭제|	프로퍼티 값 읽기|	프로퍼티 값 쓰기|	프로퍼티 어트리뷰트 재정의
|--|--|--|--|--|--|--|
객체 확장 금지|	Object.preventExtensions|	X|	O|	O|	O|	O|
객체 밀봉|	Object.seal|	X|	X|	O|	O|	X
객체 동결|	Object.freeze|	X|	X|	O|	X|	X

### 🔰 객체 확장 금지
- `Object.preventExtensions` 메서드는 객체의 확장을 금지한다. `Object.preventExtensions(person)`
- **확장이 금지된 객체는 프로퍼티 추가가 금지된다.**
- 프로퍼티 동적 추가와 `Object.defineProperty`로 추가하는 방법 모두 금지된다.
### 🔰 객체 밀봉
- `Object.seal` 메서드는 객체를 밀봉한다. `Object.seal(person)`
- 객체 밀봉이란 프로퍼티 추가 및 삭제와 프로퍼티 어트리뷰트 재정의 금지를 의미한다. 
- **밀봉된 객체는 읽기와 쓰기만 가능하다.**
- `Object.isSeal` 메소드로 객체 밀봉 여부를 확인할 수 있다.
### 🔰 객체 동결
- `Object.freeze` 메서드는 객체를 동결한다. 
- 객체 동결이란 프로퍼티 추가 및 삭제와 프로퍼티 어트리뷰트 재정의 금지, 프로퍼티 값 갱신 금지를 의미한다. 
- **동결된 객체는 읽기만 가능하다.**
- `Object.isFrozen` 메소드로 객체의 동결 여부를 확인할 수 있다.
### 🔰 불변 객체
- `Object.freeze`로 객체를 동결하여도 중첩 객체까지 동결할 수 없다.
- 객체의 중첩 객체까지 동결하여 변경이 불가능한 읽기 전용의 불변 객체를 구현하려면 객체를 값으로 갖는 모든 프로퍼티에 대해 재귀적으로 `Object.freeze` 메서드를 호출해야 한다.