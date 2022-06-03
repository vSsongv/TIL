기존의 형태는 타입이 늘어날 때마다 파라미터가 늘어난다는 단점이 존재함.

```js
function getSize(arr: number[] | string[] | boolean[] | object[]): number {
  return arr.length;
}

const arr1 = [1, 2, 3];
const arr2 = ['1', '2', '3'];
const arr3 = [true, true, false];
const arr4 = [{ name: 'ssong' }, {}, {}];
getSize(arr)
```

이럴 때 쓰는 것이 Generic이다.

```js
function getSize<T>(arr: T[]): number {
  return arr.length;
}

const arr1 = [1, 2, 3];
getSize<number>(arr1)
```

T부분이 사용자가 지정한 type으로 사용될 수 있다.

interface에서 사용해보기

```js
interface Mobile<T> {
  name: string;
  price: number;
  option: T;
}

//generic인 option에 맞는 type을 써주면 된다.
const m1: Mobile<object> {
  name: "one";
  price: 100;
  option: {
    color: "red",
    coupon: false,
  },
};

const m2: Mobile<string> {
  name: "two";
  price: 2300;
  option: "tired";
}
```

```ts
interface Book {
  price: number;
}

//항상 name은 string이어야 함.
function showName<T extends {name: string}>(data: T) string {
  return data.name
}

showName(book)
```
Book interface에는 name이 없기 때문에 showName을 쓸 수 없다.generic으로 