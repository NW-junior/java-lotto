package lotto.domain;

import java.util.Arrays;

enum Ranking {
    ONE(2_000_000_000, 6, false),
    TWO(30_000_000, 5, true),
    THREE(1_500_000, 5, false),
    FOUR(50_000, 4, false),
    FIVE(5_000, 3, false),
    NONE(0, 0, false);

    public static final long MIN_COUNT_VALUE = 0L;

    private final int prize;
    private final int sameNumberCount;
    private final boolean containsBonusNumber;

    Ranking(int prize, int sameNumberCount, boolean containsBonusNumber) {
        this.prize = prize;
        this.sameNumberCount = sameNumberCount;
        this.containsBonusNumber = containsBonusNumber;
    }

    public static Ranking calculate(int sameNumberCount, boolean containsBonusNumber) {
        return Arrays.stream(Ranking.values())
            .filter(ranking -> ranking.sameNumberCount == sameNumberCount
                && ranking.containsBonusNumber == containsBonusNumber)
            .findAny()
            .orElse(Ranking.NONE);
    }

    private static void validateCount(Long count) {
        if (count == null || count < MIN_COUNT_VALUE) {
            throw new IllegalArgumentException(String.format("갯수는 비어있거나 %d 이상이여야 합니다.", MIN_COUNT_VALUE));
        }
    }

    public long calculatePrize(Long count) {
        validateCount(count);
        return this.prize * count;
    }

}
