
JS의 연산자를 공부하다가, 흥미로운 내용을 발견했다. 바로 NaN과 NaN을 동등, 일치 비교할 경우, 값이 'False'가 된다는 사실이었다.
분명 같은 값일 텐데, 왜 False가 나오는 걸까? 지금부터 NaN에 대해서 알아보자.(211126)

## ❓ NaN
>- 전역 속성으로, Not a Number, 즉 '숫자가 아니다' 라는 의미를 가진다.

- NaN을 반환하는 연산에는 5가지가 있다.

  1. 숫자로서 읽을 수 없음 `(parseInt("어쩌구"), Number(undefined))`
  2. 결과가 허수인 수학 계산식 `(Math.sqrt(-1))`
  3. 피연산자가 NaN `(7 ** NaN)`
  4. 정의할 수 없는 계산식 `(0 * Infinity)`
  5. 문자열을 포함하면서 덧셈이 아닌 계산식 `("가" / 3)`
  
## ❓ NaN === NaN -> ?
- NaN과 NaN을 비교하면, true가 아닌 false값이 나온다.
- 처음에는, NaN은 '숫자가 아닌' 값을 판별하기 위해 쓰기 때문에, '한쪽 피연산자가 숫자여야 하나 보다' 라고 생각했다.
<span style="color:red">BUT</span> **NaN의 type 또한 number이다.**

### ❗ 첫 번째 이유
- NaN은 결국 type이 Number가 아닌지를 검사한다.
- 논리적으로 생각해 보면, NaN의 type은 Number이므로 NaN과 같지 않아야 한다.

### ❗ 두 번째 이유
- 비교 연산자가 작동하는 방식을 생각해 보면, 연산 후 boolean값을 반환한다.
- `x === y` 를 비교한다고 할 때, x가 NaN이면 False를, y가 NaN이면 False를 반환할 것이다.
- **즉 한쪽이 NaN이면 결국 False가 반환될 수밖에 없다.**

### ❗ 세 번째 이유
- 또한 NaN은 정의되지 않은 숫자로 생각할 수 있다. 정의되지 않은 값이 다른 정의되지 않은 값과 같은지, 큰지, 작은지는 비교할 수 없다.

### BUG
- 몇 사람들은 버그라고도 한다!

## ✅ NaN 확인 방법
- NaN을 확인하기 위해서는 특수한 함수를 사용해야 한다.
- `isNaN` 함수, `Number.isNaN` 함수이다.
- `Object.is` 함수 또한 사용 가능한다.
```js
console.log(isNaN(NaN));//true
Number.isNaN(NaN); // true
Object.is(NaN, NaN) //true -> Object.is 는 두 값이 같은지를 비교한다.
```

❗❗ 이때 주의해야 할 점은, `isNaN`은 값을 숫자로 변환했을 때 NaN이 되면 true를 반환하지만, `Number.isNaN`은 값이 오직 `NaN` 인 경우에만 을 반환한다.

> #### isNaN
❗❗ `isNaN`은 안티패턴이다.
- 암묵적 처리를 한다.
```js
isNaN('a') //true 
```
- isNaN은 a를 숫자로 변환한다. 즉 NaN이 되므로 true가 반환된다. -> 의도와는 다르게 동작한다.