package blackjack.domain.bet;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardShape.DIA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import blackjack.domain.card.Card;
import blackjack.domain.rule.state.InitState;
import blackjack.domain.rule.state.State;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class BetLeverageTest {

    static final State score19count2 = new InitState().draw(Card.of(TEN, DIA)).draw(Card.of(NINE, DIA)).stand();
    static final State score20count2 = new InitState().draw(Card.of(TEN, DIA)).draw(Card.of(JACK, DIA)).stand();
    static final State score21count2 = new InitState().draw(Card.of(ACE, DIA)).draw(Card.of(JACK, DIA));
    static final State score21count3 = new InitState().draw(Card.of(QUEEN, DIA)).draw(Card.of(JACK, DIA)).draw(Card.of(ACE, DIA)).stand();
    static final State score22count3 = new InitState().draw(Card.of(TEN, DIA)).draw(Card.of(JACK, DIA)).draw(Card.of(TWO, DIA));
    static final State score23count3 = new InitState().draw(Card.of(TEN, DIA)).draw(Card.of(JACK, DIA)).draw(Card.of(THREE, DIA));


    @DisplayName("딜러와 참여자의 상태로 배팅결과를 계산한다.")
    @ParameterizedTest
    @MethodSource("handsAndWinStatus")
    void of(State dealerState, State playerState, BetLeverage expected) {
        final BetLeverage betLeverage = BetLeverage.of(playerState, dealerState);

        assertThat(betLeverage).isEqualTo(expected);
    }

    static Stream<Arguments> handsAndWinStatus() {
        return Stream.of(                                                        // 딜러(카드수) | 참여자(카드수) | 레버리지
                arguments(score20count2, score21count2, BetLeverage.BLACKJACK),  // 생존(2)    , 블랙잭(2)     , 1.5
                arguments(score22count3, score21count2, BetLeverage.BLACKJACK),  // 죽음(3)    , 블랙잭(2)     , 1.5
                arguments(score21count3, score21count2, BetLeverage.BLACKJACK),  // 블랙잭(3)   , 블랙잭(2)    , 1.5
                arguments(score20count2, score21count3, BetLeverage.WIN),        // 생존(2)    , 블랙잭(3)     , 1
                arguments(score23count3, score21count3, BetLeverage.WIN),        // 죽음(3)    , 블랙잭(3)     , 1
                arguments(score23count3, score20count2, BetLeverage.WIN),        // 죽음(3)    , 생존(2)      , 1
                arguments(score19count2, score20count2, BetLeverage.WIN),        // 생존(2)    , 생존(2)      , 1
                arguments(score21count2, score20count2, BetLeverage.LOSE),       // 블랙잭(2)   , 생존(2)      , -1
                arguments(score21count3, score22count3, BetLeverage.LOSE),       // 블랙잭(3)   , 죽음(3)      , -1
                arguments(score21count2, score21count3, BetLeverage.LOSE),       // 블랙잭(2)   , 블랙잭(3)     , -1
                arguments(score20count2, score19count2, BetLeverage.LOSE),       // 생존(2)    , 생존(2)       , -1
                arguments(score20count2, score23count3, BetLeverage.LOSE),       // 생존(2)    , 죽음(3)       , -1
                arguments(score22count3, score23count3, BetLeverage.LOSE),       // 죽음(3)    , 죽음(3)       , -1
                arguments(score23count3, score22count3, BetLeverage.LOSE),       // 죽음(3)    , 죽음(3)       , -1
                arguments(score22count3, score22count3, BetLeverage.LOSE),       // 죽음(3)    , 죽음(3)       , -1
                arguments(score21count2, score21count2, BetLeverage.PUSH),       // 블랙잭(2)   , 블랙잭(2)     , 0
                arguments(score21count3, score21count3, BetLeverage.PUSH),       // 블랙잭(3)   , 블랙잭(3)     , 0
                arguments(score20count2, score20count2, BetLeverage.PUSH)        // 생존(2)    , 생존(2)       , 0
        );
    }

    @DisplayName("배팅 결과로 플레이어 수익을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACKJACK,1000,1500", "WIN,1000,1000", "LOSE,1000,-1000", "PUSH,1000,0"})
    void applyLeverage(BetLeverage betLeverage, int betAmount, int revenue) {
        // given
        BetAmount batAmount = new BetAmount(betAmount);

        // when
        BetRevenue batRevenue = betLeverage.applyLeverage(batAmount);

        // then
        assertThat(batRevenue).isEqualTo(new BetRevenue(revenue));
    }
}
