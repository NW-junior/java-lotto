package lotto;

import lotto.model.LottoBuyingInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoBuyingInfoTest {
    @DisplayName("로또 구매 금액이 1000의 배수가 아니라면 예외가 발생한다.")
    @Test
    void createLottoBuyingInfoByUndividedPrice() {
        assertThatThrownBy(() -> new LottoBuyingInfo(8001))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 갯수가 맞는지 확인한다.")
    @Test
    void createLottoByOverSize() {
        Assertions.assertThat(new LottoBuyingInfo(8000).getLottoCount()).isEqualTo(8);
    }
}
