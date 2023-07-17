package lotto;

import lotto.service.LottoMainService;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        try {
            LottoMainService.start();
        } catch (Throwable ex) {
            OutputView.printError(ex.getMessage());
        }
    }
}
