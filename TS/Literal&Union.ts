// Literal
type Job = 'police' | 'developer' | 'teacher';

interface User3 {
  name: string;
  job: Job;
}

const user3: User3 = {
  name: 'Ssong',
  job: 'developer',
};

//Union
interface Car {
  name: 'car';
  color: string;
  start(): void;
}

interface Mobile {
  name: 'mobile';
  color: string;
  call(): void;
}

// 동일한 속성의 타입을 다르게 해서 구분할 수 있는 것을 식별 가능한 Union type이라고 함
function getGift(gift: Car | Mobile) {
  if (gift.name === 'car') {
    gift.start();
  } else {
    gift.call();
  }
}

// Intersection Types
interface Car1 {
  name: string;
  start(): void;
}

interface Toy {
  name: string;
  color: string;
  price: number;
}
// &기호이므로 하나라도 없으면 안 됨.
const toyCar: Toy & Car1 = {
  name: 'Tayo',
  start() {},
  color: 'blue',
  price: 1000,
};
