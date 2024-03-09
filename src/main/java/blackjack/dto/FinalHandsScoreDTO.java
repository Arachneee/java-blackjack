package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.Score;
import java.util.LinkedHashMap;
import java.util.Map;

public record FinalHandsScoreDTO(Map<String, HandsScoreDTO> finalCards) {
    public static FinalHandsScoreDTO of(final Map<ParticipantName, Hands> playersHands,
                                    final Map<ParticipantName, Score> playersScores) {
        final Map<String, HandsScoreDTO> finalCards = new LinkedHashMap<>();
        playersHands.forEach((key, value) ->
                finalCards.put(key.getName(), HandsScoreDTO.of(value, playersScores.get(key))));

        return new FinalHandsScoreDTO(finalCards);
    }
}