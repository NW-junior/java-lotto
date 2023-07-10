package lotto.domain.vo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("금액을 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 10000})
    void createMoney(int value) {
        // given
        // when
        Money money = new Money(value);

        // then
        assertThat(money).isNotNull();
    }

    @DisplayName("금액이 최소 금액보다 작을 경우 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 999, -1, -20})
    void createMoneyWithNotPositive(int value) {
        assertThatThrownBy(() -> new Money(value))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("금액이 알맞은 단위가 아닐 경우 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 100, 1500, 12050})
    void createMoneyWithInvalidMoneyUnit(int value) {
        assertThatThrownBy(() -> new Money(value))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력한 금액에 맞는 로또 갯수를 계산한다.")
    @Test
    void calculateLottoCount() {
        // given
        Money money = new Money(5000);

        // when
        int count = money.calculateLottoCount();

        // then
        assertThat(count).isEqualTo(5);
    }

}
