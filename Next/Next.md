### Basic
- index.js 파일이 즉 Home 파일이 된다.
- page 폴더 내부에 작성하는 파일의 이름이 곧 route가 된다. page가 router 역할을 한다. 반드시 `export default` 여야 한다.
- 404 page 제공!

### CSR 
- react.js가 모든것을 rendering한다. 브라우저가 js를 불러와서 그리는 것. 즉 유저가 보는 것은 `<div id='root'></div>` 뿐이다.
- 인터넷이 느리다면 js를 불러오는 속도가 느리기 때문에 UX가 나빠진다고 볼 수 있음.

### SPR
- Static Pre Rendering
- 인터넷 속도가 느리더라도, 유저는 적어도 html코드는 볼 수 있다.
*Hydration* : Next.js는 pre rendering으로 html코드는 미리 redering함. 이후 react가 js를 client에게 전송함. 이후 리액트 코드들이 이전에 보내진 HTML DOM 요소 위에 한번 더 렌더링 하는데 이 과정을 이 과정을 Hydrate라고 함.

### Link
- route를 할 때 a를 사용하면 reload되기 때문에 Link를 사용한다.
- `<Link style={{color: 'red'}} className='Name' href='/'>Home</Link>`
- Link tag는 styling을 하려면 내부에 span tag를 넣고 styling을 해야한다.

### Router
`const router = useRouter();`
- router.pathname : 현재 pathname

### _app.js
- next는 화면을 렌더링할 때 _app.js를 가장 먼저 바라본다.
- 따라서 공통적인 부분을 _app.js 파일에 넣어주면 된다. React의 App.js의 역할을 한다고 볼 수 있을 것 같다.

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
- 즉 ssr은 api의 fetch가 완료되기 전까지는 user가 아무것도 볼 수 없지만 data가 받아와지면 전체를 다 볼 수 있다. 그러나 다른 페이지로 갈 때도 이 과정이 실행된다. csr은 모든 js파일이 들어오기 전까지 전혀 볼 수 없다. 그러나 다른 페이지로 갈 때는 이미 모든 js파일이 받아졌으므로 이동이 빠르다.