package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @DisplayName("로또를 생성한다.")
    @Test
    void createLotto() {
        // given
        List<LottoNumber> lottoNumbers = createLottoNumbers(List.of(1, 2, 3, 4, 5, 45));

        // when
        Lotto lotto = new Lotto(lottoNumbers);

        // then
        assertThat(lotto).isNotNull();
    }

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(createLottoNumbers(List.of(1, 2, 3, 4, 5, 6, 7))))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(createLottoNumbers(List.of(1, 2, 3, 4, 5, 5))))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 일치하는 갯수를 계산한다.")
    @Test
    void calculateSameNumberCount() {
        // given
        Lotto lotto = new Lotto(createLottoNumbers(List.of(1, 2, 3, 4, 5, 45)));
        Lotto anotherLotto = new Lotto(createLottoNumbers(List.of(1, 2, 3, 4, 5, 45)));

        // when
        int count = lotto.countSameNumber(anotherLotto);

        // then
        assertThat(count).isEqualTo(6);
    }

    @DisplayName("로또 번호가 일치하는 갯수를 계산한다.2")
    @Test
    void calculateSameNumberCount2() {
        // given
        Lotto lotto = new Lotto(createLottoNumbers(List.of(3, 5, 6, 8, 11, 45)));
        Lotto anotherLotto = new Lotto(createLottoNumbers(List.of(1, 2, 3, 4, 5, 45)));

        // when
        int count = lotto.countSameNumber(anotherLotto);

        // then
        assertThat(count).isEqualTo(3);
    }

    private List<LottoNumber> createLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
    }

    // 아래에 추가 테스트 작성 가능
}
