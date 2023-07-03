package lotto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoCalculatorTest {

    @DisplayName("투자금과 수익금에 따라 수익률을 올바르게 계산하는지 테스트")
    @Test
    void calculateEarningRate() {
        BigDecimal insertedCoin = new BigDecimal(2000);
        List<Reward> rewardList = List.of(Reward.FIFTH, Reward.FIFTH);
        LottoCalculator lottoCalculator = new LottoCalculator();

        BigDecimal earningRate = lottoCalculator.calculateEarningRate(insertedCoin, rewardList);

        BigDecimal expected = BigDecimal.valueOf(500.0);
        assertEquals(expected, earningRate);
    }
}
