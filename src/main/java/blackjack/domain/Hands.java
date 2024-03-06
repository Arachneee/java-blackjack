package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Hands {

    private final List<Card> cards;

    public Hands(final List<Card> cards) {
        validateDuplicate(cards);
        this.cards = new ArrayList<>(cards);
    }

    public Hands(final Card... cards) {
        this(List.of(cards));
    }

    private void validateDuplicate(final List<Card> cards) {
        if (Set.copyOf(cards).size() != cards.size()) {
            throw new IllegalStateException("중복된 카드는 존재할 수 없습니다.");
        }
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public int sum() {
        return cards.stream()
                .mapToInt(Card::getRealNumber)
                .sum();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Card getLastCard() {
        return cards.get(cards.size() - 1);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}