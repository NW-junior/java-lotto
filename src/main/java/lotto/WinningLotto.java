package lotto;

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
}
