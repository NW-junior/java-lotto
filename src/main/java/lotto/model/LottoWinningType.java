package lotto.model;

public enum LottoWinningType {
    NONE("미당첨", 0L),
    MATCH_3("3개 일치", 5000L),
    MATCH_4("4개 일치", 50000L),
    MATCH_5("5개 일치", 1500000L),
    MATCH_5_WITH_BONUS("5개 일치, 보너스 볼 일치", 30000000L),
    MATCH_6("6개 일치", 2000000000L),
    ;

    private final String displayName;
    private final Long winningPrice;

    LottoWinningType(String displayName, Long winningPrice) {
        this.displayName = displayName;
        this.winningPrice = winningPrice;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Long getWinningPrice() {
        return winningPrice;
    }

    public static LottoWinningType getLottoWinningType(Integer luckyNumCount, Boolean bonusNumMatched) {
        if (luckyNumCount == 3) return MATCH_3;
        if (luckyNumCount == 4) return MATCH_4;
        if (luckyNumCount == 5) {
            if (bonusNumMatched) return MATCH_5_WITH_BONUS;
            return MATCH_5;
        }
        if (luckyNumCount == 6) return MATCH_6;
        return NONE;
    }
}
