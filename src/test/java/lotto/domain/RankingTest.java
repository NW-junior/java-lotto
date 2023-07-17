package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class RankingTest {

    @DisplayName("같은 로또 번호 갯수와 보너스 번호가 맞는지에 따라 랭킹을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {
        "0,false,NONE",
        "1,false,NONE",
        "2,true,NONE",
        "2,false,NONE",
        "3,false,FIVE",
        "4,false,FOUR",
        "5,false,THREE",
        "5,true,TWO",
        "6, false, ONE"
    })
    void calculate(int sameNumberCount, boolean hasSameBonusNumber, Ranking expect) {
        // when
        Ranking ranking = Ranking.calculate(sameNumberCount, hasSameBonusNumber);

        // then
        assertThat(ranking).isEqualTo(expect);
    }

    @DisplayName("갯수를 입력받아 상금을 계산한다.")
    @ParameterizedTest(name = "랭킹 : {0}")
    @CsvSource(value = {"ONE,2,4_000_000_000", "TWO,3,90_000_000", "THREE,0,0"})
    void calculatePrize(Ranking ranking, Long count, long expect) {
        // given
        // when
        long prize = ranking.calculatePrize(count);

        // then
        assertThat(prize).isEqualTo(expect);
    }

    @DisplayName("상금 계산시 횟수가 비어있거나 0보다 작으면 예외를 발생한다.")
    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {-1, -50, -100})
    void calculatePrizeWithNullCount(Long count) {
        // given
        // when
        // then
        assertThatThrownBy(() -> Ranking.ONE.calculatePrize(count))
            .isInstanceOf(IllegalArgumentException.class);
    }

}
