# Number
> Number에 대해 정리하였다.(211208)
 ## ❇️ Number Property

### 🍀 `Number.EPSILON`
> - 부동소수점으로 인해 발생하는 오차를 해결하기 위해 사용한다.
>
>```js
>function isEqual(a, b) {
>    //a와 b를 뺀 값의 절댓값이 Number.EPSILON보다 작으면 같은 수로 인정한다.
>    return Math.abs(a, b) < Number.EPSILON;
>}
>
>isEqual(0.1 + 0.2, 0.3) //true
>```

### 🍀 `Number.MAX_VALUE & Number.MIN_VALUE`
> - Max : JS에서 표현할 수 있는 가장 큰 양수 값
> - 큰 수가 `Infinity` 밖에 없다.
> - Min : JS에서 표현할 수 있는 가장 작은 양수 값
> - 작은 수는 0 뿐이다.

### 🍀 `Number.MAX_SAFE_INTEGER & Number.MIN_SAFE_INTEGER`
> - MAX_SAFE : 안전하게 표현할 수 있는 가장 큰 정수값(9007199254740991)
> - MIN_SAFE : 안전하게 표현할 수 있는 가장 작은 정수값(-9007199254740991)

### 🍀 `Number.POSITIVE_INFINITY & Number.NEGATIVE_INFINITY`
> - 양의 무한대, 음의 무한대를 나타내는 숫자값.
> - 각각 `infinity`, `-infinity`와 같다.

### 🍀 `Number.NaN`
> - 숫자가 아님을 나타내는 숫자값.

## ❇️ Number Method

### 🍀 `Number.isFinite`
> - 정상적인 유한수임을 검사하여 boolean값을 반환한다.
> - 암묵적 타입변환 하지 않음. -> 숫자가 아니면 항상 `false`.
> - 인수가 `NaN`이면 언제나 false 반환
>```js
>Number.isFinite(0); //true
>Number.isFinite(Number.MAX_VALUE) //true
>Number.isFinite(Infinity) //false
>Number.isFinite(NaN) //false
>```

### 🍀 `Number.isInteger`
> - 정수임을 검사하여 boolean값을 반환한다.
> - 암묵적 타입변환 하지 않음.
>```js
>Number.isInteger('123'); //false
>Number.isInteger(23); //true
>Number.isInteger(Infinity); //false
>```

### 🍀 `Number.isNaN`
> - 숫자값이 NaN임을 검사하여 boolean값을 반환한다.
> - 암묵적 타입변환 하지 않음
>```js
>Number.isNaN(NaN); //true
>```

### 🍀 `Number.isSafeInteger`
> - 인수로 전달된 숫자값이 - (253 - 1)과(253 - 1)사이의 정수값인지 검사하여 boolean값으로 반환한다.
>```js
>Number.isSafeInteger(0.5) //false
>Number.isSafeInteger(Infinity) //false
>```

## ❇️ Number prototype Method

### 🍀 `toExponential`
> - 숫자를 지수 표기법으로 변환하여 문자열로 반환
> - `e`앞에 있는 숫자에 10의 n승을 곱하는 형식.
> - 인수로 소수점 이하로 표현할 숫자 자리수 전달 가능
>```js
>console.log((77.1234).toExponential()); //7.71234e+1
>console.log((77.1234).toExponential(2)); //7.71e+1
>console.log(77.1234.toExponential(2)); // 숫자 리터럴과 함께 사용하면 error

### 🍀 `toFixed`
> - 숫자를 반올림하여 문자열로 반환
> - 0 ~ 20 사이의 정수값을 인수로 전달할 수 있음. -> 생략시 기본값 0
>```js
>console.log((77.1234).toFixed()); //77
>console.log((77.1234).toFixed(2)); //77.12
>```

### 🍀 `toPrecision`
> - 인수로 전달받은 전체 자릿수까지 유효하도록 나머지 자릿수를 반올림하여 문자열로 반환
> - 0 ~ 20 사이의 정수값을 인수로 전달할 수 있음. -> 생략시 기본값 0
>```js
>console.log((77.1234).toPrecision()); //77.1234
>console.log((77.1234).toPrecision(2)); //77 -> 2자리 유효, 나머지 반올림
>console.log((77.1234).toPrecision(4)); //77.12 -> 4자리 유효, 나머지 반올림
>```

### 🍀 `toString`
> - 숫자를 문자열로 반환. 2 ~ 36사이 진법 지정 가능. 기본값은 10진법
>```js
>(16).toString(2) //10000
>(16).toString(8) //20
>```

## 🔢 Math
### 🔹 Math.PI
> - 원주율 값 반환

### 🔹 Math.abs
> - 인수로 전달된 숫자의 절댓값 반환
>```js
>Math.abs(-1);       // 1
>Math.abs('-1');     // 1
>Math.abs('');       // 0
>Math.abs([]);       // 0
>Math.abs(null);     // 0
>Math.abs(undefined);// NaN
>Math.abs({});       // NaN
>Math.abs('string'); // NaN
>```

### 🔹 Math.round
> - 인수로 전달된 숫자의 소수점 이하를 반올림한 정수 반환
>```js
Math.round(1.6);  // 2
Math.round(-1.4); // -1
Math.round(-1.6); // -2
>```

### 🔹 Math.ceil
> - 인수로 전달된 숫자의 소수점 이하를 올림한 정수 반환
>```js
Math.ceil(1.4);  // 2
Math.ceil(1.6);  // 2
Math.ceil(-1.4); // -1
Math.ceil(-1.6); // -1
>```

### 🔹 Math.floor
> - 인수로 전달된 숫자의 소수점 이하를 내림한 정수 반환
>```js
Math.floor(9.1);  // 9
Math.floor(-1.9); // -2
Math.floor(-9.1); // -10
>```

### 🔹 Math.sqrt
> - 인수로 전달된 숫자의 제곱근을 반환
>```js
Math.sqrt(9);  // 3
Math.sqrt(-9); // NaN
Math.sqrt(2);  // 1.414213562373095
>```

### 🔹 Math.random
> - 임의의 난수 반환

### 🔹 Math.pow
> - 첫 번째 인수를 밑으로, 두 번째 인수를 지수로 한 거듭제곱한 결과 반환
>```js
Math.pow(2, 8);  // 256
Math.pow(2, -1); // 0.5
Math.pow(2);     // NaN
>```

### 🔹 Math.max
> - 전달받은 인수 중 가장 큰 값 반환
>```js
Math.max(1, 2, 3); // 3
>```

### 🔹 Math.min
> - 전달받은 인수 중 가장 작은 값 반환
>```js
Math.min(1, 2, 3); // 1
>```
