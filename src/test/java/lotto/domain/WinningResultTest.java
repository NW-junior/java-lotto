package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningResultTest {

    private static Stream<Arguments> getRankingsAndTotalPrize() {
        return Stream.of(
            Arguments.of(List.of(
                Ranking.ONE,
                Ranking.FOUR,
                Ranking.FOUR,
                Ranking.FIVE
            ), 2_000_105_000),
            Arguments.of(List.of(
                Ranking.FOUR,
                Ranking.FOUR,
                Ranking.FIVE,
                Ranking.NONE
            ), 105_000),
            Arguments.of(List.of(
                Ranking.THREE,
                Ranking.TWO,
                Ranking.NONE,
                Ranking.NONE
            ), 31_500_000),
            Arguments.of(List.of(
                Ranking.NONE,
                Ranking.NONE,
                Ranking.NONE,
                Ranking.NONE
            ), 0)
        );
    }

    @DisplayName("당첨 결과를 생성한다.")
    @Test
    void createWinningResult() {
        // given
        List<Ranking> rankings = List.of(
            Ranking.ONE,
            Ranking.FOUR,
            Ranking.FIVE
        );

        // when
        WinningResult winningResult = new WinningResult(rankings);

        // then
        assertThat(winningResult).isNotNull();
    }

    @DisplayName("랭킹에 맞는 총 상금을 계산한다.")
    @ParameterizedTest(name = "랭킹들 : {0}")
    @MethodSource("getRankingsAndTotalPrize")
    void getTotalPrize(List<Ranking> rankings, long expect) {
        // given
        WinningResult winningResult = new WinningResult(rankings);

        // when
        long totalPrize = winningResult.getTotalPrize();

        // then
        assertThat(totalPrize).isEqualTo(expect);
    }

}
