package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import lotto.domain.vo.LottoNumber;

public class Lotto {
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final List<LottoNumber> CACHE_LOTTO_NUMBERS;

    static {
        CACHE_LOTTO_NUMBERS = IntStream.range(LottoNumber.MIN_NUMBER_VALUE, LottoNumber.MAX_NUMBER_VALUE)
            .mapToObj(LottoNumber::new)
            .collect(toList());
    }

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateDuplicate(numbers);
        validateSize(numbers);
        this.numbers = numbers;
    }

    public static Lotto publishRandomLotto() {
        Collections.shuffle(CACHE_LOTTO_NUMBERS);
        return new Lotto(CACHE_LOTTO_NUMBERS.subList(0, LOTTO_NUMBERS_SIZE));
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

}
