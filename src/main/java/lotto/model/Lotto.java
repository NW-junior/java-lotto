package lotto.model;

import static lotto.common.LottoConstants.LOTTO_LAST_NUMBER;
import static lotto.common.LottoConstants.LOTTO_SIZE;
import static lotto.common.LottoConstants.LOTTO_START_NUMBER;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public Reward draw(List<Integer> luckyNumber, Integer bonusNumber) {
        int correctCount = Math.toIntExact(numbers.stream()
                .filter(luckyNumber::contains)
                .count());
        boolean hasBonus = numbers.contains(bonusNumber);

        return Reward.getRewardByCorrectCount(correctCount, hasBonus);
    }

    private void validate(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateWrongNumber(numbers);
        validateDuplicateNumber(numbers);
    }


    public void validateAlreadyExist(Integer bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicateNumber(List<Integer> numbers) {
        List<Integer> districtList = numbers.stream().distinct().collect(Collectors.toList());

        if (districtList.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호에는 중복된 수가 있을 수 없습니다.");
        }
    }

    private void validateWrongNumber(List<Integer> numbers) {
        boolean wrongNumberExist = numbers.stream().anyMatch(it -> it < LOTTO_START_NUMBER || it > LOTTO_LAST_NUMBER);

        if (wrongNumberExist) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1과 45 사이의 수만 올 수 있습니다.");
        }
    }
}
