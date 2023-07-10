package lotto.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

enum Ranking {
    ONE(2_000_000_000, (sameNumberCount, hasBonusNumber) -> sameNumberCount == 6),
    TWO(30_000_000, (sameNumberCount, hasBonusNumber) -> sameNumberCount == 5 && hasBonusNumber),
    THREE(1_500_000, (sameNumberCount, hasBonusNumber) -> sameNumberCount == 5 && !hasBonusNumber),
    FOUR(50_000, (sameNumberCount, hasBonusNumber) -> sameNumberCount == 4),
    FIVE(5_000, (sameNumberCount, hasBonusNumber) -> sameNumberCount == 3),
    NONE(0, (sameNumberCount, hasBonusNumber) -> sameNumberCount < 3);

    private final int prize;
    private final BiPredicate<Integer, Boolean> checkRanking;

    Ranking(int prize, BiPredicate<Integer, Boolean> rankPredicate) {
        this.prize = prize;
        this.checkRanking = rankPredicate;
    }

    public static Ranking calculate(int sameNumberCount, boolean hasSameBonusNumber) {
        return Arrays.stream(Ranking.values())
            .filter(ranking -> ranking.checkRanking.test(sameNumberCount, hasSameBonusNumber))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("랭킹을 구할 수 없습니다."));
    }
}
