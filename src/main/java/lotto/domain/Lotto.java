package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.domain.vo.LottoNumber;

public class Lotto {
    private static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateDuplicate(numbers);
        validateSize(numbers);
        this.numbers = numbers;
    }

    // 랜덤 번호 뽑는거 인터페이스로 뽑는게 좀 더 유연할 것 같음...
    // static이라 불가능해 보이는데 밖에서 그냥 주입해서 하는게 더 좋을려나?
    // 테스트가 힘들어보임!
    public static Lotto publishRandomLotto() {
        return new Lotto(LottoNumber.createRandomNumbers(LOTTO_NUMBERS_SIZE));
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

    public boolean containsNumber(LottoNumber bonusNumber) {
        return numbers.stream()
            .anyMatch(lottoNumber -> lottoNumber.equals(bonusNumber));
    }

    public int countSameNumber(Lotto anotherLotto) {
        return (int)this.numbers.stream()
            .filter(anotherLotto::containsNumber)
            .count();
    }

    public List<LottoNumber> getNumbers() {
        return List.copyOf(numbers);
    }
}
