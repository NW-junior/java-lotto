package lotto.domain;

import static lotto.common.LottoConstants.LOTTO_LAST_NUMBER;
import static lotto.common.LottoConstants.LOTTO_PRICE;
import static lotto.common.LottoConstants.LOTTO_SIZE;
import static lotto.common.LottoConstants.LOTTO_START_NUMBER;

import camp.nextstep.edu.missionutils.Randoms;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.model.Lotto;
import lotto.model.Reward;

public class LottoDrawer {

    private final List<Lotto> drawnLottoList = new ArrayList<>();

    public List<Lotto> draw(BigDecimal insertedCoin) {
        int certainTimes = Integer.parseInt(insertedCoin.divide(LOTTO_PRICE).toString());

        for (int i = 0; i < certainTimes; i++) {
            List<Integer> generatedNumber = Randoms.pickUniqueNumbersInRange(LOTTO_START_NUMBER, LOTTO_LAST_NUMBER,
                    LOTTO_SIZE);

            Lotto lotto = new Lotto(generatedNumber);

            drawnLottoList.add(lotto);
        }

        return drawnLottoList;
    }

    public List<Reward> scratch(List<Integer> luckyNumbers, Integer bonusNumber) {
        return drawnLottoList.stream()
                .map(lotto -> lotto.draw(luckyNumbers, bonusNumber))
                .collect(Collectors.toList());
    }
}
