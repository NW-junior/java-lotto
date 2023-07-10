package lotto.domain.vo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @DisplayName("로또 번호를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 35, 45})
    void createLottoNumber(Integer number) {
        // when
        LottoNumber lottoNumber = new LottoNumber(number);

        // then
        assertThat(lottoNumber).isNotNull();
    }

    @DisplayName("로또 번호가 null이면 예외를 발생한다.")
    @ParameterizedTest
    @NullSource
    void createLottoNumberByNullNumber(Integer number) {
        assertThatThrownBy(() -> new LottoNumber(number))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 알맞은 범위가 아니면 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 48})
    void createLottoNumberByInvalidRangeNumber(Integer number) {
        assertThatThrownBy(() -> new LottoNumber(number))
            .isInstanceOf(IllegalArgumentException.class);
    }

}
