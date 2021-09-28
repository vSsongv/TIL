# Second Day - Tag
> html을 이루는 tag들에 다룬다. text와 관련된 요소들을 주로 다룬다.
## Text Contents
### 🔘Entity 
<, >, ", ', &, 같은 특수기호들은 _&+이름_ 으로 써줘야 한다.
### 🟠Heading Tag
제목을 표현할 수 있는 tag. &lt;h1> ~ &lt;h6>까지 존재한다. 
### ⚪&lt;a> Tag
hyper link를 추가할 수 있도록 하는 tag. &lt;a href="**_이동할 링크_**">&lt;/a>의 형태로 사용한다. **_이동할 링크_** 부분에 mailto:emiail-adress를 하면 이메일을 작성할 수 있는 프로그램으로 연결되고, tel:phone-number를 하면 전화를 할 수 있는 프로그램으로 연결된다.
기본으로 현재 브라우저에 표시한다. 그러나 &lt;a href="**_이동할 링크_**">&lt; **target = "\_blank"**/a>을 하면 새 탭으로 열린다.
- 절대경로 :현재 위치와 관계없이 절대경로로 이동
- 상대경로 :현재 위치에서 이동할 link를 입력
### 🟢quate Tag
인용 요소를 나타낼 때 사용하는 tag
- &lt;blockquate> : 맨 앞에 여백이 생성된다.(p tag내에 사용 ❌) 긴 인용문을 나눠서 표현하고 싶을 때 사용한다.
- &lt;q> : 따옴표가 자동으로 생성된다.
     - cite -> 인용문의 출처, 정보를 가리킬 용도. 코드에만 보이고 웹사이트에서는 보이지 않는다.
### 🟣&lt;pre> Tag
pre tag 내에 작성하는 코드는 그 모양이 **그대로 유지**된다.
### 🟤figure/figurecaption
figure tag로 하나의 내용을 감싸주고, figurecaption tag로 설명 부분을 감싸준다.
```
<figure>
     <p> When the sharpest words wanna cut me down I'm gonna send a flood, gonna drown 'em out
     I am brave, I am bruised
     I am who I'm meant to be, this is me
     Look out 'cause here I come
     And I'm marching on to the beat I drum
     I'm not scared to be seen
     I make no apologies, this is me </p>
     <figurecaptioin>from This is me</figurecaption>
</figure>
```
### 🔵Other Tags
- &lt;hr> Tag : 가로줄을 표현해준다. &lt;hr> 를 써주면 가로줄 하나가 생긴다.
- &lt;abbr> Tag : 약어를 나타내준다. &lt;abbr title = "World Wide Web">WWW&lt;/abbr>의 형태로 쓴다.
- &lt;bdo> Tag : text를 반대방향으로 작성할 때 사용. &lt;bdo dir= "rtl">반대로 작성할 내용 &lt;/bdo> 의 형태로 쓴다.
## Sementic Tags(text formatting)
### 🔴&lt;b>/&lt;strong> Tags
글씨를 굵게 만들어 줘서 **강조**해준다. **특정 부분**을 강조하는 것이지, 전체 부분을 강조하지 않는다.
### 🟡&lt;i>/&lt;em> Tags
글씨를 _기울임꼴_ 로 나타내 강조해준다.
### 🔵Other Tags
- &lt;mark> Tag : 글씨의 배경색을 노란색으로 칠해준다.
- &lt;small> Tag : 덧붙이는 글이나, 저작권/법률 표기 시의 작은 text를 표현해준다.
- &lt;sub>/&lt;sup> : 위 첨자 요소($2^3$) / 아래 첨자 요소($x_n$)
- &lt;del> Tag : 취소선을 넣을 때 사용
- &lt;insert> Tag : 밑줄을 칠 때 사용
- &lt;code> Tag : 코드를 나타내고 싶을 때 사용. 코드 한 줄만 표현하기 때문에 &lit;pre>로 감싼 후 사용하면 여러 코드를 표현할 수 있음.

