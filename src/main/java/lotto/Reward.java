package lotto;

import java.math.BigDecimal;
import java.util.Arrays;

public enum Reward {
    FIRST(6, new BigDecimal("2000000000"), "2,000,000,000", false),
    SECOND(5, new BigDecimal("30000000"), "30,000,000", true),
    THIRD(5, new BigDecimal("1500000"), "1,500,000", false),
    FOURTH(4, new BigDecimal("50000"), "50,000", false),
    FIFTH(3, new BigDecimal("5000"), "5,000", false),
    FAIL(0, new BigDecimal("0"), "0", false);
    ;
    private int correctCount;
    private BigDecimal prizeWon;
    private String displayMoney;
    private boolean bonus;

    Reward(int correctCount, BigDecimal prizeWon, String displayMoney, boolean bonus) {
        this.correctCount = correctCount;
        this.prizeWon = prizeWon;
        this.displayMoney = displayMoney;
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


    @Override
    public String toString() {
        if (bonus) {
            return String.format("%d개 일치, 보너스 볼 일치 (%s원)", correctCount, displayMoney);
        }
        return String.format("%d개 일치 (%s원)", correctCount, displayMoney);
    }
}
