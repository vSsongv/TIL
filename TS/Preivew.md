```js
// 이름은 string만 가능하다.
let name :string = '진영';

// array 자료 내에 string만 들어가야 한다.
let name :string[] = ['Song', 'Jin'];

// object 자료형
let name :{ name : string } = { name : 'Ssong };

// 제한 가능
let name :{ name : string };
// type을 name으로 제한했기 때문에 위 형태로만 가능
let Jin : name = { name : 123 };

// 글자로 된 모든 속성의 타입은 string이어야 한다.
type Member = {
    [key :string] : string,
}

// name 속성이 들어올 수도, 안 들어올 수도 있어요~ option이에요.
let name :{ name? : string } = { name : 'Ssong }

// union type
// string 또는 number가 들어올 수 있어요.
let name :string | numer = 'Ssong';
let name :string[] | numer = 123;

//type을 변수로 만들 수 있다.
type Name = string | number;
let name :Name = 'Ssong'

// 함수
// 무조건 number가 들어와야 하고, number가 return 되어야 한다.
function test(x: number) :number {
    return x * 2
}

//tuple type
type Member = [number, boolean];
let Ssong: Member [123, true];

//never type
//영원히 끝나지 않거나 Error를 반환하는 경우
function showError():never {
    throw new Error();
}
function infLoop():never {
    while(true) {
        // do something
    }
}
```
