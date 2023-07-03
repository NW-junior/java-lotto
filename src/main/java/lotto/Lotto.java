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

    public Reward draw(List<Integer> luckyNumber, Integer bonusNumber) {
        int correctCount = Math.toIntExact(numbers.stream()
                .filter(luckyNumber::contains)
                .count());
        boolean hasBonus = numbers.contains(bonusNumber);

        return Reward.getRewardByCorrectCount(correctCount, hasBonus);
    }

    @Override
    public String toString() {
        StringBuilder lotteryPrinter = new StringBuilder();
        List<String> stringNumberList = numbers.stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        lotteryPrinter.append("[");

        lotteryPrinter.append(String.join(", ", stringNumberList));

        lotteryPrinter.append("]");

        return lotteryPrinter.toString();
    }
}
