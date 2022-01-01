# clientHeight(width), offsetHeight(width), scrollHeight(width)
> 요소들의 높이/너비 값을 얻을 수 있는 프로퍼티들에 대해 조사하였다.(211228)

❓프론트엔드 개발을 할 때, HTML에 그려질 요소의 너비나 높이 값을 알아야 할 때가 있다. 이때 패딩, 보더를 포함한 값이 필요할 수도 있고, 패딩이나 보더를 포함하지 않은 값이 필요할 수도 있다. 
👉🏻 JS는 요소의 너비나 높이 같은 기하 정보 프로퍼티를 제공한다. `clientHeight(width)`, `offsetHeight(width)`, 스크롤의 길이까지 고려한`scrollHeight(width)` 등이 존재한다.

🔰 아래 사진은 기본 `height, width, border, padding`을 나타낸 사진이다.
![](https://images.velog.io/images/songjy377/post/a8b26886-22f1-44bc-89e6-4defeaca7806/image.png)

🔰 아래 사진에서 기하 프로퍼티인 `clientHeight(width)`, `offsetHeight(width)`, `scrollHeight(width)`를 한눈에 볼 수 있다.
![](https://images.velog.io/images/songjy377/post/d6061018-4e8b-4384-852d-6c3cf4d50406/image.png)

> 기하 프로퍼티는 화면에 보이는 요소(display element)를 대상으로만 계산된다. 즉 화면에 표시되지 않는 요소의 기하 프로퍼티는 0이다. 또한 모든 값은 정수로 반올림된다.

🌟 지금부터 하나씩 알아보자.

### 📏 `clientHeight(width)`
- **`content height(width)` + `padding` 의 값.**
- **스크롤바의 너비는 포함하지 않지만, 높이는 포함한다.**
![](https://images.velog.io/images/songjy377/post/d8ff8130-742b-4496-b576-19e47e0253ae/image.png)
- 👉🏻 따라서 패딩이 없는 경우, 요소의 넓이, 즉 `content height(width)`와 `clientHeight(width)`는 정확히 일치한다.
![](https://images.velog.io/images/songjy377/post/119be189-fe22-4bdd-9292-9bd3c4bb10be/image.png)

### `clientTop`, `clientLeft`
- `clientTop`, `clientLeft`로 테두리 두께를 측정할 수 있다.
![](https://images.velog.io/images/songjy377/post/61c47a88-0236-4124-a539-225506f0c32c/image.png)
- 그러나 아랍어나 히브리어처럼 오른쪽에서 왼쪽으로 글이 전개되는 언어일 때는 아랍어가 세팅된 브라우저에선 스크롤바가 오른쪽이 아닌 왼쪽에 나타나게 되는데, **그럼 clientLeft에 스크롤바의 너비가 포함된다.**
![](https://images.velog.io/images/songjy377/post/bb7ea7bb-b954-4792-8f18-fbda63732fd3/image.png)

### 📏 `offsetHeight(width)`
- **`content height(width)` + `padding` + `border`의 값.**
- **스크롤바가 존재하고 보여진다면 스크롤바의 너비를 포함한다.**
![](https://images.velog.io/images/songjy377/post/d3c37606-0827-48b5-a259-f4c020c18a57/image.png)
- 👉🏻 화면에 표시되지 않는 요소의 기하 프로퍼티는 0이므로, 요소의 숨김 상태 여부를 아래와 같이 확인할 수 있다.
```js
	function isHidden(elem) {
	  return !elem.offsetWidth && !elem.offsetHeight;
	}
```
- ❗ 참고로 위 코드는 요소가 화면에 있긴 하지만 사이즈가 0일 때(비어있는 &lt;div> 등)도 true를 반환하기 때문에 주의해서 사용해야 한다.

### 📏 `scrollHeight(width)`
- **전체 `content height(width)` + `padding`의 크기값**
- **스크롤바로 가려진 영역의 높이/너비도 포함한다.**
![](https://images.velog.io/images/songjy377/post/edfdac1f-5773-46f0-bb9a-a50cc30282f4/image.png)
### 📏 `scrollTop`, `scrollLeft`
- `scrollTop`, `scrollLeft`은 은 가려진 영역의 위쪽 높이/왼쪽 너비를 나타낸다.
![](https://images.velog.io/images/songjy377/post/e787638b-9fb0-45f0-b060-00bfc7e13fbd/image.png)
👉🏻 `scrollTop`, `scrollLeft` 값은 수정이 가능하다. 즉 `scrollTop`을 0이나 1e9같은 아주 큰 숫자로 설정해 스크롤바를 최상단이나 최하단으로 옮길 수 있다.

### ❌ `getComputedStyle` 로 너비와 요소를 얻지 마라
- `getComputedStyle`를 사용해 CSS가 적용된 요소의 높이와 너비를 구할 수 있다. 하지만 기하 프로퍼티를 사용해 너비와 높이 정보를 얻어야 하는 이유가 있다.

1. CSS `width`와 `height`는 다른 CSS 프로퍼티의 영향을 받는다. 요소의 너비와 높이 계산 방법을 '지정’하는 `box-sizing`이 이런 프로퍼티의 대표적인 예이다. 따라서 `box-sizing`을 변경하면 `getComputedStyle`로 구한 값이 부정확해 질 수 있다.

2. CSS `width`와 `height`는 `auto`일 수 있다. 인라인 요소(inline element)가 이런 경우에 속한다. **자바스크립트 입장에선 정확한 px값이 있어야 계산을 할 수 있기 때문에 `auto`라는 값은 쓸모가 없다.**
```html
	<span id="elem">안녕하세요!</span>
	<script>
	  alert( getComputedStyle(elem).width ); // auto
	</script>
```
3. 스크롤바 문제가 있다. `getComputedStyle(elem).width`를 사용하면, Chrome 같은 브라우저는 스크롤바 너비를 제외한 진짜 내부 너비를 반환하는데 (Windows 이외의 OS가 설치된 PC의)Firefox 에서는 스크롤바를 무시하고 CSS로 설정한 너비를 반환한다.

❗따라서, 이러한 이유로 getComputedStyle이 아닌 기하 프로퍼티를 사용해야 한다.

### ✅ Summery
>`offsetHeight(Width)` – 테두리를 포함하여, 요소 '전체’가 차지하는 높이와 너비
>`clientTop`, `clientLeft` – 요소 제일 밖을 감싸는 영역과 요소 안(콘텐츠 + 패딩)을 감싸는 영역 사이의 거리를 나타냄. 대부분의 경우 왼쪽, 위쪽 테두리 두께와 일치하지만, 오른쪽에서 왼쪽으로 글을 쓰는 언어가 세팅된 OS에선 `clientLeft`에 스크롤바 두께가 포함됨
>`clientHeight(Width)` – 콘텐츠와 패딩을 포함한 영역의 높이와 너비로, 스크롤바는 포함되지 않음
>`scrollHeight(Width)` – `clientWidth`, `clientHeight` 같이 콘텐츠와 패딩을 포함한 영역의 너비와 높이를 나타내는데, 스크롤바에 의해 숨겨진 콘텐츠 영역까지 포함됨
>`scrollTop`, `scrollLeft` – 스크롤바가 아래, 오른쪽으로 움직임에 따라 가려지게 되는 요소 콘텐츠의 너비와 높이
>스크롤바를 움직일 수 있게 해주는 `scrollLeft와` `scrollTop을` 제외한 모든 프로퍼티는 읽기 전용이다.

&lt;사진 출처 & refe : [JSInfo](https://ko.javascript.info/size-and-scroll)>