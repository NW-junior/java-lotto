package lotto.model;

import lotto.utils.LottoNumRangeValidator;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.Constants.LOTTO_NUM_SIZE;
import static lotto.model.ErrorCode.ERR_LOTTO_NUM_DUPLICATED;
import static lotto.model.ErrorCode.ERR_LOTTO_SIZE_MISMATCH;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoNumCount(numbers);
        validateLottoNumInRange(numbers);
        validateLottoNumNotDuplicated(numbers);
        this.numbers = numbers.stream().sorted().collect(Collectors.toList());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validateLottoNumCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUM_SIZE) {
            throw new IllegalArgumentException(ERR_LOTTO_SIZE_MISMATCH);
        }
    }

    private void validateLottoNumInRange(List<Integer> numbers) {
        numbers.forEach(LottoNumRangeValidator::validate);
    }

    private void validateLottoNumNotDuplicated(List<Integer> numbers) {
        List<Integer> distinctNumbers =
                numbers.stream().distinct().collect(Collectors.toList());
        if (distinctNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ERR_LOTTO_NUM_DUPLICATED);
        }
    }
}
