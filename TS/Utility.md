### keyof
- key값들을 union 형태로 받아올 수 있음.

```ts
interface User {
  id: number;
  name: string;
  age: number;
  gender: "m" | "f";
}

// keyOf는 모든 key를 가져온다는 의미.
// id | name | gender 와 같은 의미
type UserKey = keyof User; 
```

### Partial<T>
- 프로퍼티를 모두 optional로 바꿔줌.

```ts
interface User {
  id: number;
  name: string;
  age: number;
  gender: "m" | "f";
}

let admin: Partial<User> = {
  id: 1,
  name: "Bob",
}
```

### Rquired<T>
- 모든 프로퍼티를 필수로 바꿔줌.

```ts
interface User {
  id: number;
  name: string;
  age?: number;
  gender: "m" | "f";
}

// age가 없으므로 에러 발생
let admin: Rquired<User> = {
  id: 1,
  name: "Bob",
}
```

### Readonly<T>
- 읽기 전용으로 변경

```ts
interface User {
  id: number;
  name: string;
  age?: number;
  gender: "m" | "f";
}

let admin: Readonly<User> = {
  id: 1,
  name: "Bob",
}

//Readonly 이므로 에러 발생.
admin.id = 4;
```

### Record<K, T>
- K: key, T: type으로, 여러 값을 한번에 지정 가능

```ts
//예저 1
interface Score {
  '1': 'A' | 'B' | 'C' | 'D';
  '2': 'A' | 'B' | 'C' | 'D';
  '3': 'A' | 'B' | 'C' | 'D';
  '4': 'A' | 'B' | 'C' | 'D';
}

const score: Score = {
  1: 'A',
  2: 'B',
  3: 'C',
  4: 'D',
}
//였던 것을 

const score: Record<'1'|'4'|'3'|'4', 'A' | 'B' | 'C' | 'D'> = {
  1: 'A',
  2: 'B',
  3: 'C',
  4: 'D',
}
//로 바꿀 수 있고,

//최종적으로 아래와 같이 정리 가능하다.
type Grade = '1'|'4'|'3'|'4';
type Score = 'A' | 'B' | 'C' | 'D';

const score: Record<Grade, Score> = {
  1: 'A',
  2: 'B',
  3: 'C',
  4: 'D',
}

// 예제 2
interface User {
  id: number;
  name: string;
  age: number;
}

function isvalid(user: User) {
  const result: Record<keyof User, boolean> = {
    id:user.id > 0,
    name: user.name !== "",
    age: user.age > 0
  };
   return result
}
```

### Pick<K, T>
- T중에 K만 pick해서 사용할 수 있다.

```ts
interface User {
  id: number;
  name: string;
  age: number;
  gender: "m" | "f";
}

// user에서 id, name만 가져와서 사용 가능
let admin: Pick<User, "id" | "name"> = {
  id: 1,
  name: "Bob",
}
```

### Omit<K, T>
- T중에 K만 Omit해서 사용할 수 있다.

```ts
interface User {
  id: number;
  name: string;
  age: number;
  gender: "m" | "f";
}

// user에서 age, gender만 빼서 사용 가능
let admin: Omit<User, "age" | "gender"> = {
  id: 1,
  name: "Bob",
}
```

### Exclude<T1, T2>
- T1 type들 중에서 T2 type을 제외한다.

```ts
type T1 = string | number | boolean;
type T2 = Exclude<T1, number | string>;
```
- T2는 boolean만 남게 된다.

### NonNullable<T>
- null, undefined를 제외한 타입을 생성한다.
```ts
type T1 = string | number | boolean | null;
type T2 = NonNullable<T1>;
```
- T2는 string, number, boolean만 남게 된다.