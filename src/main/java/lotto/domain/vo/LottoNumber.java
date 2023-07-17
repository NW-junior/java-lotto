package lotto.domain.vo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;

public class LottoNumber {
    public static final int MIN_NUMBER_VALUE = 1;
    public static final int MAX_NUMBER_VALUE = 45;

    private final Integer number;

    public LottoNumber(Integer number) {
        validate(number);
        this.number = number;
    }

    public static List<LottoNumber> createRandomNumbers(int size) {
        return Randoms.pickUniqueNumbersInRange(LottoNumber.MIN_NUMBER_VALUE,
                LottoNumber.MAX_NUMBER_VALUE, size).stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
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

    public Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber)o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
