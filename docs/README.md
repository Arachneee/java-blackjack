### UI
- 입력
  - [x] 참여할 사람의 이름 입력
  - [x] 플레이어별 배팅 금액 입력
  - [x] 카드 추가 지급 의사 입력
  
- 출력
  - [x] 카드 지급 완료 문구 출력
  - [x] 개인 카드 목록 출력
  - [x] 딜러 카드 추가 지급 여부 출력
  - [x] 최종 카드 상태 출력
  - [x] 최종 수익 출력

### 기능
- BlackJackGame : 게임 관리 시스템
  - [x] 모든 플레이어에게 시작 카드를 2장씩 나누어준다
  - [x] 플레이어에게 카드를 1장 나누어준다
  - [x] 게임 결과를 만들어 반환한다
  
- Player : 게임 참여자
  - [x] 초기 카드 2장을 받는다
  - [x] 추가 카드를 뽑는다
  - [x] Bust인지 확인한다 (21이 넘는지)
  - [x] BlackJack인지 확인한다 (21인지)
  - Challenger : 일반 참여자 (Player를 상속받는다)
    - [x] 이름을 입력받는다
    - [x] 플레이어 이름이 `딜러`인 경우 예외가 발생한다
    - [x] 카드의 합이 21 초과인지 확인한다
  - Dealer : 딜러 (Player를 상속받는다)
    - [x] 카드의 합이 16 초과인지 확인한다
    - [x] 도전자의 게임 결과를 판정한다

- Players : Player 목록을 가지고 있는 일급 컬렉션
  - [x] 플레이어 이름 중복 시 예외가 발생한다
  
- Card : 카드
  - [x] 카드 숫자를 보고 점수를 반환한다 (ex. Ace -> 1 or 11 // King, Queen, Jack -> 10)

- CardDeck : 덱에 있는 카드. Card 목록을 가지고 있는 일급 컬렉션
  - [x] 52장의 카드를 생성한다
  - [x] 카드 순서를 무작위로 섞는다
  - [x] 맨 위의 카드를 게임 참여자에게 전달한다

- HolingCards : 참여자가 소유하고 있는 카드. Card 목록을 가지고 있는 일급 컬렉션
  - [x] 뽑은 카드를 저장한다
  - [x] 가진 카드로 가능한 합 목록을 반환한다
  - [x] 가진 카드들 중에 21보다 작은 가장 큰 값을 반환한다

- GameResult : 플레이어의 최종 수익
  - [x] 도전자의 최종 승패 결과에 따라 수익을 계산한다
  - [x] 딜러의 수익을 계산한다

- Money
  - [x] 배팅 금액은 10000원 이상 100000원 이하여야 한다