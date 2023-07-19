package lotto.view;

import static lotto.common.LottoConstants.LOTTO_INFO_DELIMITER;
import static lotto.common.LottoConstants.LOTTO_INFO_END_CHAR;
import static lotto.common.LottoConstants.LOTTO_INFO_START_CHAR;
import static lotto.common.LottoConstants.LOTTO_PRICE;

import camp.nextstep.edu.missionutils.Console;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.model.Lotto;
import lotto.model.Reward;

public class UserInterface {

    public BigDecimal getMoneyInput() {
        System.out.println("구입금액을 입력해 주세요.");

        String moneyInput = Console.readLine();

        BigDecimal investedCoin = parseInputToMoney(moneyInput);

        validateInvestedCoin(investedCoin);

        return investedCoin;
    }

    public List<Integer> getLuckyNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");

        String numberInput = Console.readLine();

        return parseInputToInteger(List.of(numberInput.split(",")));
    }

    public Integer getBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");

        String bonusInput = Console.readLine();

        return parseInputToInteger(List.of(bonusInput)).get(0);
    }

    public void printBoughtLotto(List<Lotto> lottoList) {
        System.out.printf("%n%s개를 구매했습니다.%n%n", lottoList.size());
        StringBuilder lotteryPrinter = new StringBuilder();

        for (Lotto lotto : lottoList) {
            List<String> stringNumberList = lotto.getNumbersByString();

            lotteryPrinter.append(LOTTO_INFO_START_CHAR);
            lotteryPrinter.append(String.join(LOTTO_INFO_DELIMITER, stringNumberList));
            lotteryPrinter.append(LOTTO_INFO_END_CHAR);

            System.out.println(lotteryPrinter);
            lotteryPrinter.delete(0, lotteryPrinter.length());
        }
    }

    public void printResult(Map<Reward, Integer> rewardInfo, BigDecimal earningRate) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        rewardInfo.keySet().forEach(
                reward -> System.out.printf("%s - %d개%n", reward, rewardInfo.get(reward))
        );
        System.out.printf("총 수익률은 %s%%입니다.%n", earningRate);
    }

    private BigDecimal parseInputToMoney(String moneyInput) {
        try {
            return new BigDecimal(moneyInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 입력 금액은 숫자여야 합니다.");
        }
    }

    private List<Integer> parseInputToInteger(List<String> numberInputList) {
        try {
            return numberInputList.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 행운 번호는 숫자여야 합니다.");
        }
    }

    private void validateInvestedCoin(BigDecimal investedCoin) {
        BigDecimal reminder = investedCoin.remainder(LOTTO_PRICE);

        if (!reminder.equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("[ERROR] 입력 금액은 천원 단위여야 합니다.");
        }
    }
}
