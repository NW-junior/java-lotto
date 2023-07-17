package lotto.view;

import lotto.model.Lotto;
import lotto.model.LottoWinningType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String LOTTO_BUYING_PRICE_INPUT = "구입금액을 입력해 주세요.";
    private static final String BOUGHT_LOTTO_COUNT_PRINT_FORMAT = "%d개를 구매했습니다.\n";
    private static final String BOUGHT_LOTTO_PRINT_DELIMITER = ", ";
    private static final String BOUGHT_LOTTO_PRINT_FORMAT = "[%s]\n";

    private static final String WINNING_LOTTO_INPUT = "당첨 번호를 입력해 주세요.";

    private static final String WINNING_LOTTO_BONUS_NUM_INPUT = "보너스 번호를 입력해 주세요.";

    private static final String LOTTO_WINNING_STATS_HEADER = "당첨 통계";
    private static final String LOTTO_WINNING_STATS_FORMAT = "%s (%,d원) - %d개\n";
    private static final String LOTTO_WINNING_STATS_SUMMARY_FORMAT = "총 수익률은 %.1f%%입니다.";


    private static final String DASH = "---";

    public static void printLottoBuyingPriceInput() {
        println(LOTTO_BUYING_PRICE_INPUT);
    }

    public static void printLottos(List<Lotto> lottoList) {
        printEnter();
        printBoughtLottoCount(lottoList.size());
        for (Lotto lotto : lottoList) {
            printLotto(lotto);
        }
    }

    private static void printBoughtLottoCount(Integer lottoCount) {
        printf(BOUGHT_LOTTO_COUNT_PRINT_FORMAT, lottoCount);
    }

    public static void printWinningLottoInput() {
        printEnter();
        println(WINNING_LOTTO_INPUT);
    }

    public static void printWinningLottoBonusNumInput() {
        printEnter();
        println(WINNING_LOTTO_BONUS_NUM_INPUT);
    }

    public static void printLottoWinningStats(
            Map<LottoWinningType, Integer> lottoWinningResultMap,
            Double yieldPercent
    ) {
        printEnter();
        println(LOTTO_WINNING_STATS_HEADER);
        println(DASH);
        printLottoWinningResultMap(lottoWinningResultMap);
        printf(LOTTO_WINNING_STATS_SUMMARY_FORMAT, yieldPercent);
    }

    private static void printLottoWinningResultMap(Map<LottoWinningType, Integer> lottoWinningResultMap) {
        for (var winningTypeAndCount : lottoWinningResultMap.entrySet()) {
            LottoWinningType winningType = winningTypeAndCount.getKey();
            if (winningType.getWinningPrice() <= 0) continue;
            Integer count = winningTypeAndCount.getValue();
            printf(LOTTO_WINNING_STATS_FORMAT,
                    winningType.getDisplayName(), winningType.getWinningPrice(), count
            );
        }
    }

    private static void printLotto(Lotto lotto) {
        String seperatedLottoNums = lotto.getNumbers().stream()
                .map(Object::toString)
                .collect(Collectors.joining(BOUGHT_LOTTO_PRINT_DELIMITER));
        printf(BOUGHT_LOTTO_PRINT_FORMAT, seperatedLottoNums);
    }

    public static void printEnter() {
        println("");
    }

    public static void printError(String message) {
        println(ERROR_PREFIX + message);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }
}
