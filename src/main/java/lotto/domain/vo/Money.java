package lotto.domain.vo;

public class Money {
    private static final long MIN_MONEY_VALUE = 1_000L;
    private static final long VALID_SUBTRACT_VALUE = 0L;
    private static final long PERCENT_VALUE = 100;

    private final long value;

    public Money(long value) {
        validateValue(value);
        this.value = value;
    }

    private void validateValue(long value) {
        if (value < MIN_MONEY_VALUE) {
            throw new IllegalArgumentException(
                String.format("구입 금액은 %d원 이상 이여야 합니다. 입력 금액 : %d", MIN_MONEY_VALUE, value));
        }

        if (value % MIN_MONEY_VALUE != VALID_SUBTRACT_VALUE) {
            throw new IllegalArgumentException(
                String.format("구입 금액은 %d원 단위여야 합니다. 입력 금액 : %s ", MIN_MONEY_VALUE, value));
        }
    }

    public int calculateLottoCount() {
        return (int)(this.value / MIN_MONEY_VALUE);
    }

    public double calculateRateOfReturn(long value) {
        return (double)value / this.value * PERCENT_VALUE;
    }

}
