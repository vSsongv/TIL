# JS 기초 지식

> JS에 대한 기본 지식을 다룬다.(211123)

### ❓ JS란?

![](https://images.velog.io/images/songjy377/post/d26369fc-8f91-442b-aebe-8aafe2ca2cd9/image.png)

- 브랜던 아이크가 개발한 브라우저에서 동작하는 프로그래밍 언어.
- ms에서 JScript를 IE에 탑재했을 때, JS와 표준화되지 않자 자사 브라우저에만 동작하는 기능을 경쟁적으로 추가했다. -> **크로스 브라우징 이슈 발생**
- 이로 인해 모든 브라우저에서 정상적으로 동작하는 웹페이지를 개발하기 위해 표준화된 JS인 **ECMAScript**가 등장했다.
- 현재 ES12(ECMAScript 2021)까지 등장했다.

### 🔰 Node JS

![](https://images.velog.io/images/songjy377/post/45760e4b-49a6-4190-aba9-5019f3ac9264/image.png)

- 브라우저 이외의 환경에서도 동작할 수 있도록 JS 엔진을 브라우저에서 독립시키 JS실행 환경이다. 보통 server side application 개발에 주로 사용된다.
- 비동기 I/O를 지원하며 single thread 이벤트 루프 기반으로 동작함으로써 요청처리 성능이 좋다.

### JS는 client side Web API이다.

- ECMAScript + DOM, BOM, Canvas, XMLHttpRequest, Fetch, requestAnimationFrame, SVG, Web Storage, Web Component, Web worker
- JS는 웹 브라우저에서 동작하는 유일한 프로그래밍 언어이다.
- 개발자가 별도의 compile 작업을 수행하지 않는 interpreter 언어이다.

| compiler 언어                                                                          | interpreter 언어                                                                               |
| -------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| 코드가 실행되기 전 컴파일 타임에 소스코드 전체를 한번에 머신코드로 변환한 후 실행한다. | 코드가 실행되는 단계인 런타임에 문 단위로 한 줄씩 중간 코드(바이트 코드)로 변환한 후 실행한다. |
| 실행 파일을 생성한다.                                                                  | 실행 파일을 생성하지 않는다.                                                                   |
| 실행에 앞서 컴파일은 단 한 번 수행된다.                                                | 코드가 실행될 때마다 인터프리터 과정이 반복 수행된다.                                          |
| 컴파일과 실행 단계가 분리되어 있으므로 코드 실행 속도가 빠르다.                        | 반복 수행되므로 실행 속도가 비교적 느리다.                                                     |

- JS는 **명령형, 함수형, 프로토타입 기반 객체지향 프로그래밍**을 지원하는 멀티 프로그래밍 언어이다.
- JS는 garbage collector를 내장하고 있는 언어로서, 메모리 누수를 방지한다.
