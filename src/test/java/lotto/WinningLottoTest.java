package lotto;

import lotto.model.Lotto;
import lotto.model.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.Constants.MAX_LOTTO_NUM;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {
    @DisplayName("당첨 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createWinningLottoByOverSize() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6, 7), 8))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createWinningLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 5), 8))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호와 보너스 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createWinningLottoByDuplicatedBonusNumber() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 범위 밖의 숫자가 있으면 예외가 발생한다.")
    @Test
    void createWinningLottoByNotInRangedNumber() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, MAX_LOTTO_NUM + 1), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @DisplayName("당첨 번호에 범위 밖의 숫자가 있으면 예외가 발생한다.")
    @Test
    void createWinningLottoByNotInRangedBonusNum() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), MAX_LOTTO_NUM + 1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
