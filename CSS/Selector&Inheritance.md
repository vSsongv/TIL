# Selector & Inheritance
> CSS의 selector, Inheritance에 대해 다룬다.에 대한 기초 설명이다.(210929)
## 🧿Selector
- CSS 규칙을 적용할 요소를 정의한다.
### 🏹Type Selector
- 전체 웹페이지에서 일반적으로 적용해야 하거나, 일괄적으로 적용해줘야 하는 스타일이 있을 때 사용한다. 일괄적으로 바뀌기 때문에 사용시 주의해야한다.
![](https://images.velog.io/images/songjy377/post/3f1cc078-1f0a-44d4-9dfb-28cde1198f39/image.png)
### 🏹ID Selector
- 전체 웹페이지에서 일반적으로 적용해야 하거나, 일괄적으로 적용해줘야 하는 스타일이 있을 때 사용한다. 일괄적으로 바뀌기 때문에 사용시 주의해야한다.
![](https://images.velog.io/images/songjy377/post/345e5567-7800-4d59-afd4-a58b22f3a192/image.png)
### 🏹Class Selector
- 전체 웹페이지에서 일반적으로 적용해야 하거나, 일괄적으로 적용해줘야 하는 스타일이 있을 때 사용한다. 일괄적으로 바뀌기 때문에 사용시 주의해야한다.
![](https://images.velog.io/images/songjy377/post/e3f827df-05d4-4c57-ae6a-d68c8b3804f2/image.png)
### 🏹Attribute Selector(속성 선택자)
이 선택자 그룹은 요소에 특정 속성이 있는지에 따라 요소를  선택하는 다른 방법을 제공한다.

   1. **type[attr]** : 속성값들에 대해 style을 적용한다.
   2. **type[attr=value]** : 속성들 중 특정 val에만 style을 적용한다.
   3. **type[attr^=value]** : val로 시작하는 속성들에게 style을 적용한다.
   4. **type[attr$=value]** : val로 끝나는 속성들에게 style을 적용한다.
   5. **type[attr*=value]** : val값을 포함하는 속성들에게 style을 적용한다.

![](https://images.velog.io/images/songjy377/post/87e33efc-0a69-4058-a63e-35b8c338da87/image.png)
### 🏹Pseudo-Class Selector(가상 클래스 선택자)
- 실제로 존재하지 않는 상태에 이름을 붙여서 사용하고, 클래스가 없지만 존재하는 것처럼 이름을 붙여서 스타일을 적용한다. 
1. **type: first-child/last-child** : 해당 type의 형제 중에서 첫번째/마지막 자식만 style을 적용한다. 
   - <span style = "color:red">**주의점!!** -  selector가 class인 경우, 부모의 첫번째 자식에 style을 적용한다.</span>
```
	<ul>
            <li>Toy Story</li>
            <li class='movie">Avengers</li>
        </ul>
        
        .movie:first-child {
           font-size: 32px;
         } 
```
   
   인 경우, movie의 부모인 ul의 첫번째 자식은 Toy Story지만, movie class가 아니므로 style이 적용되지 않는다.
2. **type: nth-child(n)** : n번째 자식에 style을 적용한다.(n은 함수가 될 수 있다. odd/even으로 사용 가능하다.)
3. **type: first/last-of-type** : 같은 type의 첫번째/마지막 자식에 모두 style을 적용한다. **type: nth-of-type(n)** 또한 동일하다.
![](https://images.velog.io/images/songjy377/post/399f826c-be4f-4a28-b1ee-2fec280095f4/image.png)
4. **type:not(selector)** : type중 selector가 아닌 것들에 style을 적용한다. input type일때는 [selector]의 형태로 써줘야함.
5. **type:link** : 링크는 클릭시 색이 보라색으로 자동 변경되는데, link 선택자를 이용하면 클릭되었든 되지 않았든 style이 적용된다.
6. **type:visited** : 클릭된 link들에 대해 style이 적용된다.
![](https://images.velog.io/images/songjy377/post/135480c7-ac45-455e-9396-8a2378c8fd83/image.png)
7. **input[type=type]:hover** : 마우스를 올린 요소들에 대해 style이 적용된다.
8. **input[type=type]:active** : 마우스를 클릭한 후 떼기 전(mouseDown 상태)까지 style이 적용된다. **hover, link, visited에 의해 덮어씌어진다.**
9. **input[type=type]:focus** : 현재 focusing되고 있는 요소들에 대해 style이 적용된다. _(버튼을 클릭했다가 다른 곳을 클릭하면 적용이 풀린다.)_
link-visited-hover-active(LVHA) 순서로 배치하는 것이 좋다.
![](https://images.velog.io/images/songjy377/post/8bfb9ab5-d09e-44c0-b36f-c15371cd269e/image.png)
10. **input[type=type]:enabled** : 속성이 enabled인 요소들에 style이 적용된다.
11. **input[type=type]:disabled** : 속성이 disabled인 요소들에 style
12. **input[type=type]:cheched** : 속성이 checked인 요소들에 style
![](https://images.velog.io/images/songjy377/post/00b0992d-c12a-4028-b580-6b931e9aaae3/image.png)
### 🏹Pseudo-Element Selector(가상 요소 선택자)
- 실제로 존재하지 않는 요소를 만들거나, 범위를 만들어서 스타일 적용을 한다. 
- :대신 **::** 을 사용해주는 것이 좋다. 
- content라는 요소를 선언 블록 내에 추가해줘야 한다. 
1. **type::before/after** : type앞/뒷부분에 content를 추가한다.
```
	<body>
            <div>Toy Story</div>
            <div class='movie">Avatar</div>
            <div class='movie favorite">Avengers</div>
            <div class='movie">Zootopia</div>
        <body>
        
        .favorite::after {
           content: '✨'
         }
```
인 경우, 아래 사진처럼 나타난다.
![](https://images.velog.io/images/songjy377/post/d58670db-f5e1-4045-a683-fbfbfa20f9f4/Image%2011.jpg)
1. **type::before** : content라는 요소를 선언 블록 내에 추가해줘야 한다.
2. **type::first-letter** : type의 첫번째 글자에 대해 style을 적용한다.
3. **type::first-line** : type의 첫번째 줄에 대해 style을 적용한다. **창의 가로 길이에 따라 첫째 줄이 변화한다.**
4. **type::selection** : 선택 영역에 대해 style을 적용한다.
![](https://images.velog.io/images/songjy377/post/4e9ba7b4-174b-44a3-8149-b9f4110b0f38/image.png)

|selection 코드 적용시|결과|
|-|-|
| .lorem::selection { color : blue }| ![](https://images.velog.io/images/songjy377/post/6a57f594-37c0-4d2b-93dd-f7aa1ff861be/image.png)|
### 🏹Selector Combinators(선택자 결합)
- 두 가지를 결합하여 조금 더 디테일한 style을 적용시킬 수 있다.
- type type의 형태로, 스페이스를 사용하여 2가지 type을 결합할 수 있다. 첫번째 type의 scope에 포함된 요소들 중 두번째 type에 대해 모두 style을 적용한다.
- 첫번째 type의 scope가 어디까지인지 잘 파악해야 한다.
1. **type type:~~**: 하위 선택자 결합.
```
	<ul id="list">
            <li>Toy Story</li>
            <li>Avengers</li>
            <li>Avatar</li>
        </ul>
        
        <ol>
            <li>Strawberry</li>
            <li>Peach</li>
            <li>Melon</li>
        </ol>
        
        ul li:last-of-type {
           color: red;
         } 
```
- 위 코드에 따르면 ul의 하위요소 li,ol 중 li의 첫번째 요소에 대해서 style이 적용된다. (#list li: 의 형태로도 사용 가능하다)
![](https://images.velog.io/images/songjy377/post/6e5b2f66-8d75-46db-b341-35d7c9b5d1fb/image.png)

2. **type > type:~~** : 자식 선택자 결합. 바로 밑의 하위 자식 요소에 대해 style 적용한다.
```
	<ul id="list">
            <li>Toy Story
            	<ol>
                   <li>Strawberry</li>
            	   <li>Peach</li>
            	   <li>Melon</li>
            	</ol>
            </li>
            <li>Avengers</li>
            <li>Avatar</li>
        </ul>
        <ol>
            <li>Strawberry</li>
            <li>Peach</li>
            <li>Melon</li>
        </ol>
        
        #List > li:first-of-type {
           color: red;
         } 
```
- 위 코드에 따르면 ul tag의 바로 밑에 있는 li tag내의 첫번째 요소에 style이 적용된다.
![](https://images.velog.io/images/songjy377/post/e6eb9467-d931-40fe-89f5-698612fd1752/image.png)
3. **type ~ selector** : 일반 형제 선택자 결합. selector와 type은 형제 관계. -> 같은 부모를 가진 요소들 중에 **type보다 뒤에 있는** selector 모두에게 style을 적용한다.
```
<div>
    <p>P</p>
    <code>Span code</code>
    <p>P2</p>
    <div>Div</div>
</div>

code ~p {
  color: green;
}
```
위 코드에서 **code ~p** 이기 때문에 code뒤쪽에 있는 **P2**만 style이 적용된다.![](https://images.velog.io/images/songjy377/post/1dae24b2-212e-48b8-9c8b-2c6f499f45c8/image.png)
4. **type + selector** : 인접 형제 선택자 결합. type과 인접한(바로 위/아래) 형제 중에 selector에 대해 style을 적용한다.
```
<div>
    <p>P</p>
    <span>Span</span>
    <code>code</code>
    <p>P2</p>
    <div>Div</div>
</div>

code + div{
  color: green;
}
```
위 코드에서 **code +div** 이기 때문에 code바로 위/아래에 div가 없으므로 적용되지 않을 것이다. 만약 **code + p**라면 아래에 적용된다.
![](https://images.velog.io/images/songjy377/post/3f6cef79-d862-4383-8951-f1d6962bcbbf/image.png)
5. **type, type, type** : 그룹화. code,div,span 처럼 원하는 tag들을 그룹화하여 style을 적용시킬 수 있다. 
6. * : 범용 선택자. 모든 요소들에 style을 적용시킨다. **p + *** 라면 p 바로 아래에 있는 요소들에게 스타일을 적용시킨다. 위의 예시에서는 div, span에 적용된다.
![](https://images.velog.io/images/songjy377/post/642eebf9-09fa-4eaf-a588-2c732057c5ee/image.png)
## 🧿Inheritance
### 🏹상속 제어하기
- 자신에게 특정된 style이 없을 때, 부모 class의 style속성을 자식들은 무조건적으로 상속받는다.

```
<div class="parent">Parent
	<div class="child1">child1</div>
	<div class="child2">child1</div>    
</div>

.parent {
    color: blue;
}
```
![](https://images.velog.io/images/songjy377/post/554ce849-a326-4a47-a579-b39d70c663a9/image.png)
- **initial** : 상속을 받지 않기 위해 사용한다. parent의 color가 blue이지만, 아래처럼 child1에 color값을 initial로 해 주면 bule를 상속받지 않는다. **all: initial**로 모든 속성에 대해 상속을 막을 수 있다.
```


.child1 {
    color: initial;
}
```
- **inherit** : 무조건 상속을 받도록 한다. **all: initial** 가능하다. 아래 코드에서는 child1에 color값이 특정되어 있지만, parent의 모든 자식들에 대해 blue값을 주라고 되어 있으므로 child1이 blue로 나타난다. (만약 child에 대한 style속성이 맨 아래에 있다면 inherit를 무시한다.)
```
<div class="parent">Parent
	<div class="child1">child1</div>
	<div class="child2">child1</div>    
</div>

.parent {
    color: blue;
}

.child1 {
    color: red;
} 

.parent * {
	color: inherit
}
```
![](https://images.velog.io/images/songjy377/post/4643c940-7970-488e-8c1b-7e8f4549b614/image.png)
- **unset** : 자신의 모든 속성을 삭제한다. <span style="color:red">
- **BUT 부모로부터 상속받을 값이 있다면 -> inherit, 무조건 상속을 받고, 없다면 -> initial, 초기값으로 돌아간다.**</span> 아래 코드에서도 결국 부모로부터 상속받을 blue color값이 존재하므로, 색을 제외한 모든 값이 사라진다.
```
    <div class="parent">Parent
        <div class="child1">child1</div>
        <div class="child2">child2</div>    
    </div>
    
    .parent {
    	color: blue;
    }
    .child1 {
        color: red;
    }
    .parent * {
        color: inherit
    }
    .child1 {
    	all: unset;
    }

```
![](https://images.velog.io/images/songjy377/post/2b1737d5-68cb-47af-b1d1-dae277304067/image.png)
### 🏹우선순위
0. !important
```
	div {
    	   color: purple !important
    	}
```
1. inline style
2. ID
3. Class, Attribute,Pseudo Class
4. Type(tag)
5. *
6. inherited
순으로 명시도가 낮다.
