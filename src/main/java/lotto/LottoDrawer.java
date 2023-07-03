package lotto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.stream.Collectors;

public class LottoDrawer {

    private final List<Lotto> drawnLottoList = new ArrayList<>();

    public List<Lotto> draw(BigDecimal insertedCoin) {
        int certainTimes = Integer.parseInt(insertedCoin.divide(BigDecimal.valueOf(1000)).toString());

        for (int i = 0; i < certainTimes; i++) {
            List<Integer> generatedNumber = Randoms.pickUniqueNumbersInRange(1, 45, 6);

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
