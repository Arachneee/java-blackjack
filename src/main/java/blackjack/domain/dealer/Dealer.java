package blackjack.domain.dealer;

import blackjack.domain.result.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.ParticipantName;
import java.util.List;

public class Dealer {
    public static final Score NEED_CARD_CRITERION = new Score(17);
    private static final String DEALER_SIGNAL = "DEALER";

    private final Deck deck;
    private final Participant participant;

    public Dealer(final Deck deck) {
        this.deck = deck;
        this.participant = Participant.from(DEALER_SIGNAL);
    }

    public boolean needMoreCard() {
        return NEED_CARD_CRITERION.isBiggerThan(participant.calculate());
    }


    public Card drawCard() {
        return deck.pick();
    }

    public List<Card> drawCards(final int count) {
        return deck.pick(count);
    }

    public void addCard() {
        participant.addCard(drawCard());
    }

    public void addStartCard() {
        participant.addStartCard(drawCard(), drawCard());
    }

    public Score calculate() {
        return participant.calculate();
    }

    public boolean isNotBlackjack() {
        return participant.isNotBlackjack();
    }

    public Hands getOpenedHands() {
        return participant.getFirstCard();
    }

    public Hands getHands() {
        return participant.getHands();
    }

    public ParticipantName getName() {
        return participant.getName();
    }
}
