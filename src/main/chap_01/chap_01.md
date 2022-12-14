# 객체, 설계

## 티켓 판매 애플리케이션 구현하기

- 현재 소극장을 경영하고 있다.
- 소극장의 홍보도 할겸 이벤트로 추첨을 통해 선정된 관람객에게 공연을 무료로 관람할 수 있는 초대장을 발송한다.
- 이벤트에 당첨되지 않아도 돈으로 티켓을 구매할 수 있다.

실습코드 : 

---
## Step_1
로버트 마틴은 <클린 소프트웨어: 애자일 원칙과 패턴, 그리고 실천방법> 에서 소프트웨어 모듈이 가져야 하는 세 가지 기능에 관해 설명한다.
> - 실행 중에 제대로 동작할 것
> - 변경을 위해 존재하는 것
> - 코드를 읽는 사람과 의사소통 하는것

- 현재 코드의 문제점은 변경에 용이하지 못하며 코드를 읽는 사람과의 의사소통이라는 목적은 만족시키지 못한다.

현재 코드를 풀어써보자
> - 소극장은 관람객의 가방을 열어 그 안에 초대장이 들어 있는지 살펴본다.
>   - 관람객과 판매원이 소극장의 통제를 받는 수동적인 존재가 된다. 

이해 가능한 코드란 그 동작이 우리의 예상에서 크게 벗어나지 않는 코드다. 안타깝게 앞에서 살펴본 코드는 예상을 벗어난다.
- 현재 Theater 의 enter 메서드를 이해하기 위해서는 Audience가 Bag을 가지고 있고, Bag 안에는 현금과 티켓이 들어 있으며 TicketSeller가 TicketOffice에서 티켓을
판매하고, TicketOffice 안에 돈과 티켓이 보관돼 있다는 사실을 동시에 기억하고 있어야 한다.
-  가장 심각한 문제는 Audience와 TicketSeller를 변경할 경우 Theater도 함께 변경해야 한다는 점이다.<br>

이것은 객체 사이의 의존성(dependecy)와 관련된 문제이다. 의존성은 변경에 대한 영향을 암시한다. 의존성이라는 말 속에는 어떤 객체가 변경될 때 객체에게 의존하는
다른 객체도 함께 변경될 수 있다는 사실이 내포 돼 있다.<br>
<img height="300" src="https://user-images.githubusercontent.com/101342145/193878958-3b6459c2-8fc3-4731-8107-2449d0e52c57.jpeg" width="500"/>
- 객체 사이에 의존성이 과한 경우를 가르켜 결합도(coupling)가 높다고 말한다.
> 두 객체 사이의 결합도가 높으면 높을수록 함께 변경될 확률도 높아지기 때문에 변경하기 어려워진다. 따라서 설계의 목표는 객체 사이의 결합도를 낮춰 변경이 용이한 설계를 만드는 것이어야 한다.

## Step_2
#### 무엇이 개선됐는가
> -  수정된 Audience와 TicketSeller는 자신이 가지고 있는 소지품을 스스로 관리한다.
> - Aidience나 TicketSeller의 내부 구현을 변경하더라도 Theater를 함께 변경할 필요가 없어졌다.

#### 어떻게 한 것인가
- 객체의 자율성을 높이는 방향으로 개선
> - 판매자가 티켓을 판매하기 위해 TicketOffice를 사용하는 모든 부분을 TicketSeller 내부로
> - 관람객이 티켓을 구매하기 위해 Bag을 사용하는 모든 부분을 Audience 내부로 옮겼다.

#### 캡슐화와 응집도
> 객체 내부의 상태를 캡슐화하고 객체 간에 오직 메시지를 통해서만 상호작용하도록 만드는것이다.
> 밀접하게 연관된 작업만을 수행하고 연관성 없는 작업은 다른 객체에게 위임하는 객체를 가리켜 응집도(cohesion)가 높다고 말한다.
> 자신의 데이터를 스스로 처리하는 자율적인 객체를 만들면 결합도를 낮출 수 있을뿐더라 응집도를 높일 수 있다.

- TicketSeller가 sellTo 메시지를 이해하고 응답할 수 있다는 사실만 알고 있고
- TicketSeller 역시 Audience의 내부에 대해서는 전혀 알지 못한다.

## Step_3

#### 객체지향
- 현실에서는 수동적인 존재라고 하더라도 일단 객체지향 세계에 들어오면 모든 것이 능동적이고 자율적인 존재로 바뀐다.
- 능동적이고 자율적인 존재로 소프트웨어 객체를 설계하는 원칙을 가리켜 의인화라고 부른다.
> - 객체지향 프로그래밍은 의존성을 효율적으로 통제할 수 있는 다양한 방법을 제공함으로써 요구사항 변경에 좀 더 수월하게 대응할 수 있는 가능성을 높여준다.
> - 변경 가능한 코드란 이해하기 쉬운 코드다.
> - 훌륭한 객체지향 설계란 협력하는 객체 사이의 의존성을 적절하게 관리하는 설계다. 객체 간의 의존성은 애플리케이션을 수정하기 어렵게 만드는 주범이다.