# Sementic & NonSementic Web
>Sementic/NonSementic Web을 이루는 각 tag들에 대해 다룬다. (210927)
## 🔲 Outline Algorithm(아웃라인 알고리즘)
- HTML5에서 정보 구조를 명확히 하기 위한 개념.
- header, main, footer등 section을 나누어 각 section의 역할을 명확히 하기 위해서 사용한다.
- 보조 기기가 웹 문서의 개요를 사용자에게 전달할 때, 그 의미를 전달하는 데 더 효과적이다. 
- [아웃라인 알고리즘 게시글](https://velog.io/@songjy377/Outline-Algorithm%EC%95%84%EC%9B%83%EB%9D%BC%EC%9D%B8-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98)
## 🟥 NonSementic Web
- 아무런 의미가 없는 tag로 이루어진 web
### 🔴 **&lt;div> Tag**
- 일관적으로 여러가지 요소를 묶어 특정 구역을 구분해준다. **Block 요소**이다.
### 🔴 **&lt;span> Tag**
- div와 동일한 역할이지만, **Inline요소**이다.
## 🟧 Sementic Web
- **Sementic Markup** : **기계도 이해할 수 있고, 인간도 이해할 수 있도록 의미에 맞는 tag를 사용하여 markup하는 것**을 말한다.
  - 다양한 platform에서 모두에게 동일한 웹 접근성을 제공하기 위해서 사용한다.
  - Cross Browsing(웹 페이지 제작 시에 모든 브라우저에서 깨지지 않고 의도한 대로 올바르게(호환성) 나오게 하는 작업)을 위해서 사용한다.
  - SEO(Search Engine Optimization) 검색엔진최적화를 위해서도 사용한다.
- 의미를 가진 tag들로 이루어진 web
- 검색 엔진은 sementic markup을 중요한 키워드로 간주함.
- 시각 장애가 있는 사용자가 스크린리더로 페이지를 탐색할 때 sementic markup을 기준으로 사용할 수 있음
![] (https://images.velog.io/images/songjy377/post/1aa2ba2c-a391-4262-a5de-38e55d0ad67a/Image%2011.jpg)
- **웹 접근성을 고려하여, 스크린리더가 인지할 수 있도록 content block에는 반드시 제목을 달아줄 수 있도록 하자. **
- ex)nav tag에 h2 tag로 navigation part라는 내용을 적어 주는 것
### 🟠 &lt;header> 
- 소개 및 탐색에 도움을 주는 content.(제목, 로고, 검색 폼 등), **header는 1개만 사용한다.**
 ![](https://images.velog.io/images/songjy377/post/9065d792-7400-49d5-805a-874e494a31fd/Image%2011.jpg) (네이버의 header부분)
### 🟠 &lt;main>
- Body의 주요 Content를 나타냄. 문서의 핵심 주제나 기능에 직접적으로 연결됐거나 확장하는 contents로 이루어진다. **하나만 있어야 한다** 모든 주요 Contents를 아우른다.
### 🟠 &lt;nav>
- 목차, 색인, 메뉴 같은 한 페이지 내 다른 페이지로의 링크를 보여주는 구획을 나타낸다.
  ![](https://images.velog.io/images/songjy377/post/1a24e726-f20c-442c-82c0-b9cee4718f98/Image%2011.jpg)
### 🟠 &lt;aside> 
- 문서의 주요 내용이 아닌, 간접적으로만 연관된 부분을 나타낸다.
```
<article>
  <p>
    디즈니 만화영화 <em>인어 공주</em>는
    1989년 처음 개봉했습니다.
  </p>
  <aside>
    인어 공주는 첫 개봉 당시 8700만불의 흥행을 기록했습니다.
  </aside> <-- 별로 안 중요한 내용이라는거임!
  <p>
    영화에 대한 정보...
  </p>
</article>
```

### 🟠 &lt;article>
- 게시판과 블로그 글, 매거진이나 뉴스 기사같이 문서, 페이지, 애플리케이션, 또는 사이트 안에서 독립적으로 구분해 배포하거나 재사용할 수 있는 구획을 나타낸다.
   여러 개의 article이 하나의 문서 안에 존재할 수 있다.
   article안에 여러 개의 section이 존재할 수 있다.
	
### 🟠 &lt;section>
- 독립적인 구획을 나타내며, 더 적합한 의미를 가진 요소가 없을 때 사용한다. 요소의 content를 따로 구분해야 한다면 section을 사용한다. 보통 제목을 포함하지만, 항상 그런 것은 아니다.

### 🟠 &lt;footer>
- 저작권 정보, 관련 문서 등의 내용을 담는다. **footer 또한 1개만 사용한다.**
![](https://images.velog.io/images/songjy377/post/82cd9d9d-1ad2-410d-8995-d51eb655bbc7/Image%2011.jpg)(네이버의 footer 부분)
