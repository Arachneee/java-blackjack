package blackjack.domain.player;

import blackjack.exception.NeedRetryException;
import java.util.Objects;

public class PlayerName {

    private final String name;

    public PlayerName(final String input) {
        validateBlank(input);
        this.name = input.trim();
    }

    private void validateBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new NeedRetryException("참여자 이름에 공백을 입력할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PlayerName that = (PlayerName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
