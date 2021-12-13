# Array
> Array의 정의, 호출 등과 많은 메서드들에 대해 정리하였다.(211208)

## ♒ Array 메서드 종류
> - 배열은 원본 배열을 **직접 변경하는 메서드**와 **새로운 배열을 생성하여 반환하는 메서드**가 있다.
> - 가급적 원복 배열을 직접 변경하지 않는 메서드를 사용하는 것이 좋다.

### ♊ `isArray()`
> - 전달된 인수가 배열인지 확인하여 boolean값을 반환한다.
> - 유일한 static 메서드이다. 나머지는 전부 prototype 메서드이다.
>```js
>console.log(Array.isArray([])); //true
>console.log(Array.isArray(null)); //false
>```

### ♊ `indexOf()`
> - 인수로 전달된 요소를 검색하여 인덱스를 반환한다.
> - 중복되는 요소가 여러 개 있다면 첫번째로 검색된 요소의 인덱스를 반환한다.
>```js
>//indexOf
>const arr = [1, 2, 2, 3];
>arr.indexOf(2); //1
>arr.indexOf(4); //-1 -> 없는 값이므로 -1을 반환한다.
>arr.indeOf(2, 2); // 두 번째 요소는 검색을 시작할 인덱스다. 두 번째 인수를 생략하면 처음부터 검색한다.

### ♊ `push() & pop() & unshift() & shift()`
> 📌 **원본 배열을 직접 변경한다.**
> - `push` : 배열에 인수로 전달받은 모든 값을 배열의 마지막 요소로 추가하고, 변경된 length의 프로퍼티 값을 반환한다. 
> - `push`는 부수 효과가 있으므로 length 프로퍼티를 이용하여 추가하거나, spread syntax를 사용하는 것이 좋다.
>```js
>const arr = [1, 2];
>const newArr = [...arr, 4]; //원본 배열 유지하면서 마지막에 요소 추가 가능.
>console.log(newArr); //[1, 2, 4]
>```
> - `pop` : 원본 배열에서 마지막 요소로 추가한  요소를 제거하고, 제거한 요소를 반환한다. 
> - `unshift` : 인수로 전달받은 모든 값을 배열의 선두에 추가하고 변경된 length 프로퍼티 값을 반환한다.
> - `unshift`는 부수 효과가 있으므로 length 프로퍼티를 이용하여 추가하거나, spread syntax를 사용하는 것이 좋다.
>```js
>const arr = [1, 2];
>const newArr = [3,...arr]; //원본 배열 유지하면서 처음에 요소 추가 가능.
>console.log(newArr); //[3, 1, 2]
>```
> - `shift` : 원본 배열에서 첫 번째 요소를 제거하고 제거한 요소를 반환한다. 

### ♊ concat()
> - 인수로 전달된 값들을 원본 배열의 마지막 요소로 추가한 새로운 배열을 반환한다.
> - 인수로 전달한 값이 배열인 경우 **배열을 해체하여** 새로운 배열의 요소로 추가한다.
>📌 **원본 배열은 변경되지 않는다.**
>```js
>const arr1 = [1, 2];
>const arr2 = [3, 4];
>
>let result = arr1.concat(arr2);
>console.log(result); //[ 1, 2, 3, 4 ]
>console.log(arr1); //[ 1, 2 ] -> 원본 변경 안 됨.
>```

### ♊ `splice()`
> 3개의 매개변수가 존재한다. 
> `string.splice('start', 'deleteCount', 'itmes')`
> 📌 **원본 배열을 직접 변경한다.**
> - `start` 인덱스부터  `deleteCount`개를 삭제하고, 그 자리에 `item`들을 넣는다.
>- start: -1이면 마지막 요소를 가리키고, -n은 뒤에서부터 n번째 요소.
> - deleteCount(✔️옵션) : - 0으로 지정하면 새로운 요소들 삽입만 해주고, 생략하면 start부터 모든 요소를 제거한다.
> - item(✔️옵션)
>```js
>const arr1 = [1, 2, 3, 4];
>
>let result = arr1.splice(1, 2, 20, 30); //1번 인덱스부터 2개 없애고 20, 30 >채워넣어라
>console.log(result); //[ 2, 3 ] //제거 요소
>console.log(arr1); //[ 1, 20, 30, 4 ] -> 원본 변경.
>```
> - 배열에서 특정 원소를 제거하려면 `indexOf`로 index를 찾아서 splice로 잘라주면 된다.
> - `filter`메서드를 사용하여 제거할 수도 있다. 그러나 특정 요소가 중복된 경우 모두 제거된다는 문제가 있다.
>```js
>const arr1 = [1, 2, 3, 4, 2];
>
>function removeAll(arr, item) {
>    return arr.filter(v => v !== item);
>}
>
>console.log(removeAll(arr1, 2)); //[ 1, 3, 4 ]
>```

### ♊ `slice()`
> `string.slice('startIdx', 'endIdx')` 
> - `start`부터 `endIdx`전까지 얕은 복사본을 새로운 배열 객체로 반환한다. 
> - 매개변수를 모두 생략하면 전체 배열의 얕은 복사본을 반환한다.
>📌 **원본 배열이 변경되지 않는다.**
>```js
>const arr = [ 1, 2, 3, 4, 2 ];
>//0번 인덱스부터 1번 인덱스까지 잘라낸 배열 반환
>console.log(arr.slice(0, 2)); //[ 1, 2 ]
>//2번 인덱스부터 모두 잘라낸 배열 반환.
>console.log(arr.slice(2)); //[ 3, 4, 2 ]
>
>console.log(arr); //[ 1, 2, 3, 4, 2 ] -> 원본 안 바뀜
>
>//인수를 모두 생략하면 원본 배열의 얕은 복사본을 생성하여 반환한다.
>console.log(arr.slice()); //[1, 2, 3, 4, 2]
>
>//첫번째 인수가 음수면 끝에서부터 복사해서 배열로 반환
>console.log(arr.slice(-1)); //[2]
>console.log(arr.slice(-2)); //[4, 2]
>
>const todos = [
>    { id: 1, content: 'HTML', completed: false },
>    { id: 2, content: 'CSS', completed: true },
>    { id: 3, content: 'Javascript', completed: false }
>];
>
>// shallow copy
>const _todos = todos.slice();
>// const _todos = [...todos];
>console.log(_todos === todos); // false
>
>// 배열의 요소는 같다. 즉, 얕은 복사되었다.
>console.log(_todos[0] === todos[0]); // true
>_todos[0]['id'] = 3;
>console.log(todos[0]['id']); //3 -> 값이 변경된다.
>
>//유사 배열 객체를 배열로 변환한다.
>function makeArray() {
>    const arr = Array.prototype.slice.call(arguments);
>    const arr = Array.from(arguments); //Array.from 이용
>    return arr;
>}
>console.log(makeArray(1, 2, 3, 4));
>```

### ♊ `join()` & `reverse()`
> - `join`: 배열의 모든 요소를 문자열로 변환하여 구분자(✔️옵션)로 연결한 하나의 문자열로 반환한다. 기본 구분자는 콤마(,)다.
> - `reverse` : 원본 배열의 순서를 반대로 뒤집는다. 📌 **원본 배열이 변경된다.**
>```js
>const arr = [1, 2, 3, 4];
>
>//원본 배열의 요소들을 문자열로 변경하여 연결한다.
>console.log(arr.join()); //1,2,3,4
>console.log(arr.join('')); //1234
>console.log(arr.join(':')); //1:2:3:4
>
>//원본 배열의 순서를 반대로 뒤집는다.
>console.log(arr.reverse()); //[ 4, 3, 2, 1 ] 
>console.log(arr.reverse().join('')); //4321 -> arr이 origin상태라고 가정, reverse후 연결
>```

### ♊ `fill()`
> - 인수로 전달받은 값을 배열의 처음부터 끝까지 채운다. 📌 **원본 배열이 변경된다.**
>```js
>let arr = [1, 2, 3, 4];
>console.log(arr.fill(0)); //[0, 0, 0, 0]
>
>//두 번째 인수로 채우기를 시작할 인덱스를 전달할 수 있다. 
>arr = [1, 2, 3, 4];
>console.log(arr.fill(0, 2)); //[ 1, 2, 0, 0 ]
>
>//세 번째 인수로 채우기를 멈출 인덱스를 전달할 수 있다. 
>arr = [1, 2, 3, 4];
>console.log(arr.fill(0, 1, 2)); //[ 1, 0, 3, 4 ]
>
>//fill 메서드로는 하나의 값만으로밖에 채울 수가 없다.
>//두 번째 인수로 콜백 함수를 사용해서 인덱스를 이용해 다른 값들을 가지는 배열을 생성할 수 있다.
>const seq = (length = 0) => Array.from({ length }, (_, i) => i);
>console.log(seq(5)); //[ 0, 1, 2, 3, 4 ]
>```

### ♊ `includes()`
> - 배열에 특정 요소가 포함되어 있는지 확인하여 boolean값을 리턴한다.
> - 두 번재 인수로 검색을 시작할 인덱스를 전달할 수 있다.
>```js
>const arr = [1, 2, 3];
> arr.includes(3) //true
> arr.includes(2, 1) //true -> 인덱스 1부터 검사한다.
>```

### ♊ `flat()`
> - 인수로 전달한 깊이만큼 재귀적으로 배열을 평탄화한다.
>```js
>console.log([1, [2, [3, [4]]]].flat()); //[ 1, 2, [ 3, [ 4 ] ] ]
>
>console.log([1, [2, [3, [4]]]].flat(2)); //[ 1, 2, 3, [ 4 ] ]
>//위의 코드는 아래와 동일하다.
>console.log([1, [2, [3, [4]]]].flat().flat()); //[ 1, 2, 3, [ 4 ] ]
>
>console.log([1, [2, [3, [4]]]].flat(Infinity)); //[ 1, 2, 3, 4 ] -> 무한으로 설정하여 모두 평탄화한다.
>```

## 🔯 Array 고차 함수
> - 고차 함수는 함수를 인수로 전달받거나 함수를 반환하는 함수를 말한다. 자바스크립트의 함수는 일급 객체이므로 함수를 값처럼 인수로 전달할 수 있으며 반환할 수도 있다. 
>- 고차 함수는 외부 상태의 변경이나 가변 데이터를 피하고 **불변성을 지향**하는 함수형 프로그래밍에 기반을 두고 있다.

### ✡️ `sort()`
> - 배열의 요소를 정렬하고 정렬된 배열을 반환한다. 
> 📌 **원본 배열을 직접 변경한다.**
> - 기본적으로 오름차순으로 요소를 정렬한다.
> - 숫자는 기본적으로 문자열로 인식되어 정렬된다. -> 정렬 순서를 정의하는 비교 함수를 인수로 전달해야 한다.
>```js
>const fruits = ['Apple', 'Peach', 'Melon'];
>//알파벳/한글 오름차순 정렬
>console.log(fruits.sort()); //[ 'Apple', 'Melon', 'Peach' ]
>
>const arr = [40, 100, 1, 5, 2, 25, 10];
>
>console.log(arr.sort()); //[1, 10, 100, 2, 25, 40, 5] -> 숫자 크기대로 정렬되지 않는다.
>// 비교 함수의 반환값이 0보다 작으면 a를 우선하여 정렬한다.
>// 내림차순은 b - a로 변경하면 된다.
>arr.sort((a, b) => a - b);
>console.log(arr); // [1, 2, 5, 10, 25, 40, 100]
>
>const todos = [
>    { id: 3, content: 'Javascript', completed: false },
>    { id: 1, content: 'HTML', completed: false },
>    { id: 2, content: 'CSS', completed: true }
>];
>
>function compare(key) {
>    return (a, b) => (a[key] > b[key] ? 1 : (a[key] < b[key] ? -1 : 0));
>}
>
>todos.sort(compare('id'));
>console.log(todos);
>
>todos.sort(compare('content'));
>console.log(todos);
>```

### ✡️ `forEach()`
> - **조건문과 반복문을 제거하고, 변수의 사용을 억제하는 함수형 프로그래밍의 취지에 맞게, for문을 대체할 수 있는 함수다.**
> - forEach 메서드는 반복문을 추상화한 고차 함수로서 내부에서 반복문을 통해 자신을 호출한 배열을 순회하면서 수행해야 할 처리를 콜백 함수로 전달받아 반복 호출한다.
> - **배열의 모든 요소를 빠짐없이 순회하고, break, continue등의 문을 사용할 수 없기 때문에 중간에 순회를 중단할 수 없다.**
>📌 원본 배열(호출한 배열, this)을 변경하지 않는다. 그러나 콜백 함수를 통해 원본 배열을 변경할 수는 있다.
>
>```js
>const numbers = [1, 2, 3];
>const pows = [];
>
>numbers.forEach(item => pows.push(item ** 2));
>console.log(pows); // [1, 4, 9]
>
>const numbers = [1, 2, 3];
>// 콜백 함수의 arr을 직접 변경하면 원본 배열 numbers가 변경된다.
>numbers.forEach((item, index, arr) => { arr[index] = item ** 2});
>console.log(numbers); // [1, 4, 9]
>// forEach 메서드의 반환값은 언제나 undefined다.
>const result = [1, 2, 3].forEach(console.log);
>console.log(result); //undefined
>
>//forEach 메서드의 두 번째 인수로 메서드의 콜백 함수 내부에서 this로 사용할 객체를 전달할 수 있다.
>//화살표 함수를 이용한다.
>class Number {
>    numberArray = [];
>    multiply(arr) {
>        //화살표 함수 내부에서 this를 참조하면 상위 스코프의 this를 그대로 참조한다.
>        arr.forEach(item => this.numberArray.push(item * item));
>    }
>}
>
>const numbers = new Number();
>numbers.multiply([1, 2, 3]);
>console.log(numbers.numberArray); //[ 1, 4, 9 ]
>```

### ✡️ `map()`
> - map 메서드는 자신을 호출한 배열의 모든 요소를 순회하면서 인수로 전달받은 콜백 함수를 반복 호출한다. 그리고 **콜백 함수의 반환값들로 구성된 새로운 배열을 반환한다.**
>📌 원본 배열은 변경되지 않는다.
>```js
>const numbers = [1, 4, 9];
>const roots = numbers.map(item => Math.sqrt(item));
>console.log(roots); // [1, 2, 3] -> 배열의 요소를 순회하면서 새로운 배열 반환
>console.log(numbers); // [1, 4, 9] -> 원본 안 바뀜
>
>// map(요소값, 인덱스, this)
>[1, 2, 3].map((item, index, arr) => {
>    console.log(item, index, arr);
>})
>/*
>1 0 [ 1, 2, 3 ]
>2 1 [ 1, 2, 3 ]
>3 2 [ 1, 2, 3 ]
>*/
>// > - 메서드의 콜백 함수 내부에서 this로 사용할 객체를 전달할 수 >있다.
>class Prefixer {
>    constructor(prefix) {
>        this.prefix = prefix;
>    }
>    add(arr) {
>        return arr.map(item => this.prefix + item);
>    }
>}
>
>const prefixer = new Prefixer('-webkit-');
>console.log(prefixer.add(['transition', 'user-select'])); //[ >'-webkit-transition', '-webkit-user-select' ]
>```


> ❓ forEach 메서드와 map 메서드
>  ✔️공통점 : 자신을 호출한 배열의 모든 요소를 순회하면서 인수로 전달받은 콜백 함수를 반복 호출한다는
> ✔️ 차이점 : **forEach 메서드는 언제나 undefined를 반환**하고, **map 메서드는 콜백 함수의 반환값들로 구성된 새로운 배열을 반환**하는 차이가 있다. 즉, **forEach 메서드는 단순히 반복문을 대체하기 위한 고차 함수**이고, **map 메서드는 요소값을 다른 값으로 매핑한 새로운 배열을 생성**하기 위한 고차 함수다.

### ✡️ `filter()`
> - filter 메서드는 자신을 호출한 배열의 모든 요소를 순회하면서 인수로 전달받은 콜백 함수를 반복 호출한다. 그리고 **콜백 함수의 반환값이 true인 요소로만 구성된 새로운 배열을 반환한다.**
> - 반환된 배열의 length는 filter를 호출한 배열의 length 프로퍼티 값과 같거나 작다.
>📌 원본 배열은 변경되지 않는다.
>```js
>const numbers = [1, 2, 3, 4, 5];
>//짝수인 숫자들만 필터링
>const odds = numbers.filter(item => item % 2);
>console.log(odds); // [1, 3, 5]
>
>// filter(요소값, 인덱스, this)
>[1, 2, 3].filter((item, index, arr) => {
>    console.log(item, index, arr);
>})
>
>//자신을 호출한 배열에서 특정 요소를 제거할 수도 있다.
>class User {
>    constructor() {
>        this.users = [
>            { id: 1, name: 'Song' },
>            { id: 2, name: 'Jinyoung' }
>        ]
>    };
>
>    findById(id) {
>        //id가 일치하는 사용자만 반환한다.
>        return this.users.filter(user => user.id === id);
>    }
>
>    remove(id) {
>        //id가 일치하지 않는 사용자를 제거한다.
>        this.users = this.users.filter(user => user.id !== id);
>    }
>}
>
>
>const user = new User();
>
>console.log(user.findById(1)); //[ { id: 1, name: 'Song' } ]
>user.remove(2);
>console.log(user); //{ users: [ { id: 1, name: 'Song' } ] }
>```

### ✡️ `reduce()`
> - reduce 메서드는 자신을 호출한 배열을 모든 요소를 순회하며 인수로 전달받은 콜백 함수를 반복 호출한다. 그리고 콜백 함수의 반환값을 다음 순회 시에 콜백 함수의 첫 번째 인수로 전달하면서 콜백 함수를 호출하여 **하나의 결과값을 만들어 반환한다.**
> reduce(콜백 함수, 초기값(✔️옵션)) -> 초기값은 꼭 전달하는 것이 좋다.
> 📌이때 원본 배열은 변경되지 않는다.
>```js
>//reduce(콜백 함수, 초기값(✔️옵션))
>// 누적 합 
>const sum = [1, 2, 3, 4].reduce((accumulator, currentValue, index, array) => accumulator + currentValue, 0);
>console.log(sum); // 10
>
>//reduce를 활용한 평균 값 구하기
>const numbers = [1, 2, 3, 4, 5];
>
>const avg = numbers.reduce((acc, cur, i, { length }) => {
>    return i === length - 1 ? (acc + cur) / length : acc + cur;}, 0);
>
>console.log(avg);
>
>//reduce를 활용한 최대 값 구하기
>const values = [1, 2, 3, 4, 5];
>const max = values.reduce((acc, cur) => (acc > cur ? acc : cur), 0);
>console.log(max); // 5
>
>//reduce를 활용한 중첩 배열 평탄화
>const numbers1 = [1, [2, 3], [4, 5]];
>
>const flat = numbers1.reduce((acc, cur) => acc.concat(cur), []);
>console.log(flat);
>```

### ✡️ `some()`
>- some 메서드는 자신을 호출한 배열의 요소를 순회하면서 인수로 전달된 콜백 함수를 호출한다. 이때 some 메서드는 콜백 함수의 반환값이 **단 한 번이라도 참이면 `true`**, 모두 거짓이면 `false`를 반환한다.
> - this 전달 가능
>❗ some 메서드를 호출한 배열이 빈 배열인 경우 언제나 `false`를 반환한다.
>```js
>[5, 10, 15].some(item => item > 10); // true
>[5, 10, 15].some(item => item < 0); // false
>[].some(item => item > 3)// false
>```

### ✡️ `every()`
> - every 메서드는 자신을 호출한 배열의 요소를 순회하면서 인수로 전달된 콜백 함수를 호출한다. 이때 every 메서드는 콜백 함수의 반환값이 **모두 참이면 `true`**, 단 한번이라도 거짓이면 `false`를 반환한다.
> - `this` 전달 가능
>❗ every 메서드를 호출한 배열이 빈 배열인 경우 언제나 true를 반환한다.
>```js
>[5, 10, 15].every(item => item > 3); // true
>[5, 10, 15].every(item => item > 10); // false
>[].every(item => item > 3)// true
>```

### ✡️ `find()`
> - 자신을 호출한 배열의 요소를 순회하면서 인수로 전달된 콜백 함수를 호출하여 **반환값이 true인 첫 번째 요소를 반환**한다.
> - `this` 전달 가능
> - 콜백 함수의 반환값이 `true`인 요소가 존재하지 않는다면 `undefined`를 반환한다.
>```js
>const users = [
>  { id: 2, name: 'Kim' },
>  { id: 2, name: 'Choi' }
>];
>//id가 2인 첫번째 요소 반환
>users.find(user => user.id === 2); // {id: 2, name: 'Kim'}
>//filter 메서드는 배열을 반환한다.
>[1, 2, 3, 4].filter(item => item === 2); //[2, 2]
>//find 메서드는 요소를 반환한다.
>[1, 2, 3, 4].filter(item => item === 2); //2
>```

### ✡️ `findIndex()`
> - 자신을 호출한 배열의 요소를 순회하면서 인수로 전달된 콜백 함수를 호출하여 **반환값이 true인 첫 번째 요소의 인덱스를 반환**한다. 콜백 함수의 반환값이 **true인 요소가 존재하지 않는다면 -1을 반환**한다.
>```js
>const users = [
>    { id: 2, name: 'Kim' },
>    { id: 2, name: 'Song' }
>];
>
>console.log(users.findIndex(user => user.id === 2)); // 0
>console.log(users.findIndex(user => user.name === 'Song')); // 1
>console.log(users.findIndex(user => user.univ === 2)); // -1
>```

### ✡️ `flatMap()`
> - map 메서드를 통해 생성된 새로운 배열을 평탄화한다. 즉, map 메서드와 flat 메서드를 순차적으로 실행하는 효과가 있다.
```js
>const arr = ['hello', 'world'];
>
>console.log(arr.flatMap(x => x.split(''))); // 'h', 'e', 'l', 'l', 'o', 'w', 'o', 'r', 'l', 'd']
>
>// 평탄화 깊이를 지정할 수는 없고 1단게만 평탄화한다.
>
>const arr1 = ['hello', 'world'];
>console.log(arr1.flatMap((str, index) => >[index, [str, str.length]]));
>//[[ 0, [ 'hello', 5 ]], 1, [ 'world', 5 ] ]]] => [ 0, [ 'hello', 5 ], 1, [ 'world', 5 ] ]
```

> 📌 배열 메소드를 사용할 때는 먼저 `map, filter`로 구현이 가능한지를 생각해 보라(결과값과 원본의 개수가 같다면 `map`을, 적거나 같으면 `filter`를 사용해라.). 이들로 안 되면 다른 고차 함수들을 사용해 봐라. 안 되면 `reduce`, 를 생각해 보고, 그것도 안 되면 `for of`, 마지막으로 `forEach`를 이용해라.