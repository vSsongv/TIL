## ✅no opner / no referrer

- a link를 사용할 때 웹페이지를 새 탭에서 열게 할 경우 target="\_blank" 속성을 사용함. 그런데 이건 문제점이 있음.

1. 보안상 취약점이 생김. -> js에서 window.opener로 부모 윈도우의 url을 바꿀 가능성이 있음
2. 퍼포먼스가 떨어질 수 있음. -> 링크된 페이지에서 높은 부하를 유발하는 js가 실행되고 있으면 링크를 건 페이지에도 영향을 줄 수 있음

### **no opner**

- noopener(노오프너)를 지정하면, 링크된 페이지에서 window.opener을 사용해서 링크를 건 페이지를 참조(reference)할 수 없게 됩니다. 더불어 링크된 페이지와 링크를 건 페이지는 별개의 프로세스로 취급되기 때문에 서로 연결되어 퍼포먼스가 떨어지는 일도 없게 됩니다.

### **no referrer**

- noreferrer(노리퍼러)를 지정하면 다른 페이지로 이동할 때, 링크를 건 페이지의 주소 등의 정보를 브라우저가 Referer: HTTP 헤더로 리퍼러(referer 또는 referrer)로서 송신하지 않습니다.

## ✅ 점진적 향상법

- 구식 기술 환경에서 동작할 수 있는 기능을 구현하고, 최신 기술을 사용할 수 있는 환경에서는 더 나은 사용자 경험을 제공할 수 있는 최신 기술을 제공하는 방법입니다. 즉, 구식 환경에서도 충분히 서비스를 사용할 수 있고, 최신 환경이라면 더 나은 기능들을 사용할 수 있도록 만드는 것입니다. 구식 브라우저를 사용하는 사용자에게 100만큼의 기능을 제공하고, 최신 브라우저를 사용하는 사용자에게는 130정도의 기능을 제공하도록 웹사이트를 만드는 것.

## ✅ 우아한 성능 저하법

- 최신 기술 기반 또는 최신 기기에서 동작하는 기능을 만들고 나서, 오래된 기술 또는 오래된 기기에서 동작하게 하기 위해 유사한 기능을 만들어 동작하게 하는 것.

## ✅ BEM 방법론

- css class명 가이드
- block element modifier
- block--**element(내부 요소)\_\_modifier(상태/색상)** (ex)headersearch--btn
- 👍🏻장점: 직관적 클래스명으로 구조 파악이 쉬움
- 👎🏻단점: 클래스명이 상대적으로 길어질수밖에 없음.

## ✅ SMACSS 방법론

- Scalable and Modular Architecture for CSS
  -Base - 기본 규칙
  - 각 브라우저의 기본 스타일 (default.css, reset.css), 요소 element 스타일의 기본 정의 값
- Layout - 레이아웃 규칙
  - 큰 틀의 레이아웃, 요소를 배치, 구별하는데 적용
    주요 컴포넘트 : header, footer, aside, container, content 등
    하위 컴포넘트 : list, item, form 등
    클래스명은 접수사 i-, layout-명시
- Module - 모듈 규칙
  - 페이지에서 재사용 가능한 요소 : 버튼, 배너, 아이콘, 박스 요소 등
    각 모듈은 독립성을 가지게 스타일 선언 : 재사용이 가능하게 id, 태그 선택자는 사용하지 않음.
- State - 상태 규칙
  - 요소의 상태변화를 표현하는 요소 : 툴팁, 아코디언 등
    active나 disable 등이며 suffix "is-"나 "s-"를 붙여서 사용
    모듈과 레이아웃 모두 적용 가능
- Theme - 테마 규칙
  - 사용자가 선택 가능하도록 스타일을 재선언하여 사용.
    Theme는 전반적인 Look and feel을 정의하며 suffix "theme-"를 붙인다.

## ✅ OOCSS 방법론

- Object Oriented CSS
- 중복을 최소화하여, 캡슐화
- 구조와 외양을 분리한다.
- 👍🏻장점: 공통 부분을 찾아 재활용 가능
- 👎🏻단점: 다중 클래스 사용으로 유지보수의 어려움과 가독성이 떨어질수 있다.

## ✅ DOM

- document object model
- web browser가 html 페이지를 인식하는 방식.
- view port에 렌더링할 요소들을 결정하기 위해 사용된다.
- 보이지 않는 요소를 포함하고, 가상 요소는 포함하지 않는다.

## ✅ CSSOM

- browser에서 dom을 생성하고 나면 CSS를 병합해서 CSSOM tree를 만든다.
- 모든 스타일에 관련된 속성들이 포함되어 있다.
- JS는 DOM구조를 변경시킬수가 있다. parsing을 하다가 JS를 만나면 JS를 다운받기 전까지 parsing을 멈춘다.
- parsing: 원하는 데이터를 특정 패턴이나 순서로 추출하여 가공하는 것,
- crawling: 웹 페이지를 수집하여 분류하고 저장하는 것

## ✅ Render tree

- DOM을 만들고 CSSOM이 만들어지면 **최종적으로 사용자에게 보여질 것들만** render tree로 만들어진다.

  > DOM 트리의 루트에서 시작해서 노드 각각을 읽으며 표시합니다.
  > 메타 태그나 스크립트 태그 등의 노드는 표시되지 않습니다. (사용자에게 보이지 않아도 되는 내용은 렌더링 출력에 반영되지 않습니다.)
  > 일부 노드는 CSS를 통해 숨겨지며 렌더 트리에서도 생략이 됩니다. 예를 들어 span 노드에 display : none 속성을 설정하면 렌더 트리에서 누락 됩니다.
  > 표시된 각 노드에 대해 적절하게 일치하는 CSSOM 규칙을 찾아 적용합니다.
  > 표시된 노드를 콘텐츠 및 계산된 스타일과 함께 내보냅니다.

- Rendering 과정

1. DOM(Document Object Model), CSSOM(CSS Object Model) 생성
2. Render Tree 생성
3. Layout - Layout 단계는 브라우저의 뷰포트(Viewport) 내에서 각 노드들의 정확한 위치와 크기를 계산합니다. 풀어서 얘기하자면 생성된 Render Tree 노드들이 가지고 있는 스타일과 속성에 따라서 브라우저 화면의 어느위치에 어느크기로 출력될지 계산하는 단계라고 할 수 있습니다. Layout 단계를 통해 %, vh, vw와 같이 상대적인 위치, 크기 속성은 실제 화면에 그려지는 pixel단위로 변환됩니다.
4. Paint - Layout 계산이 완료되면 이제 요소들을 실제 화면을 그리게 됩니다. 이전 단계에서 이미 요소들의 위치와 크기, 스타일 계산이 완료된 Render Tree 를 이용해 실제 픽셀 값을 채워넣게 됩니다. 이 때 텍스트, 색, 이미지, 그림자 효과등이 모두 처리되어 그려집니다.

## ✅ Reflow / Repaint

- 자바스크립트 코드가 실행되는 과정에서 DOM과 CSSOM을 변경하면 변경된 DOM과 CSSOM이 다시 렌더트리로 결합되고 레이아웃과 페인트 과정을 거쳐 다시 렌더링 됩니다. 이 과정을 리플로우(reflow), 리페인트(repaint)라고 합니다.
- reflow는 레이아웃을 다시 계산하는 것이며, repaint는 재결합된 렌더트리를 기반으로 다시 페인트하는 것입니다.

- 줄이는 방법

1. 사용하지 않는 노드에는 visibilty: invisible 보다 display: none을 사용하기
2. Reflow, Repaint 가 발생하는 속성 사용 피하기

- 아래는 각각 Reflow, Repaint가 일어나는 CSS 속성들 입니다. Reflow가 일어나면 Repaint는 필연적으로 일어나야 하기 때문에 가능하다면 Reflow가 발생하는 속성보다 Repaint 만 발생하는 속성을 사용하는것이 좋습니다.
  ![repaint,reflow](./image1.jpg)

## ✅ WebPack

- 많은 js파일을 하나로 관리하기 어려워서 등장한 개념
- 수정된 파일만 자동으로 반영해주므로 performance 우수
- 현대 Javascript Application의 Static Module Bundler
- Entry(엔트리) : dependency graph를 만들기 위해 필요한 Input Source
- Output(출력) : Webpack이 생성한 bundles의 결과물의 위치
- Loaders(로더) : 다른 Type의 파일을 Webpack이 이해하고 처리가능한 모듈로 변환시키는 작업을 담당
- Plugins(플러그인) : bundle optimization, asset management and injection of environment과 같은 일 진행
- Mode(모드) : 진행할 profile 지정.

## ✅CORS(Cross-Origin Resource Sharing)

-
