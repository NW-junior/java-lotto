package lotto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoCalculatorTest {
    
    @DisplayName("투자금과 수익금에 따라 수익률을 올바르게 계산하는지 테스트")
    @Test
    void calculateEarningRate() {
        Long insertedCoin = 2000L;
        Lotto lottoA = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lottoB = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        List<Lotto> lottoList = List.of(lottoA, lottoB);
        LottoCalculator lottoCalculator = new LottoCalculator();
        List<Integer> luckyNumber = List.of(1, 2, 3, 10, 11, 12, 13);

        lottoA.scratch(luckyNumber);
        lottoB.scratch(luckyNumber);
        Double earningRate = lottoCalculator.calculate(insertedCoin, lottoList);

        Double expected = 500.0;
        assertEquals(earningRate, expected);
    }
}
