package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lotto.domain.vo.Money;

public class WinningResult {

    public final Map<Ranking, Long> result;

    public WinningResult(List<Ranking> rankings) {
        Map<Ranking, Long> rankingMap = rankings.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        this.result = Arrays.stream(Ranking.values())
            .filter(Ranking::isNotNone)
            .collect(Collectors.toMap(Function.identity(), ranking -> rankingMap.getOrDefault(ranking, 0L)));
    }

    public double getRateOfReturn(Money purchaseMoney) {
        long totalPrize = calculateTotalPrize();
        return purchaseMoney.calculateRateOfReturn(totalPrize);
    }

    long calculateTotalPrize() {
        return this.result.entrySet().stream()
            .filter(entry -> entry.getValue() > 0)
            .mapToLong(entry -> entry.getKey().calculatePrize(entry.getValue()))
            .sum();
    }

    public Map<Ranking, Long> getResult() {
        return Map.copyOf(this.result);
    }
}
