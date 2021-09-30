# Box Model
>Box Model의 margin, border, padding 영역과 관련된 내용을 다룬다.(210930)
## 📦Box Model
![](https://images.velog.io/images/songjy377/post/a45024e5-116e-4eca-9a68-0347e9cd2740/image.png)
> - content : 콘텐츠가 표시되는 영역.
> - padding : content와 border사이의 영역.
> - border : padding과 margin사이의 테두리. 기본값은 0
> - margin : 가장 바깥 쪽 레이어로 content와 padding, border를 둘러싸면서 해당 box와 다른 요소 사이의 공백 역할을 한다.

### <크기>
- box의 크기를 설정할 수 있는 요소들이다.
- block 요소는 기본 content만큼의 크기로 height, width가 적용되고, 직접 지정할 시 지정한 만큼 크기를 가진다.
- inline 요소는 height/width 속성이 적용되지 않는다. -> display 속성으로 block요소로 변경 가능하다.
 ## 🗳️ width
 - 요소의 너비를 설정한다. 기본값은 콘텐츠 영역의 너비이지만, box-sizing이 border-box라면 테두리 영역의 너비를 설정한다. 상속되지 않는다.
 ## 🗳️ height
 -  요소의 높이를 지정한다. 기본값은 콘텐츠 영역의 높이지만, box-sizing이 border-box라면 테두리 영역의 높이를 설정한다. 상속되지 않는다.
 ## 🗳️ min/max-height/width
 - 최소/최대 너비/높이를 지정 가능하여, 해당 값을 넘어가게 되면 더 지정한 값보다 작아지거나 커지지 않는다.
```
   .blcok {
      width: 100px
      height :200px
   }
   
   #parent {
      width: 100px
      height :200px
   }
   
   #child {
      width: 50%px
      min-wid: 100px -> 50%한 값이 100px이하로 줄어들게 되면 더이상 줄어들지 않는다.
      height :200px
   }
```
![](https://images.velog.io/images/songjy377/post/33b8b422-3c19-4d46-a7b4-6a06be1a4df0/image.png)
### <여백>
- box model의 여백을 결정하는 요소들이다.

  > - 한 개의 값은 모든 네 면의 테두리 스타일을 설정.
  > - 두 개의 값을 지정하면 첫 번째는 위와 아래, 두 번째는 왼쪽과 오른쪽 테두리 스타일을 설정.
  > - 세 개의 값을 지정하면 첫 번째는 위, 두 번째는 왼쪽과 오른쪽, 세 번째 값은 아래 테두리 스타일을 설정.
  > - 네 개의 값을 지정하면 각각 상, 우, 하, 좌 순서로 테두리 스타일을 지정. (시계방향)
## 🗳️ margin
- box model의 맨 **바깥쪽 여백** 부분이다.
- margin-bottom, margin-left, margin-right, margin-top의 네 부분을 조절할 수 있다.
```
  /* 네 면 모두 적용 */
  padding: 1em;

  /* 세로방향 | 가로방향 */
  padding: 5% 10%;

  /* 위 | 가로방향 | 아래 */
  padding: 1em 2em 2em;

  /* 위 | 오른쪽 | 아래 | 왼쪽 */
  padding: 5px 1em 0 2em;

  /* 전역 값 */
  padding: inherit;
  padding: initial;
  padding: unset;
```
### &lt;Value>
- length를 지정할 경우 : 여백의 크기로 고정값 사용.
- %를 사용할 경우 : 여백의 크기로 컨테이닝 블록 너비의 백분율 사용. **부모의 width값에 대해 계산된 값이다. 
- 음수값 사용 가능

```
  #parent {
  	width: 200px
    	height: 200px
  }
  #child {
  	margin: 30% -> 부모의 width인 200px의 30%의 값을 가지게 된다.
  }
```
## 🗳️ margin collapsing(마진 상쇄)
 - 여러 block요소들의 위/아래 margin이 경우에 따라 가장 큰 크기를 가진 margin으로 결합(상쇄)되는 현상. 

**아래 3가지 경우에 일어난다.**
### 1. 인접 형제
- 두 형제 요소의 위/아래 여백이 만나 상쇄된다.
ex) box의 위 margin이 10px이고, 아래 margin이 20px일 경우 두 box가 겹쳐진다면 20px로 margin을 가지게 된다.
![](https://images.velog.io/images/songjy377/post/69c78b63-edb1-4518-beef-f515af03222c/Image%2017.jpg)
### 2. 부모-자식 요소 간
- 부모 블록에 **border, padding, inline content가 없어서** 부모와 자식의 **margin-top**이 만나는 경우
- _아래 사진에서 border,padding, content값이 없으면 child의 margin과 patent의 margin이 만날수밖에 없다._
![](https://images.velog.io/images/songjy377/post/3df5201f-95aa-4c55-9e17-b464b24939af/image.png)
- 같은 이유로 **height값이 지정되지 않아서** 부모와 자식의 **margin-buttom**이 만나는 경우
### 3. 빈 블록
- **border, padding, inline content가 없고 height값이 지정되지 않으면**, 해당 블록의 margin-top과 margin-bottom이 상쇄된다.
![](https://images.velog.io/images/songjy377/post/5a18034b-0fdd-4d66-83b7-04fdc152fbec/image.png)

## 🗳️ padding
### <테두리>
-  요소의 네 방향 **안쪽 여백** 영역을 설정한다. padding-top, padding-right, padding-bottom, padding-left의 단축 속성.
```
    /* 네 면 모두 적용 */
    padding: 1em;

    /* 세로방향 | 가로방향 */
    padding: 5% 10%;

    /* 위 | 가로방향 | 아래 */
    padding: 1em 2em 2em;

    /* 위 | 오른쪽 | 아래 | 왼쪽 */
    padding: 5px 1em 0 2em;

    /* 전역 값 */
    padding: inherit;
    padding: initial;
    padding: unset;
```
- length를 지정할 경우 : 여백의 크기로 고정값 사용.
- %를 사용할 경우 : 여백의 크기로 컨테이닝 블록 너비의 백분율 사용. **부모의 width값에 대해 계산된 값이다.
- 음수값 사용 불가
## 🗳️ border
- 요소의 테두리를 설정한다. border-width(테두리 두께), border-style(테두리 모양) border-colo(테두리 색)의 값을 설정한다.
- border style은 여러 종류가 있다. 
![](https://images.velog.io/images/songjy377/post/a7239bcd-705c-41e9-8199-59e9b52b440f/image.png)
- **style이 none이면 다른 속성들 모두 적용되지 않는다.**
- line-width :  테두리의 굵기로 기본값은 medium입니다. border-width를 참고하세요.
- line-style : 테두리의 스타일로 기본값은 none입니다. border-style을 참고하세요.
- color : 테두리의 색상으로 기본값은 currentcolor입니다. border-color를 참고하세요.
```
  /* 스타일 */
  border: solid;

  /* 너비 | 스타일 */
  border: 2px dotted;

  /* 스타일 | 색 */
  border: outset #f33;

  /* 너비 | 스타일 | 색 */
  border: medium dashed green;

  /* 전역 값 */
  border: inherit;
  border: initial;
  border: unset;

```
### ✅ border vs outline
1. outline 요소 콘텐츠의 밖에 그려지며 절대 공간을 차지하지 않습니다.
2. outline은 box model에 포함되지 않는다.

## 🗳️ border-radius
- 요소 테두리 경계의 꼭짓점을 둥글게 만든다. 하나의 값을 사용해 원형 꼭짓점을, 두 개의 값을 사용해 타원형 꼭짓점을 적용할 수 있다. 
- radius를 사용하면 꼭짓점에 지정한px을 반지름으로 가지는 원이 그려진다고 생각하면 된다.
- radius는 여러 종류가 있다.
![](https://images.velog.io/images/songjy377/post/9e7a06f4-6b0c-4aab-9cb8-4ce975e4bca8/image.png)
- length를 지정할 경우 : 길이 값을 사용해 원의 반지름 또는 타원의 짧은반지름과 긴반지름을 지정.
- %를 사용할 경우 : 백분율 값을 사용해 원의 반지름 또는 타원의 짧은반지름과 긴반지름을 지정합니다. **가로축 값은 요소 박스의 너비에 대한 백분율**, **세로축 값은 박스의 높이에 대한 백분율**.
- 음수값 사용 불가.

## 🗳️ border-box
	box-sizing: border-box; 로 사용한다.
- 요소의 너비와 높이를 계산하는 방법을 지정한다.
- CSS 박스 모델의 기본값에서, 지정한 너비와 높이는 요소의 **콘텐츠 박스 크기에만 적용**된다. 요소에 테두리나 안쪽 여백이 있으면 너비와 높이에 더해서 화면에 그린다는 이야기다. 따라서 크기를 설정할 때, **원하는 크기를 얻으려면 테두리나 안쪽 여백을 고려해야 한다.**
ex) 아래 그림에서 가로 길이와 세로 길이는 각각 300,200이지만 border의 30px이 더해져서 실질적으로는 360, 260이 된다.
![](https://images.velog.io/images/songjy377/post/1a4ad960-c914-46ba-804c-f29e57a9e38d/image.png)
- **border-box는 테두리와 안쪽 여백의 크기도 요소의 크기로 고려한다.**
![](https://images.velog.io/images/songjy377/post/5d868bfd-c753-4b48-a494-49fd7870c26e/image.png)
### ✅ border vs outline
css의 맨 상단에 아래 코드처럼 선언해 주면 각 요소에 속성을 부여하지 않아도 된다.
```
  * {
    box-sizing: content-box;
  }
```
