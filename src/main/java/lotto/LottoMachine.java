package lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lotto.domain.Lotto;
import lotto.domain.LottoTicket;
import lotto.domain.Ranking;
import lotto.domain.WinningLotto;
import lotto.domain.WinningResult;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMachine {

    public void run() {
        Money money = inputMoney();
        LottoTicket lottoTicket = LottoTicket.publishLottoTicket(money.calculateLottoCount());
        OutputView.printBuyLottos(lottoTicket.getValue());

        Lotto lotto = inputLotto();
        LottoNumber bounsNumber = inputBonusNumber();
        WinningLotto winningLotto = new WinningLotto(lotto, bounsNumber);

        List<Ranking> rankings = lottoTicket.calculateRankings(winningLotto);
        WinningResult winningResult = new WinningResult(rankings);
        OutputView.printResult(winningResult, money);
    }

    private Money inputMoney() {
        while (true) {
            try {
                return new Money(InputView.inputMoney());
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private Lotto inputLotto() {
        while (true) {
            try {
                List<LottoNumber> inputLottoNumbers = Stream.of(InputView.inputWinningLottoNumbers().split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .map(LottoNumber::new)
                    .collect(Collectors.toList());

                return new Lotto(inputLottoNumbers);
            } catch (Exception e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private LottoNumber inputBonusNumber() {
        while (true) {
            try {
                return new LottoNumber(InputView.inputBonusNumber());
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
