package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}
