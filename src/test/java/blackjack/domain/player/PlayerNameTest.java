package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerNameTest {

    @DisplayName("이름이 공백일 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    @NullSource
    void validateBlank(String input) {
        assertThatThrownBy(() -> new PlayerName(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여자 이름에 공백을 입력할 수 없습니다.");
    }

    @DisplayName("이름이 공백이 아니면 생성된다.")
    @Test
    void create() {
        // given
        String name = "pobi";

        // when
        final PlayerName playerName = new PlayerName(name);

        // then
        assertThat(playerName.getName()).isEqualTo(name);
    }
}