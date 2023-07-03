package lotto;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }

        List<Integer> distinctList = numbers.stream().distinct().collect(Collectors.toList());

        if (numbers.size() != distinctList.size()) {
            throw new IllegalArgumentException();
        }
    }

    public Reward draw(List<Integer> luckyNumber) {
        List<Integer> onlyNumber = luckyNumber.subList(0, 6);
        Integer bonusNUmber = luckyNumber.get(6);

        int correctCount = Math.toIntExact(numbers.stream()
                .filter(onlyNumber::contains)
                .count());
        boolean hasBonus = numbers.contains(bonusNUmber);

        return Reward.getRewardByCorrectCount(correctCount, hasBonus);
    }
}
