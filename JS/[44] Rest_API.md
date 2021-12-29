# REST API
> REST API에 대해 알아보고, 실습한 내용을 정리했다.(211229)

## ❓ REST API
- REST는 `HTTP`를 기반으로 클라이언트가 서버의 리소스에 접근하는 방식을 규정한 아키텍처다.
- `HTTP`의 장점을 최대한 활용할 수 있는 아키텍처로서 REST(REpresentational State Transfer)가 나오게 되었고, HTTP 프로토콜을 의도에 맞게 디자인하도록 유도하고 있다. **REST의 기본 원칙을 잘 지킨 서비스 디자인을 `RESTful`이라고 표현한다.**
- REST API는 REST를 기반으로 서비스 API를 구현한 것이다.

### 💠 REST 특징
1. **Server-Client(서버-클라이언트 구조)**
- 자원이 있는 쪽이 Server, 자원을 요청하는 쪽이 Client가 된다. 각각의 역할이 확실히 구분되기 때문에 클라이언트와 서버에서 개발해야 할 내용이 명확해지고 서로간 의존성이 줄어든다.
- REST Server: API를 제공하고 비즈니스 로직 처리 및 저장을 책임진다.
- Client: 사용자 인증이나 context(세션, 로그인 정보) 등을 직접 관리하고 책임진다.
2. **Stateless(무상태)**
- REST는 무상태성 성격을 가진다. 다시 말해 작업을 위한 상태정보를 따로 저장하고 관리하지 않는다. 세션 정보나 쿠키 정보를 별도로 저장하고 관리하지 않기 때문에 API 서버는 들어오는 요청만을 단순히 처리하면 된다. 그래서 서비스의 자유도가 높아지고 서버에서 불필요한 정보를 관리하지 않음으로써 구현이 단순해진다.
3. **Cacheable(캐시 처리 가능)**
- REST의 가장 큰 특징 중 하나는 HTTP라는 기존 웹 표준을 그대로 사용하기 때문에, 웹에서 사용하는 기존 인프라를 그대로 활용할 수 있다. 따라서 HTTP가 가진 캐싱 기능이 적용할 수 있다. HTTP 프로토콜 표준에서 사용하는 Last-Modified 태그나 E-Tag를 이용하면 캐싱 구현이 가능하다.
4. **Layered System(계층화)**
- REST 서버는 다중 계층으로 구성될 수 있으며 보안, 로드 밸런싱, 암호화 계층을 추가해 구조상의 유연성을 둘 수 있고 PROXY, 게이트웨이 같은 네트워크 기반의 중간매체를 사용할 수 있게 한다.
5. **Self-descriptiveness (자체 표현 구조)**
- REST의 또 다른 큰 특징 중 하나는 REST API 메시지만 보고도 이를 쉽게 이해 할 수 있는 자체 표현 구조로 되어 있다.
6. **Uniform Interface(인터페이스 일관성)**
- URI로 지정한 Resource에 대한 조작을 통일되고 한정적인 인터페이스로 수행한다.
즉 HTTP 표준 프로토콜에 따르는 모든 플랫폼에서 사용이 가능하고 특정 언어나 기술에 종속되지 않는다.

### 💠REST API 구성
- REST API는 자원(resource), 행위(verb), 표현(representations)의 3가지 요소로 구성된다. 
-  REST는 자체 표현 구조(Self-descriptiveness)로 구성되어 REST API만으로 요청을 이해할 수 있다.

구성 요소|	내용|	표현 방법
|:---:|:---:|:---:|
Resource|	자원|	HTTP URI
Verb|	자원에 대한 행위|	HTTP 요청 메서드
Representations|	자원에 대한 행위의 내용|	Pay Load


### 💠 REST API 설계 원칙
**1. URI는 리소스를 표현해야 한다.**
- 리소스를 식별할 수 있는 이름은 동사보다는 **명사**를 사용한다. 이름에 get 같은 행위에 대한 표현이 들어가서는 안 된다.
```js
# bad
GET /getTodos/1
GET /todos/show/1

# good
GET /todos/1
```
**2. 리소스에 대한 행위는 HTTP 요청 메서드로 표현한다.**
- HTTP 요청 메서드는 클라이언트가 서버에게 요청의 종류와 목적을 알리는 방법이다. 주로 5가지 요청 메서드(GET, POST, PUT, PATCH, DELETE)를 사용해 CRUD를 구현한다.

![](https://images.velog.io/images/songjy377/post/289cea21-2806-489d-ac43-ced3f6d78069/image.png)

```js
# bad
GET /todos/delete/1

# good
DELETE /todos/1
```
- 리소스를 취득하는 경우에는 GET을, 리소스를 삭제하는 경우에는 DELETE를 사용해 리소스에 대한 행위를 명확히 표현한다.

### 💠 REST API 설계 규칙
1. 슬래시 구분자(/ )는 계층 관계를 나타내는데 사용한다.
```
http://restapi.example.com/houses/apartments
```
2. URI 마지막 문자로 슬래시(/)를 포함하지 않는다.
- URI에 포함되는 모든 글자는 리소스의 유일한 식별자로 사용되어야 하며 URI가 다르다는 것은 리소스가 다르다는 것이고, 역으로 리소스가 다르면 URI도 달라져야 한다.
3. URI 경로의 마지막에는 슬래시(/)를 사용하지 않는다.
```js
# bad
http://restapi.example.com/houses/apartments/ 

# good
http://restapi.example.com/houses/apartments  
```
4. 하이픈(-)은 URI 가독성을 높이는데 사용
- 불가피하게 긴 URI경로를 사용하게 된다면 하이픈을 사용해 가독성을 높인다.
5. 밑줄(_)은 URI에 사용하지 않는다.
- 밑줄은 보기 어렵거나 밑줄 때문에 문자가 가려지기도 하므로 가독성을 위해 밑줄은 사용하지 않는다.
```
# bad
http://khj93.com/test_blog

# good
http://khj93.com/test-blog  
```
6. URI 경로에는 소문자가 적합하다.
- URI 경로에 대문자 사용은 피하도록 한다.
RFC 3986(URI 문법 형식)은 URI 스키마와 호스트를 제외하고는 대소문자를 구별하도록 규정하기 때문
7. 파일확장자는 URI에 포함하지 않는다.
- REST API에서는 메시지 바디 내용의 포맷을 나타내기 위한 파일 확장자를 URI 안에 포함시키지 않는다. 대신 Accept header를 사용한다.
```js
# bad
http://restapi.example.com/members/soccer/345/photo.jpg

# good
GET / members/soccer/345/photo HTTP/1.1 Host: restapi.example.com Accept: image/jpg
```

✅ 리소스 간에 연관 관계가 있는 경우
```js
// /리소스명/리소스 ID/관계가 있는 다른 리소스명
GET : /users/{userid}/devices (일반적으로 소유 ‘has’의 관계를 표현할 때)

// 관계명이 복잡하다면 서브 리소스에 명시적으로 표현
GET : /users/{userid}/likes/devices
```

## 💠 자원을 표시하는 Collection과 Document
- 아래와 같은 URI가 있을 때, 
```
http:// restapi.example.com/sports/soccer/players/13
```
- sports, players는 collection이고, soccer, 13은 document이다. 즉 컬렉션은 복수로 사용한다.

## ♒ REST API 실습 코드
### 🔰 `GET`
- todos 리소스에서 모든 todo를 취득한다.
```js
const xhr = new XMLHttpRequest();
xhr.open('GET', 'http://localhost:5000/todos');
xhr.send();

xhr.onload = () => {
  if(xhr.status === 200) {
    document.querySelector('pre').textContent(xhr.response);
  } else {
    console.log("Error!", xhr.status, xhr.statusText);
  }
};

/*
[
  {
    "id": 1,
    "content": "HTML",
    "completed": false
  },
  {
    "id": 2,
    "content": "CSS",
    "completed": true
  },
  {
    "id": 3,
    "content": "Javascript",
    "completed": false
  }
]
*/
```

- id를 사용하여 특정 todo를 조회한다.
```js
const xhr = new XMLHttpRequest();
// 1번 todo를 조회
xhr.open('GET', 'http://localhost:5000/todos/1');
xhr.send();

xhr.onload = () => {
  if(xhr.status === 200) {
    document.querySelector('pre').textContent(xhr.response);
  } else {
    console.log("Error!", xhr.status, xhr.statusText);
  }
};

/*
{
  "id": 1,
  "content": "HTML",
  "completed": false
}
*/
```

### 🔰 `POST`
- todos 리소스에 새로운 todo를 생성한다.
- post 요청시에는 `setRequestHeader` 메서드를 사용하여 요청 몸체에 담아 서버로 전송할 페이로드의 MIME 타입을 지정해야 한다.
```js
const xhr = new XMLHttpRequest();
xhr.open('POST', 'http://localhost:5000/todos');
xhr.setRequestHeader('Content-type', 'application/json');
// 페이로드 전송
xhr.send(JSON.stringify({ id: 4, content: 'Angular', completed: true }));

xhr.onload = () => {
  // 200 : OK, 201 : created
  if(xhr.status === 200 || xhr.status === 201) {
    document.querySelector('pre').textContent(xhr.response);
  } else {
    console.log("Error!", xhr.status, xhr.statusText);
  }
};

/*
{
  "id": 4,
  "content": "Angular",
  "completed": true
}
*/
```

### 🔰 `PUT`
- 특정 리소스 전체를 교체할 때 사용한다.
- put 요청시에는 `setRequestHeader` 메서드를 사용하여 요청 몸체에 담아 서버로 전송할 페이로드의 MIME 타입을 지정해야 한다.
```js
const xhr = new XMLHttpRequest();
xhr.open('PUT', 'http://localhost:5000/todos/4');
xhr.setRequestHeader('Content-type', 'application/json');
xhr.send(JSON.stringify({ id: 4, content: 'React', completed: false }));

xhr.onload = () => {
  if(xhr.status === 200) {
    document.querySelector('pre').textContent(xhr.response);
  } else {
    console.log("Error!", xhr.status, xhr.statusText);
  }
};

/*
{
  "id": 4
  "content": "React",
  "completed": false,
}
*/
```

### 🔰 `PATCH`
- 특정 리소스 일부를 교체할 때 사용한다.
- patch 요청시에는 `setRequestHeader` 메서드를 사용하여 요청 몸체에 담아 서버로 전송할 페이로드의 MIME 타입을 지정해야 한다.
```js
const xhr = new XMLHttpRequest();
// id로 todo를 특정.
xhr.open('PATCH', 'http://localhost:5000/todos/4');
xhr.setRequestHeader('Content-type', 'application/json');
// completed 값만 수정.
xhr.send(JSON.stringify({ completed: true }));

xhr.onload = () => {
  if(xhr.status === 200) {
    document.querySelector('pre').textContent(xhr.response);
  } else {
    console.log("Error!", xhr.status, xhr.statusText);
  }
};

/*
{
  "id": 4,
  "content": "React",
  "completed": true
}
*/
```

### 🔰 `DELETE`
- 리소스를 삭제할 때 사용한다.
```js
const xhr = new XMLHttpRequest();
xhr.open('DELETE', 'http://localhost:5000/todos/4');
xhr.send();

xhr.onload = () => {
  if(xhr.status === 200) {
    document.querySelector('pre').textContent(xhr.response);
  } else {
    console.log("Error!", xhr.status, xhr.statusText);
  }
};

/*
{}
/*
```
