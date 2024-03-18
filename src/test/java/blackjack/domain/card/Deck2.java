package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Deck2 {

    private static final List<Card> CACHED_CARDS = cacheCards();

    private final Deque<Card> cards;

    public Deck2(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static Deck2 create() {
        return new Deck2(CACHED_CARDS);
    }

    private static List<Card> cacheCards() {
        List<Card> cards = createOneDeck();

        for (int i = 0; i < 100000; i++) {
            cards.addAll(createOneDeck());
        }

        Collections.shuffle(cards);

        return cards;
    }

    private static List<Card> createOneDeck() {
        return Arrays.stream(CardShape.values())
                .flatMap(shape -> Arrays.stream(CardNumber.values()).map(number -> Card.of(number, shape)))
                .collect(Collectors.toList());
    }

    public Card pick() {
        validateEmpty();

        return cards.pop();
    }

    private void validateEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 부족합니다.");
        }
    }
}
