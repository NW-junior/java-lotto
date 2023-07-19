package lotto.model;

import java.util.Collections;
import java.util.List;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final Integer bonusNumber;

    public WinningLotto(Lotto winningNumbers, Integer bonusNumber) {
        validate(winningNumbers, bonusNumber);

        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto winningNumbers, Integer bonusNumber) {
        winningNumbers.validateAlreadyExist(bonusNumber);
    }

    public List<Reward> calculateReward(List<Lotto> drawnLotteries) {
        // TODO
        return Collections.emptyList();
    }
}
