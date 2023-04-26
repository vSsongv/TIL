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

### Router
`const router = useRouter();`
- router.pathname : 현재 pathname

### _app.js
- next는 화면을 렌더링할 때 _app.js를 가장 먼저 바라본다.
- 따라서 공통적인 부분을 _app.js 파일에 넣어주면 된다. React의 App.js의 역할을 한다고 볼 수 있을 것 같다.


