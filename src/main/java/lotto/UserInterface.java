package lotto;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static lotto.LottoConstants.LOTTO_LAST_NUMBER;
import static lotto.LottoConstants.LOTTO_PRICE;
import static lotto.LottoConstants.LOTTO_START_NUMBER;

import camp.nextstep.edu.missionutils.Console;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserInterface {

    private List<Integer> luckyNumberList;

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

        luckyNumberList = parseInputToInteger(List.of(numberInput.split(",")));

        validateLuckyNumber(luckyNumberList);

        return luckyNumberList;
    }

    public Integer getBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");

        String bonusInput = Console.readLine();

        Integer bonusNumber = parseInputToInteger(List.of(bonusInput)).get(0);

        validateBonusNumber(bonusNumber);

        return bonusNumber;
    }

    public void printBoughtLotto(List<Lotto> lottoList) {
        System.out.printf("%n%s개를 구매했습니다.%n%n", lottoList.size());

        for (Lotto lotto : lottoList) {
            System.out.println(lotto);
        }
    }

    public void printResult(Map<Reward, Integer> rewardInfo, BigDecimal earningRate) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        rewardInfo.keySet().forEach(
                (reward) -> {
                    System.out.printf("%s - %d개%n", reward, rewardInfo.get(reward));
                }
        );
        System.out.printf("총 수익률은 %s%%입니다.%n", earningRate);
    }

    private BigDecimal parseInputToMoney(String moneyInput) {
        try {
            return new BigDecimal(moneyInput);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    private List<Integer> parseInputToInteger(List<String> numberInputList) {
        try {
            return numberInputList.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    private void validateInvestedCoin(BigDecimal investedCoin) {
        BigDecimal reminder = investedCoin.remainder(LOTTO_PRICE);

        if (!reminder.equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("입력 금액은 천원 단위여야 합니다.");
        }
    }

    private void validateBonusNumber(Integer bonusNumber) {
        if (bonusNumber < LOTTO_START_NUMBER || bonusNumber > LOTTO_LAST_NUMBER) {
            throw new IllegalArgumentException("당첨 번호는 1과 45 사이의 수만 올 수 있습니다.");
        }

        if (luckyNumberList.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private void validateLuckyNumber(List<Integer> luckyNumber) {
        long wrongNumberCount = luckyNumber.stream().filter(it -> it < LOTTO_START_NUMBER || it > LOTTO_LAST_NUMBER)
                .count();

        if (wrongNumberCount != 0) {
            throw new IllegalArgumentException("당첨 번호는 1과 45 사이의 수만 올 수 있습니다.");
        }

        List<Integer> districtList = luckyNumber.stream().distinct().collect(Collectors.toList());

        if (districtList.size() != luckyNumber.size()) {
            throw new IllegalArgumentException("당첨 번호에는 중복된 수가 있을 수 없습니다.");
        }
    }

}
