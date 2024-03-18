package blackjack.domain.card;



import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Deck2Test {

    @DisplayName("빈덱에서 카드를 뽑을 수 없다.")
    @Test
    void validateEmpty() {
        Deck2 deck = new Deck2(List.of());

        assertThatThrownBy(deck::pick)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드가 부족합니다.");
    }

    @DisplayName("덱에서 카드를 하나 뽑는다.")
    @Test
    void validateDuplicate() {
        // given
        Card expected = Card.of(CardNumber.TWO, CardShape.CLOVER);
        Deck2 deck = new Deck2(List.of(expected));
        // when
        Card card = deck.pick();

        // then
        assertThat(card).isEqualTo(expected);
    }

    @DisplayName("Deque 성능테스트")
    @Test
    void arrayList() {
        // given
        Deck2 deck = Deck2.create();

        // when
        final long before = System.currentTimeMillis();

        for (int i = 0; i < 1000_000; i++) {
            deck.pick();
        }
        System.out.println(System.currentTimeMillis() - before);
    }
}

