package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayingCardsTest {

    private Card aceSpade;
    private Card threeSpade;
    private Card queenSpade;

    @BeforeEach
    void before() {
        aceSpade = new Card(CardNumber.ACE, CardShape.SPADE);
        threeSpade = new Card(CardNumber.THREE, CardShape.SPADE);
        queenSpade = new Card(CardNumber.QUEEN, CardShape.SPADE);
    }

    @DisplayName("카드들의 총합이 올바르게 계산되는지 확인한다.")
    @Test
    void calculate_total() {
        PlayingCards cards = new PlayingCards();
        cards.add(Card.getDeck());
        assertThat(cards.calculateTotal()).isEqualTo(340);
    }

    @DisplayName("카드들의 총합이 21을 초과하는지 확인한다.")
    @Test
    void is_over_blackjack() {
        PlayingCards cards = new PlayingCards();
        cards.add(new ArrayList<>(List.of(queenSpade, queenSpade, queenSpade)));
        assertThat(cards.isOverBlackjack()).isTrue();
    }

    @DisplayName("카드들의 총합이 21미만인지 확인한다.")
    @Test
    void is_under_blackjack() {
        PlayingCards cards = new PlayingCards();
        cards.add(new ArrayList<>(List.of(queenSpade, queenSpade)));
        assertThat(cards.isUnderBlackjack()).isTrue();
    }
}
