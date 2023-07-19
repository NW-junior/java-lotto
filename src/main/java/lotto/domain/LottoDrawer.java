package lotto.domain;

import static lotto.common.LottoConstants.LOTTO_LAST_NUMBER;
import static lotto.common.LottoConstants.LOTTO_PRICE;
import static lotto.common.LottoConstants.LOTTO_SIZE;
import static lotto.common.LottoConstants.LOTTO_START_NUMBER;

import camp.nextstep.edu.missionutils.Randoms;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lotto.model.Lotto;
import lotto.model.Reward;
import lotto.model.WinningLotto;

public class LottoDrawer {

    public List<Lotto> draw(BigDecimal insertedCoin) {
        int certainTimes = Integer.parseInt(insertedCoin.divide(LOTTO_PRICE).toString());
        List<Lotto> drawnLottoList = new ArrayList<>();

        for (int i = 0; i < certainTimes; i++) {
            List<Integer> generatedNumber = Randoms
                    .pickUniqueNumbersInRange(LOTTO_START_NUMBER, LOTTO_LAST_NUMBER, LOTTO_SIZE);

            Lotto lotto = new Lotto(generatedNumber);

            drawnLottoList.add(lotto);
        }

        return drawnLottoList;
    }

    public List<Reward> calculateReward(WinningLotto winningLotto, List<Lotto> drawnLotteries) {
        return winningLotto.calculateReward(drawnLotteries);
    }
}
