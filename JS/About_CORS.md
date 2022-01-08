# CORS
> CORS에 대해 조사하고 공부해 보았다.(220101)

## ❓`CORS`란
- CORS(Cross-Origin Resource Sharing) 란, 도메인이나 서브도메인, 프로토콜, 포트가 **다른 출처의** 자원을 요청하는 매커니즘이다.
![](https://images.velog.io/images/songjy377/post/f9bedc31-5db1-48b0-9be8-30f410762864/image.png)
_<[출처: mozilla](https://developer.mozilla.org/ko/docs/Web/HTTP/CORS)>_
- 예전에는 A 사이트에서 B 사이트에 있는 콘텐츠에는 접근할 수 없었다.
- 그러나 개발자들은 이런 기능을 원했고, 그래서 생겨난 것이 CORS이다.

### ❓ 출처(Origin)란
- URL의 scheme(프로토콜), host(도메인), port(포트)로 정의된다. 
![](https://images.velog.io/images/songjy377/post/13be910b-bf0e-4547-8076-b50a114f5d27/image.png)
  - scheme: http/https
  - host: velog.io
  - port : 88, 9000(HTTP의 기본 포트는 80)

- 이 세 가지가 모두 같은 경우 same-origin이라고 한다. 
- 아래의 두 URL은 같은 출처이다.
![](https://images.velog.io/images/songjy377/post/eea82d47-73dd-4cee-ad4e-311c2ec9badb/image.png)
- 즉, 이 3가지 중 하나라도 다르면 cross-origin 이라고 하는 것이다.
- 아래는 모두 다른 출처이다.

|다른 스킴|다른 호스트|다른 포트
|:--:|:--:|:--:|
|http://example.com/app1|http://www.example.com|http://example.com:8080
|https://example.com/app2|http://myapp.example.com|http://example.com

## 🔒 `SOP(Same-Origin Policy)`
- 웹에서는 다른 출처의 리소스 요청을 제한하는 것과 관련된 두가지 정책이 존재한다. 하나는 CORS, 다른 한가지는 SOP(Same-Origin Policy)이다.
- SOP는 Same-Origin인 경우에만 리소스를 공유할 수 있는 정책이다. 
- 이때 출처가 다르더라도, **CORS 정책을 지킨 리소스 요청은 허용하기로 했다.**

> ❓왜 이런 보안 규칙들이 만들어진 것인가?
> - 웹페이지를 생각해 보면, 누구나 개발자 도구만 열어도 어떻게 DOM이 작성되어 있는지와 같은 정보들을 확인할 수 있다. 
>- 따라서 악의를 가지고 script등을 이용하여 사용자의 정보를 빼갈 수도 있기 때문이다.

- 출처를 비교하는 로직은 서버에 구현된 스펙이 아닌 **브라우저에 구현된 스펙**이다. 
- 만약 CORS정책을 위반하는 요청에 서버가 정상적으로 응답을 하더라도, 브라우저가 이 응답을 분석해서 CORS정책에 위반되면 그 응답은 처리하지 않게 된다.

❓ 그럼 CORS는 어떻게 동작하는 걸까?

## 🔒 `CORS`의 동작 원리

1. 기본적으로 웹은 다른 출처의 리소스를 요청할 때는 **`HTTP` 프로토콜**을 사용하여 요청하는데, 이때 브라우저는 요청 헤더 (request header)에 Origin 필드에 요청을 보내는 출처를 담아 전송한다.
2. 서버는 요청에 대한 응답을 하는데, 응답 헤더 (response header)에 `Access-Control-Allow-Origin`이라는 값에 '이 리소스를 접근하는 것이 허용된 출처'를 내려준다. 이후 응답을 받은 브라우저는 자신이 보냈던 요청의 `Origin`과 서버가 보내준 응답의 `Access-Control-Allow-Origin`을 비교해 본 후 이 응답이 유효한 응답인지 아닌지를 결정한다.
- HTTP 메소드들 중 GET 이 외의 메소드나, POST 메소드에서 특정 MIME Type은 서버 데이터에 사이드 이펙트를 발생시킬 수 있기 때문에 기본적으로 브라우저는 **Preflight Request** 방식으로 요청할 수 있도록 규제한다.

- CORS는 총 3가지의 시나리오를 가진다.

### ✅ `Simple Request`
- 예비 요청 없이 바로 서버에게 본 요청을 한 후, 서버가 이에 대한 응답의 헤더에 `Access-Control-Allow-Origin`과 같은 값을 보내주면 그때 브라우저가 CORS 정책 위반 여부를 검사하는 방식이다.
![](https://images.velog.io/images/songjy377/post/88f2a293-b574-4931-8f27-f14aaf962dc8/image.png)
- 아래의 경우에만 Simple Request 방식으로 요청한다.
>1. HTTP Method가 `GET`, `POST`, `HEAD `이셋 중에 하나여야 한다.
>2.  Fetch 명세에서 `"CORS-safelisted request-header"`로 정의한 헤더들인 `Accept, Accept-Language, Content-Language, Content-Type, DPR, Downlink, Save-Data, Viewport-Width, Width`를 제외한 헤더를 사용하면 안된다.
>3. 만약 `Content-Type`를 사용하는 경우에는 `application/x-www-form-urlencoded, multipart/form-data, text/plain`만 허용된다.
- Simple Request가 어려운 이유는, 1번 조건의 경우 그리 까다롭지는 않지만,
- 2번의 명시된 헤더들은 정말 기본적인 헤더들이다. 복잡하고 상용화된 웹 어플리케이션에서는 이 헤더들 외에 추가적인 헤더를 사용하지 않는 경우는 찾기 힘들다. 인증 동작을 위한 `Authoriztion` 헤더만 하더라도 저 조건을 지킬 수 없다.
- 오늘 날의 REST 혹은 HTTP API들은 `text/xml`이나 `application/json` 컨텐츠 타입을 가지도록 설계 되기 때문에 조건을 충족시키기 어렵다.

### ✅ `Prefilght Request`
- 브라우저는 요청을 한번에 보내지 않고 예비 요청과 본 요청으로 나누어서 서버로 전송한다.
![](https://images.velog.io/images/songjy377/post/06b1f45c-bd63-4d38-b400-dc34387c8aaa/image.png)
-  예비 요청을 `Preflight`라고 부르고, `Preflight`에는 `HTTP` 메소드 중 `OPTIONS` 메소드가 사용된다. (`Preflight`의 역할은 본 요청을 보내기 전에 브라우저 스스로 이 요청을 보내는 것이 안전한지 판단하는 것이다.)
- 우리가 자바스크립트의 `fetch API`를 사용하여 브라우저에게 리소스를 받아오라는 명령을 내리면 브라우저는 서버에게 예비 요청을 먼저 보내고, 서버는 이 예비 요청에 대한 응답으로 현재 자신이 어떤 것들을 허용하고, 어떤 것들을 금지하고 있는지에 대한 정보를 응답 헤더에 담아서 브라우저에게 다시 보내주게 된다.
- 이후 브라우저는 자신이 보낸 예비 요청과 서버가 응답에 담아준 허용 정책을 비교한 후, 이 요청을 보내는 것이 안전하다고 판단되면 같은 엔드포인트로 다시 본 요청을 보내게 된다. 
- 이후 서버가 이 본 요청에 대한 응답을 하면 브라우저는 최종적으로 이 응답 데이터를 자바스크립트에게 넘겨준다.

[코드 출처](https://evan-moon.github.io/2020/05/21/about-cors/)
```js
const headers = new Headers({
  'Content-Type': 'text/xml',
});
fetch('https://evanmoon.tistory.com/rss', { headers });
OPTIONS https://evanmoon.tistory.com/rss
```

```http
Accept: */*
Accept-Encoding: gzip, deflate, br
Accept-Language: en->US,en;q=0.9,ko;q=0.8,ja;q=0.7,la;q=0.6
// preflight요청을 할 때 실제 요청에서 어떤 header를 사용할 것인지 서버에게 알리기 위해 사용
Access-Control-Request-Headers: content-type
// preflight 요청을 할 때 실제 요청에서 어떤 메서드를 사용할 것인지 서버에게 알리기 위해 사용
Access-Control-Request-Method: GET
Connection: keep-alive
Host: evanmoon.tistory.com
Origin: https://evan-moon.github.io
Referer: https://evan-moon.github.io/2020/05/21/about-cors/
Sec-Fetch-Dest: empty
Sec-Fetch-Mode: cors
Sec-Fetch-Site: cross-site
```

- 위 `Preflight`는 자신이 예비 요청 이후에 보낼 본 요청에 대한 다른 정보들도 함께 포함되어 있다. 이렇게 요청을 보내면 서버에서 `Preflight`에 대한 응답을 보낸다.

```http
OPTIONS https://evanmoon.tistory.com/rss 200 OK
// 브라우저가 해당 origin이 자원에 접근할 수 있도록 허용. 
// 혹은 *은 credentials이 없는 요청에 한해서 모든 origin에서 접근이 가능하도록 허용한다.
Access-Control-Allow-Origin: https://evanmoon.tistory.com
Content-Encoding: gzip
Content-Length: 699
Content-Type: text/xml; charset=utf-8
Date: Sun, 24 May 2020 11:52:33 GMT
P3P: CP='ALL DSP COR MON LAW OUR LEG DEL'
Server: Apache
Vary: Accept-Encoding
X-UA-Compatible: IE=Edge
```
- 응답 헤더에 유효한 `Access-Control-Allow-Origin` 값이 존재하면, CORS 정책 위반이 아니다.

### ✅ `Credentialed Request`
- 인증된 요청을 사용하는 방법이다. 이 시나리오는 CORS의 기본적인 방식이라기 보다는 다른 출처 간 통신에서 좀 더 보안을 강화하고 싶을 때 사용하는 방법이다.
- 기본적으로 브라우저가 제공하는 비동기 리소스 요청 API인 `XMLHttpRequest` 객체나 `fetch API`는 별도의 옵션 없이 브라우저의 쿠키 정보나 인증과 관련된 헤더를 함부로 요청에 담지 않는다. 이때 요청에 인증과 관련된 정보를 담을 수 있게 해주는 옵션이 바로 `credentials` 옵션이다. 옵션은 3가지가 있다.

옵션 값|	설명|
|--|--|
|same-origin (기본값)|	같은 출처 간 요청에만 인증 정보를 담을 수 있다
|include|	모든 요청에 인증 정보를 담을 수 있다
|omit|	모든 요청에 인증 정보를 담지 않는다

- 만약 `same-origin`이나 `include`와 같은 옵션을 사용하여 리소스 요청에 인증 정보가 포함된다면, 이제 브라우저는 다른 출처의 리소스를 요청할 때 단순히 `Access-Control-Allow-Origin`만 확인하는 것이 아니라 좀 더 꼼꼼하게 검사하게 된다.

-요청에 인증정보가 담겨있는 상태에서 다른 출처의 리소스를 요청하게 되면 브라우저는 CORS정책 위반 여부를 검사하는 룰에 다음 두가지를 추가하게 된다.
>- **`Access-Control-Allow-Origin : *` 이면 안 되며, 명시적인 URL이어야 한다.**
>- **응답 헤더에는 반드시 `Access-Control-Allow-Credentials:true`가 존재해야 한다.**

## 🔓 `CORS` 해결 방법

### 🔑 Access-Control-Allow-Origin 세팅
- HTTP 응답헤더 `Access-Control-Allow-Origin : *` 혹은 `Access-Control-Allow-Origin: 허용하고자 하는 도메인` 값을 주는 방법이다.
- *은 보안적 이슈가 발생할 수도 있으니 출처를 명시하는 것이 좋다.

### 🔑 Webpack Dev Server로 리버스 프록싱
-프론트엔드 개발자는 대부분 웹팩과 webpack-dev-server를 사용하여 자신의 머신에 개발 환경을 구축하게 되는데, 이 라이브러리가 제공하는 프록시 기능을 사용하면 아주 편하게 CORS 정책을 우회할 수 있다.
```js
module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'https://api.evan.com',
        changeOrigin: true,
        pathRewrite: { '^/api': '' },
      },
    }
  }
}
```
- 이렇게 설정을 해놓으면 로컬 환경에서 /api로 시작하는 URL로 보내는 요청에 대해 브라우저는 localhost:8000/api로 요청을 보낸 것으로 알고 있지만, 사실 뒤에서 웹팩이 https://api.evan.com으로 요청을 프록싱해주기 때문에 마치 CORS 정책을 지킨 것처럼 브라우저를 속이면서도 우리는 원하는 서버와 자유롭게 통신을 할 수 있다. **즉, 프록싱을 통해 CORS 정책을 우회할 수 있는 것**이다.
❗ 아무래도 중간단계가 있기 때문에 속도가 느려지는 단점이 있다.

### 🔑 node.js의 미들웨어 CORS 추가
- 이미 만들어진 node.js 미들웨어중 이를 해결해주는 미들웨어가 있는데 바로 CORS이다.
```js 
npm install --save cors
yarn add cors
```
- 이것을 이용하면 더욱 간단하게 CORS를 허가해줄 수 있다.
```js
const express = require('express');
const cors = require('cors');

const app = express();

app.use(cors()); // CORS 미들웨어 추가

...
```
- 하지만 위에 처럼 헤더를 추가 하거나 미들웨어를 적용하면 모든 요청에 대해 허가를 하게 된다. -> 보안적으로 취약해질 수 있음.

- 그래서 cors 미들웨어에는 여러가지를 설정할 수가 있다.
```js
( https://www.npmjs.com/package/cors )

const corsOptions = {
    origin: 'http://localhost:3000', // 허락하고자 하는 요청 주소
    credentials: true, // true로 하면 설정한 내용을 response 헤더에 추가
};

app.use(cors(corsOptions)); // config 추가
```

## 🌟 Summery
- CORS이란 도메인 또는 포트가 다른 서버의 자원을 요청하면 발생하는 이슈이다.
- CORS 정책은 출처가 다른 리소스를 접근할 때 HTTP 헤더를 사용하여 권한을 부여하도록 브라우저에 알려주는 체제다.
- 서버와 클라이언트가 분리되어 있는 앱에서는 cross-origin HTTP 요청을 **서버에서** 승인해주는 것이 좋다.
- 출처를 비교하는 스펙은 **브라우저에** 구현되어 있다.

&lt;reference>
https://developer.mozilla.org/ko/docs/Web/HTTP/CORS
