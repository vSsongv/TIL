# Layout
> Layout을 이루는 box, inline 요소들의 display, visibility, float, position, overflow, z-index에 대해  다룬다.(211001)
## 🔲Layout
- 블록 요소와 인라인 요소가 보여지는 방식
## 🔲display
- 요소를 블록과 인라인 요소 중 어느 쪽으로 처리할지와 함께, flow, grid, flex처럼 자식 요소를 배치할 때 사용할 레이아웃을 설정한다.
```
	display: inline-block
    display: none
```

### <속성>
- **inline-block**: span은 inline요소이나, css의 display속성으로 block요소로 변경해 줄 수 있다.(width,height와 margin top/bottom 설정 가능)
- **none**: 문서의 layout을 변경하면서 요소를 숨긴다.
![](https://images.velog.io/images/songjy377/post/7ddcf78f-1796-4ac4-a49f-152a25fc13a1/image.png)
## 🔲 visibility 
- 문서의 layout을 변결하지 않고 요소를 보이거나 숨긴다.
- **visible / hidden**으로 결정한다. 아래는 ```visibility: hidden```인 경우이다.
![](https://images.velog.io/images/songjy377/post/b0c109b8-3918-43fd-869d-75db426eeb97/image.png)
## 🔲 float
- 한 요소가 normal flow _(block,inline요소가 일반적으로 보여지는 방식)_ 으로부터 빠져 텍스트 및 inline 요소가 그 주위를 감싸는 자기 컨테이너의 좌우측을 따라 배치할 수 있도록 한다.
![](https://images.velog.io/images/songjy377/post/4aca0832-f3f3-4eaf-af81-78304bd45951/image.png)
```
    /* 키워드 값 */
    float: left;
    float: right;
    float: none;
    float: inline-start;
    float: inline-end;
```
### <속성>
**left** : containing block(부모 영역)의 제일 좌측으로 떠오른다.
**right** : containing block(부모 영역)의 제일 우측으로 떠오른다.
## 🔲 <span style="color:red">position</span>
- 문서 상에 요소를 배치하는 방법을 지정한다. 기본은 ```position: static```이다. 
- static인 요소들은 static이 아닌 요소보다 z-index가 낮다.
### &lt;Value>
- 아래 값들에 대해 top,bottom, right, left속성이 최종 배치 위치를 결정한다.
- ### **relative**
     ◼ 요소를 normal flow 따라 배치하고, **static경우의 위치를 기준**으로 위치를 이동한다. 
    
    ◼ top/bottom, right/left를 동시에 쓰는 경우 **top과 left**가 우선시된다. 

    ![](https://images.velog.io/images/songjy377/post/9809dc7d-8f5e-4ec0-91a1-7b4ebdc6f02c/image.png)
- ### **absolute**
     ◼ 요소를 normal flow에서 제거한다.

    ◼ 대신 **가장 가까운 위치의 부모 요소의 position 값에 대해 상대적으로 배치**한다.**(주로 기준으로 삼고 싶은 블록 요소에 relative값을 준다.)** 

    ◼ **단, 부모요소가 위치 지정 요소가 없다면(staic이라면) 가장 상위 블록(body)을 기준으로 삼는다.**
    
    ![](https://images.velog.io/images/songjy377/post/ad3f06b9-f9e3-4290-99db-d82974ea789f/image.png)![](https://images.velog.io/images/songjy377/post/88d2ba31-08e0-4901-b98b-5809d0940e17/image.png)
- ### **fixed**
     ◼ **뷰포트를 기준으로 삼아 위치가 고정된다.** 
    
    ![](https://images.velog.io/images/songjy377/post/b6eef804-f66f-4c28-8706-548bf3616b2d/image.png)
- ### **sticky**
     ◼ 요소를 normal flow에 따라 배치하고, **가장 가까운 스크롤 되는 부모 컨테이너**를 기준으로 top, right, bottom, left의 값에 맞추어 fixed처럼 작동한다.

    ◼ 아래 코드에서는 스크롤되는 부모는 body이기 때문에 parent가 body에 대해 stiky작용을 한다.
    ![](https://images.velog.io/images/songjy377/post/7da34be5-69b7-45e8-984b-54b6f2195fe4/image.png)

## 🔲 overflow
- 요소의 콘텐츠가 너무 커서 요소의 블록 서식 맥락에 맞출 수 없을 때의 처리법을 지정한다.
```
  /* 키워드 값 */
  overflow: visible; - 넘치는 부분도 보인다. 
  overflow: hidden; - 넘치는 부분은 보이지 않는다.
  overflow: scroll; - 스크롤을 하면 숨겨진 내용을 볼 수 있다.
  overflow: auto; - 넘치면 자동으로 스크롤이 가능하도록 한다.
```
![](https://images.velog.io/images/songjy377/post/a38a2fd9-43c7-4a1c-9de9-c529e8b6781a/image.png)
## 🔲 z-index
- 요소의 z축 index를 지정할 수 있다. 더 큰 z-index를 가진 요소가 작은 요소보다 위에 나타난다. ```z-index: [integer]```

![](https://images.velog.io/images/songjy377/post/fbedca17-e7d7-4cf7-ab5f-2a0d944812f7/image.png)
