package lotto;

import static lotto.LottoConstants.LOTTO_LAST_NUMBER;
import static lotto.LottoConstants.LOTTO_PRICE;
import static lotto.LottoConstants.LOTTO_SIZE;
import static lotto.LottoConstants.LOTTO_START_NUMBER;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.stream.Collectors;

public class LottoDrawer {

    private final List<Lotto> drawnLottoList = new ArrayList<>();

    public List<Lotto> draw(BigDecimal insertedCoin) {
        int certainTimes = Integer.parseInt(insertedCoin.divide(LOTTO_PRICE).toString());

        for (int i = 0; i < certainTimes; i++) {
            List<Integer> generatedNumber = Randoms.pickUniqueNumbersInRange(LOTTO_START_NUMBER, LOTTO_LAST_NUMBER, LOTTO_SIZE);

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
