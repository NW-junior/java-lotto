package lotto.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

enum Ranking {
    ONE((sameNumberCount, hasBonusNumber) -> sameNumberCount == 6),
    TWO((sameNumberCount, hasBonusNumber) -> sameNumberCount == 5 && hasBonusNumber),
    THREE((sameNumberCount, hasBonusNumber) -> sameNumberCount == 5 && !hasBonusNumber),
    FOUR((sameNumberCount, hasBonusNumber) -> sameNumberCount == 4),
    FIVE((sameNumberCount, hasBonusNumber) -> sameNumberCount == 3),
    NONE((sameNumberCount, hasBonusNumber) -> sameNumberCount < 3);

    private final BiPredicate<Integer, Boolean> checkRanking;

    Ranking(BiPredicate<Integer, Boolean> rankPredicate) {
        this.checkRanking = rankPredicate;
    }

    public static Ranking calculate(int sameNumberCount, boolean hasSameBonusNumber) {
        return Arrays.stream(Ranking.values())
            .filter(ranking -> ranking.checkRanking.test(sameNumberCount, hasSameBonusNumber))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("랭킹을 구할 수 없습니다."));
    }
}
