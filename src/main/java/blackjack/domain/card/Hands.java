package blackjack.domain.card;

import blackjack.domain.rule.Score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Hands implements Comparable<Hands> {

    private static final int ACE_SCORE_GAP = CardNumber.ACE.getNumber() - CardNumber.ACE_BONUS_NUMBER;

    private final List<Card> cards;

    public Hands() {
        this.cards = new ArrayList<>();
    }

    public Hands(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hands addCard(final Card card) {
        final List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return new Hands(newCards);
    }

    public Score calculateScore() {
        int sum = sum();
        int aceCount = countAce();

        while (isBustScore(sum) && aceCount-- > 0) {
            sum -= ACE_SCORE_GAP;
        }

        return new Score(sum);
    }

    private int sum() {
        return cards.stream().mapToInt(Card::getNumber).sum();
    }

    private int countAce() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    private boolean isBustScore(final int sum) {
        return new Score(sum).isBust();
    }

    public boolean isBustScore() {
        return calculateScore().isBust();
    }

    public boolean isBlackjackScore() {
        return calculateScore().isBlackjack();
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public int compareTo(final Hands o) {
        return calculateScore().compareTo(o.calculateScore());
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
