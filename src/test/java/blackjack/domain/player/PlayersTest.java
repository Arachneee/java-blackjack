package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.exception.NeedRetryException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("중복된 플레이어를 생성할 수 없다.")
    @Test
    void validateDuplicate() {
        final List<String> names = List.of("kirby", "kirby");
        assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(NeedRetryException.class)
                .hasMessage("중복된 이름의 참여자는 참여할 수 없습니다.");
    }

    @DisplayName("카드를 참가자 별로 2장씩 나눠준다.")
    @Test
    void divideCard() {
        // given
        String kirby = "kirby";
        String pobi = "pobi";
        Players players = Players.from(List.of(kirby, pobi));
        Card card1 = new Card(CardNumber.ACE, CardShape.SPADE);
        Card card2 = new Card(CardNumber.TWO, CardShape.SPADE);
        Card card3 = new Card(CardNumber.THREE, CardShape.SPADE);
        Card card4 = new Card(CardNumber.FOUR, CardShape.SPADE);
        List<List<Card>> cardsBundle = List.of(List.of(card1, card2), List.of(card3, card4));

        // when
        players.divideCard(cardsBundle);

        // then
        assertAll(
                () -> assertThat(players.getHandsOf(kirby).getCards())
                        .hasSize(2)
                        .containsExactly(card1, card2),
                () -> assertThat(players.getHandsOf(pobi).getCards())
                        .hasSize(2)
                        .containsExactly(card3, card4));
    }

    @DisplayName("카드를 참가자에게 나눠줄 때 수량이 참가자 수의 2배와 다르면 안된다.")
    @Test
    void validateCardSize() {
        // given
        Players players = Players.from(List.of("kirby", "pobi"));
        List<List<Card>> cards = List.of(List.of(new Card(CardNumber.ACE, CardShape.SPADE),
                new Card(CardNumber.TWO, CardShape.SPADE)));

        // when
        assertThatThrownBy(() -> players.divideCard(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 묶음 수량이 플레이어 수량과 맞지 않습니다.");
    }
}
