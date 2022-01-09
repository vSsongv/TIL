# 객체(Object)
> 객체, 프로퍼티에 대해 다룬다.(211125)

## ❔ 객체
>- 원시 타입(Primitives)을 제외한 나머지 값들(함수, 배열, 정규표현식 등)은 모두 객체이다.

- 원시 타입은 단 하나의 값만 나타내지만 객체 타입은 다양한 타입의 값(원시 값 또는 다른 객체)를 하나의 단위로 구성한 복합적인 자료구조다.
- 객체는 0개 이상의 프로퍼티(property)로 구성된 집합이며, **key**와 **value로** 구성된다.
- JS에서 사용할 수 있는 모든 값은 프로퍼티 값이 될 수 있다. 함수 또한 일급 객체이므로 사용할 수 있다.
   - 일급 객체 : 다른 객체들에 일반적으로 적용 가능한 연산을 모두 지원하는 객체, 함수에 매개변수로 넘기기, 수정하기, 변수에 대입하기와 같은 값의 성질을 갖는 연산을 지원할 때 일급 객체라고 한다.
```js
	var person = {
	  name: 'Lee', -> 프로퍼티
	  sayHello: function () { -> 메소드
	    console.log('Hi! My name is ' + 	this.name);
	  }
	};
```
📌 객체는 데이터(프로퍼티)와 그 데이터에 관련되는 동작(메소드)을 모두 포함할 수 있기 때문에 데이터와 동작을 하나의 단위로 구조화할 수 있어 유용하다.

## ✅ 객체 생성 방법
- 자바스크립트는 프로토타입 기반 객체 지향 언어로서 클래스라는 개념이 없고 별도의 객체 생성 방법이 존재한다. 
- <span style="color:red">BUT</span> ECMAScript 6에서 새롭게 클래스가 도입되었다. class로 객체를 생성하는 방법은 다음에 다루도록 하겠다.
### 1. 객체 리터럴
- 가장 일반적인 방법. 중괄호({})를 사용하여 생성.
```js
	//{} 내에 아무것도 기술하지 않으면 빈 객체가 생성된다.
	var emptyObject = {};
	console.log(typeof emptyObject); // 	object

	//{} 내에 1개 이상의 프로퍼티를 기술하면 해당 프로퍼티가 추가된 객체를 생성할 수 있다.
	var person = {
	  name: 'Lee',
	  gender: 'male',
	  sayHello: function () {
	    console.log('Hi! My name is ' + 	this.name);
	  }
	};

	console.log(person); // {name: "Lee", gender: "male", sayHello: ƒ}
```
- 객체 리터럴의 중괄호는 코드 블록을 의미하지 않는다. -> 맨 뒤에 `;`을 붙이지 않는다.

```js
	var person = {
	  name: 'Lee',
	  gender: 'male',
	  sayHello: function () {
	    console.log('Hi! My name is ' + 	this.name);
	  }
	};

	person = {
	  name: 'Lee',
	  gender: 'male',
	  sayHello: function () {
	    console.log('Hi! My name is ' + 	this.name);
	  }
	};
```
- 만약 위와 같은 상황이라면 person이라는 객체를 새로운 메모리에 생성하고, 기존 person의 연결을 옮긴다. 

### 2. Object 생성자 함수
- new 연산자와 Object 생성자 함수를 호출하여 빈 객체를 생성할 수 있다. 
- 빈 객체 생성 이후 프로퍼티 또는 메소드를 추가하여 객체를 완성하는 방법
- 객체가 소유하고 있지 않은 프로퍼티 키에 값을 할당하면 해당 객체에 프로퍼티를 추가하고 값을 할당한다. 이미 존재하는 프로퍼티 키에 새로운 값을 할당하면 프로퍼티 값은 할당한 값으로 변경된다.
```js
	// 빈 객체의 생성
	var person = new Object();
	// 프로퍼티 추가
person.name = 'Ssong';
	person.gender = 'female';
	person.sayHello = function () {
	  console.log('Hi! My name is ' + this.name);
	};
	
	console.log(typeof person); // object
	console.log(person); // {name: "Ssong", gender: "female", sayHello: ƒ}
```
📌객체 리터럴 방식은 사실 JS가 내부적으로 Object 생성자를 이용해 객체를 만드는 것과 같다.
### 3. 생성자 함수
- 마치 하나의 템플릿처럼 사용하는 방법.
- 생성자 내부 변수는 private하다.
- this는 instance를 가리킨다.
- this에 연결되있는 property와 method는 public하다.
- 생성자 함수가 아닌 일반 함수에 new 연산자를 붙여 호출해도 생성자로 동작한다. -> **따라서 첫문자를 대문자로 기술하여 혼란을 방지하도록 한다.**
```js
	// 생성자 함수
	function Person(name, gender) {
	  this.name = name;
	  this.gender = gender;
	  this.sayHello = function(){
	    console.log('Hi! My name is ' + this.name);
	  };
	}

	// 인스턴스의 생성
	var person1 = new Person('Lee', 'male');
	var person2 = new Person('Kim', 'female');
```


## ❔ 프로퍼티(property)
>- 프로퍼티는 프로퍼티 키(이름)와 프로퍼티 값으로 구성된다. 
```js
	var person = {
	  name: 'Ssong', //key는 name, value는 'Lee'
	  gender: 'female', //프로퍼티 나열은 `,`로 구분
	  age: '24',
    	}
```

### ✅ method
> - 프로퍼티 값이 함수일 경우, 일반 함수와 구분하기 위해 **메소드(method)** 라 부른다. 즉, 메소드는 객체에 제한되어 있는 함수를 의미한다.
```js
	var circle = {
	  radius: 5, -> 프로퍼티
	  getDiameter: function () { -> 메소드, getDiameter() 만 메소드이다.
	    return 2 * this.radius;
    	  }
	};
```

### ✅ 프로퍼티 key
- 프로퍼티는 프로퍼티 키로 유일하게 식별할 수 있다. -> 프로퍼티 키는 프로퍼티를 식별하기 위한 **식별자(identifier) 역할을 한다**.
- 프로퍼티 키 : 빈 문자열을 포함하는 모든 문자열 또는 symbol 값
- 프로퍼티 값 : 모든 값 
❗ **프로퍼티 키가 식별자 네이밍 규칙을 따르지 않을 때에는 반드시 따옴표를 사용해야 한다.**
```js
	var person = {
	  firstName: 'Ssong', //네이밍 규칙을 따랐음
	  'last-name': 'Jin', //네이밍 규칙을 따르지 않았음. -> ❌따옴표를 생략하면 - 연산자가 있는 표현식으로 해석할 위험이 있음!
    	}
```
- 문자열로 평가할 수 있는 표현식을 사용해 동적으로 key를 생성할 수 있다. 이 경우에는 `[]`로 묶어야 한다.
```js
	var person = {};
	var key = 'name';
    
	person[key]: 'Ssong';

	console.log(person) // { name : 'Ssong'} 
```
- 프로퍼티 key에 문자열이나 symbol외의 값을 사용하면 암묵적 타입 변환을 통해 문자열이 된다.
- 숫자 리터럴을 사용하면 따옴표는 붙지 않지만 내부적으로는 문자열로 변환된다.

❌ var, function같은 예약어를 사용해도 에러는 발생하지 않지만 예상치 못한 에러가 발생할 수도 있으니 사용하지 않는 게 좋다.

❗ 이미 존재하는 키를 중복 선언하면 나중에 선언한 프로퍼티가 덮어쓴다.
```js
	var person = {
	  name: 'Lee',
	  name: 'Ssong',
	}; // person = { name : 'Ssong' }
```

### ✅ 프로퍼티 접근
> - 객체의 프로퍼티 값에 접근하는 방법은 마침표(.) 표기법과 대괄호([]) 표기법이 있다.

- 프로퍼티 키가 유효한 자바스크립트 이름이고 예약어가 아닌 경우 프로퍼티 값은 마침표 표기법, 대괄호 표기법 모두 사용할 수 있다.
- 📌 객체가 아닌 값 `'acb'`에 마침표 표기법을 사용하면 JS엔진이 객체처럼 변화했다가 끝나면 다시 돌린다.
```js
	'abc'.length 
	//abc는 string. 원시 타입이지만 .을 사용하여 객체처럼 동작한다. 
	//'abc'의 길이를 알 수 있다.
```
- 프로퍼티 이름이 유효한 자바스크립트 이름이 아니거나 예약어인 경우 프로퍼티 값은 대괄호 표기법으로 읽어야 한다.
- 숫자인 경우 따옴표를 생략할 수 있다.

```js
var person = {
  'first-name': 'Ssong',
  gender: 'female',
  1: 10
};

console.log(person['first-name']); // 'Ssong

console.log(person.gender);    // 'female'
console.log(person['gender']); // 'female'

console.log(person['1']); // 10
console.log(person[1]);   // 10 : person[1] -> person['1']
```

- ❗ 대괄호([]) 표기법을 사용하는 경우, 대괄호 내에 들어가는 프로퍼티 이름은 반드시 문자열이어야 한다. 따옴표가 없는 경우 식별자로 해석하기 때문이다.
```js
	var person = {
  	  name: 'Ssong',
    }
    
	console.log(person[name]);   // ReferenceError: gender is not defined
```

- 객체에 존재하지 않는 프로퍼티를 참조하면 undefined를 반환한다.
```js
	var person = {
  	  name: 'Ssong',
    }
    
	console.log(person['age']);   // undefined
```

### ✅ 프로퍼티 값 갱신
- 이미 존재하는 프로퍼티에 새로운 값을 할당하면 프로퍼티 값은 갱신된다.
```js
	var person = {
	  name: 'Ssong',
	};
	
	person[name] = 'Kim'; // person.name = 'Kim'; 과 동일
	
	console.log(person[name] ); // 'Kim'
```

### ✅ 프로퍼티 동적 생성
- 존재하지 않는 프로퍼티 키에 값을 할당하면 주어진 키와 값으로 프로퍼티를 동적으로 생성하여 객체에 추가한다.
```js
	var person = {
	  name: 'Ssong',
	};
	
	person.age = 24;
	
	console.log(person[age] ); // '24'
```
### ✅ 프로퍼티 삭제
- delete 연산자를 사용하면 객체의 프로퍼티를 삭제할 수 있다. 이때 피연산자는 프로퍼티 값에 접근할 수 있는 표현식이어야 한다. 즉 key값이어야 한다.
```js
	var person = {
	  name: 'Ssong',
	  age: 24;
	};
	delete person.age; //age 프로퍼티 삭제
	console.log(person); // {name: "Ssong"}
	delete person.address //address라는 프로퍼티가 없지만 에러가 발생하지 않는다.
```
- ❗ delete는 안티패턴 -> 부수 효과가 있으므로, 그리고 삭제할 값을 왜 만들었니?

## ✅ ES6에서 추가된 객체 리터럴의 확장 기능
### 1. 프로퍼티 축약 표현
- ES6에서는 프로퍼티 값으로 변수를 사용하는 경우 변수 이름과 프로퍼티 키가 동일한 이름일 때 프로퍼티 키를 생략할 수 있다. 이때 프로퍼티 키는 변수 이름으로 자동 생성된다.
```js
	let x = 1, y = 2;
	const obj = { x, y };
	console.log(obj); // {x: 1, y: 2}
```

### 2. 계산된 프로퍼티 이름
- 문자열 또는 문자열로 타입 변환할 수 있는 값으로 평가되는 표현식을 사용해 프로퍼티 키를 동적으로 생성할 수도 있다. 
- 단, 프로퍼티 키로 사용할 표현식을 대괄호로 묶어야 한다. 이를 _계산된 프로퍼티 이름_ 이라 한다.
```js
const prefix = 'prop';
let i = 0;
const obj = {
  [`${prefix}-${++i}`]: i,
  [`${prefix}-${++i}`]: i,
  [`${prefix}-${++i}`]: i
};
console.log(obj); // {prop-1: 1, prop-2: 2, prop-3: 3}
```

### 3. 메서드 축약 표현
- ES6에서는 메서드를 정의할 때 function 키워드를 생략한 축약 표현을 사용할 수 있다.
```js
const obj = {
  name: 'Ssong',
  //origin
  sayHi: function(){
    console.log('Hi! ' + this.name); 
  }
  
  // 메서드 축약 표현
  sayHi(){
    console.log('Hi! ' + this.name); 
  }
};
obj.sayHi(); // Hi! Ssong
```
