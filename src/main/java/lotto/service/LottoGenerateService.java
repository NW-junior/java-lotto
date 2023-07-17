package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.model.Lotto;
import lotto.model.LottoBuyingInfo;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerateService {
    public static List<Lotto> start(LottoBuyingInfo buyingInfo) {
        List<Lotto> lottos = getLottos(buyingInfo.getLottoCount());
        OutputView.printLottos(lottos);
        return lottos;
    }

    public static List<Lotto> getLottos(Integer size) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            lottos.add(getLotto());
        }
        return lottos;
    }

    public static Lotto getLotto() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
    }
}
