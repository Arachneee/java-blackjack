# java-blackjack

블랙잭 미션 저장소

# 요구사항 분석
## 주요 객체의 속성, 역할
### PlayersFactory
- [x] 입력된 이름을 가지고 `Players`를 만들어 반환한다.
  - [x] 최소 2명에서 최대 8명의 `Players`를 생성한다.

### Participant
- [x] `Cards`를 가진다.
- [x] `Card`를 받는다.

### Player (extends Participant)
- [x] 이름을 가진다.
  - [x] 이름의 길이는 최소 1글자에서 최대 5글자이다.
- [x] `Cards`를 가진다.
- [x] `Card`를 받는다.

### Players
- [x] `Player`들을 가진다.

### Dealer (extends Participant)
- [x] `Players`를 가진다.
- [x] `Deck`을 가진다.
- [x] `Cards`를 가진다.
- [x] `Card`를 받는다.
- [x] `Players`의 모두에게 2장의 카드를 나눠준다.
- [x] 자신의 카드 2장을 뽑는다.
- [x] 카드를 더 받길 희망하는 참가자에게 카드를 한 장 준다.
- [x] 최종 승패를 결정한다.
- [x] 점수가 16점 초과일 때까지, 본인의 카드를 한 장 더 뽑는다.

### Result (Enum)
- [x] 결과를 가진다. (승, 무, 패)

### Card
- [x] `Number`와 `Pattern`을 가진다.

### Cards
- [x] `Card`들을 가진다.
- [x] 가지고 있는 `Card`로 총점을 계산한다.
- [x] 가지고 있는 `Card`가 블랙잭인지 계산한다.
- [x] 가지고 있는 `Card`가 버스트인지 계산한다.
- [x] 가지고 있는 `Card`가 16점 이하인지 계산한다.

### Deck
- [x] 게임에서 뽑히지 않은 랜덤한 카드를 뽑아준다.
- [x] 뽑힌 카드를 가진다.

### Number (Enum)
- [x] 숫자를 가진다. (A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K)
- [x] 숫자를 점수로 변환해준다.

### Pattern (Enum)
- [x] 문양을 가진다. (하트, 스페이드, 클로버, 다이아몬드)
  - [x] 문양에 맞는 이름을 가진다.

## 입출력 요구사항
### 입력 요구사항
- [x] 참가자의 이름을 입력받는다. (','로 구분짓는다.)
  - [x] 공백을 입력했는지 검증한다.
- [x] 카드를 더 받을지 입력받는다.
  - [x] 'y' 또는 'n' 을 입력했는지 검증한다.

### 출력 요구사항
- [x] 딜러와 참가자들에게 나누어준 카드를 출력한다.
  - [x] 딜러는 한 장의 카드만 출력한다.
- [x] 참가자의 현재 가진 카드들을 출력한다.
- [x] 딜러의 점수가 16점 이하인 경우, 딜러가 한 장의 카드를 더 받았다고 출력한다.
- [x] 참가자와 딜러가 가진 카드들을 모두 출력하고, 총점도 같이 출력한다.
- [x] 최종 승패를 출력한다.

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

# 미션을 진행하며 궁금했던 점
1. 일급 컬렉션을 쓰다보니 컨트롤러에서 `dealer.getPlayers().getPlayers()` 로 쓰이게 됩니다. 그래서 아래와 같은 방법을 생각해봤습니다.
```java
public class Dealer {
  // ...
  public List<Player> getPlayers() {
      return this.players.getPlayers();
  }
}
```
그런데, 아무래도 캡슐화가 깨지는 느낌을 받아서 가독성 측면에서 보기 안좋지만 `dealer.getPlayers().getPlayers()` 처럼 하게 됐습니다. 더 나은 방법이 있을까요?

2. 저희는 `Controller`에서 도메인으로부터 값을 받아 `View`에게 도메인 객체를 넘기지 않고, 필요한 정보를 재가공하여 전달하는 방식을 사용했습니다. 이렇게 하니, `OutputView`는 단순히 print만 하는 클래스가 되는 느낌이었고, `Controller`의 코드가 복잡해졌습니다.<br>
이유는, `View`가 `Domain`을 직접적으로 아는 듯하여 의존성을 약하게 하기 위함입니다.<br>
`OutputView`에서 `Domain` 객체를 직접 가공하는 것은 MVC 패턴에 위배되는 것 같은데, 저희의 방법이 적절한가요?

3. `Enum` 객체에서 출력을 위한 상태를 가져도 괜찮은 지 궁금합니다.
