package lotto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class LottoCalculator {
    public BigDecimal calculateEarningRate(Long insertedCoin, List<Reward> rewardList) {
        BigDecimal rewardedCoin = rewardList.stream()
                .map(Reward::getPrizeWon)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal investedCoin = new BigDecimal(insertedCoin);
        BigDecimal earningRate = rewardedCoin.divide(investedCoin, 1, RoundingMode.HALF_UP);

        return earningRate.multiply(new BigDecimal(100));
    }
}
