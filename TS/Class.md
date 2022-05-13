```ts
class Car {
  color: string;
  constructor(color: string) {
    this.color = color;
  }
  start() {
    console.log('start');
  }
}

class Car {
//   color: string; 아래 public한 거랑 똑같음
  constructor(public color: string) {
  constructor(readonly color: string) {
    this.color = color;
  }
  start() {
    console.log('start');
  }
}
```

### 접근 제한자 - public, private, protected

```ts
class Car {
  // private color: string인 경우 자식 class내부에서 사용 불가.
  // # 으로 표현 가능 -> #color
  //protected는 자식 class에서는 접근 가능하지만 instance로는 사용 불가.
  protected name: string;
  protected color: string;
  //static
  static wheels = 4;
  constructor(color: string) {
    this.color = color;
  }
  start() {
    console.log('start');
  }
}

class Bmw extends Car {
  color: string;
  constructor(color: string) {
    this.color = color;
  }
  showName() {
    console.log(super.name);
    //color가 public일 경우 가능. private일 경우 에러
  }
}

const z = new Bmw('black');
console.log(z.name); //protected인 경우 에러
z.name = 'dddsf'; //readonly인 경우 수정 불가
console.log(Car.wheels); //static으로 선언된 정적 메소드나 변수는 this가 아니라 class 이름으로 접근해야 한다.
```

public - 자식 class, class instance 모두 접근 가능.
protected - 자식 class에서만 접근 가능
private - 해당 class 내에서만 접근 가능

### 추상클래스

```ts
abstract class Car {
  color: string;
  constructor(public color: string) {
  constructor(readonly color: string) {
    this.color = color;
  }
  abstract start() {  } //추상 메소드는 상속받은 클래스에서 반드시 구현을 해 줘야 함.
}

// 추상클래스는 반드시 상속으로 생성해야 한다.
// const car = new Car("red"); 이렇게 생성 불가
class Bmw extends Car {
  color: string;
  constructor(color: string) {
    this.color = color;
  }
  showName() {
    console.log(super.name);
    //color가 public일 경우 가능. private일 경우 에러
  }
}
```
