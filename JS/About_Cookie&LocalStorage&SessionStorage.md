# Cookie & Local Storage & Session Storage
> 과제를 하다가 local storage를 사용하게 되었는데, 정확한 차이와 개념을 알고 싶어져서 Cookie, Local Storage, Session Storage에 대해 조사하고 공부해보았다. (211221)

❗JS로 개발을 하다 보면 간단한 애플리케이션이라도 데이터를 저장해야 하는 필요성이 생긴다. 보통 DB나 서버에 저장한다고 생각하는 경우가 많지만, 브라우저 내에 데이터를 저장할 수 있는 방법이 있다. 이를 클라이언트 스토리지(client storage)라고 한다. 

✨ client strage를 이용하면 client상에 저장된 데이터를 사용할 수 있기 때문에, 서버 요청에 대한 부담이 줄어든다. 예를 들면, 이전 활동을 저장하거나, 사용자 기본 저장 설정 등에 유용하다. 특히 다수의 column이 필요하지 않은 단순한 형태의 데이터를 저장할 때 유용하다.

📌 종류로는 cookie, HTML5 에서 생긴 Web Storage의 LocalStorage와 임시저장소 SessionStorage가 있다. 지금부터 각각 알아보자.

## 🍪 Cookie
- Cookie는 하나의 텍스트 형태로 저장된다.
- 사용자의 컴퓨터(로컬)에 저장된다.
- 하나의 쿠키 최대 크기는 4KB로, 도메인 당 최대 개수는 20개로 제한되어 있다. 
- 쿠키 정보는 모두 서버로 전송된다. **즉 서버에서도 읽기가 가능하다.** 
- JS가 읽지 못하게 만들 수 있다. `Set-Cookie: 쿠키명=쿠키값; path=/; HttpOnly`
- 쿠키는 만료일을 지정하게 되어 있다. **즉 언젠가는 제거된다.**
- 대부분의 브라우저에서 지원된다.
- 웹페이지를 요청할 때, 쿠키를 함께 전송하여 서버가 해당 사용자를 식별할 수 있도록 한다.
- **쿠키는 서버가 만든다!**

👉🏻 cookie는 데이터를 매번 서버에서 받아오지 않을 수 있는 좋은 대안이었다. **그러나 매번 서버로 전송해야 했고, 용량에 제한이 있었다.** HTML5부터, Web Storage라는 쿠키의 단점을 보완한 대안이 등장했다.

## 📁 Web Storage
- **문자열/문자열화**된 JSON 데이터를 쉽게 저장할 수 있다.
- 쿠키와 비슷하지만 **개수와 용량의 제한이 없다.**
- 영구적인 데이터 저장이 가능하다.
- **서버로 전송하지 않아 트래픽 비용이 줄어든다.**

- JS가 접근하여 값을 가져올 수 있다.
- 아래 메서드와 프로퍼티를 가지고 있다.
```js
setItem(key, value) – 키-값 쌍을 저장.
getItem(key) – 키에 해당하는 값을 받아온다.
removeItem(key) – 키와 해당 값을 삭제.
clear() – 모든 것을 삭제.
key(index) – 인덱스(index)에 해당하는 키를 받아온다.
length – 저장된 항목의 개수.
// length 프로퍼티가 있지만 iterable 객체가 아니다. Object.keys 이용가능.
let keys = Object.keys(localStorage);
for(let key of keys) {
  alert(`${key}: ${localStorage.getItem(key)}`);
}
```

- 아래 이벤트들을 사용 가능하다.
```js
key – 변경된 데이터의 키(.clear()를 호출했다면 null)
oldValue – 이전 값(키가 새롭게 추가되었다면 null)
newValue – 새로운 값(키가 삭제되었다면 null)
url – 갱신이 일어난 문서의 url
storageArea – 갱신이 일어난 localStorage나 sessionStorage 객체
```

- 두 storage의 사용법은 동일하므로, localstorage 기준으로 예시를 작성한다. local을 session으로만 바꾸면 sessionstorage로 사용가능하다.
```js
//세 가지 방법 모두 동일
localStorage.setItem('email', 'test@user.com')
localStorage.email = 'test@user.com';
localStorage['email'] = 'test@user.com';
> undefined

//세 가지 방법 모두 동일
localStorage.getItem('email')
localStorage.email;
localStorage['email'];
> "test@user.com"

localStorage.setItem('email', 'test@admin.com')
> undefined
localStorage.getItem('email')
> "test@admin.com"

// 데이터 삭제
localStorage.removeItem('email')
> undefined
localStorage.getItem('email')
> null

// 주의사항 - web storage는 문자열밖에 저장할 수 없으므로, 객체 타입을 저장할 때는 JSON 형태로 읽고 저장해야 한다.
localStorage.setItem('json', JSON.stringify({a: 1, b: 2})) 
> undefined
JSON.parse(localStorage.getItem('json')) 
> {a: 1, b: 2}
```
👉🏻 Session Storage와 Local Storage는 **데이터 저장의 영구성**의 차이가 있다. 따라서 데이터 저장의 지속 시간을 기준으로 선택하여 사용하면 된다. 

### 🗂️ Session Storage
- **임시 스토리지**
- 웹페이지의 세션이 끝날 때 데이터가 사라진다. 즉 **브라우저 또는 탭이 닫히기 전까지만 데이터가 저장된다.**
- 저장 용량 한도 최소 5MB이다.

### 🗂️ Local Storage
- **영구 스토리지**
- 저장한 데이터를 지우지 않는 이상 영구적으로 보관이 가능하다.
- **유저가 브라우저 윈도우를 닫더라도 데이터는 삭제되지 않는다.** -> 다음번 접속에도 데이터 사용이 가능하다.
- 도메인마다 별도 로컬 스토리지가 생성된다.
- 데이터의 만료 기간이 없고, JavaScript를 통해서만 지워진다.(불필요한 데이터는 직접 지워주는것이 좋다.)
- **여러 탭이나 창 간에 데이터가 서로 공유**된다.
- 저장 용량 한도는 3가지 중에서 가장 높다. (5MB/10MB)

👉🏻 지속적으로 필요한 데이터는 로컬 스토리지에 저장하고, 잠깐 동안 필요한 정보는 세션 스토리지에 저장하면 된다. 
 - 팝업 창 : Cookie
 - 자동 로그인, : Local Storage
 - 입력 폼 정보, 일회성 로그인 정보, 비로그인 장바구니 :  Session Storage

 ✅ 개발자 도구에서 web storage와 cookie를 확인할 수 있다.
 
 ![](https://images.velog.io/images/songjy377/post/67d7fd0a-cb36-41fd-b128-0e7e1600adec/image.png)

### 🔐 보안 관점에서 생각해볼 점

- Session Storage는 도메인별로 별도로 생성된다. 브라우저 컨텍스트가 다르기 때문이다. 탭 브라우징이나 브라우저를 하나 더 실행해서 같은 페이지를 실행했을 때, 이 두 페이지의 Session Storage는 각각 별개의 영역으로 서로 침범하지 못한다는 의미이다. 이는 도메인만 같으면 전역적으로 공유 가능한 Local Storage와 구분되는 특징이다.

- Web Storage의 보안은 서로 다른 도메인의 데이터 침범을 막고는 있지만 클라이언트, 즉 사용자를 막고 있지는 않다. 클라이언트는 얼마든지 저장된 값을 임의로 수정이 가능하다. **따라서 개발자는 사용자에 의한 임의 변경에 대한 방어 코드의 작성을 잊지 말아야 한다.**


📌 로컬 스토리지의 데이터 영속성은 어디까지나 계속해서 동일한 컴퓨터에서 동일한 브라우저를 사용할 때만 해당한다. 즉, 같은 컴퓨터에서 다른 브라우저를 사용하거나 (e.g. 크롬 -> 사파리), 또는 다른 컴퓨터에서 같은 브라우저를 사용하는 경우에는 (e.g. 집 -> 회사), 엄연히 다른 브라우저이므로 **서로 다른 두 개의 로컬 스토리지에 데이터가 저장된다.** 

✨ 즉, **다른 기기나 브라우저 간에 데이터가 공유되고 영속되야 한다면 당연히 클라우드(Cloud) 플랫폼이나 데이터베이스(DB) 서버를 사용해야 한다는 것을 잊지 말자. 또한 보안이 보장되어야 하는 정보들을 저장하는 경우 또한 로컬 스토리지는 최선의 선택이 아니다.**

refe : https://ko.javascript.info/localstorage, https://www.daleseo.com/js-web-storage/