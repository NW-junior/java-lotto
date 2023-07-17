package lotto.domain;

import static lotto.domain.Ranking.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.vo.LottoNumber;

class LottoTicketTest {

    @DisplayName("입력받은 숫자만큼 로또를 발행한다")
    @Test
    void publishLottoTicket() {
        // given
        int count = 5;

        // when
        LottoTicket lottoTicket = LottoTicket.publishLottoTicket(count);

        // then
        assertThat(lottoTicket.getValue()).hasSize(count)
            .doesNotHaveDuplicates();
    }

    @DisplayName("로또들과 우승로또를 비교하여 당첨 결과를 반환한다.")
    @Test
    void calculateRankings() {
        // given
        Lotto winningLottoNumbers = new Lotto(createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber winningBonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, winningBonusNumber);

        LottoTicket lottoTicket = new LottoTicket(List.of(
            new Lotto(createLottoNumbers(List.of(1, 2, 3, 4, 5, 6))),
            new Lotto(createLottoNumbers(List.of(1, 2, 3, 4, 5, 7))),
            new Lotto(createLottoNumbers(List.of(1, 2, 3, 4, 5, 8)))
        ));

        // when
        List<Ranking> rankings = lottoTicket.calculateRankings(winningLotto);

        // then
        assertThat(rankings).hasSize(3)
            .containsExactly(ONE, TWO, THREE);
    }

    private List<LottoNumber> createLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
    }

}
