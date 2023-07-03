package lotto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        LottoDrawer lottoDrawer = new LottoDrawer();
        LottoCalculator lottoCalculator = new LottoCalculator();

        BigDecimal investedCoin = ui.getMoneyInput();
        List<Lotto> lottoList = lottoDrawer.draw(investedCoin);
        ui.printBoughtLotto(lottoList);

        List<Integer> luckyNumbers = ui.getLuckyNumbers();
        Integer bonusNumber = ui.getBonusNumber();
        List<Reward> rewardList = lottoDrawer.scratch(luckyNumbers, bonusNumber);

        BigDecimal earningRate = lottoCalculator.calculateEarningRate(investedCoin, rewardList);
        Map<Reward, Integer> rewardInfoMap = lottoCalculator.getRewardInfos();
        ui.printResult(rewardInfoMap, earningRate);
    }
}
