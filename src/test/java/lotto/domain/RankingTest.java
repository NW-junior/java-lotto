package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

}
