# 웹 접근성(Web Accessibility)과 WAI-ARIA

> 웹 접근성과 wai-aria에 대해 다룬다.(211026)

### ❓Web Accessibility(웹 접근성이란?)

-   **장애를 가진 사람, 장애를 갖지 않은 사람 모두에게 동등하게 웹에 접근할 수 있게 하는 것**. (스크린리더, 화면 돋보기, 음성 인식, 키보드 오버레이 등이 포함된다.)
-   이런 기능이 잘 되어있는 사이트가 접근성이 좋은 사이트라고 한다.
-   접근성이 좋은 개발을 하는 것이 중요하다.

📼[웹 접근성의 다양한 관점들에 대한 비디오](https://www.youtube.com/watch?v=3f31oufqFSM)📼

### ❔왜 중요한가?

-   서로 다른 상황에 놓인 모두에게 평등하게 좋은 서비스를 제공하기 위해서!
-   서로 다른 다양한 환경에서, 나의 서비스를 사용하고자 하는 사용자가 언제 어디서나 쉽고 편하게 접근할 수 있도록 해야 좋은 서비스라고 할 수 있지 않을까? :)

### ❗❗ 기억해야 할 점 ❗❗

> -   웹 접근성은 어느 누군가에게는 직접적으로 피부에 와 닿는 생활의 일부이다.
> -   이러한 정보의 격차를 줄이고 모두 함께 공유할 수 있는 웹 세상을 만드는 것이 웹 접근성의 궁극적인 목표!

### 🌐W3C(World Wide Web Consortium)

[w3c](https://www.w3.org/) 에서 웹 접근성에 대한 지침을 정하고 있다.

**✅웹 접근성을 높이기 위해 WAI-ARIA를 사용할 수 있다.**

## 🔆WAI-ARIA?

> WAI-ARIA(Web Accessibility Initiative – Accessible Rich Internet Applications)는 웹 페이지, 특히 동적 콘텐츠, 그리고 Ajax, HTML, 자바스크립트 및 관련 기술로 개발된 사용자 인터페이스 구성 요소의 접근성을 증가시키는 방법에 대해 규정한 W3C가 출판한 기술 사양이다.

-   마우스와 같은 포인팅 장비를 사용하기 힘든, 스크린 리더를 사용하는 사용자들에게 동적 컨텐츠, javascript, ajax, vue, react 등과 같이 페이지를 새로고침 하지 않고도 페이지의 내용과 데이터가 바뀌는 영역에 역할, 속성, 상태 정보를 추가하여 동적인 컨텐츠에 보다 원활하게 접근하고 페이지에 접근성을 높여 여러 사용자들에게 원활한 페이지 이용을 도와준다.
-   (ex. 버튼을 클릭하여 페이지 새로고침이나 링크 이동으로 페이지가 전환되는 것이 아닌 컨텐츠 내용이나 구조가 바뀌는 상황에서 페이지 전환 상태나 정보를 WAI-ARIA로 알려준다.)

### 🎡역할(Role), 속성(Property), 상태(State) 이 3가지의 기능을 제공한다.

🎈Role : UI에 포함된 특정 element의 기능을 정의하는 것.

🎈Property : 해당 컴포넌트의 특징이나 상황을 정의하며 속성명으로 'aria-\*'라는 접두사를 사용한다.

-   읽기 전용(Read Only)인지, 필수 항목(Require)인지, 사용자 입력에 대해 자동완성 (Auto Complete) 기능을 지원할 것인지, 드래그(Drag)가 가능한지, 팝업(has Popup)이 뜨는지, 업데이트된 정보(Live)가 있는지 등의 상황을 사용자가 인지할 수 있도록 한다.

🎈State : 해당 컴포넌트의 상태 정보를 정의한다.

-   expanded: 메뉴가 펼쳐진 상태인가?
-   invalid: 적절하지 못한 값이 입력되었는가?
-   hidden: 콘텐츠가 숨김 상태인가?

> 예를 들어, 항공권 구매 시 좌석 등급을 선택해야 할 때, 이코노미/프레스티지/일등석을 라디오버튼으로 제작하였으나 시각적인 타이틀만 눈에 보일 뿐, 그룹으로 묶지 않아 스크린리더 사용자는 정보를 인지하지 못하기 때문에 이해하기 어렵게 된다.

```
<div id="classtxt">Cabin Class</div>
<ul>
    <li>
        <input id="economy" type="radio" value="economy"
         checked="checked" name="cabin">
        <label for="economy">Economy Class</label>
    </li>
    <li>
        <input id="Prestige " type="radio" value="Prestige" name="cabin">
        <label for="Prestige ">Prestige Class</label>
    </li>
    <li>
        <input id="First" type="radio" value="First" name="cabin">
        <label for="First">First Class</label>
    </li>
</ul>
```

스크린리더로 듣게 되면 그룹 정보에 대한 내용은 없고 라디오 버튼 정보만 읽어 맥락 이해가 어렵다.
라디오버튼 선택됨
Economy Class 1/3
해결방안
전체를 감싸는 <ul>요소에 ARIA role="group"과 aria-labelledby를 사용하여 그룹으로 묶고 그룹 제목을 아래와 같이 명시한다.

```
<div id="classtxt">Cabin Class</div>
<ul role="group" aria-labelledby="classtxt">
    <li>
        <input id="economy" type="radio" value="economy"
         checked="checked" name="cabin">
        <label for="economy">Economy Class</label>
    </li>
    <li>
        <input id="Prestige " type="radio" value="Prestige" name="cabin">
        <label for="Prestige ">Prestige Class</label>
    </li>
    <li>
        <input id="First" type="radio" value="First" name="cabin">
        <label for="First">First Class</label>
    </li>
</ul>
```

그룹정보를 명시하고 스크린리더로 듣게되면 아래와 같이 들리게 되며, 역순으로 포커스가 이동하여도 그룹정보를 읽어주게 된다.
Cabin Class 그룹
라디오버튼 선택됨
Economy Class 1/3

> 예를 들어, 아래와 같은 코드가 있을 때 스크린리더를 이용하는 사용자의 경우 checkbox의 정보를 얻을 수 없다.

```css
<li tabindex="0" class="checkbox" checked>
  Receive promotional offers
</li>
```

이때 WAI-ARIA를 사용하여

```css
<li tabindex="0" class="checkbox" role="checkbox" checked aria-checked="true">
  Receive promotional offers
</li>
```

처럼 코드를 변경하면 role과 aria-checked 속성을 이용하면 스크린리더를 사용하는 사용자도 해당 css요소를 인지할 수 있게 된다.

### ✅생각해 볼 점

-   아시아나 항공 페이지에 들어가서 키보드 tab버튼을 눌러보면, 상위 메뉴인 예약, 여행준비, 여행, 아시아나클럽 부터 선택되는것이 아니라, 제일 왼쪽의 예약 메뉴부터 선택되고,
-   해당 메뉴의 하위 항목들이 모두 포커징된 후 두번째 상위 메뉴인 여행 준비에 포커싱이 된다. 만약 사용자가 가장 오른쪽 메뉴인 아시아나클럽을 선택하고자 한다면 상당히 오랜 시간동안 tab키를 눌러야 한다는 것이다.

![](https://images.velog.io/images/songjy377/post/1de5a74c-1be9-4b45-8b6d-7c558cb59093/image.png)

-   스타벅스 코리아 페이지에서도 마찬가지였다. 제일 왼쪽 상위 요소가 선택되고, 그 하위 요소들이 먼저 포커싱되었다.
-   현재 가장 사용자가 많은 스트리밍 사이트인 넷플릭스와 티빙을 확인해보았는데, 넷플릭스는 마우스가 없는 사용자를 위해 tab으로 요소들이 선택될 수 있도록 해두었지만, 티빙은 tab키를 사용할 수 없는 것을 확인하였다.
-   만약 마우스가 없는 사용자는 티빙을 이용할 수 없을 텐데, 접근성을 고려하지 않은 사이트라고 생각했다.
-   과연 이게 UX적으로 좋을지를 생각해보면, 분명 그렇게 효율적인 UX는 아니라고 생각한다.
