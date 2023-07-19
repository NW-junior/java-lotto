package lotto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoCalculator;
import lotto.domain.LottoDrawer;
import lotto.model.Lotto;
import lotto.model.Reward;
import lotto.model.WinningLotto;
import lotto.view.UserInterface;

public class Application {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        LottoDrawer lottoDrawer = new LottoDrawer();
        LottoCalculator lottoCalculator = new LottoCalculator();

        BigDecimal investedCoin = ui.getMoneyInput();
        List<Lotto> drawnLotteries = lottoDrawer.draw(investedCoin);
        ui.printBoughtLotto(drawnLotteries);

        Lotto winningNumbers = new Lotto(ui.getLuckyNumbers());
        Integer bonusNumber = ui.getBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        List<Reward> rewardList = lottoDrawer.calculateReward(winningLotto, drawnLotteries);
        BigDecimal earningRate = lottoCalculator.calculateEarningRate(investedCoin, rewardList);
        Map<Reward, Integer> rewardInfoMap = lottoCalculator.getRewardInfos();
        
        ui.printResult(rewardInfoMap, earningRate);
    }
}
