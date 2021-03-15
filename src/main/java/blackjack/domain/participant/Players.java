package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import blackjack.domain.card.Deck;
import blackjack.domain.state.PlayerState;
import blackjack.domain.state.StateFactory;

public class Players {
	private final List<Player> players;
	private final Gamer dealer;

	public Players(List<String> value, Dealer dealer) {
		this.players = createPlayers(value);
		this.dealer = dealer;
	}

	private List<Player> createPlayers(List<String> value) {
		List<Player> splitPlayers = new ArrayList<>();
		for (String name : value) {
			splitPlayers.add(new Player(name, 0));
		}
		return splitPlayers;
	}

	public void initialCards(Deck deck) {
		dealer.playerState = StateFactory.drawTwoCards(deck.dealCard(), deck.dealCard());
		for (Player player : players) {
			player.playerState = StateFactory.drawTwoCards(deck.dealCard(), deck.dealCard());
		}
	}

	public int calculateProfits(Dealer dealer) {
		PlayerState dealerState = dealer.playerState;
		int total = players.stream()
			.peek(player -> player.calculateProfit(dealerState))
			.map(Player::getMoney)
			.reduce(0, Integer::sum);
		return total * -1;
	}

	public List<Player> toList() {
		return players;
	}

	public String getDealerName() {
		return dealer.getName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Players players1 = (Players)o;
		return Objects.equals(players, players1.players) && Objects.equals(dealer, players1.dealer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(players, dealer);
	}
}