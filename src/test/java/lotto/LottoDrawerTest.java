package lotto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import lotto.domain.LottoDrawer;
import lotto.model.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoDrawerTest {
    @DisplayName("올바른 로또 번호를 정해진 개수 만큼 생성한다.")
    @Test
    void createCorrectLottoNUmberForCertainTimes() {
        BigDecimal insertedCoin = new BigDecimal(3000);
        int certainTimes = Integer.parseInt(insertedCoin.divide(new BigDecimal(1000)).toString());
        LottoDrawer lottoDrawer = new LottoDrawer();

        List<Lotto> lottoList = lottoDrawer.draw(insertedCoin);

        assertEquals(certainTimes, lottoList.size());
    }

}
