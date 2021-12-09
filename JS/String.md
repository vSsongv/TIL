# String
> - String의 메서드들에 대해 정리했다.(211210)

## 🆎 String 메서드
> - **String 객체의 메서드는 언제나 새로운 문자열을 반환한다.** 
> - 문자열은 변경 불가능한 원시 값이기 때문에 String 래퍼 객체도 **읽기 전용** 객체로 제공된다. 

### 📍 indexOf
> -  indexOf 메서드는 대상 문자열에서 인수로 전달받은 문자열을 검색하여 첫 번째 인덱스를 반환한다. 
> - 검색에 실패하면 -1을 반환한다.
```js
const str = 'Hello World';
str.indexOf('l'); // 2
str.indexOf('or'); // 7
str.indexOf('x'); // -1
str.indexOf('l', 3); // 3 , 인덱스 3부터 l을 검색하여 첫 번째 인덱스 반환
```

### 📍 search
> - search 메서드는 대상 문자열에서 인수로 전달받은 정규 표현식과 매치하는 문자열을 검색하여 첫번째로 일치하는 문자열의 인덱스를 반환한다. 
> -검색에 실패하면 -1을 반환한다.
```js
const str = 'Hello World';

str.search(/o/); // 4
str.search(/r/); // 8
```

### 📍 includes
> - ES6에서 도입된 includes 메서드는 대상 문자열에 인수로 전달받은 문자열이 포함되어 있는지 확인하여 boolean 값을 반환한다.
```js
const str = 'Hello World';

str.includes('Hello'); // true
str.includes(''); // true
str.includes('H', 4); // false
```

### 📍 startsWith
> - ES6에서 도입된 startsWith 메서드는 대상 문자열이 인수로 전달받은 문자열로 시작하는지 확인하여 boolean값으로 반환한다.
```js
const str = 'Hello World';
str.startsWith('He'); // true
//2번째 인수로 검색을 시작할 인덱스를 전달할 수 있다.
str.startsWith(' ', 5); //true
```

### 📍 endsWith
> - ES6에서 도입된 endsWith 메서드는 대상 문자열이 인수로 전달받은 문자열로 끝나는지 확인하여 그 결과를 boolean 값으로 반환한다.
```js
const str = 'Hello World';
str.endsWith('ld'); // true
//2번째 인수로 검색할 문자열의 길이를 전달할 수 있다.
const str = 'Hello World';
str.endsWith('lo', 5); // true
```

### 📍 charAt
> - charAt 메서드는 대상 문자열에서 인수로 전달받은 인덱스에 위치한 문자를 검색하여 반환한다.
> - ❗ 인덱스가 문자열의 범위를 벗어난 정수인 경우 빈 문자열을 반환
```js
const str = 'Hello World';

console.log(str.charAt(7)); //o

for(let i = 0; i < str.length; i++) {
  console.log(str.charAt(i)); // H e l l o
}
```

### 📍 substring
> - `string.substring('startIdx', 'endIdx')` startIdx부터, endIdx 전까지 자른다. startIdx만 넣으면 startIdx부터 맨 끝까지 자른다.
```js
const str = 'Hello World';
// index 1 ~ (4-1) 까지 부분 문자열 반환
str.substring(1, 4); // ell
str.substring(1); // ello World

str.substring(0, str.indexOf(' ')); // -> 'Hello'
```

### 📍 slice
> - `string.slice('startIdx', 'endIdx')` 
startIdx부터, endIdx 전까지의 얕은 복사본을 새로운 배열 객체로 반환한다.
> - 값을 안 넣으면 전체 배열을 반환한다.
> - 음수인 인수를 전달하면 대상 문자열의 가장 뒤에서부터 시작하여 문자열을 잘라내어 반환한다.
```js
const str = 'Hello World';

str.substring(0, 5); // hello
str.slice(0, 5); // hello
str.slice(-5); // world
```

### 📍 toUpperCase & toLowerCase
> - toUpperCase(toLowerCase) 메서드는 대상 문자열을 모두 대문자(소문자)로 변경한 문자열을 반환한다.
```js
const str = 'Hello World';

str.toUpperCase(); // 'HELLO WORLD'
str.toLowerCase(); // 'hello world'
```

### 📍 trim
> - trim 메서드는 대상 문자열 앞뒤에공백 문자가 있을 경우 이를 제거한 문자열을 반환한다.
```js
const str = '  foo  ';
str.trim(); // 'foo'

str.trimStart() // 'foo   '
str.trimEnd() // '   foo'
```

### 📍 repeat
> - ES6에서 도입된 repeat 메서드는 대상 문자열을 인수로 전달받은 정수만큼 반복해 연결한 새로운 문자열을 반환한다.
> - 0을 전달받으면 빈 문자열을 반환한다.
```js
const str = 'abc';
str.repeat(0); // ''
str.repeat(1); // 'abc'
str.repeat(3); // 'abcabcabc'
str.repeat(2.5); // ❌ RangeError: Invalid count value
```

### 📍 replace
> - replace 메서드는 대상 문자열에서 첫 번째 인수로 전달받은 문자열 또는 정규표현식을 검색하여 두 번째 인수로 전달한 문자열로 치환한 문자열을 반환한다.
> - 원본 문자열이 변경되지 않으므로, 변수에 담도록 해라. 
```js
const str = 'Hello world';

str.replace('world', 'Lee'); // 'Hello Lee'
console.log(str.replace('world', 'Lee')); // 'Hello Lee'
console.log(str); // Hello world
```

### 📍 split
> - split 메서드는 대상 문자열에서 첫 번째 인수로 전달한 문자열 또는 정규 표현식으로 문자열을 구분한 후 분리된 각 문자열로 이루어진 배열을 반환한다.
> - 구분자로 사용된 문자열은 배열 안에 포함되지 않는다.
```js
const str = 'Hello world?';

str.split(' '); // [ 'Hello', 'world']
str.split('l'); // [ 'He', '', 'o wor', 'd' ]