# 변수(Valud)
> hoisting, 재할당 등 JS의 변수에 대한 내용을 다룬다. 

### ❓ 변수

- **하나의 값을 저장하기 위해 확보한 메모리 공간 자체 또는 그 메모리 공간을 식별하기 위해 붙인 이름**
- 변수 == 변수명(식별자)
- 값의 위치를 가리키는 상징적인 이름.
- 메모리 공간에 상징적인 이름을 붙인 것.
- 메모리에 저장되는 모든 값은 2진수로 저장됨.
- **할당(대입, 저장)** : 변수에 값을 저장하는 것.  `식별자 = 표현식` _왼쪽이 식별자가 아니면 할당이 아니다!_
- **참조** : 변수에 저장된 값을 읽어들이는 것.

### ❓ 식별자

- **어떤 값을 구별해서 식별할 수 있는 고유한 이름**
- 값을 식별할 수 있는 이름은 모두 식별자라고 부른다.
- 식별자는 메모리 공간에 저장되어 있는 어떤 값을 구별해서 식별해낼 수 있어야 한다.
- 이를 위해 식별자는 해당 값이 저장되어 있는 메모리 주소를 기억해야 한다.
- 식별자는 메모리 주소와 **매핑 관계**를 맺으며, 이 매핑 정보도 메모리에 저장되어야 한다.
- 따라서 식별자는 **값이 아니라 메모리 주소**를 기억하고 있다.

### ✅ 변수 선언

- 값을 저장하기 위한 메모리 공간을 확보하고, 메모리 주소와 변수 이름을 연결하는 것. -> '변수를 생성한다.' 고 한다.
- var, let, const 키워드를 사용한다.
- JS엔진은 변수 선언을 2단계에 걸쳐 수행한다.
  > - 변수 이름을 비롯한 모든 식별자는 실행 컨텍스트(JS 엔진이 소스코드를 실행하기 위해 관리하는 영역)에 등록된다.
  >
  > 1.  선언 단계 : 변수 이름을 등록하여 존재를 알린다.
  > 2.  초기화 단계 : 값 저장을 위한 메모리를 확보하고 암묵적으로 undefined를 할당한다.
  >     -> undefined를 할당하기 때문에 쓰레기 값이 나올 위험으로부터 안전하다.
  >     ❗ 변수를 사용하려면 반드시 선언을 해줘야 한다.

### ✅ 변수 선언 실행 시점과 Hoisting

```js
console.log(Ssong); // output: undefined
var Ssong;
```

- 위 코드는 에러가 발생하지 않는다.
- **JS는 변수 선언이 런타임 시점 이전에 실행되기 때문이다.**
- JS는 소스코드를 한 줄씩 실행시키기 전에 소스코드를 평가하는 과정을 거친다.
- 이때 JS엔진은 모든 선언문들을 먼저 실행한다.
- 변수,함수 등의 선언문이 코드의 선두로 끌어올려진 것처럼 동작하는 특징을 JS의 **변수 호이스팅**이라고 한다.

### ✅ 값의 할당

```js
var a;
a = 100;

var a = 100;
```

- 위 두 문은 동일하게 동작한다. 한 줄로 선언한다고 하더라도 JS 엔진은 선언과 할당 2줄로 나누어 각각 실행한다.
- **값의 할당은 선언과 달리 런타임에 실행된다.**
  ![](https://images.velog.io/images/songjy377/post/0f7d0a66-c089-4e64-bce4-287c88c32f62/image.png)

```js
console.log(a); // undefined -> 값이 할당되지 않았으므로 undefined가 출력됨.
var a;
a = 100;

console.log(a); //100
```

### ✅ 값의 재할당

```js
var a = 100;
a = 90;
```

- 변수는 값을 재할당할 수 있다.
- **기존의 값을 변경하는 것이 아니라 새로운 메모리 공간을 확보한 후 그 메모리 공간에 새로운 값을 저장한다.**
- 더 이상 사용하지 않는 값들은 가비지 콜렉터(메모리 공간을 주기적으로 검사하여 더 이상 사용되지 않는 메모리를 해제하는 기능)에 의해 메모리에서 자동 해제된다.
- 변수에 값을 재할당할 수 없으면 상수(const)라고 한다.

### ✅ 식별자 네이밍 규칙

> 1. 특수문자를 제외한 문자, 숫자, \_, $를 포함할 수 있다.
> 2. 문자, \_, $로 시작해야 한다.
> 3. 예약어는 식별자로 사용할 수 없다.
>    <예약어> _(\*은 식별자로 사용 가능하지만 strict 모드에서 사용 불가)_
>    abstract, arguments, boolean, break, byte,
>    case, catch, char, class*, const, continue, debugger, default, delete, do, double, else, enum*, eval, export*, extends*, false, final, finally, float, for, function, goto, if, implements, import*, in instanceof, int, nterface, let, long, native, new, null, package, private, protected, public, eturn, short, static, super* switch, synchronized, this, throw, throws, transient, true, try, typeof, var, void, volatile,
>    while, with, yield,

### ✅ 네이밍 컨벤션(nameing convetion)

- camelCase : 중간 단어 첫머리만 대문자
- snake*case : *로 연결
- PascalCase : 모든 앞머리 대문자
- **JS에서는 변수, 함수 이름에는 camel case를, 생성자 함수, 클래스의 이름에는 pascal case를 사용한다.**
