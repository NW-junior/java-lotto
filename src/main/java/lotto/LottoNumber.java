package lotto;

public class LottoNumber {
    private static final int MIN_NUMBER_VALUE = 1;
    private static final int MAX_NUMBER_VALUE = 45;

    private final Integer number;

    public LottoNumber(Integer number) {
        validate(number);
        this.number = number;
    }

    private void validate(Integer number) {
        if (number == null) {
            throw new IllegalArgumentException("번호는 비어있을 수 없습니다.");
        }

        if (isInValidRangeNumber(number)) {
            throw new IllegalArgumentException(
                String.format("번호는 %s 이상 %s이하만 가능합니다.", MIN_NUMBER_VALUE, MAX_NUMBER_VALUE));
        }
    }

    private boolean isInValidRangeNumber(Integer number) {
        return MIN_NUMBER_VALUE > number || number > MAX_NUMBER_VALUE;
    }
}
