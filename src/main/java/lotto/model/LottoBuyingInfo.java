package lotto.model;

import static lotto.Constants.LOTTO_PRICE;
import static lotto.model.ErrorCode.ERR_NOT_LOTTO_BUYING_PRICE_DIVIDABLE;

public class LottoBuyingInfo {
    private final Integer price;
    private final Integer lottoCount;

    public LottoBuyingInfo(Integer price) {
        validatePriceDividable(price);
        this.price = price;
        this.lottoCount = price / LOTTO_PRICE;
    }

    private void validatePriceDividable(Integer price) {
        if (price % LOTTO_PRICE > 0) {
            throw new IllegalArgumentException(ERR_NOT_LOTTO_BUYING_PRICE_DIVIDABLE);
        }
    }

    public Integer getPrice() { return price; }
    public Integer getLottoCount() { return lottoCount; }
}
