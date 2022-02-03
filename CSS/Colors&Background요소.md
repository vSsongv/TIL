# Color & Background 요소
>  Color와,  background-position, background-repeat같은 Background 요소들에 대해 다룬다.(211001)
## 🎨 Color
- 16진수 표기법 / 함수형 표기법(rgb() / rgba())이 있다.
- 보통 함수형 표기법을 사용한다.
### 🖌️opacity
 - 요소 자체(글씨, 배경색 모두 포함)의 불투명도를 설정한다. 초기값은 1로, 뒤의 요소가 하나도 보이지 않는다.
 ![](https://images.velog.io/images/songjy377/post/085ee890-d963-4c6b-90dc-f06d3bb2f160/image.png)
 - **상속되지 않는다.**
 - **자식 요소는 불투명하게 유지하고 싶다면 background 속성을 대신 사용하자.*** ```background: rgba(0, 0, 0, 0.4);```
```
   div { background-color: yellow; }
  .light {
    opacity: 0.2; /* 배경 위로 겨우 볼 수 있음 */
  }
  .medium {
    opacity: 0.5; /* 배경 위로 조금 더 잘 보임 */
  }
  .heavy {
    opacity: 0.9; /* 배경 위로 뚜렷하게 보임 */
  }
 ```
## 🎨 background-color
- 요소의 배경 색을 지정한다.
```background-color: hsla(50, 33%, 25%, 0.75);  /* 75% 불투명도 */```
-  **background-image 뒤**에 렌더링 된다.
## 🎨 background-image
- 요소의 배경 이미지를 한 개나 여러 개 지정한다.
```
background-image:
  linear-gradient(to bottom, rgba(255,255,0,0.5), rgba(0,0,255,0.5)),
  url('https://mdn.mozillademos.org/files/7693/catfront.png');
```
![](https://images.velog.io/images/songjy377/post/4687714a-9951-41cc-baea-40ec1131f28f/image.png)
## 🎨 background-repeat
- 배경 이미지의 반복 방법을 지정한다. 가로축 및 세로축을 따라 반복할 수 있고, 아예 반복하지 않을 수도 있다.
- 반복한 이미지는 요소 크기에 따라 잘릴 수도 있다. 그러나 잘리지 않도록 배경 이미지 크기를 조절하거나(round) 끝에서 끝까지 고르게 분배(space)할 수 있다.
```
  /* 키워드 값 */
  background-repeat: repeat-x; -> x축으로 반복
  background-repeat: repeat-y; -> y축으로 반복
  background-repeat: repeat; -> x,y축 모두 반복
  background-repeat: space; -> 요소가 잘리지 않을 만큼만 반복한다. 요소의 양쪽 끝에 기본으로 고정되고, 남은 부분을 고르게 분배한다. 분배할 수 없으면 빈칸이 된다.
  background-repeat: round; -> 원래 이미지를 줄여 새로운 이미지를 집어넣는다.
  background-repeat: no-repeat;
```
![](https://images.velog.io/images/songjy377/post/fc45096d-ea3b-4b45-812c-346811d687c3/image.png)
## 🎨 background-position
- 배경 이미지의 초기 위치를 설정한다.
```
  /* Keyword values */
  background-position: top;
  background-position: bottom;
  background-position: left;
  background-position: right;
  background-position: center;
```
![](https://images.velog.io/images/songjy377/post/af4b41fa-3ceb-4c7a-838f-5f10a32aae38/image.png)
## 🎨 background-origin
- 배경의 원점을 테두리 시작점, 테두리 내부, 안쪽 여백 내부 중 하나로 지정한다.
```
  /* 키워드 값 */
  background-origin: border-box;
  background-origin: padding-box;
  background-origin: content-box;
```
![](https://images.velog.io/images/songjy377/post/c2271ef9-a765-49cd-acee-db00387350be/image.png)
## 🎨 background-size
-  요소 배경 이미지의 크기를 설정한다. 
- 그대로 두거나, 늘리고 줄이거나, 공간에 맞출 수 있다.
```
  /* 키워드 값 */
  background-size: cover;
  background-size: contain;

  /* 단일 값 구문 */
  /* 이미지 너비 (높이는 'auto'가 됨) */
  background-size: 50%;
  background-size: 3.2em;
  background-size: 12px;
  background-size: auto;

  /* 두 개 값 구문 */
  /* 첫 번째 값: 이미지 너비, 두 번째 값: 이미지 높이 */
  background-size: 50% auto; 
  background-size: 3em 25%;
  background-size: auto 6px;
```
![](https://images.velog.io/images/songjy377/post/a20edc4a-a7d0-4c95-9c35-8e9ec1f5d912/image.png)
### <속성>
**contain** : 이미지가 잘리거나 찌그러지지 않는 한도 내에서 제일 크게 설정. 빈 공간 생길 수 있음. 기본으로 repeat됨.

**cover** : 이미지가 찌그러지지 않는 한도 내에서 제일 크게 설정. 이미지의 가로세로비가 요소와 다르다면 이미지를 **세로 또는 가로방향으로 잘라내어** 빈 공간이 생기지 않도록 설정.

**percantage** : 이미지가 찌그러지지 않는 한도 내에서 제일 크게 설정하지만, **가로에 맞춘다.**

**length** : 원본 크기의 너비/높이를 주어진 값으로 늘리거나 줄임. 음수는 유효하지 않음.

### ✅ background-shorthand
- 초기값
![](https://images.velog.io/images/songjy377/post/5ba0e115-2fd4-4f59-b8f7-65e21b371b44/image.png)

- color는 맨 뒤에 위치해야 한다.
- size값은 position바로 뒤에만 위치할 수 있으며, '/'문자로 구분해야 한다.
```
  /* <background-color> 사용 */
  background: green;

  /* <bg-image>와 <repeat-style> 사용 */
  background: url("test.jpg") repeat-y;

  /* <box>와 <background-color> 사용 */
  background: border-box red;

  /* 단일 이미지, 중앙 배치 및 크기 조절 */
  background: no-repeat center/80% url("../img/image.png");
```
