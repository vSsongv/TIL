# List & Table
> Html의 List와 Table tag들에 대해 다룬다.(210928)
## 🔷List
### 🔹Ordered List
순서가 존재하는 List. ex)레시피 
- **&lt;li>** 바깥을 **&lt;ol>**로 묶어준다.
- &lt;ol _**type="지정값"**_> / &lt;li _**value="지정값"**_ > - 지정값에 맞추어 자동 numbering을 매겨준다. (A로 하면 A,B,C로) 지정하지 않으면 자동으로 숫자로 맞춘다.
- &lt;ol _**reversed**_>를 사용하면 numbering이 반대로 된다.
### 🔹UnOrdered List
순서가 존재하지 않는 List. ex)우리나라 꽃의 종류
- **&lt;li>** 바깥을 **&lt;ul>**로 묶어준다.
- 하위 list가 존재한다면, 하위 list를 **&lt;ul>**로 한번 더 묶어주면 된다. 
```
<ul>
    <li>Milk</li>
    <li>Cheese
        <ul>
            <li>Blue cheese</li>
            <li>Feta</li>
        </ul>
    </li>
</ul>
```
![](https://images.velog.io/images/songjy377/post/662ad86f-1361-4a01-aebe-fa6a045269f1/image.png)
### 🔹Description List
 주로 용어사전, 메타데이터(키-값 쌍 목록)같은 설명 목록을 나타낸다. **&lt;dt>** 로 표기한 **용어**와 **&lt;dd>** 요소로 표기한 **설명 그룹**의 목록을 **&lt;dl>** 가 감싼다. 
```
<dl>
    <dt>Beast of Bodmin</dt>
    <dd>A large feline inhabiting Bodmin Moor.</dd>

    <dt>Morgawr</dt>
    <dd>A sea serpent.</dd>

    <dt>Owlman</dt>
    <dd>A giant owl-like creature.</dd>
</dl>
```
## 🔶Table
행과 열로 이루어진 표를 나타낸다.
### 🔸 basic tags
```
<table>
	<caption>Example</caption>
    <tr>
    	<th>나라이름</th>
        <th>수도</th>
        <th>인구/th>
    </tr>
    
    <tr>
    	<td>한국</td>
        <td>서울</td>
        <td>5100</td>
    </tr>
    
    <tr>
    	<td>미국/td>
        <td>워싱턴</td>
        <td>69000만</td>
    </tr>    
</table>
```
                  Example

| 나라이름 | 수도 | 인구 |
|-|-|-|
| 한국 | 서울 | 5100만 |
| 미국 | 워싱턴 | 3억 |
| 태국 | 방콕 | 6900만 |

- **&lt;tr> ** : table의 제목으로 사용할 수 있다. 이때 반드시 table의 첫 번째 자식이어야 한다.
- **&lt;tr> ** : "table row" 한 row을 나타낸다. 
- **&lt;th> ** : "table head", table의 대표 col 부분을 나타낸다.(나라이름, 수도, 인구 부분) 한국, 미국, 태국 부분을 th로 바꿀 수도 있다. 이때 이들은 열을 대표하는 내용을 이룬다.
     - &lt;th **scope="col/row"** > : 해당 head가 col을 대표하는지, row를 대표하는지 나타내는 것이 좋다.
- **&lt;td>** : table의 data들을 이룬다. 
     - &lt;td **colspan="숫자"** > : 숫자 개수만큼의 col를 차지하게 된다.
```
       <td colspan="2"합계</td>
       <td>4억 2000만</td>
```
### 🔸 table section tags
table또한 head, body, footer로 구획을 나눌 수 있다.
각 부분을 &lt;thead>, &lt;tbody>, &lt;tfoot>으로 감싸주면 된다.
![](https://images.velog.io/images/songjy377/post/25d4e70b-516d-4dd8-8569-bc6f74251dfc/image.png)