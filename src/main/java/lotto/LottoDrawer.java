package lotto;

import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;

public class LottoDrawer {

    private final List<Lotto> drawnLottoList = new ArrayList<>();


    public List<Lotto> draw(int certainTimes) {
        for (int i = 0; i < certainTimes; i++) {
            List<Integer> generatedNumber = Randoms.pickUniqueNumbersInRange(1, 45, 6);

            Lotto lotto = new Lotto(generatedNumber);

            drawnLottoList.add(lotto);
        }

        return drawnLottoList;
    }
}
