package lotto.service;

import lotto.model.Lotto;
import lotto.model.LottoBuyingInfo;
import lotto.model.LottoWinningType;
import lotto.model.WinningLotto;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LottoWinningResultService {
    public static void start(List<Lotto> lottos, WinningLotto winningLotto, LottoBuyingInfo buyingInfo) {
        Map<LottoWinningType, Integer> lottoWinningResultMap =
                getLottoWinningResults(lottos, winningLotto);
        Double yieldPercent = calculateYieldPercent(lottoWinningResultMap, buyingInfo);
        OutputView.printLottoWinningStats(lottoWinningResultMap, yieldPercent);
    }

    public static Map<LottoWinningType, Integer> getLottoWinningResults(
            List<Lotto> lottos,
            WinningLotto winningLotto
    ) {
        Map<LottoWinningType, Integer> lottoWinningTypeResultMap = initializeLottoWinningResults();
        lottos.forEach(lotto -> {
            Integer luckyNumCount = calculateLuckyNumCount(lotto, winningLotto.getNumbers());
            Boolean bonusNumMatched = calculateBonusNumMatched(lotto, winningLotto.getBonusNum());
            LottoWinningType winningType = LottoWinningType.getLottoWinningType(luckyNumCount, bonusNumMatched);
            lottoWinningTypeResultMap.computeIfPresent(winningType, (key, value) -> value + 1);
        });
        return lottoWinningTypeResultMap;
    }


    public static Map<LottoWinningType, Integer> initializeLottoWinningResults() {
        Map<LottoWinningType, Integer> lottoWinningTypeResultMap = new TreeMap<>();
        for (LottoWinningType type : LottoWinningType.values()) {
            lottoWinningTypeResultMap.put(type, 0);
        }
        return lottoWinningTypeResultMap;
    }

    private static Integer calculateLuckyNumCount(Lotto lotto, List<Integer> luckyLottoNum) {
        List<Integer> lottoNums = lotto.getNumbers();
        List<Integer> intersection = new ArrayList<>(lottoNums);
        intersection.retainAll(luckyLottoNum);
        return intersection.size();
    }

    private static Boolean calculateBonusNumMatched(Lotto lotto, Integer bonusNum) {
        List<Integer> lottoNums = lotto.getNumbers();
        return lottoNums.contains(bonusNum);
    }

    private static Double calculateYieldPercent(
            Map<LottoWinningType, Integer> lottoWinningResultMap,
            LottoBuyingInfo buyingInfo
    ) {
        long totalPrice = 0L;
        for (LottoWinningType winningType : lottoWinningResultMap.keySet()) {
            if (winningType.getWinningPrice() > 0) {
                Integer count = lottoWinningResultMap.get(winningType);
                totalPrice = totalPrice + winningType.getWinningPrice() * count;
            }
        }
        return (double) totalPrice / (double) buyingInfo.getPrice() * 100;
    }

}
