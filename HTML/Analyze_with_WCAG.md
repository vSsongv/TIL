# ✅ WCAG에 기반한 웹사이트 분석

> WCAG에 기반하여 NETFLIX와 TIVING을 분석해 보았다.(211031)

## 🟥 NETFLIX vs 🟧 TVING

### &lt;openWAX score>

|       | NETFLIX | TVING |
| :---: | :-----: | :---: |
| score |  94.6   |  100  |

![](https://images.velog.io/images/songjy377/post/3d87b029-7cf7-4e4b-911b-cc2b5fae7068/image.png)

## ❗ WCAG를 따르지 않는 요소들

### 1. NETFLIX - 적절한 대체 텍스트 미사용.

![](https://images.velog.io/images/songjy377/post/fe3a6b32-2ca0-49d7-b64a-002f9a6dbfb8/image.png)
![](https://images.velog.io/images/songjy377/post/e64b5288-3fcd-483d-9619-5771143db314/image.png)

-   img tag에 대한 대체 텍스트 값을 입력해두지 않았다. 이럴 경우 screen reader가 읽을 때 어떤 이미지인지 식별하기 어렵다.
-   WCAG **1. 인식의 용이성 중 1.1 대체 텍스트** 지짐을 만족하지 않는다.
    **<생각해본 올바른 방법>**
-   alt 속성에 올바른 대체 텍스트 값을 입력해주어, 보조 기술을 이용하는 사용자들이 정보를 인식하기 쉽도록 해야 한다. 아래 TVING이 좋은 예시이다.

### ⭕ TVING - 적절한 대체 텍스트 사용.

![](https://images.velog.io/images/songjy377/post/767797d6-31e5-4cd8-9f8c-c2f43d4be820/image.png)

![](https://images.velog.io/images/songjy377/post/09cc7ad4-4464-4e30-b5fc-8f54fd973f45/image.png)

-   img tag에 대한 대체 텍스트 값을 모두 입력해 두었다. 스크린리더가 사용자들에게 현재 어떤 이미지가 선택되고 있는지 올바르게 알려줄 수 있다.
-   WCAG **1. 인식의 용이성 중 1.1 대체 텍스트** 지침을 만족한다.

### 2. NETFLIX - 적절한 link text 미사용.

![](https://images.velog.io/images/songjy377/post/636e51eb-7a17-4db5-ac6a-c6411b63a5de/a%20tag%20no%20content.jpg)

-   내용이 없는 a tag를 사용했다. css 적용 시 '메뉴'라는 글씨가 보이지 않는 것으로 보아 메뉴 영역을 나타내고 싶었던 것 같은데, a tag를 사용했지만 내용이 없는 a tag를 사용했으므로
-   WCAG **2. 운용의 용이성 중 2.4 네비게이션 가능, 2.4.4 링크 목적** 지침에 맞지 않는다.
    **<생각해본 올바른 방법>**
-   fieldset으로 묶어주는게 메뉴 부분을 묶어주는 것이 좋았을 것 같다.

### ⭕ TVING - 적절한 link text 사용.

![](https://images.velog.io/images/songjy377/post/78b0f5f8-f074-4d7b-a9a1-98c3d41997a5/image.png)

-   모든 a link에 대해 적절한 link text를 제공함으로써 장애인 사용자들의 접근성을 높였다.
-   WCAG **2. 운용의 용이성 중 2.4 네비게이션 가능, 2.4.4 링크 목적** 지침을 만족한다.
    ![](https://images.velog.io/images/songjy377/post/ce10d77d-e096-4cad-bf4a-fbb765cf6879/image.png)

### 3. TVING - 키보드 포커스 식별 불가능.

![](https://images.velog.io/images/songjy377/post/864a5e0d-ceb8-42d5-9584-8540da6f863c/image.png)

-   키보드로 모든 interface를 사용 가능하나, (**2. 운용의 용이성 중 2.1 키보드 접근성 만족**) 키보드 포커스를 명확하게 하지 않아 사용자가 현재 어느 요소를 선택하고 있는지 알 수가 없다.
-   WCAG **2. 운용의 용이성 중 2.4 네비게이션 가능, 2.4.4 링크 목적 2.4.7 식별 가능한 포커스** 지침을 만족하지 않는다.
    **<생각해본 올바른 방법>**
-   키보드 포커스가 식별될 수 있도록 해야 한다.

### ⭕ NETFLIX - 키보드 포커스 식별 가능.

![](https://images.velog.io/images/songjy377/post/d6990739-3eea-4827-b9fe-6cd3db1b317a/image.png)

-   NETFLIX역시 키보드로 모든 interface를 사용 가능하기 때문에, **2. 운용의 용이성 중 2.1 키보드 접근성**을 만족하고, 키보드 포커스 또한 명확하기 때문에 WCAG **2. 운용의 용이성 중 2.4 네비게이션 가능, 2.4.4 링크 목적 2.4.7 식별 가능한 포커스** 지침 또한 만족하고 있다.

### 4. TVING - 한국어 자막 제공 없음.

![](https://images.velog.io/images/songjy377/post/89a56b2f-5fd7-41c5-8543-6893e884b27e/image.png)

-   위 사진은 한국 드라마를 재생한 상태이다. 외국 영화나 방송 같은 경우 영상 내부에 자막이 삽입되어 있지만, 청각장애인등 소리를 들을 수 없는 사용자에 대한 자막을 제공해주지 않는다.
-   WCAG **1. 인식의 용이성 중 1.2 시간 기반 미디어, 1.2.2 자막** 지침을 만족하지 않는다.
    **<생각해본 올바른 방법>**
-   한국 사용자 중 소리를 들을 수 없는 사용자들을 위해 자막을 제공해 줘야 한다.

### ⭕ NETFLIX - 한국어 자막 제공.

![](https://images.velog.io/images/songjy377/post/dcb4a631-e331-495f-a00e-daaefd678f18/image.png)

-   NETFLIX같은 경우 한국어 자막을 제공하고 있어 청각장애가 있는 사용자들도 시청할 수 있는 영상 콘텐츠가 증가하였고, 단순히 귀로 들으면 이해하기 힘들었던 발음들도, 자막을 통해 명확히 이해할 수 있게 되었다.
-   WCAG **1. 인식의 용이성 중 1.2 시간 기반 미디어, 1.2.2 자막** 지침을 만족하지 않는다.
