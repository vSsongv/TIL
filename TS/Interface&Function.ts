// 프로퍼티를 정해서 객체를 표현하고자 할 때 interface를 사용한다.

type Score = 'A' | 'B' | 'C' | 'D' | 'F';

interface User {
  name: string;
  age: number;
  gender?: string;
  //읽기 전용 속성
  readonly birth: number;
  //key가 number이고 값이 string인 값을 여러 개 받을 수 있다.
  // 이때 score로 지정해주면 score내부 알파벳만 사용 가능하다.
  [grade: number]: Score;
}

let user: User = {
  name: 'x',
  age: 25,
  birth: 199,
  1: 'A',
  2: 'B',
};

//interface 함수augusts
interface Add {
  (num1: number, num2: number): number;
}

const add: Add = function (x, y) {
  return x + y;
};

add(10, 20);

interface IsAdult {
  (age: number): boolean;
}

const a: IsAdult = (age) => {
  return age > 19;
};

function IsAdult(age: number): boolean {
  return age > 19;
}

//optional parameter
function hello(name?: string) {
  return `Hello, ${name || 'world'}`;
}

//optional parameter는 앞에 올 수 없음.
// (name?: string, age:number) -> 이건 안 됨
function age(name: string, age?: number) {
  return `Hello, ${name}. You are ${age}.`;
}

function add2(...nums: number[]) {
  return nums.reduce((result, num) => result + num, 0);
}

add2(1, 2, 3, 4, 5, 6, 7);

//Overload
interface User2 {
  name: string;
  age: number;
}

function join(name: string, age: number): User2;
function join(name: string, age: string): string;
function join(name: string, age: number | string): User2 | String {
  if (typeof age === 'number') {
    return { name, age };
  } else return '나이를 숫자로 입력해주세요.';
}
