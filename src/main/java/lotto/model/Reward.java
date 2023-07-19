package lotto.model;

import java.math.BigDecimal;
import java.util.Arrays;

public enum Reward {
    FIRST(6, new BigDecimal("2000000000"), false),
    SECOND(5, new BigDecimal("30000000"), true),
    THIRD(5, new BigDecimal("1500000"), false),
    FOURTH(4, new BigDecimal("50000"), false),
    FIFTH(3, new BigDecimal("5000"), false),
    FAIL(0, new BigDecimal("0"), false);;
    private int correctCount;
    private BigDecimal prizeWon;
    private boolean bonus;

    Reward(int correctCount, BigDecimal prizeWon, boolean bonus) {
        this.correctCount = correctCount;
        this.prizeWon = prizeWon;
        this.bonus = bonus;
    }

    public static Reward getRewardByCorrectCount(int correctCount, boolean hasBonus) {
        if (hasBonus && correctCount == 5) {
            return SECOND;
        } else {
            return Arrays.stream(Reward.values())
                    .filter(reward -> reward.correctCount == correctCount)
                    .findAny()
                    .orElse(FAIL);
        }
    }

    public boolean isDisplayed() {
        return this != FAIL;
    }

    public BigDecimal getPrizeWon() {
        return prizeWon;
    }
}
