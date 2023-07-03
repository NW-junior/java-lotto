package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoDrawerTest {
    @DisplayName("올바른 로또 번호를 정해진 개수 만큼 생성한다.")
    @Test
    void createCorrectLottoNUmberForCertainTimes() {
        int certainTimes = 3;
        LottoDrawer lottoDrawer = new LottoDrawer();

        List<Lotto> lottoList = lottoDrawer.draw(certainTimes);

        lottoList.forEach( lotto ->
                assertThat(lotto.validate())
        );
    }

}
