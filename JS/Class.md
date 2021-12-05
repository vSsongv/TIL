### 클래스 vs 생성자 함수
1. 클래스를 new 연산자 없이 호출하면 에러가 발생하지만 생성자 함수를 new 연산자 없이 호출하면 일반 함수로서 호출된다.
2. 클래스는 상속을 지원하는 extends와 super 키워드를 제공하지만 생성자 함수는 지원하지 않는다.
3. 클래스는 호이스팅이 발생하지 않는 것처럼 동작한다. 하지만 함수 선언문으로 정의된 생성자 함수는 함수 호이스팅이, 표현식으로 정의한 생성자 함수는 변수 호이스팅이 발생한다.
4. 클래스 내의 모든 코드에는 암묵적으로 strict mode가 지정되어 실행되지만 생성자 함수는 암묵적으로 strict mode가 지정되지 않는다.
5. 클래스의 constructor, 프로토타입 메서드, 정적 메서드는 모두 [[Enumerable]]의 값이 false다. 즉, 열거되지 않는다. 
![](https://images.velog.io/images/songjy377/post/32393917-e87a-4065-a688-b3c3140b8311/image.png)
### 클래스 정의
클래스는 class 키워드를 사용하여 정의한다. 클래스 이름은 생성자 함수와 마찬가지로 파스칼 케이스를 사용하는 것이 일반적이다.
```js
// 클래스 선언문
class Person {}

// 익명 클래스 표현식
const Person = class {};

// 기명 클래스 표현식
const Person = class MyClass {};
```
✔ 클래스를 표현식으로 정의할 수 있다는 것은 클래스가 값으로 사용할 수 있는 일급 객체라는 것을 의미한다.

>📌 클래스의 특징
>1. 무명의 리터럴로 생성할 수 있다. 즉, 런타임에 생성이 가능하다.
>2. 변수나 자료구조에 저장할 수 있다.
>3. 함수의 매개변수에게 전달할 수 있다.
>4. 함수의 반환값으로 사용할 수 있다.

```js
class Person {
  // 생성자
  constructor (name) {
    this.name = name;
  }
  // 프로토타입 메서드
  sayHi() {
    console.log(`Hi! My name is ${this.name}`);
  }
  // 정적 메서드
  static sayHello() {
    console.log('Hello!');
  }
}

const me = new Person('Lee');

console.log(me.name); //Lee
//프로토타입 메소드 호출
me.sayHi(); //Hi! My name is Lee
//정적 메소드 호출
Person.sayHello(); //Hello!
```

### 클래스 호이스팅
클래스는 함수로 평가된다. 클래스 선언문으로 정의한 클래스는 함수 선언문과 같이 소스코드 평가 과정, 즉 런타임 이전에 먼저 평가되어 함수 객체를 생성한다. 이때 클래스가 평가되어 생성된 함수 객체는 생성자 함수로서 호출할 수 있는 함수, 즉 constructor다. -> 프로토타입도 더불어 생성된다.
❗**단, 클래스는 클래스 정의 이전에 참조할 수 없다.**
```js
console.log(Person); // ❌ ReferenceError

//클래스 선언문
class Person {}
```
>단, 클래스는 let,const 키워드로 선언한 변수처럼 호이스팅된다. 따라서 클래스 선언문 이전에 일시적 사각지대에 빠지기 때문에 호이스팅이 발생하지 않는 것처럼 동작한다.

### 인스턴스 생성
클래스는 생성자 함수이며 **반드시 new 연산자와 함께** 호출되어 인스턴스를 생성한다.
```js
class Person {}
//인스턴스 생성
const me = new Person();
console.log(me); // Person {}

const you = Person(); // ❌ TypeError
```

❗ 기명 클래스 표현식의 클래스 이름을 사용해 인스턴스를 생성하면 에러가 발생한다.
```js
const Person = class MyClass {};
const me = new Person();
console.log(Myclass); // ❌ ReferenceError
const you = new Myclass(); // ❌ ReferenceError
```

### 메서드
> 클래스 몸체에서 정의할 수 있는 메서드는 `constructor`, **_프로토타입 메서드_**, **_정적 메서드_**의 3가지가 있다.

### constructor
> constructor는 인스턴스를 생성하고 초기화하기 위한 특수한 메서드다. constructor는 이름을 변경할 수 없다.
```js
class Person {
  constructor(name) {
    //인스턴스 생성 및 초기화
    this.name = name;
  }
}
```
✔ 클래스는 평가되어 함수 객체가 된다. 함수와 동일하게 프로토타입과 연결되어 있으며 자신의 스코프체인을 구성한다.
✔ constructor 내부의 this는 생성자 함수와 마찬가지로 클래스가 생성한 인스턴스를 가리킨다.
✔ constructor를 생략하면 클래스에 빈 constructor가 암묵적으로 정의된다.
❗ constructor는 클래스 내에 최대 한 개만 존재할 수 있다.
❗ 인스턴스를 초기화하려면 constructor를 생략해서는 안된다.
❗ constructor는 return 문을 반드시 생략해야 한다. 암묵적으로 this를 반환하기 때문이다.(원시값은 반환해도 무시된다.)

### 프로토타입 메서드
> 클래스 몸체에서 정의한 메서드는 생성자 함수에 의한 객체 생성 방식과는 다르게 클래스의 prototype 프로퍼티에 메서드를 추가하지 않아도 기본적으로 프로토타입 메서드가 된다.
> 생성자 함수와 마찬가지로 클래스가 생성한 인스턴스는 프로토타입 체인의 일원이 된다.

```js
Class Person {
  //생성자
  constructor(name) {
    this.name = name;
  }
  
  //프로토타입 메소드
  sayHi() {
    console.log(`Hi, my name is ${this.name}`);
  }
}

const me = new Person('Ssong');

Object.getPrototypeOf(me) === Person.prototype; //true
me instanceof Person; //true

//me 객체의 constructor는 Person 클래스다.
me.constructor === Person;
```

### 정적 메서드
>정적 메서드는 인스턴스를 생성하지 않아도 호출할 수 있는 메서드를 말한다. 클래스에서는 메서드에 static 키워드를 붙이면 정적 메서드(클래스 메서드)가 된다.
```js
class Person {
  constructor(name) {
    this.name = name;
  }
  // 정적 메서드
  static sayHi() {
    console.log('Hi!');
  }
}
Person.sayHi(); // Hi!
const me = new Person('Cho');
me.sayHi(); // ❌ TypeError
```
❗ 정적 메서드는 인스턴스로 호출할 수 없다. 정적 메서드가 바인딩된 클래스는 인스턴스의 프로토타입 체인상에 존재하지 않기 때문이다.

### 정적 메서드와 프로토타입 메서드의 차이
1. 자신이 속해 있는 프로토타입 체인이 다르다.
2. 정적 메서드는 클래스로 호출하고 프로토타입 메서드는 인스턴스로 호출한다.
3. 정적 메서드는 인스턴스 프로퍼티를 참조할 수 없지만 프로토타입 메서드는 인스턴스 프로퍼티를 참조할 수 있다.
4. 정적 메서드 내부의 this는 인스턴스가 아닌 클래스를 가리키고, 프로토타입 메소드의 this는 자신을 호출한 객체를 가리킨다.
✔ **this를 사용하지 않는 메서드는 정적 메서드로 정의하는 것이 좋다.**

### 클래스에서 정의한 메서드의 특징
1. function 키워드를 생략한 메서드 축약 표현을 사용한다.
2. 객체 리터럴과는 다르게 클래스에 메서드를 정의할 때는 콤마가 필요 없다.
3 암묵적으로 strict mode로 실행된다.
4. for...in 문이나 Object.keys메서드 등으로 열거할 수 없다.
5. 내부 메서드 [[Construct]]를 갖지 않는 non-constructor다. 따라서 new 연산자와 함께 호출할 수 없다.

## 클래스의 인스턴스 생성 과정
### 인스턴스 생성과 this 바인딩
> new 연산자와 함께 클래스를 호출하면 constructor의 내부 코드가 실행되기 앞서 암묵적으로 빈 객체가 생성된다. 이때 클래스가 생성한 인스턴스의 프로토타입으로 클래스의 prototype 프로퍼티가 가리키는 객체가 설정된다. 인스턴스는 this에 바인딩된다.

### 인스턴스 초기화
>constructor의 내부 코드가 실행되어 this에 바인딩 되어 있는 인스턴스를 초기화한다. this에 바인딩되어 있는 인스턴스에 프로퍼티를 추가하고, constructor가 인수로 전달받은 초기값으로 인스턴스의 프로퍼티 값을 초기화한다. 만약 constructor가 생략되었다면 이 과정도 생략된다.

### 인스턴스 반환
>클래스의 모든 처리가 끝나면 완성된 인스턴스가 바인딩된 this가 암묵적으로 반환된다.
```js
class Person {
  constructor(name) {
    // 1. 암묵적으로 인스턴스 생성, this에 바인딩
    console.log(this);
    // 2. this에 바인딩되어 있는 인스턴스를 초기화
    this.name = name;
    // 3. 완성된 인스턴스가 바인딩된 this가 암묵적으로 반환
  }
}
```

## 프로퍼티
### 인스턴스 프로퍼티
>인스턴스 프로퍼티는 constructor 내부에서 정의해야 한다.
```js
class Person {
  constructor(name) {
    // 인스턴스 프로퍼티
    this.name = name;
  }
}
const me = new Person('Lee');
console.log(me); // Person {name: "Lee"}
```

### 접근자 프로퍼티
>접근자 프로퍼티는 자체적으로는 값(내부 슬롯 value)을 갖지 않고 다른 데이터 프로퍼티의 값을 읽거나 저장할 때 사용하는 접근자 함수로 구성된 프로퍼티다. 접근자 프로퍼티는 getter 함수와 setter 함수로 구성되어 있다.
> 클래스의 메서드는 기본적으로 프로토타입 메소드가 된다. 즉 접근자 프로퍼티 또한 인스턴스 프로퍼티가 아닌 프로토타입의 프로퍼티가 된다.

```js
class Person {
    constructor(firstName, lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    //getter 함수
    get fullName() {
        return `${this.firstName} ${this.lastName}`;
    }
    //setter 함수
    set fullName(name) {
        [this.firstName, this.lastName] = name.split(' ');
    }
}

const me = new Person('Jinyoung', 'Song');

//fullName에 값을 저장하면 setter함수가 호출된다.
me.fullName = 'Jinyoung Song';
console.log(me); //{ firstName: 'Jinyoung', lastName: 'Song' }

console.log(me.fullName); //getter 함수가 호출된다.

console.log(Object.getOwnPropertyDescriptor(Person.prototype, 'fullName'));
//{get: [Function: get fullName], set: [Function: set fullName], enumerable: false, configurable: true }
```
<span style="color:blue">getter 함수</span>
인스턴스 프로퍼티에 접근할 때마다 프로퍼티 값을 조작하거나 별도의 행위가 필요할 때 사용한다. getter는 메서드 이름 앞에 get 키워드를 사용해 정의한다. 참조하는 형식으로 사용하며, 내부적으로 getter가 호출된다. 반드시 무언가를 반환해야 한다.
<span style="color:violet">setter 함수</span>
인스턴스 프로퍼티에 값을 할당할 때마다 프로퍼티 값을 조작하거나 별도의 행위가 필요할 때 사용한다. setter는 메서드 이름 앞에 set 키워드를 사용해 정의한다. 반드시 매개변수가 있어야 한다. 단 하나의 값만 할당받기 때문에 단 하나의 매개변수만 선언할 수 있다.

## 클래스 필드 정의 제안
> 클래스 필드는 클래스 기반 객체지향 언어에서 클래스가 생성할 인스턴스의 프로퍼티를 가리키는 용어다.
>```js
>class Person {
>  // 클래스 필드 정의
>  name = 'Lee';
>}
>
>const me = new Person('Lee');
>```

❗ 클래스 몸체에서 클래스 필드를 정의하는 경우 this에 클래스 필드를 바인딩해서는 안 된다. this는 클래스의 constructor와 메서드 내에서만 유효하다.
```js
class Person{
  this.name = ''; // ❌ SyntaxError
}
```

❗ this를 생략하고 클래스 필드를 참조할 수 없다.
```js
class Person {
  name = 'Lee';
  constructor() {
    console.log(name); // ❌ ReferenceError
  }
}
```
✔ 클래스 필드에 초기값을 할당하지 않으면 undefined를 갖는다.
```js
class Person {
  name;
}

const me = new Person();
console.log(me); // Person {name: undefined}
```

✔ 인스턴스를 생성할 때 외부의 초기값으로 클래스 필드를 초기화해야 할 필요가 있다면 constructor에서 클래스 필드를 초기화해야 한다.
```js
class Person {
  name;
  
  constructor(name) {
    this.name = name; // 초기화
  }
}

const me = new Person('Lee');
console.log(me); // Person {name: "Lee"}
```

❗ 클래스 필드에 함수를 할당하는 것은 가능하지만 권장하지 않는다. -> 인스턴스 메소드가 되기 때문이다.

### private 필드 정의 제안
인스턴스 프로퍼티는 인스턴스를 통해 클래스 외부에서 언제나 참조할 수 있다. 즉, 언제나 public이다.

#를 붙여서 private 필드를 참조할 수 있다.

class Person {
  // private 필드 정의
  #name = '';
  
  constructor(name) {
    // private 필드 참조
    this.#name = name;
  }
  get name() {
    return this.#name.trim();
  }
}
const me = new Person('Cho');
console.log(me.#name);  // ❌ SyntaxError
console.log(me.name); // Cho
✔ private 필드는 클래스 내부에서만 참조할 수 있다.

❗ private 필드는 반드시 클래스 몸체에 정의해야 한다. 직접 constructor에 정의하면 에러가 발생한다.

### static 필드 정의 제안
클래스에는 static 키워드를 사용하여 정적 메서드를 정의할 수 있다.

class MyMath {
  // static public 필드 정의
  
  static PI = 22/7;

  // static private 필드 정의
  static #num = 10;
  
  // static 메서드
  static increment() {
    return ++MyMath.#num;
  }
}

console.log(MyMath.PI); // 3.14~
console.log(MyMath.increment()); // 11

## 상속에 의한 클래스 확장
### 클래스 상속과 생성자 함수 상속
상속에 의한 클래스 확장은 기존 클래스를 상속받아 새로운 클래스를 확장하여 정의하는 것이다.

### extends 키워드
상속을 통해 클래스를 확장하려면 extends 키워드를 사용하여 상속받을 클래스를 정의한다.

class Base{} // 수퍼(베이스/부모) 클래스
class Derived extends Base{} // 서브 클래스
서브 클래스 : 상속을 통해 확장된 클래스
수퍼 클래스 : 서브클래스에게 상속된 클래스
동적 상속
extends 키워드는 클래스뿐만 아니라 생성자 함수를 상속받아 클래스 확장이 가능하다. 클래스뿐만 아니라 [[Consturct]] 내부 메서드를 갖는 함수 객체로 평가될 수 있는 모든 표현식 사용이 가능하다. 이를 통해 동적으로 상속 받는 대상을 결정할 수 있다.

### 서버클래스의 constructor
서브클래스에서 constructor를 생략하면 다음과 같이 암묵적으로 정의된다. args는 new 연산자와 함께 클래스를 호출할 때 전달한 인수의 리스트다. super()는 수퍼클래스의 constructor를 호출하여 인스턴스를 생성한다.

constructor(...arge) { super(...args); }
✔ Rest 파라미터 : 매개변수에 ...을 붙이면 Rest 파라미터가 된다. Rest 파라미터는 함수에 전달된 인수들의 목록을 배열로 전달받는다.

### super 키워드
super 키워드는 함수처럼 호출할 수도 있고 this와 같이 식별자처럼 참조할 수 있는 특수한 키워드다.

#### super 호출
super를 호출하면 수퍼클래스의 constructor를 호출한다.

❗ 수퍼클래스에서 추가한 프로퍼티와 서브클래스에서 추가한 프로퍼티를 갖는 인스턴스를 생성한다면 서브클래스의 constructor를 생략할 수 없다.
❗ 서브클래스에서 constructor를 생략하지 않으면 반드시 super을 호출해야 한다.
❗ 서브클래스의 constructor에서 super를 호출하기 전에는 this를 참조할 수 없다.
❗ super는 반드시 서브클래스의 constructor에서만 호출한다.

#### super 참조
메서드 내에서 super를 참조하면 수퍼클래스의 메서드를 호출할 수 있다.

### super 참조 의사코드

super = Object.getPrototypeOf([[HomeObject]])
❗ ES6의 메서드 축약 표현으로 정의된 함수만이 [[HomeObject]]를 갖는다는 것이다.

### 상속 클래스의 인스턴스 생성 과정
1. 서브클래스의 super 호출
자바스크립트 엔진은 수퍼클래스와 서브클래스를 구분하기 위해 'base' 또는 'derived' 값을 갖는 내부 슬롯 [[ConstructorKind]]를 갖는다. 상속받는 서브 클래스를 derived로, 상속받지 않는 클래스는 base로 설정된다.
서브클래스는 자신이 직접 인스턴스를 생성하지 않고 수퍼클래스에게 인스턴스 생성을 위임한다. 이것이 바로 서브클래스의 constructor에서 반드시 super를 호출해야 하는 이유다.

2. 수퍼클래스의 인스턴스 생성과 this 바인딩
수퍼클래스의 constructor 내부의 this는 생성된 인스턴스를 가리킨다. 인스턴스는 수퍼클래스가 생성한 것이지만 new.target은 서브클래스를 가리킨다. 따라서 인스턴스는 new.target이 가리키는 서브클래스가 생성한 것으로 처리된다.

3. 수퍼클래스의 인스턴스 초기화
수퍼클래스의 constructor가 실행되어 this에 바인딩되어 있는 인스턴스를 초기화한다. 즉, this에 바인딩 되어 있는 인스턴스에 프로퍼티를 추가하고 constructor가 인수로 전달받은 초기값으로 인스턴스의 프로퍼티를 초기화한다.

4. 서브클래스 constructor로의 복귀와 this 바인딩
super의 호출이 종료되고 서브클래스 constructor로 돌아온다. 이때 super가 반환한 인스턴스가 this에 바인딩 된다. 서브클래스는 별도의 인스턴스를 생성하지 않고 super가 반환한 인스턴스를 this에 바인딩하여 그대로 사용한다.
❗ super가 호출되지 않으면 인스턴스가 생성되지 않으며, this 바인딩도 할 수 없다.

5. 서브클래스의 인스턴스 초기화
super 호출 이후, 서브클래스의 constructor에 기술되어 있는 인스턴스 초기화가 실행된다.

6. 인스턴스 반환
클래스의 모든 처리가 끝나면 완성된 인스턴스가 바인딩된 this에 암묵적으로 반환된다.

### 표준 빌트인 생성자 함수 확장
String, Number, Array 같은 표준 빌트인 객체도 [[Construct]] 내부 메서드를 갖는 생성자 함수이므로 extends 키워드를 사용하여 확장할 수 있다.