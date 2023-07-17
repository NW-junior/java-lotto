package lotto.model;

import static lotto.Constants.*;

public class ErrorCode {
    // input ErrorCodes
    public static final String ERR_NOT_INTEGER = "입력값은 정수값이여야 합니다.";
    public static final String ERR_FAILED_TO_CONVERT_INTEGER_LIST = "입력값을 정수 배열로 받아들일수 없습니다.";

    // logical ErrorCodes
    public static final String ERR_NOT_LOTTO_BUYING_PRICE_DIVIDABLE = String.format(
            "로또 구입 금액은 로또 가격인 %d로 나눠 떨어져야합니다.", LOTTO_PRICE
    );
    public static final String ERR_NOT_LOTTO_NUM_RANGE = String.format(
            "로또 번호는 %d부터 %d 사이의 숫자여야 합니다.", MIN_LOTTO_NUM, MAX_LOTTO_NUM
    );

    public static final String ERR_BONUS_NUM_DUPLICATED = "보너스 번호가 당첨 번호와 중복됩니다.";

    public static final String ERR_LOTTO_NUM_DUPLICATED = "당첨 번호 내에 중복되는 숫자는 없어야합니다.";

    public static final String ERR_LOTTO_SIZE_MISMATCH = String.format(
            "로또 번호 갯수는 %d 개여야 합니다.", LOTTO_NUM_SIZE
    );
}
