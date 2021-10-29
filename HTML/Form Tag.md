# Form Tag
> form tag, select, button, datalist등 input을 구성하는 여러 가지 tag들을 다룬다.(210928)
## 🏠&lt;Form> Tag
- 사용자에게 정보를 입력할 수 있도록 하는 구성요소들을 이룬다. ex)로그인 시의 아이디/비밀번호 입력 칸
```
<form action="" method="get" class="form-example">
  <div class="form-example">
    <label for="name">Enter your name: </label>
    <input type="text" name="name" id="name" required>
  </div>

  <div class="form-example">
    <input type="submit" value="Subscribe!">
  </div>
</form>
```
### 🔨action 속성
- 양식 데이터를 처리할 프로그램의 URI. 
### 🔨method 속성
- 양식을 제출할 때 사용할 HTTP 메서드.
- **post**: POST 메서드. 양식 데이터를 요청 본문으로 전송합니다.
- **get**: GET 메서드. 양식 데이터를 action URL과 ? 구분자 뒤에 이어 붙여서 전송합니다.
- dialog: 양식이 &lt;dialog> 안에 위치한 경우, 제출과 함께 대화 상자를 닫습니다.
### 🔨button Tag
- 클릭 가능한 버튼을 나타낸다.
- **자식을 가질 수 있다.** input보다 스타일 적용이 수월하다.
```
	<button name="button">눌러보세요</button>
```
- ### ❗ 접근성 고려사항
  - 아이콘만 사용해서 버튼을 만드는 것은 접근성이 좋지 않다. text로 어떤 역할을 하는 버튼인지 명시하는 것이 좋다. 
- ### ✔️ type에 들어갈 수 있는 값들
  - **reset** : 입력된 내용을 초기화시킬수 있다.
  - **submit** : 양식을 전송하는 버튼이다.(기본으로 버튼이 가능하다. **value** 속성으로 내용 변경이 가능하다.
  - **button** : 기본 행동이 없으며 클라이언트 쪽 스크립트와 연결할 수 있다.
### 🔨textarea Tag
- 여러 줄을 입력받을수 있는 부분
- 빈 요소가 아니라 자식을 가질 수 있다.
```
  <label for="story">Tell us your story:</label>

  <textarea id="story" name="story"
            rows="5" cols="33">
  It was a dark and stormy night...
  </textarea>

```
- **rows, col** 속성으로 원하는 입력 줄 개수를 지정할 수 있다.
- disabled, readonly 등 input tag의 속성들도 이용 가능하다.
### 🔨input Tag
- 사용자에게 입력을 받는 부분을 의미한다.
- **빈 요소로, 자식을 가질 수 없다.**
![](https://images.velog.io/images/songjy377/post/bea16c0d-3ae6-4da3-9020-6fe0487094f6/Image%2011.jpg)
```
  <div class="form-example">
    <label for="email">Enter your email: </label>
    <input type="email" name="email" id="email" required>
  </div>
```
### &lt;속성 종류>
- **name** : input 양식 컨트롤의 이름. 이 필드가 어떤 값을 가지고 있는지 식별하는 데에 사용. 이름/값 짝(name/value pair)의 일부로서 양식과 함께 전송된다.
- <span style="color: red">**disabled**</span> : 모든 자손에 대해 비활성화한다. 값 자체를 사용하지 않아서 post/get으로 값 전송이 불가하다.
- <span style="color: red">**readonly**</span> : 값 입력을 받지 못하게 만든다. disabled과 달리 post/get으로 값 전송이 가능하다. value 속성으로 변경 불가능한 값을 지정하여 사용할 수 있다.
- **placeholder** : 입력값에 대한 예시를 제공한다.(글자 색이 옅다)
- **autocomplete** : 값을 **on**으로 주면 자동완성 기능을 제공한다.
- **required** : 해당 input에 값을 입력하지 않으면 입력을 하라는 에러메세지를 나타내준다.
   - ### ✔️ type에 들어갈 수 있는 값들
   ```
   <input type="text" id="name" name="name" required
       minlength="4" maxlength="8" size="10">
   ```
     - **text** : 한 줄로만 입력이 가능하다. **minlength, maxlength** 로 최대 입력 개수를 조절할 수 있다.
     - **submit** : 양식을 전송하는 버튼이다.(기본으로 버튼이 가능하다. **value** 속성으로 내용 변경이 가능하다.
     - <span style="color: red">**button**</span>: 버튼 형태를 만들 수 있다. **value** 속성으로 내용 변경이 가능하다. (string만 입력 가능)
     - <span style="color: red">**checkbox**</span> : checkbox를 만들 수 있다. name 속성으로 구분할 수 있다. **checked일 때 on이라는 value값**을 가진다. **checked 속성으로 기본값을 checked**로 해둘 수 있다.
     - <span style="color: red">**radio**</span> : checkbox와 같은 기능을 한다. 그러나 **name 속성을 같게 하는 경우에는 동시선택이 안 된다.** checked 속성으로 기본값을 checked로 해둘 수 있다.
     - **file** : 파일을 지정할 수 있는 컨트롤. accept 특성을 사용하면 허용하는 파일 유형을 지정할 수 있다.
     - **reset** : 입력된 내용을 초기화시킬수 있다.
     - **password** : 비밀번호를 입력받을 수 있다. 입력값이 마스킹되어 보인다. 역시 **minlength, maxlength**가 사용 가능하다.
     - **email** : 이메일을 입력받을 수 있다. @가 입력되지 않으면 에러 메세지가 나타나고, 모바일은 email type이면 바로 영어를 입력받도록 해준다.
     - **tel** : 전화번호를 입력받을 수 있다. 모바일은 바로 숫자를 입력받도록 해준다.
     - **number** : 숫자를 입력받을 수 있다. 숫자가 아닌 경우 에러메세지를 나타내준다. **min, max**로 숫자의 범위를 지정할 수 있다. **step**으로 증가/증감 수를 지정할 수 있다.
     - **range** : 숫자 범위를 입력받을 수 있다. 기본 0~100의 범위를 가지고 있다. 조절바로 범위 조절이 가능하다. **step**으로 증가/증감 정도를 지정할 수 있다. ![](https://images.velog.io/images/songjy377/post/6ee95560-8f8d-47e1-9a7c-4479828040f3/image.png)
     - **data** : 시간을 제외한 날짜 입력을 할 수 있다.(month, week도 가능하다.)
     - **time** : 시간 입력을 할 수 있다.(month, week도 가능하다.)
     
     
### 🔨label Tag
- input과 짝을 이루어 해당 input에 대해 사용자에게 정보제공을 하는 역할
- **for 특성** : id로 자신과 짝을 이루는 input을 찾는다. input을 형제 요소로 사용할 경우 **반드시 필요**하다.
- label의 자식 요소로 input을 사용할 경우, for와 id를 사용하지 않아도 된다.
```
 <div class="form-example">
    <label>Enter your email: 
    	<input type="email", name="email>
    </label>
 </div>
```

### 🔨fieldset Tag
- 여러 개의 input과 label을 한 group으로 묶기 위해 사용한다.Block 요소이다.
- filedset tad에 disabled 속성을 사용하면, 모든 자식 input을 입력받을수 없도록 막기 때문에, 조금 더 편리하다.
```
<form>
  <fieldset>
    <legend>Choose your favorite monster</legend>

    <input type="radio" id="kraken" name="monster">
    <label for="kraken">Kraken</label><br/>

    <input type="radio" id="sasquatch" name="monster">
    <label for="sasquatch">Sasquatch</label><br/>

    <input type="radio" id="mothman" name="monster">
    <label for="mothman">Mothman</label>
  </fieldset>
</form>
```
### 🔨legend Tag
- fieldset 부분의 이름을 뜻한다.
- 무조건 fieldset tag의 **첫번째 자식**이어야 한다.
### 🔨Select Tag
- 옵션 메뉴를 제공하는 컨트롤을 나타낸다.
- disable, required 속성 사용 가능하다.
```
  <label for="pet-select">Choose a pet:</label>

  <select id="pets" id="pet-select">
  	<optgroup label = "animal">
        <option value="">--Please choose an option--</option>
        <option value="dog">Dog</option>
        <option value="cat">Cat</option>
        <option value="goldfish">Goldfish</option>
    <optgroup label = "animal">
  </select>

```
- ###  **option tag**
    - 제일 처음 option의 내용값이 기본값으로 화면에 나타난다.
    - option tag에 selected 속성을 사용하여 항상 해당 값이 선택되도록 할 수 있다.
    - option tag에서 value 값으로 post할 때 값을 지정할 수 있다. 아래 사진처럼 안내 문구를 화면에 보이고 싶은 경우, 해당 option value 값을 0으로 지정해줘야 서버에 전송했을 때 control이 가능하다.
    ![](https://images.velog.io/images/songjy377/post/ac94248c-cde8-4bfe-bb74-a98fdf50da6e/image.png)
 - ### **optgroup tag**
   - option들을 group으로 묶어줄 수 있다. label 속성으로 naming도 가능하다.    ![](https://images.velog.io/images/songjy377/post/a995e886-9af3-443e-9d80-c778180491a4/image.png)
   
### 🔨datalist/list Tag
- 다른 컨트롤에서 고를 수 있는 가능한, 혹은 추천하는 선택지를 나타내는 &lt;option> 요소 여럿을 담을 수 있다.
```
  <label for="ice-cream-choice">Choose a flavor:</label>
  <input list="ice-cream-flavors" id="ice-cream-choice" name="ice-cream-choice" />

  <datalist id="ice-cream-flavors">
      <option value="Chocolate">
      <option value="Strawberry">
      <option value="Vanilla">
  </datalist>
```
- <span style="color: red">selct tag와의 차이점</span> : 기본이 text이기 때문에, 값 수정이 가능하다.
- text값을 입력하면 해당 값을 인식하여 가능한 선택지를 추천해준다.
![](https://images.velog.io/images/songjy377/post/dd04376e-ccde-4103-b5f9-e61aec6cadbc/image.png)
