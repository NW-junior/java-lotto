package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WinningResult {

    public final Map<Ranking, Long> result;

    public WinningResult(List<Ranking> rankings) {
        this.result = rankings.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public long getTotalPrize() {
        return this.result.entrySet().stream()
            .mapToLong(entry -> entry.getKey().calculatePrize(entry.getValue()))
            .sum();
    }

    public Map<Ranking, Long> getResult() {
        return this.result;
    }
}
