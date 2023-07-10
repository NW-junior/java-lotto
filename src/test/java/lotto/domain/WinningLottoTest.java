package lotto.domain;

import static lotto.domain.Ranking.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @DisplayName("로또와 보너스 번호를 받아 당첨 로또를 생성한다.")
    @Test
    void createLotto() {
        // given
        Lotto lottoNumbers = new Lotto(createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonusNumber = new LottoNumber(7);

        // when
        WinningLotto winningLotto = new WinningLotto(lottoNumbers, bonusNumber);

        // then
        assertThat(winningLotto).isNotNull();
    }

    @DisplayName("로또와 보너스 번호가 중복되면 예외를 발생한다.")
    @Test
    void createWinningLottoByDuplicateBonsNumber() {
        // given
        Lotto lottoNumbers = new Lotto(createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonusNumber = new LottoNumber(6);

        // when
        // then
        assertThatThrownBy(() -> new WinningLotto(lottoNumbers, bonusNumber))
            .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO : TC 케이스 더 추가해야함
    @DisplayName("로또와 보너스 번호를 받아 당첨 등수를 계산한다.")
    @Test
    void calculateRanking() {
        // given
        Lotto winningLottoNumbers = new Lotto(createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber winningBonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, winningBonusNumber);

        Lotto lottoNumbers = new Lotto(createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));

        // when
        Ranking ranking = winningLotto.calculateRanking(lottoNumbers);

        // then
        assertThat(ranking).isEqualTo(ONE);
    }

    private List<LottoNumber> createLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
    }

}
