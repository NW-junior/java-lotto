package lotto.utils;

import static lotto.Constants.MAX_LOTTO_NUM;
import static lotto.Constants.MIN_LOTTO_NUM;
import static lotto.model.ErrorCode.ERR_NOT_LOTTO_NUM_RANGE;

public class LottoNumRangeValidator {
    public static void validate(Integer lottoNum) {
        if (lottoNum < MIN_LOTTO_NUM || lottoNum > MAX_LOTTO_NUM) {
            throw new IllegalArgumentException(ERR_NOT_LOTTO_NUM_RANGE);
        }
    }
}
