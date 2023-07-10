package lotto.domain;

class WinningLotto {

    private final Lotto winningLottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lottoNumbers, LottoNumber bonusNumber) {
        isBonusNumberDuplicateLottoNumbers(lottoNumbers, bonusNumber);
        this.winningLottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void isBonusNumberDuplicateLottoNumbers(Lotto lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.hasNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 로또 번호와 중복될 수 없습니다.");
        }
    }

    public Ranking calculateRanking(Lotto lotto) {
        int sameNumberCount = this.winningLottoNumbers.countSameNumber(lotto);
        boolean hasBonusNumber = lotto.hasNumber(this.bonusNumber);
        return Ranking.calculate(sameNumberCount, hasBonusNumber);
    }
}
