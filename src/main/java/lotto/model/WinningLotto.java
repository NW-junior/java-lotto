package lotto.model;

import lotto.utils.LottoNumRangeValidator;

import java.util.List;

import static lotto.model.ErrorCode.ERR_BONUS_NUM_DUPLICATED;

public class WinningLotto {
    private final Lotto lotto;
    private final Integer bonusNum;

    public WinningLotto(List<Integer> numbers, Integer bonusNum) {
        this.lotto = new Lotto(numbers);
        validateBonusNum(numbers, bonusNum);
        this.bonusNum = bonusNum;
    }

    private void validateBonusNum(List<Integer> numbers, Integer bonusNum) {
        LottoNumRangeValidator.validate(bonusNum);
        if(numbers.contains(bonusNum)) {
            throw new IllegalArgumentException(ERR_BONUS_NUM_DUPLICATED);
        }
    }

    public List<Integer> getNumbers() {
        return lotto.getNumbers();
    }
    public Integer getBonusNum() { return bonusNum; }
}
