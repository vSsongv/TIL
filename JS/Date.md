# Date
> Date 객체의 다양한 함수를 정리했다.(211209)

Date 객체는 날짜와 시간(년, 월, 일, 시, 분, 초, 밀리초(천분의 1초(millisecond, ms)))을 위한 메소드를 제공하는 빌트인 객체이면서 생성자 함수이다.

Date 생성자 함수로 생성한 Date 객체는 내부적으로 숫자값을 갖는다. 기본적으로 현재 날짜와 시간을 나타내는 정수값을 가진다.
-> 이 값은 1970년 1월 1일 00:00(UTC)을 기점으로 현재 시간까지의 밀리초를 나타낸다.

> **UTC(Coordinated Universal Time)** : 협정 세계시, 국제 표준시
> **KST(Korea Standard Time)** : 한국 표준시

현재의 날짜와 시간은 자바스크립트 코드가 동작한 시스템의 시계에 의해 결정된다. 시스템 시계의 설정(timezone, 시간)에 따라 서로 다른 값을 가질 수 있다.
 
## 📆 `new Date()`
>인수를 전달하지 않으면 현재 날짜와 시간을 가지는 인스턴스를 반환한다.
>```js
>const date = new Date();
>console.log(date); // Thu May 16 2019 17:16:13 GMT+0900 (한국 표준시)
>
>// new Date(milliseconds)
>// 인수로 숫자 타입의 밀리초를 전달하면 1970년 1월 1일 00: 00(UTC)을 기점으로 인수로 전달된 밀리초만큼 경과한 날짜와 시간을 가지는 인스턴스를 반환한다.
>
>// KST(Korea Standard Time)는 GMT(그리니치 평균시: Greenwich Mean Time)에 9시간을 더한 시간이다.
>let date = new Date(0);
>console.log(date); // Thu Jan 01 1970 09:00:00 GMT+0900 (한국 표준시)
>
>// 86400000ms는 1day를 의미한다.
>// 1s = 1,000ms
>// 1m = 60s * 1,000ms = 60,000ms
>// 1h = 60m * 60,000ms = 3,600,000ms
>// 1d = 24h * 3,600,000ms = 86,400,000ms
>date = new Date(86400000);
>console.log(date); // FFri Jan 02 1970 09:00:00 GMT+0900 (한국 표준시)
>
>// new Date(dateString)
>// 인수로 날짜와 시간을 나타내는 문자열을 전달하면 지정된 날짜와 시간을 가지는 인스턴스를 반환한다.이때 인수로 전달한 문자열은 Date.parse 메소드에 의해 해석 가능한 형식이어야 한다.
>
>let date = new Date('May 16, 2019 17:22:10');
>console.log(date); // Thu May 16 2019 17:22:10 GMT+0900 (한국 표준시)
>
>date = new Date('2019/05/16/17:22:10');
>console.log(date); // Thu May 16 2019 17:22:10 GMT+0900 (한국 표준시)
>```

### ⏲️ `new Date(year, month[, day, hour, minute, second, millisecond])`
> -인수로 년, 월, 일, 시, 분, 초, 밀리초를 의미하는 숫자를 전달하면 지정된 날짜와 시간을 가지는 인스턴스를 반환한다.이때 년, 월은 반드시 지정하여야 한다.지정하지 않은 옵션 정보는 0 또는 1으로 초기화된다.
>
>인수 | 내용
>--|--
>year|	1900년 이후의 년
>month|	월을 나타내는 0 ~11까지의 정수(주의: 0부터 시작, 0 = 1월)
>day|	일을 나타내는 1 ~31까지의 정수
>hour|	시를 나타내는 0 ~23까지의 정수
>minute|	분을 나타내는 0 ~59까지의 정수
>second|	초를 나타내는 0 ~59까지의 정수
>millisecond|	밀리초를 나타내는 0 ~999까지의 정수
>년, 월을 지정하지 않은 경우 1970년 1월 1일 00: 00(UTC)을 가지는 인스턴스를 반환한다.
>```js
>// 월을 나타내는 4는 5월을 의미한다.
>// 2019/5/1/00:00:00:00
>new Date(2019, 4);
>// Wed May 01 2019 00:00:00 GMT+0900 (한국 표준시)
>
>// 월을 나타내는 4는 5월을 의미한다.
>// 2019/5/16/17:24:30:00
>new Date(2019, 4, 16, 17, 24, 30, 0);
>// Thu May 16 2019 17:24:30 GMT+0900 (한국 표준시)
>
>// 가독성이 훨씬 좋다.
>new Date('2019/5/16/17:24:30:10');
>// Thu May 16 2019 17:24:30 GMT+0900 (한국 표준시)
>```

## 📆 Date Method

### ⏲️ `getFullYear`
> - 년도를 나타내는 4자리 숫자를 반환한다.
>```js
>const today = new Date();
>const year = today.getFullYear();
>
>console.log(today); // Thu May 16 2019 17:39:30 GMT+0900 (한국 표준시)
>console.log(year);  // 2019
>```]]

### ⏲️ `setFullYear`
> - 년도를 나타내는 4자리 숫자를 설정한다. 년도 이외 월, 일도 설정할 수 있다.
>```js
>dateObj.setFullYear(year[, month[, day]])
>const today = new Date();
>
>// 년도 지정
>today.setFullYear(2000);
>
>let year = today.getFullYear();
>console.log(today); // Tue May 16 2000 17:42:40 GMT+0900 (한국 표준시)
>console.log(year);  // 2000
>
>// 년도 지정
>today.setFullYear(1900, 0, 1);
>
>year = today.getFullYear();
>console.log(today); // Mon Jan 01 1900 17:42:40 GMT+0827 (한국 표준시)
>console.log(year);  // 1900
>```

### ⏲️ `getMonth & setMonth`
> - get : 월을 나타내는 0 ~ 11의 정수를 반환한다. 1월은 0, 12월은 11이다.
> - set : 월을 나타내는 0 ~ 11의 정수를 설정한다. 1월은 0, 12월은 11이다. 월 이외 일도 설정할 수 있다.
>```js
>const today = new Date();
>const month = today.getMonth();
>
>console.log(today); // 2021-12-09T04:19:58.314Z
>console.log(month); // 11
>
>dateObj.setMonth(month[, day])
>const today = new Date();
>
>// 월을 지정
>today.setMonth(0); // 1월
>
>let month = today.getMonth();
>console.log(today); // Wed Jan 16 2019 17:45:20 GMT+0900 (한국 표준시)
>console.log(month); // 0
>
>// 월/일을 지정
>today.setMonth(11, 1); // 12월 1일
>
>month = today.getMonth();
>console.log(today); // Sun Dec 01 2019 17:45:20 GMT+0900 (한국 표준시)
>console.log(month); // 11
>```

### ⏲️ `getDate`
> -  날짜(1 ~ 31)를 나타내는 정수를 반환한다.
>```js
>const today = new Date();
>const date = today.getDate();
>
>console.log(today); // Thu May 16 2019 17:46:42 GMT+0900 (한국 표준시)
>console.log(date);  // 16
>```

### ⏲️ `setDate`
> - 날짜(1 ~ 31)를 나타내는 정수를 설정한다.
>```js
>const today = new Date();
>
>// 날짜 지정
>today.setDate(1);
>
>const date = today.getDate();
>console.log(today); // Wed May 01 2019 17:47:01 GMT+0900 (한국 표준시)
>console.log(date);  // 1
>```

### ⏲️ `getDay`
> - 요일(0 ~ 6)를 나타내는 정수를 반환한다. 반환값은 아래와 같다.
>
>|요일|	반환값
>|-----|----|
>|일요일|	0
>|월요일|	1
>|화요일|	2
>|수요일|	3
>|목요일|	4
>|금요일|	5
>|토요일|	6
>
>```js
>const today = new Date();
>const day = today.getDay();
>
>console.log(today); // 2021-12-09T04:22:37.467Z
>console.log(day);   // 4
>```

### ⏲️ `getHours & setHours`
> - get : 시간(0 ~ 23)를 나타내는 정수를 반환한다.
> - set : 시간(0 ~ 23)를 나타내는 정수를 설정한다. 시간 이외 분, 초, 밀리초도 설정할 수 있다.
>```js
>const today = new Date();
>const hours = today.getHours();
>
>console.log(today); // Thu May 16 2019 17:48:03 GMT+0900 (한국 표준시)
>console.log(hours); // 17
>
>dateObj.setHours(hour[, minute[, second[, ms]]])
>const today = new Date();
>
>// 시간 지정
>today.setHours(7);
>
>let hours = today.getHours();
>console.log(today); // Thu May 16 2019 07:49:06 GMT+0900 (한국 표준시)
>console.log(hours); // 7
>
>// 시간/분/초/밀리초 지정
>today.setHours(0, 0, 0, 0); // 00:00:00:00
>
>hours = today.getHours();
>console.log(today); // Thu May 16 2019 00:00:00 GMT+0900 (한국 표준시)
>console.log(hours); // 0
>```

### ⏲️ `getMinutes & setMinutes`
>- get : 분(0 ~ 59)를 나타내는 정수를 반환한다.
> - set : 분(0 ~ 59)를 나타내는 정수를 설정한다. 분 이외 초, 밀리초도 설정할 수 있다.
>```js
>Date('2021/12/09/13:28').getMinutes()
>
>dateObj.setMinutes(minute[, second[, ms]])
>const today = new Date();
>
>// 분 지정
>today.setMinutes(50);
>
>let minutes = today.getMinutes();
>console.log(today);   // Thu May 16 2019 17:50:30 GMT+0900 (한국 표준시)
>console.log(minutes); // 50
>
>// 분/초/밀리초 지정
>today.setMinutes(5, 10, 999); // HH:05:10:999
>
>minutes = today.getMinutes();
>console.log(today);   // Thu May 16 2019 17:05:10 GMT+0900 (한국 표준시)
>console.log(minutes); // 5
>```

### ⏲️ `getSeconds & setSeconds`
> - get : 초(0 ~ 59)를 나타내는 정수를 반환한다.
> - set : 초(0 ~ 59)를 나타내는 정수를 설정한다. 초 이외 밀리초도 설정할 수 있다.
>```js
>const today = new Date();
>const seconds = today.getSeconds();
>
>console.log(today);   // Thu May 16 2019 17:53:17 GMT+0900 (한국 표준시)
>console.log(seconds); // 17
>
>dateObj.setSeconds(second[, ms])
>const today = new Date();
>
>// 초 지정
>today.setSeconds(30);
>
>let seconds = today.getSeconds();
>console.log(today);   // Thu May 16 2019 17:54:30 GMT+0900 (한국 표준시)
>console.log(seconds); // 30
>
>// 초/밀리초 지정
>today.setSeconds(10, 0); // HH:MM:10:000
>
>seconds = today.getSeconds();
>console.log(today);   // Thu May 16 2019 17:54:10 GMT+0900 (한국 표준시)
>console.log(seconds); // 10
>```

### ⏲️ `getMilliseconds & setMilliseconds`
> - 밀리초(0 ~ 999)를 나타내는 정수를 반환 & 설정한다.
>```js
>const today = new Date();
>const ms = today.getMilliseconds();
>
>console.log(today); // Thu May 16 2019 17:55:02 GMT+0900 (한국 표준시)
>console.log(ms);    // 905
>
>const today = new Date();
>
>// 밀리초 지정
>today.setMilliseconds(123);
>
>const ms = today.getMilliseconds();
>console.log(today); // Thu May 16 2019 17:55:45 GMT+0900 (한국 표준시)
>console.log(ms);    // 123
>```

### ⏲️ `getTime & setTime`
>1970년 1월 1일 00:00:00(UTC)를 기점으로 현재 시간까지 경과된 밀리초를 반환 & 설정한다.
>```js
>const today = new Date();
>const time = today.getTime();
>
>console.log(today); // Thu May 16 2019 17:56:08 GMT+0900 (한국 표준시)
>console.log(time);  // 1557996968335
>
>dateObj.setTime(time)
>const today = new Date();
>
>// 1970년 1월 1일 00:00:00(UTC)를 기점으로 현재 시간까지 경과된 밀리초 지정
>today.setTime(86400000); // 86400000 === 1day
>
>const time = today.getTime();
>console.log(today); // Fri Jan 02 1970 09:00:00 GMT+0900 (한국 표준시)
>console.log(time);  // 86400000
>```

### ⏲️ `getTimezoneOffset`
> - UTC와 지정 로케일(Locale) 시간과의 차이를 분단위로 반환한다.
>```js
>const today = new Date();
>const x = today.getTimezoneOffset() // 60; // -9
>
>console.log(today); // Thu May 16 2019 17:58:13 GMT+0900 (한국 표준시)
>console.log(x);     // -9
>KST(Korea Standard Time)는 UTC에 9시간을 더한 시간이다. 즉, UTC = KST - 9h이다.
>```

### ⏲️ `toDateString`
> - 사람이 읽을 수 있는 형식의 문자열로 날짜를 반환한다.
>```js
>const d = new Date('2019/5/16/18:30');
>
>console.log(d.toString());     // Thu May 16 2019 18:30:00 GMT+0900 (한국 표준시)
>console.log(d.toDateString()); // Thu May 16 2019
>```

### ⏲️ `toTimeString`
> - 사람이 읽을 수 있는 형식의 문자열로 시간을 반환한다.
>```js
>const d = new Date('2019/5/16/18:30');
>
>console.log(d.toString());     // Thu May 16 2019 18:30:00 GMT+0900 (한국 표준시)
>console.log(d.toTimeString()); // 18:30:00 GMT+0900 (한국 표준시)
>```

### ⏲️ `toISOString`
> - ISO 형식으로 Data객체의 시간을 표현한 문자열로 반환
>```js
>const d = new Date();
>console.log(d.toString());     // Thu May 16 2019 18:30:00 GMT+0900 (한국 표준시)
>console.log(d.toISOString()); // 2021-12-09T04:46:50.299Z
>console.log(d.toISOString().slice(0, 10)); //2021-12-09
>```

### ⏲️ `toLocalString & toLocalTimeString`
> - 인수로 전달한 Local기준으로 Date 객체의 날짜와 시간을 문자열로 표현해 반환한다.
> - 인수로 전달한 Local 기준으로 Date 객체의 시간을 문자열로 표현해 반환.
>```js
>const today = new Date('2021/12/09/13:53);
>
>today.toString();
>console.log(today.toLocaleString('ko-KR')); //2021. 12. 9. 오후 1:53:00
>console.log(today.toLocaleString('en-US')); //12/9/2021, 1:53:00 PM
>
>console.log(today.toLocaleTimeString('ko-KR')); //오후 1:53:00
>console.log(today.toLocaleTimeString('en-US')); //1:53:00 PM
>```

## 📆 Date 활용 Example
>현재 날짜와 시간을 초단위로 반복 출력하는 예제이다.
>
>```js
>(function printNow() {
>  const today = new Date();
>
>  const dayNames = ['(일요일)', '(월요일)', '(화요일)', '(수요일)', '(목요일)', '(금요일)', '(토요일)'];
>  // getDay: 해당 요일(0 ~ 6)를 나타내는 정수를 반환한다.
>  const day = dayNames[today.getDay()];
>
>  const year = today.getFullYear();
>  const month = today.getMonth() + 1;
>  const date = today.getDate();
>  let hour = today.getHours();
>  let minute = today.getMinutes();
>  let second = today.getSeconds();
>  const ampm = hour >= 12 ? 'PM' : 'AM';
>
>  // 12시간제로 변경
>  hour %= 12;
>  hour = hour || 12; // 0 => 12
>
>  // 10미만인 분과 초를 2자리로 변경
>  minute = minute < 10 ? '0' + minute : minute;
>  second = second < 10 ? '0' + second : second;
>
>  const now = `${year}년 ${month}월 ${date}일 ${day} ${hour}:${minute}:${second} ${ampm}`;
>
>  //1초마다 printnow 함수를 재귀 호출한다.
>  console.log(now);
>  setTimeout(printNow, 1000);
>}());
>```
![](https://images.velog.io/images/songjy377/post/494892b5-095a-4316-8a48-f6d5257a94ff/image.png)
