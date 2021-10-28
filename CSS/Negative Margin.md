# Negative Margin
> negative margin의 정의를 알아보고, 예제를 통해 작동 원리를 익힌다.(211029)

## ✅ Negative Margin
- mragin은 요소의 시작점을 바꾼다.
- 음수 margin을 주면 시작점을 원래 위치보다 앞으로 옮긴다고 생각하자.
- **width가 정해진 경우**
  - static 요소인 경우, top/left에 음수 negative margin을 주면 해당 요소가 위쪽/왼쪽으로 이동한다. 해당 위치에 다른 요소가 있다면 그 다른 요소를 덮는다.
  - 그러나  bottom/right에 negative margin을 주면 margin-right, margin-bottom은 뒤에 요소의 시작기준점과 맞물리기 때문에 뒤에 요소가 영향을 받아서 움직인다.   - **해당 요소는 그대로 있고, 해당 요소 다음에 오는 요소가 있다면 그 모든 요소들을 위/오른쪽으로 끌어당긴다.**
- **width가 부모의 크기로 정해지는 경우**
  - **negative margin을 left/right에 모두 주게 되면, width를 확장하는 효과를 줄 수 있다. 같은 의미로 top/bottom도 가능하다.**
![](https://images.velog.io/images/songjy377/post/35a9a7f7-4279-4fac-a947-f25e70bf5c62/image.png)

### ❗ float된 요소의 경우
>- float 이 적용된 엘리먼트 다음에 나오는 정상적인 요소(float 이 적용되지 않은 요소)는 >float된 요소의 존재를 인식하지 못하고 자신의 영역을 확보하게 된다.
>- **하지만 float 이 적용된 엘리먼트의 영역은 침범하지 않는다.**
>- 이 점을 이용하여 float 된 요소에 음수 마진을 적용할 수 있는데, float 의 반대 방향으로 >음수 마진을 줘서 컨텐츠를 끌어당기는 효과를 줄 수 있다.

## ☑️ 예제 
```html
    <div class="border">
        <div class="box1"></div>
        <div class="box2"></div>
    </div>
```
```css
.border {
    border: solid black;
}
* {
    font-size: 0;
  }
.box1 {
  display: inline-block;
  width: 100px;
  height: 100px;
  background-color: red;
}
.box2 {
  display: inline-block;
  width: 100px;
  height: 100px;
  background-color: blue;
}
```
- 아래와 같은 박스 2개가 있다고 하자. 위의 코드 참고!

    ![](https://images.velog.io/images/songjy377/post/8aef221e-aa18-4265-bafb-8cce067d48ba/image.png)
### ❓ margin-top, margin-left
- box1 에 margin-left: -30px을 주면
```css
.box1 {
  display: inline-block;
  width: 100px;
  height: 100px;
  background-color: red;
  margin-left: -30px;
}
```
- ![](https://images.velog.io/images/songjy377/post/90880c3a-4c43-4f33-8d21-1982df2bc5f4/image.png)

- 위 사진과 같이 box의 시작점이 -30px 앞으로 이동하게 된다. (top을 test하려면 block 요소로 바꾸면 된다.)

- ![](https://images.velog.io/images/songjy377/post/6a6ff6c2-d7b4-4909-8d1a-11d5c66b8fcf/image.png)

### ❓ margin-bottom, margin-right
- box1 에 margin-right: -30px을 주면
```css
.box1 {
  display: inline-block;
  width: 100px;
  height: 100px;
  background-color: red;
  margin-right: -30px;
}
```
- ![](https://images.velog.io/images/songjy377/post/d7f4b3f4-98b5-4a73-a8e9-6d5f3e42bda0/image.png)
- 위 사진과 같이 box2가 box1을 가리면서 왼쪽으로 이동되는것을 볼 수 있다!

### ❓ float요소에 적용
```css
* {
  font-size: 0;
}
.box1 {
  float: left;
  width: 100px;
  height: 100px;
  background-color: red;
  margin-bottom: -30px;
}
```
- ![](https://images.velog.io/images/songjy377/post/0842d1cf-b983-41f7-9b65-689480e5e9fe/image.png)
- 위 사진과 같이 box2가 box1의 영역을 침범하지 않고, margin-bottom으로 인해 끌어당겨진 것을 볼 수 있다.

### ❓ width/height 확장의 효과
```css
.border {
    width: 500px;
    margin: 50px 0;
    padding: 10px;
    border: solid b
    ![](https://images.velog.io/images/songjy377/post/93362469-f087-45d7-ab0b-83273bc26309/image.png)lack;
}

.box1 {
    height: 100px;
    background-color: red;
}
```
- 아래와 같은 상황일 때,
![](https://images.velog.io/images/songjy377/post/5956c0c6-35cc-4053-9f9e-775f66046438/image.png)

```css
.box1 {
    height: 100px;
    background-color: red;
    margin-top: -30px;
    margin-bottom: -30px;
}
```

- ![](https://images.velog.io/images/songjy377/post/34b3650c-a3fc-47d8-bcdd-a075fe54eaa3/image.png)
- 위 아래로 negative margin 값을 주니 요소의 높이가 늘어난 것을 확인할 수 있다. (right, left만 주게 되면 양 옆으로만 늘어난다!)
![](https://images.velog.io/images/songjy377/post/93362469-f087-45d7-ab0b-83273bc26309/image.png)

- 따라서 이런 디자인도 가능할 것이다.
![](https://images.velog.io/images/songjy377/post/645b88a4-b8cb-42f6-83b5-ac74052ce11d/image.png)