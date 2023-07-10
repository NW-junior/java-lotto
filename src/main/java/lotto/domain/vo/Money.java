package lotto.domain.vo;

public class Money {
    private static final int MIN_MONEY_VALUE = 1_000;
    private static final int VALID_SUBTRACT_VALUE = 0;

    private final int value;

    public Money(int value) {
        validateValue(value);
        this.value = value;
    }

    private void validateValue(int value) {
        if (value < MIN_MONEY_VALUE) {
            throw new IllegalArgumentException(
                String.format("구입 금액은 %d원 이상 이여야 합니다. 입력 금액 : %d", MIN_MONEY_VALUE, value));
        }

        if (value % MIN_MONEY_VALUE != VALID_SUBTRACT_VALUE) {
            throw new IllegalArgumentException(
                String.format("구입 금액은 %d원 단위여야 합니다. 입력 금액 : %s ", MIN_MONEY_VALUE, value));
        }
    }
}
