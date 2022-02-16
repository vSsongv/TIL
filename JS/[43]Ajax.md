# Ajax
> Ajax에 대해 다룬다. (211228)

## ❓Ajax란?
- 매번 새로운 HTML을 전송받아 웹페이지를 처음부터 렌더링하는 기존 방식에서, 웹페이지의 변경에 필요한 데이터만 **비동기 방식**으로 전송받아 **변경이 필요한 부분만 렌더링**이 가능하게 하는 프로그래밍 방식이다.

- Ajax는 다음과 같은 장점이 있다. 
  1. 변경할 부분을 갱신하는 데 필요한 데이터만 서버로부터 전송받기 때문에 불필요한 데이터 통신이 발생하지 않는다. 
  2. 변경이 필요 없는 부분은 재렌더링하지 않으므로 화면이 깜빡이지 않는다. 
  3. 클라이언트와 서버와의 통신이 비동기 방식으로 동작하기 때문에 서버에게 요청을 받은 이후 블로킹이 발생하지 않는다.
  
## ❓JSON이란?
- json은 클라이언트와 서버 간의 HTTP 통신을 위한 텍스트 데이터 포맷이다. key-value 형식으로 구성되어 있다.
- key와 문자열은 반드시 큰따옴표로 묶어야 한다.
```js
{
  "name": "Ssong",
  "gender": "female",
  "hobby": ["singing", "watching movies"]
}
```
### 🔰 `JSON.stringify`
- 객체를 JSON 포맷의 문자열로 변환한다. _직렬화_ 라고 한다.
- **클라이언트가 서버로 객체를 전송할 때** 사용한다. 
- `JSON.stringify(value[, replacer[, space]])`

&lt;매개변수>
>🔷 **value** 
>- JSON 문자열로 변환할 값.
>
>🔷 **replacer(Option)** 
>- 문자열화 동작 방식을 변경하는 함수, 혹은 JSON 문자열에 포함될 값 객체의 속성들을 선택하기 위한 배열. **이 값이 null 이거나 제공되지 않으면, 객체의 모든 속성들이 JSON 문자열 결과에 포함된다.**
>
>🔷 **space(Option)** 
>- 가독성을 목적으로 JSON 문자열 출력에 공백을 삽입하는데 사용되는 String 또는 Number 객체. 
>- 이것이 Number 라면, 공백으로 사용되는 스페이스(space)의 수를 나타낸다. (이 수가 10 보다 크면 10 으로 제한된다. 1 보다 작은 값은 스페이스가 사용되지 않는 것을 나타낸다.) 
>- 이것이 String 이라면, 그 문자열(만약 길이가 10 보다 길다면, 첫번째 10 개의 문자)이 공백으로 사용된다. 
>- 이 매개 변수가 제공되지 않는다면(또는 null 이면), 공백이 사용되지 않는다.

```js
const SJY = {
  name: 'Ssong',
  age: 24,
  gender: 'female',
  hobby: ['singing', 'watching movies'],
};

// 객체 => JSON 형식의 문자열
const json = JSON.stringify(SJY);
console.log(typeof json, json);
// string {"name":"Ssong","age":"24","gender":"female","hobby":["singing","watching movies"]}

// prettify
// 객체를 json 포맷의 문자열로 변환하면서 들여쓰기한다.
const prettyJson = JSON.stringify(SJY, null, 2);
console.log(typeof prettyJson, prettyJson);
/*
string {
  "name": "Ssong",
  "age": "24",
  "gender": "female",
  "hobby": [
    "singing",
    "watching movies"
  ]
}
*/

// replacer
// 값의 타입이 Number이면 필터링되어 반환되지 않는다.
function filter(key, value) {
  // undefined: 반환하지 않음
  return typeof value === 'number' ? undefined : value;
}

// 객체 => JSON 형식의 문자열 + replacer + prettify
const filteredJson = JSON.stringify(SJY, filter, 2);
console.log(typeof filteredJson, filteredJson);
/*
string {
  "name": "Ssong",
  "gender": "female",
  "hobby": [
    "singing",
    "watching movies"
  ]
}
*/

const arr = ['html', 'css', 'js'];

// 배열 객체 => 문자열
const strArray = JSON.stringify(arr);
console.log(typeof strArray, strArray); // string ["html","css","js"]
console.log(strArray[2]); //h

// replacer
// 모든 값을 대문자로 변환된 문자열을 반환한다
function replaceToUpper(key, value) {
  return value.toString().toUpperCase();
}

// 배열 객체 => 문자열 + replacer
const filteredArray = JSON.stringify(arr, replaceToUpper);
console.log(typeof filteredArray, filteredArray); // string "HTML,CSS,JS"
```

### 🔰 `JSON.parse`
- JSON 포맷의 문자열을 객체로 변환한다. _역직렬화_ 라고 한다.
서버로부터 전송받은 JSON을 이를 객체로서 사용하고자 할 때 사용한다.
- `JSON.parse(text[, reviver])`

&lt;매개변수>
>🔷 **text**
>- JSON으로 변환할 문자열. 배열인 경우 배열의 요소까지 객체로 반환한다.
>
> 🔷 **reviver(Option)**
>- 함수라면, 변환 결과를 반환하기 전에 이 인수에 전달해 변형함.

```js
const SJY = {
  name: 'Ssong',
  age: 24,
  gender: 'female',
  hobby: ['singing', 'watching movies'],
};

// 객체 => JSON 형식의 문자열
const json = JSON.stringify(SJY);
const parsed = JSON.parse(json);
console.log(typeof parsed, parsed);
/* 
object {
  name: 'Ssong',
  age: 24,
  gender: 'female',
  hobby: [ 'singing', 'watching movies' ]
}
*/
const arr = ['html', 'css', 'js'];

// JSON 포맷의 문자열 => 배열 객체
const strArray = JSON.stringify(arr);
const parsedArray = JSON.parse(strArray);
console.log(typeof parsedArray, parsedArray); // object [ 'html', 'css', 'js' ]

// replacer
const replacer = JSON.parse(
  '{"p": 5}',
  (key, value) =>
    typeof value === 'number'
      ? value * 2 // 숫자라면 2배
      : value, // 나머진 그대로
);

console.log(replacer); // { p: 10 }
```

## ❓ `XMLHttpRequest`
- JS를 사용하여 HTTP 요청을 전송하려면 `XMLHttpRequest` 객체를 사용한다.Web API인 `XMLHttpRequest` 객체는 HTTP 요청 전송과 HTTP 응답 수신을 위한 다양한 메서드와 프로퍼티를 제공한다.
### 🔰 `XMLHttpRequest` 객체 생성
```js
const xhr = new XMLHttpRequest();
```
### 💠 `XMLHttpRequest` 객체의 프로토타입 프로퍼티
|프로퍼티명|설명|
|:---:|:---:|
|**`readyState`**|HTTP 요청의 현재 상태를 나타내는 정수. `XMLHttpRequest`의 정적 프로퍼티를 값으로 갖는다.|
|**`status`**|HTTP 요청에 대한 응답 상태(HTTP 상태 코드)를 나타내는 정수(ex: 200)|
|**`statusText`**|HTTP 요청에 대한 응답 메시지를 나타내는 문자열 (ex: OK)|
|**`responseType`**|HTTP 응답 타입 (ex: document, JSON)|
|**`response`**|HTTP 요청에 대한 응답 몸체. responseType에 따라 타입이 다르다.|
|`responseText`|서버가 전송한 HTTP 요청에 대한 응답 문자열|

### 💠 `XMLHttpRequest` 객체의 이벤트 핸들러 프로퍼티
|프로퍼티명|설명|
|:---:|:---:|
|**`onreadystatechange`**|readyState 프로퍼티 값이 변경된 경우|
|`onloadstart`|HTTP 요청에 대한 응답을 받기 시작한 경우|
|`onprogress`|HTTP 요청에 대한 응답을 받는 중 주기적으로 발생|
|**`onerror`**|HTTP 요청에 에러가 발생한 경우|
|**`onload`**|HTTP 요청이 성공적으로 완료한 경우|
|`onabort`|abort 메서드에 의해 HTTP 요청이 중단된 경우|
|`ontimeout`|HTTP 요청시간이 초과한 경우|
|`onloadend`|HTTP 요청이 완료된 경우, HTTP 요청이 성공 또는 실패하면 발생|

### 💠 `XMLHttpRequest` 객체의 메서드
|메서드명|설명|
|:---:|:---:|
|**`open`**|HTTP 요청 초기화|
|**`send`**|HTTP 요청 전송|
|**`abort`**|이미 전송된 HTTP 요청 중단|
|**`setRequestHeader`**|특정 HTTP 요청 헤더의 값을 설정|
|`getResponseHeader`|특정 HTTP 요청 헤더의 값을 문자열로 반환|

### 💠 `XMLHttpRequest` 객체의 정적 프로퍼티
|메서드명|값|설명|
|:---:|:---:|:---:|
|`UNSENT`|0|open 메서드 호출 이전|
|`OPEND`|1|open 메서드 호출 이후|
|`HEADERS_RECEIVED`|2|send 메서드 호출 이후|
|`LOADING`|3|서버 응답 중(응답 데이터 미완성)|
|**`DONE`**|4|서버 응답 완료|

### ➡️ `HTTP` 요청 전송
HTTP 요청 전송 순서는 아래와 같다.

1. `XMLHttpRequest.prototype.open` 메서드로 HTTP 요청을 초기화한다.  
2. 필요에 따라 `XMLHttpRequest.prototype.setRequestHeader` 메서드로 특정 HTTP 요청의 헤더 값을 설정한다.
3. `XMLHttpRequest.prototype.send` 메서드로 HTTP 요청을 전송한다.

```js
// XMLHttpRequest 객체의 생성
const xhr = new XMLHttpRequest();
// HTTP 요청 초기화
xhr.open('GET', '/users');
// 클라이언트가 서버로 전송할 데이터의 MIME 타입 지정: json
xhr.setRequestHeader('content-type', 'application/json');
// Request를 전송한다
xhr.send();
```

### 💠 `open`
- 서버에 전송할 HTTP 요청을 초기화한다.
```js
xhr.open(method, url[, async])
```

|매개변수|설명|
|:---:|:---:|
|`method`|HTTP 요청 메서드(GET, POST, PUT, DELETE)|
|`url`|HTTP 요청을 전송할 URL|
|`async`|비동기 요청 여부, 옵션으로 기본값은 true 이며, 비동기 방식으로 동작한다.|

- HTTP 요청 메서드는 클라이언트가 서버에서 요청의 종류와 목적을 알리는 방법이다. 주로 5가지의 요청 메서드를 사용하여 CRUD를 구현한다.

>✅ 페이로드란 실제 보내고자 하는데이터를 의미한다. 아래 json에서 "data"를 의미한다.
```json
{
	"status" : 
	"from": "localhost",
	"to": "http://melonicedlatte.com/chatroom/1",
	"method": "GET",
	"data":{ "message" : "There is a cutty dog!" }
}
```

|HTTP 요청 메서드|종류|목적|페이로드(전송되는 데이터)|
|:---:|:---:|:---:|:---:|
|`GET`|index/retrieve|모든/특정 리소스 취득|X
|`POST`|create|리소스 생성|O
|`PUT`|replace|리소스의 전체 교체|O
|`PATCH`|modify|리소스의 일부 수정|O
|`DELETE`|delete|모든/특정 리소스 삭제|X

### 💠 `send`
- `open` 메서드로 초기화된 HTTP 요청을 서버에 전송한다. `GET`, `POST` 요청 메서드에 따라 전송 방식에 차이가 있다.

✅ `GET` 요청 메서드의 경우 데이터를 URL의 일부분인 쿼리 문자열로 서버에 전송한다.

✅ `POST` 요청 메서드의 경우 데이터(페이로드)를 Request Body에 담아 전송한다.

![](https://images.velog.io/images/songjy377/post/55182370-e83d-41e0-84b8-7f3d95593bdb/image.png) 
&lt;출처: [fmc-modeling](http://www.fmc-modeling.org/category/projects/apache/amp/2_3Protocols_Standards.html)>

- `send` 메서드에는 페이로드를 인수로 전달할 수 있다. **페이로드가 객체인 경우, 반드시 JSON.stringify 메서드로 직렬화한 다음 전달해야 한다.**
```js
xhr.send(JSON.stringify({id: 1, content: 'HTML', completed: false}))
```
- 만약 요청 메소드가 `GET`인 경우, `send` 메소드의 인수는 무시되고 request body는 `null`로 설정된다.

### 💠 `setRequestHeader`
- HTTP Request Header의 값을 설정한다. `setRequestHeader` 메소드는 반드시 `open` 메소드를 호출한 이후에 호출해야 한다.

- 자주 사용하는 Request Header인 `Content-type`, `Accept`에 대해 살펴보자.

🔰 `Content-type`
- `Content-type`은 request body에 담아 전송할 데이터의 MIME-type의 정보를 표현한다. 자주 사용되는 MIME-type은 아래와 같다.

타입|	서브타입|
|:--:|:--:|
text |	text/plain, text/html, text/css, text/javascript
Application |	application/json, application/x-www-form-urlencode
File을 업로드하기 위한 타입	|multipart/formed-data

```js
// json 전송 예제
// 요청 초기화
xhr.open('POST', '/users');

// 클라이언트가 서버로 전송할 데이터의 MIME-type 지정: json
xhr.setRequestHeader('Content-type', 'application/json');

// HTTP 요청 전송
xhr.send(JSON.stringify({id: 1, content: 'HTML', completed: false}))

// x-www-form-urlencoded으로 전송 예제
xhr.open('POST', '/users');

// 클라이언트가 서버로 전송할 데이터의 MIME-type 지정: x-www-form-urlencoded
// application/x-www-form-urlencoded는 key=value&key=value...의 형태로 전송
xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

const data = { title: 'JavaScript', author: 'Park', price: 5000};

xhr.send(Object.keys(data).map(key => `${key}=${data[key]}`).join('&'));
// escaping untrusted data
// xhr.send(Object.keys(data).map(key => `${key}=${encodeURIComponent(data[key])}`).join('&'));
```

🔰 `Accept`
- HTTP 클라이언트가 서버에 요청할 때, 서버가 응답할 데이터의 MIME-type을 `Accept`로 지정할 수 있다.
```js
// 서버가 응답할 데이터의 MIME-type 지정: json
xhr.setRequestHeader('Accept', 'application/json');
```
- 만약 Accept 헤더를 설정하지 않으면, send 메소드가 호출될 때 Accept 헤더가 \*/*으로 전송된다.

### ⬅️ `HTTP` 응답 처리
- 서버의 응답을 처리하려면 `XMLHttpRequest` 객체가 발생시키는 이벤트를 캐치해야 한다.
- `HTTP` 요청의 현재 상태를 나타내는 `readyState` 프로퍼티 값이 변경된 경우 발생하는 `readystatechange` 이벤트를 캐치하여 처리할 수 있다.
```js
// XMLHttpRequest 객체의 생성
const xhr = new XMLHttpRequest();

// XMLHttpRequest.readyState 프로퍼티가 변경(이벤트 발생)될 때마다 onreadystatechange 이벤트 핸들러가 호출된다.
xhr.onreadystatechange = function (e) {
  // readyStates는 XMLHttpRequest의 상태(state)를 반환
  // readyState: 4 => DONE(서버 응답 완료)
  if (xhr.readyState !== XMLHttpRequest.DONE) return;

  // status는 response 상태 코드를 반환 : 200 => 정상 응답
  if(xhr.status === 200) {
    console.log(xhr.responseText);
  } else {
    // 200이 아닌 경우 에러 발생 상태
    console.log('Error!');
  }
};
```
- `send` 메서드를 통해 HTTP 요청을 서버에 전송하면 서버는 응답을 반환한다. 하지만 언제 클라이언트에 도달하는지를 알 수 없기 때문에 `readyState` 프로퍼티가 변경될 때마다 `readystatechange` 이벤트로 확인하는 것이 좋다.
- `readystatechange` 이벤트 대신 `load` 이벤트를 캐치해도 된다. `load` 이벤트는 `HTTP`요청이 성동적으로 완료된 경우 발생하기 때문이다.

```js
var xhr = new XMLHttpRequest();
xhr.open('GET', 'data/test.json');
xhr.send();

// load 이벤트는 서버 응답이 완료된 경우에 발생한다.
xhr.onload = function (e) {
  //정상적으로 응답한 상태라면 reponse 프로퍼티에 서버의 응답 결과가 담겨 있다.
  if(xhr.status === 200) {
    console.log(xhr.responseText);
  } else {
    console.log('Error!');
  }
};
```
