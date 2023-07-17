package lotto.service;

import lotto.model.Lotto;
import lotto.model.LottoBuyingInfo;
import lotto.model.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoMainService {
    public static void start() {
        LottoBuyingInfo buyingInfo = inputLottoBuyingPrice();
        List<Lotto> lottos = LottoGenerateService.start(buyingInfo);
        WinningLotto winningLotto = getWinningLottoInfo();
        LottoWinningResultService.start(lottos, winningLotto, buyingInfo);
    }

    public static LottoBuyingInfo inputLottoBuyingPrice() {
        OutputView.printLottoBuyingPriceInput();
        Integer buyingPrice = InputView.getInt();
        return new LottoBuyingInfo(buyingPrice);
    }

    private static WinningLotto getWinningLottoInfo() {
        OutputView.printWinningLottoInput();
        List<Integer> luckyLottoNums = InputView.getIntegerList();
        OutputView.printWinningLottoBonusNumInput();
        Integer bonusNum = InputView.getInt();
        return new WinningLotto(luckyLottoNums, bonusNum);
    }
}
