package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateDuplicate(numbers);
        validateSize(numbers);
        this.numbers = numbers;
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        Set<LottoNumber> nonDuplicateLottoNumbers = new HashSet<>(numbers);
        if (nonDuplicateLottoNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public boolean hasNumber(LottoNumber bonusNumber) {
        return numbers.stream()
            .anyMatch(lottoNumber -> lottoNumber.equals(bonusNumber));
    }

    // TODO: 추가 기능 구현
}
