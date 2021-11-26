## ☁️ Float

-   한국어로 '뜨다'라는 의미를 가진 것처럼, 요소를 다른 요소 위에 **띄워주는** 역할을 한다.

### <값>

-   left
    요소가 자신의 포함(containing) 블록의 좌측에 부동(float, 떠움직여)해야 함을 나타내는 키워드.
-   right
    요소가 자신의 포함 블록의 우측에 부동해야 함을 나타내는 키워드.
-   none
    요소가 부동하지 않아야 함을 나타내는 키워드.
-   inline-start
    요소가 자신의 포함 블록의 시작쪽에 부동해야 함을 나타내는 키워드. 즉, ltr(left to right) 스크립트 상에서 왼쪽 그리고 rtl(right to left) 스크립트 상에서는 오른쪽.
-   inline-end
    요소가 자신의 포함 블록의 끝쪽에 부동해야 함을 나타내는 키워드. 즉, ltr 스크립트 상에서 오른쪽 그리고 rtl 스크립트 상에서는 왼쪽.

## ❓ How Float works?

-   기본적으로 웹에서는 _Normal Layout Flow_ 를 따른다. 왼쪽 위에서부터 오른쪽 아래 방향으로.

✔️ **아래와 같은 layout이 있다고 하자. (Main은 자식 요소들에 의해 Height가 정해진다.)**
![](https://images.velog.io/images/songjy377/post/607a71e5-bf5b-459a-b004-0832358ce74e/image.png)

### ✔️ BOX2를 왼쪽으로 float하게 되면? (float : left)

![](https://images.velog.io/images/songjy377/post/db430d34-b5da-431a-8be7-b4b1360f7888/image.png)
**1. BOX2가 BOX1위로 뜨게 되어, BOX1을 가린다. 2. BOX3는 Normal Flow를 따라서 BOX2가 있던 자리로 올라가게 된다. 3. Main 영역 또한 사라진 BOX2의 영역만큼 줄어든다.**

### ✔️ 위의 상황에서 BOX1의 width가 main의 width와 같고, BOX1에 text가 있다면?

![](https://images.velog.io/images/songjy377/post/9785824d-761b-4f2a-bd6d-78008c88148b/image.png)
**BOX2가 BOX1위로 뜨게 되어, BOX1을 가리는데, text는 box의 크기만큼 밀리게 된다.**

### ✔️ 만약 BOX 모두 float 시키면?

![](https://images.velog.io/images/songjy377/post/a5fadf69-e0f3-4cc8-bc3b-58acafb72dbc/image.png)
**모든 자식요소들이 떠 있기 때문에 부모 영역이 그 높이를 잃게 되어, Layout을 무너뜨리게 된다!**

### ✔️ Footer부분의 Layout을 살리려면?

### ❗ Clear를 이용하자!

## ☁️ Clear

-   어떤 요소가 float된 요소 바로 다음으로 오는지, (위의 사진처럼) 아나면 그 아래로 내려가야 하는지를 지정할 수 있다.(float 해제)
-   **block요소에만 사용 가능**

### <값>

-   none
    요소가 지난 부동 요소를 해제하기 위해 아래로 이동되지 않음을 나타내는 키워드.
-   left
    요소가 지난 left 부동체를 해제하기 위해 아래로 이동됨을 나타내는 키워드.
-   right
    요소가 지난 right 부동체를 해제하기 위해 아래로 이동됨을 나타내는 키워드.
-   both
    요소가 지난 both left 및 right 부동체를 해제하기 위해 아래로 이동됨을 나타내는 키워드.
-   inline-start
    요소가 포함 블록의 시작 쪽 부동체를 해제하기 위해 아래로 이동됨을 나타내는 키워드. 즉 ltr 스크립트의 left 부동체 및 rtl 스크립트의 right 부동체.
-   inline-end
    요소가 포함 블록의 끝 쪽 부동체를 해제하기 위해 아래로 이동됨을 나타내는 키워드. 즉 ltr 스크립트의 right 부동체 및 rtl 스크립트의 left 부동체.

✔️ **footer에 clear: both를 사용하면 해당 요소가 float요소들의 영향을 받지 않고 아래에 위치할 수 있다.**

![](https://images.velog.io/images/songjy377/post/a8839960-5dd6-4275-8ac0-bf0251648402/image.png)

### ✔️ float 사용 시 사라진 부모height 값을 살리려면?

### ❗ 가상 요소 선택자 이용

> -   부모 클래스에게 가상 요소를 만들어서 해당 요소를 출력하는 방법!
> -   뒤쪽에 가상 요소를 만들어서 해당 요소를 block으로 해주면 가상요소가 보여야 하므로 부모가 높이를 되찾는다.
>
> ```css
> .main::after {
>     content: "";
>     clear: both;
>     display: block;
> }
> ```
>
> <span style="color:red">**가장 권장된다!**<span>

### ❗ 부모 tag에 clearfix 추가하기

> -   부모 main tag내에 clearfix 라는 div를 만들어서 해당 div에 `clear: both` 속성을 주어, 해당 div가 float된 요소들 아래에 위치하도록 하는 방법!
>
> ```html
> <main>
>     <div class="group group1">1</div>
>     <div class="group group2">2</div>
>     <div class="group group3">3</div>
>     <div class="clearfix"></div>
> </main>
> ```
>
> ```css
> main .clearfix {
>     clear: both;
> }
> ```
>
> <span style="color:red">**불필요한 div를 이용하는 것이므로 가장 권장되지 않는다!**<span>

### ❗ BFC 활용하기

## ☁️ BFC(Box Formatting Context)

-   하위 요소들을 대상으로 독립적인 레이아웃 환경을 만들수 있고 다른 주변 요소와도 레이아웃 관계를 형성할 수 있다. 내부의 모든 요소를 포함한다.
-   float 요소를 그릴 때 사용하는 박스와 같은 녀석. BF의 크기 및 위치에 따라서 float 요소가 어디서 생기는지가 달라진다.
-   **BFC가 생기게 되면 같은 div 안에 있는 요소들이 그 BFC 안에 들어가고, 그 요소들의 크기는 BFC의 크기에 영향을 준다.**

### ✅ BFC가 생기는 조건

> -   html root 요소일때
>     -   html는 웹 문서의 시작을 알리는 최상위 요소이다. 따라서 기본적으로 하위 요소들은 독립적인 레이아웃을 가질 수 있도록 되어 있다.
> -   float 상태일때
>     -   하위 요소가 선택적으로 float 속성 값을 none이 아닌 값을 가지게 되면 독립적인 레이아웃 환경을 갖는다.
> -   position: fixed거나 absolute일때
>     -   하위 요소가 선택적으로 position 속성 값을 absolute 또는 fixed 값을 가지게 되면 독립적인 레이아웃 환경을 갖는다.
> -   display: inline-block, table, table-cell, table-caption
>     -   하위 요소가 inline-block을 갖게되면 주변 inline 요소와 관계가 무너지면서 독립적인 공간을 형성하게 되므로 역시 독립적인 레이아웃 환경을 갖는다.
> -   overflow: visible가 아닐 때
>     -   overflow 속성 값이 visible 이외의 값을 갖는 다는 것은 공간에 대한 크기 정의를 했다고 볼 수 있다. 그렇게 때문에 독립적인 공간을 형성한 것으로 볼 수 있으며 따라서 BFC를 요건을 가지고 있다.
> -   display: flow-root (일부 브라우저만 동작함)
>     -   부모 요소에 적용시켜서 블록 컨테이너를 생성하고, float가 적용된 자식 요소를 인식시키기 때문에 독립적인 공간을 형성한다.

[참조링크](https://blueshw.github.io/2020/05/17/know-css-block-formatting-context/)

### ✅ BFC의 활용

-   margin callapse를 막는다.
-   새로운 bfc를 만들어서 float된 요소들을 포함할 수 있다.

### ❗ overflow:hidden

> -   float된 요소의 부모태그에 overflow:hidden을 적용하여, 자식 요소가 부모 요소 박스보다 커지는 경우 자식 요소를 가리는 방법!
> -   넘치는 콘텐츠를 숨긴다는 의미는 부모 요소 박스가 그 자식 요소 콘텐츠를 숨긴다는 것인데,
> -   부모 요소가 플롯된 자식 요소로 인해 높이를 인지하지 못하고 있는 상황에서 overflow: hidden; 속성을 적용하게 되면 부모 요소(컨테이닝 박스)는 넘치는 요소를 숨김 처리를 하려고 하기 때문에 자식 요소의 높이를 인지하기 위해 자동으로 높이값을 계산하게 된다!
>
> ```css
> main {
>     overflow: hidden;
> }
> ```
>
> <span style="color:red">**but 해당 요소 안의 컨텐츠를 박스 외부로 표현해줄 수 없는 경우가 있다. auto를 쓰면 스크롤이 생겨서 보기에 좋지 않을수도 있다.**<span> > ![](https://images.velog.io/images/songjy377/post/cb5c90a5-fc9f-43d0-ab1f-d6c1cdb3bd0d/image.png)

### ❗ Flow-root

> -   생긴지 얼마 안 된 속성이며, 비교적 최근에 만들어진 속성이다.
> -   부모 요소에 적용시켜서 블록 컨테이너를 생성하고, float가 적용된 자식 요소를 인식시키고, 요소의 시작 지점을 변경시킬 수 있다.
>
> ```css
> main {
>     display: flow-root;
> }
> ```
>
> <span style="color:red">**IE에서는 지원 불가! **<span> > ![](https://images.velog.io/images/songjy377/post/a57760a2-ea5b-4a3b-9815-dfa270e433e6/image.png)

### ❗ 부모요소를 float 시키기

> -   자식요소가 모두 float 되었을 때 부모요소까지도 float 시키는 방법.
> -   부모 요소는 딱 자식 요소를 담을 만큼만 높이를 다시 가진다.
> -   <span style="color:red">**부모 요소를 전부 float시켜야 하므로 좋지 않은 방법.**<span>
>
> ```css
> main {
>     float: left;
> }
> ```
