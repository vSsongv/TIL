# Module
> JS의 모듈 개념과, default, export, import, 모듈 스코프에 대해 다룬다.(211231)

## ❓ 모듈이란
- 모듈(module)은 애플리케이션을 구성하는 개별적 요소로서 **재사용 가능한 코드 조각**을 말한다. 
- 일반적으로 모듈은 **기능을 기준으로 파일 단위로 분리**하며, 모듈은 **파일 스코프(모듈 스코프)**를 가질 수 있어야 한다.

- 자신만의 파일 스코프를 갖는 모듈의 모든 자산은 캡슐화되어 다른 모듈에서 접근할 수 없기 때문에, **모듈이 다른 모듈에 의해 재사용될 수 있도록 공개가 필요한 자산에 한정해 공개할 수 있도록 한다. 이를 `export`라 한다.**

- `export`된 모듈의 자산은 다른 모듈에서 재사용할 수 있고, 공개된 모듈의 자산을 사용하는 모듈을 모듈 사용자(module consumer)라 한다. **모듈 사용자는 모듈이 `export`한 자산 중 일부 또는 전체를 선택해 자신의 스코프 내로 불러들여 재사용할 수 있다. 이를 `import`라 한다.**

![](https://images.velog.io/images/songjy377/post/7438cf4c-4a3a-417e-babd-42e65d25fa93/image.png)

- 모듈은 개별적으로 존재하다가 필요에 따라 다른 모듈에 의해 재사용된다. 코드의 단위를 정확히 분리해 애플리케이션을 구성할 수 있고, 재사용성이 좋아 개발 효율성과 유지보수성을 높일 수 있다.

## ✳️ 자바스크립트와 모듈
- 자바스크립트는 모듈 시스템을 지원하지 않는다. script 태그를 사용해 여러 자바스크립트 파일을 로드할 수는 있지만 분리된 js 파일들은 결국 하나의 js 파일 내에 있는 것처럼 동작해, 분리된 js 파일들의 전역 변수가 중복되는 등의 문제가 발생할 수 있다.

- 자바스크립트에서 모듈 시스템을 사용하기 위해 CommonJS와 AMD(Asyncronous Module Definition)이 나오게 되었고, 브라우저 환경에서 모듈을 사용하기 위해 CommonJS나 AMD를 구현한 모듈 로더 라이브러리를 사용해야 했다.

- 이러한 상황에서 ES6 에서는 client-side 자바스크립트에서도 동작하는 모듈 기능이 추가되었다. Node.js 환경에서는 파일별로 독립적인 파일 스코프(모듈 스코프)를 갖는다.

## ✳️ ES6 모듈(ESM)
- ES6 모듈(이하 ESM)은 script 태그에 `type="module"` 속성을 추가하면 로드된 js 파일은 모듈로서 동작한다. 일반 js 파일이 아닌 ESM임을 명확히 하기 위해 ESM의 파일 확장자는 msj를 사용할 것을 권장한다.
```js
<script type="module" src="app.mjs"></script>
```
> ✅ ESM에는 기본적으로 strict mode가 적용된다.  또한 기본이 defer이다.

### ❇️ 모듈 스코프
- ESM이 아닌 일반 js 파일은 script 태그로 분리해서 로드해도 독자적인 모듈 스코프를 갖지 않는다.
```js
// bar.js
var y = 'bar';

//foo.js
console.log(y); // bar
<!DOCTYPE html>
<html>
  <body>
    <script type="module" src="foo.js"></script>
    <script type="module" src="bar.js"></script>
  </body>
</html>
```
- ESM은 독자적인 모듈 스코프를 가진다. 
```js
// foo.mjs
var x = 'foo';
var y = 'bar';
console.log(x); // foo
console.log(window.x); // undefined

// bar.mjs
var x = 'bar';
console.log(x); // bar
console.log(window.x); // undefined
console.log(y); // ReferenceError: y is not defined
<!DOCTYPE html>
<html>
  <body>
    <script type="module" src="foo.mjs"></script>
    <script type="module" src="bar.mjs"></script>
  </body>
</html>
```

- foo와 bar 모듈의 x 변수는 전역 변수가 아니고 window 프로퍼티도 아니다. foo 모듈에서 선언한 x 변수와 bar 모듈에서 선언한 **x 변수는 스코프가 다르다.** 모듈 내에서 선언한 식별자는 모듈 외부에서 참조할 수 없다. 모듈 스코프가 다르기 때문이다.

### ❇️ export 키워드
- 모듈 내부에서 선언한 식별자를 외부에 공개해 다른 모듈에서 재사용할 수 있도록 `export` 키워드가 제공된다.
- `export`는 선언문 앞에 사용하여 변수, 함수, 클래스 등 모든 식별자를 `export`할 수 있다.
```js
// 변수 공개
export const pi = Math.PI;

// 함수 공개
export function sqaure(x) {
  return x * x;
}

// 클래스 공개
export class Person {
  constructor(name) {
    this.name = name;
  }
}
```
- 매번 `export`를 붙이는 게 번거롭다면 `export`할 대상을 하나의 객체로 구성해 한 번에 `export`할 수도 있다.
```js
const pi = Math.PI;

function square(x) {
  return x * x;
}

class Person {
  constructor(name) {
    this.name = name;
  }
}

export { pi, square, Person };
```

### ❇️ import 키워드
- 다른 모듈에서 공개(export)한 식별자를 자신의 모듈 스코프 내부로 로드하기 위해 `import` 키워드를 사용한다.
```js
import { pi, square, Person } from './lib.mjs';

console.log(pi); // 3.141592653589793
console.log(square(10)); // 100
console.log(new Person('Ssong')); // Person { name: 'Ssong' }
```
- 모듈을 하나의 식별자로 정의하여 한 번에 `import`할 수도 있다.
- 이때 `하나로 정의한 식별자.export된 식별자` 로 접근한다.
```js
import * as lib from './lib.mjs';

console.log(lib.pi); // 3.141592653589793
console.log(lib.square(10)); // 100
console.log(new lib.Person('Ssong')); // Person { name: 'Ssong' }
```

- 모듈이 `exoprt`한 식별자 이름을 변경해 `import`할 수도 있다.
```js
import { pi as PI, square as sq, Person as P } from './lib.mjs';

console.log(PI); // 3.141592653589793
console.log(sq(10)); // 100
console.log(new P('Ssong')); // Person { name: 'Ssong' }
```

- 모듈에서 하나의 값만 `export`한다면 `default` 키워드를 사용한다. `default`를 사용하는 경우에는 var, let, const 키워드는 사용할 수 없다.
```js
const showName = name => {
    document.body.InnerHTML = name;
};

export default moveLink;

export default const foo = () => {}; // SyntaxError: Unexpected token 'const'
```
- `default` 와 함께 export한 모듈은 {} 없이 임의의 이름으로 import 한다.
```js
import square from './lib.mjs';

console.log(square(3)); // 9
```
