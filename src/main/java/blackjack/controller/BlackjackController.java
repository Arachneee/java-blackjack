package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Gamers;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        final Deck deck = Deck.create();
        final Gamers gamers = deck.initiateGamers(InputView.receiveNames());
        OutputView.gameStart(gamers);

        for (Gamer gamer : gamers.players()) {
            hitOrStand(deck, gamer);
        }
        dealerHitOrStand(deck, gamers);
        printResult(gamers);
    }

    private void hitOrStand(Deck deck, Gamer gamer) {
        while (InputView.receiveAnswer(gamer.getName())) {
            gamer.receiveCard(deck.giveCard());
            OutputView.allCards(gamer);
        }
    }

    private void dealerHitOrStand(Deck deck, Gamers gamers) {
        if (gamers.dealer().checkBoundary()) {
            gamers.dealer().receiveCard(deck.giveCard());
            OutputView.dealerHit();
        }
    }

    private void printResult(Gamers gamers) {
        OutputView.gamersAllCards(gamers);
        OutputView.printResultTitle();
        OutputView.dealerResult(gamers.resultWithCount());
        OutputView.playersResult(gamers.resultWithName());
    }
}