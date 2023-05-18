### Basic

- index.js 파일이 즉 Home 파일이 된다.
- page 폴더 내부에 작성하는 파일의 이름이 곧 route가 된다. page가 router 역할을 한다. 반드시 `export default` 여야 한다.
- 404 page 제공!

### **Next 13 부터 Page 폴더가 아닌 App 폴더 구조를 지향한다.**

- app 폴더 내의 page파일이 index가 된다.
- folder는 route를 정의하게 된다.
- folder내에는 page파일이 있어야 하고 이 파일이 UI을 정의한다.
- layout파일은 page파일 밖에 있는 파일이다. 페이지 간 공유하고 싶은 파일들.(header등)
- `xxx.module.css`로 파일명을 지으면 xxx파일에만 css가 적용된다.
- next는 client component, server component 2가지로 나누어짐. 상단에 `use client` 라고 쓰지 않으면 기본 server component로 동작하여, html에 js 기능을 넣을 수 없다.(onClick, useState 등)

### CSR

- react.js가 모든것을 rendering한다. 브라우저가 js를 불러와서 그리는 것. 즉 유저가 보는 것은 `<div id='root'></div>` 뿐이다.
- 인터넷이 느리다면 js를 불러오는 속도가 느리기 때문에 UX가 나빠진다고 볼 수 있음.

### SPR

- Static Pre Rendering
- 인터넷 속도가 느리더라도, 유저는 적어도 html코드는 볼 수 있다.
  _Hydration_ : Next.js는 pre rendering으로 html코드는 미리 redering함. 이후 react가 js를 client에게 전송함. 이후 리액트 코드들이 이전에 보내진 HTML DOM 요소 위에 한번 더 렌더링 하는데 이 과정을 이 과정을 Hydrate라고 함.

### Link

- route를 할 때 a를 사용하면 reload되기 때문에 Link를 사용한다.
- `<Link style={{color: 'red'}} className='Name' href='/'>Home</Link>`
- Link tag는 styling을 하려면 내부에 span tag를 넣고 styling을 해야한다.

### Router

`const router = useRouter();`

- router.pathname : 현재 pathname

### \_app.js

- next는 화면을 렌더링할 때 \_app.js를 가장 먼저 바라본다.
- 따라서 공통적인 부분을 \_app.js 파일에 넣어주면 된다. React의 App.js의 역할을 한다고 볼 수 있을 것 같다.

### public

- public folder에 있는 파일은 "/"로 바로 참조 가능

### redirect

- next.config 파일에서

```
async redirects() {
    return [
      {
        source: '/contact:path*',
        destination: '/form:path*',
        permanent: false,
      },
    ];
  },

// 위의 코드는 contact라는 주소로 갔을 때 form으로 redirect하도록 해준다.
// * 은 all을 의미.
```

로 redirect를 관리할 수 있다.

- source: 들어오는 request 경로 패턴 (request 경로)
- ndestination: 라우팅하려는 경로 (redirect할 경로)
- permanent: true인 경우 클라이언트와 search 엔진에 redirect를 영구적으로 cache하도록 지시하는 308 status code를 사용하고, false인 경우 일시적이고 cache되지 않은 307 status code를 사용. 예를 들어 path 부분을 기억함.

### rewrite

- request에서 user는 api key등을 알 수 있게 된다.
  <img src="Image 1.jpg">

```
  async rewrites() {
    return [
      {
        source: '/api/movies',
        destination: `https://api.themoviedb.org/3/movie/popular?api_key=${API_KEY}`,
      },
    ];
  },
```

- 이처럼 rewrites 키를 이용하여 request를 destination으로 mapping해 줄 수 있다.

<img src="Image 3.jpg">

- Rewrites은 URL proxy 역할을 하고 destination 경로를 mask하여 사용자가 사이트에서 위치를 변경하지 않은 것처럼 보이게 한다.

### getServerSideProps

```
export async function getServerSideProps () {
    해당 이름을 가진 함수는 어떤 내용이 들어가더라도 server side에서만 작동함.
}
```

- getSeverSideProps 함수를 사용하면 컴포넌트 단위로 페이지 pre-render에 필요한 비동기 연산들을 브라우저가 아닌 서버에서 실행하도록 만들 수 있다.
- 단, 만약 api가 느려서 해당 연산이 끝날 때까지는 페이지 전체가 렌더링되지 않는 단점이 있다.
- 즉 ssr은 api의 fetch가 완료되기 전까지는 user가 아무것도 볼 수 없지만 data가 받아와지면 전체를 다 볼 수 있다. 그러나 다른 페이지로 갈 때도 이 과정이 실행된다. SEO를 위해서는 ssr이 유리하다.
- csr은 모든 js파일이 들어오기 전까지 전혀 볼 수 없다. 그러나 다른 페이지로 갈 때는 이미 모든 js파일이 받아졌으므로 이동이 빠르다.
- request time에 반드시 데이터를 fetch해와야 하는 페이지를 pre-render해야 하는 경우에만 getServerSideProps를 사용해야 한다.
- 데이터를 pre-render할 필요가 없다면 client side에서 데이터를 가져오는 것을 고려해야 한다.

> 클라이언트 측에서 데이터 가져오는 과정 (Fetching data on the client side)
>
> 페이지에 자주 업데이트되는 데이터가 포함되어 있고 데이터를 pre-render할 필요가 없는 경우 클라이언트 측에서 데이터를 가져올 수 있다.
>
> 1.  먼저 데이터가 없는 페이지를 즉시 표시.
> 2.  페이지의 일부는 Static Generation을 사용해 pre-render할 수 있다.
> 3.  없는 데이터를 위해 loading 상태를 표시할 수 있다.
> 4.  그런 다음 클라이언트 측에서 데이터를 가져와 준비가 되면 표시한다.

- 이 접근 방식은 예를 들어 사용자 대시보드 페이지에 적합하다. 왜냐하면 대시보드는 사용자별 비공개 페이지이기 때문에 SEO와는 관련이 없으며, 페이지를 미리 렌더링할 필요가 없다. 또한 데이터는 자주 업데이트되므로 요청 시 데이터를 가져와야 한다.

### Routing

> Link, useRouter, folder로 navigating 가능.

- next는 page 내부의 folder name으로 routing을 해준다.(path로 구분하지 않아도 된다면 folder가 아니어도 된다.)
- movies라는 폴더가 있다면, 해당 폻더 내의 index파일은 `localhost:3000/movies`로 routing을 해주고, movies내부에 all이라는 파일이 있다면 `localhost:3000/movies/all`로 routing을 해준다.
- **Dynamic URL**
  - path가 필요한 경우, `[path_name]`의 형태로 폴더명을 지정해야 한다.

```
movies > [id].js
- movies/123의 형태로 url지정 가능
```

```
const router = useRouter();

const onClick = (id) => {
  router.push(`movies/${id}`)
}
// push는 해당 url로 routing해줌
```

```
  const router = useRouter();

  const onClick = (id) => {
    router.push({
      pathname: `movies/${id}`,
      query: {
        title: "title"
      }
    },
    `movies/${id}` //as option으로, url창에선 masking 처리 가능
    );
  };
// 위와 같은 식으로 query전달 가능
// router.query의 값은 유저가 home에서 상세페이지로 이동했을때만 존재함

<Link
  // onClick={() => onClick(movie.id, movie.original_title)}
  key={movie.id}
  href={{
    pathname: `movies/${movie.id}`,
    query: {
      title: movie.original_title,
    },
  }}
  as={`/movies/${movie.id}`}>

// Link tag내에서도 pathname, query입력 가능
```

### Catch-all Url

- `[...pathname]`파일은 pathname이외의 url에 있는 모든 query값들을 받아온다.

<img src="Image 1.jpg">
<img src="Image 3.jpg">

- useRouter는 client side에서만 동작하므로, SEO등이 필요하거나 pre-rendering을 하고자 한다면 getServerSideProps을 사용하면 됨.

### NotFound Page

- App 폴더 내부에 not-found파일을 만들어주고, custom하면 됨!
