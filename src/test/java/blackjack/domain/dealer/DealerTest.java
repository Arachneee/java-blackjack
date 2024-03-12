package blackjack.domain.dealer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 카드의 합이 17 미만인 경우 카드가 더 필요하다.")
    @Test
    void needMoreCard() {
        Card card1 = Card.of(CardNumber.SEVEN, CardShape.HEART);
        Card card2 = Card.of(CardNumber.NINE, CardShape.HEART);

        Dealer dealer = new Dealer(new Deck(List.of(card1, card2)));
        dealer.start();

        boolean needMoreCard = dealer.needMoreCard();

        assertThat(needMoreCard).isTrue();
    }

    @DisplayName("딜러는 카드의 합이 17 이상인 경우 카드가 더 필요하지 않다.")
    @Test
    void needNoMoreCard() {
        Card card1 = Card.of(CardNumber.NINE, CardShape.HEART);
        Card card2 = Card.of(CardNumber.EIGHT, CardShape.HEART);

        Dealer dealer = new Dealer(new Deck(List.of(card1, card2)));
        dealer.start();

        boolean needMoreCard = dealer.needMoreCard();

        assertThat(needMoreCard).isFalse();
    }

    @DisplayName("플레이어의 수와 각 카드 수에 맞게 카드를 뽑는다.")
    @Test
    void drawCard() {
        // given
        final List<Card> expected = List.of(Card.of(CardNumber.NINE, CardShape.HEART),
                Card.of(CardNumber.EIGHT, CardShape.HEART));

        Dealer dealer = new Dealer(new Deck(expected));

        final int playerSize = 1;
        final int eachCardCount = 2;

        // when
        final List<List<Card>> cards = dealer.drawCards(playerSize, eachCardCount);

        // then
        assertThat(cards).hasSize(playerSize)
                .containsExactly(expected);

    }
}
