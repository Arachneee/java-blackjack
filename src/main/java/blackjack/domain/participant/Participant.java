package blackjack.domain.participant;

import blackjack.domain.betting.BetAmount;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.card.Score;
import java.util.ArrayList;
import java.util.Objects;

public class Participant {

    // TODO : 인스턴스 필드 2개로 줄이기
    private final Hands hands;
    private final ParticipantName name;
    private BetAmount betAmount;

    private Participant(final String name) {
        this.name = new ParticipantName(name);
        this.hands = new Hands(new ArrayList<>());
    }

    public static Participant from(final String name) {
        return new Participant(name);
    }

    public void addCard(final Card card) {
        hands.add(card);
    }

    public Score calculateScore() {
        return hands.calculateScore();
    }

    public boolean isName(final String name) {
        return this.name.isSameName(name);
    }

    public boolean isNotBlackjack() {
        return hands.isNotBlackjack();
    }

    public boolean isNotDead() {
        return hands.isNotDead();
    }

    public Hands getHands() {
        return new Hands(hands.getCards());
    }

    public Hands getFirstCard() {
        return hands.getFirstCard();
    }

    public ParticipantName getName() {
        return name;
    }

    public BetAmount getBetAmount() {
        return betAmount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Participant that)) {
            return false;
        }
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
