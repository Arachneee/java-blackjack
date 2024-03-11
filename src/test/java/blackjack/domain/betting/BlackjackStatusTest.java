package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.BlackjackStatus;
import blackjack.domain.card.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackjackStatusTest {

    @DisplayName("숫자에 따른 블랙잭 상태를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"20, ALIVE", "21, BLACKJACK", "22, DEAD"})
    void from(int sum, BlackjackStatus expected) {
        // given & when
        BlackjackStatus blackjackStatus = BlackjackStatus.from(new Score(sum));

        // then
        assertThat(blackjackStatus).isEqualTo(expected);
    }
}