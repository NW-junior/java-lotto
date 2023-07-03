package lotto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoCalculator {

    private Map<Reward, Integer> rewardInfos;

    public Map<Reward, Integer> getRewardInfos() {
        return rewardInfos;
    }

    public BigDecimal calculateEarningRate(BigDecimal investedCoin, List<Reward> rewardList) {
        rewardInfos = rewardList.stream()
                .filter(Reward::isDisplayed)
                .collect(Collectors.groupingBy(e -> e, Collectors.summingInt(e -> 1)));

        for (Reward r : Reward.values()) {
            rewardInfos.putIfAbsent(r, 0);
        }

        BigDecimal rewardedCoin = rewardList.stream()
                .map(Reward::getPrizeWon)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return rewardedCoin.multiply(new BigDecimal(100))
                .divide(investedCoin, 1, RoundingMode.HALF_UP);
    }
}
