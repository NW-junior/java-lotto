package lotto.view;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.Ranking;
import lotto.domain.WinningResult;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Money;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");

    private OutputView() {
    }

    public static void printBuyLottos(List<Lotto> lottos) {
        System.out.printf("\n%d개를 구매했습니다.%n", lottos.size());
        for (Lotto lotto : lottos) {
            String lottoResult = lotto.getNumbers().stream()
                .map(LottoNumber::getNumber)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
            System.out.println("[" + lottoResult + "]");
        }
    }

    public static void printResult(WinningResult winningResult, Money purchaseMoney) {
        StringBuilder totalResult = new StringBuilder();
        totalResult.append("당첨 통계\n---\n");

        List<Map.Entry<Ranking, Long>> results = getWinningEntryResults(winningResult);

        for (Map.Entry<Ranking, Long> result : results) {
            Ranking ranking = result.getKey();
            Long count = result.getValue();
            totalResult.append(createResultLine(ranking, count)).append('\n');
        }
        totalResult.append("총 수익률은 ")
            .append(String.format("%.1f", winningResult.getRateOfReturn(purchaseMoney)))
            .append("%입니다.");
        System.out.println(totalResult);
    }

    private static String createResultLine(Ranking ranking, Long count) {
        String bonusNumberResult = ranking.isContainsBonusNumber() ? ", 보너스 볼 일치" : "";
        return String.format("%d개 일치", ranking.getSameNumberCount()) +
            bonusNumberResult +
            String.format(" (%s원) - %d개", DECIMAL_FORMAT.format(ranking.getPrize()), count);
    }

    private static List<Map.Entry<Ranking, Long>> getWinningEntryResults(WinningResult winningResult) {
        Map<Ranking, Long> result = winningResult.getResult();
        return result.entrySet().stream()
            .sorted(Comparator.comparingInt(entry -> entry.getKey().getPrize()))
            .collect(Collectors.toList());
    }

    public static void printError(String errorMessage) {
        System.out.println(ERROR_PREFIX + errorMessage);
    }
}
