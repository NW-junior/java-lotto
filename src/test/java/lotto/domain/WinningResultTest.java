package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.vo.Money;

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

    private static Stream<Arguments> getRankingsAndpurchaseMoneyAndRateOfReturn() {
        return Stream.of(
            Arguments.of(List.of(
                Ranking.ONE,
                Ranking.FOUR,
                Ranking.FOUR
            ), 3000, 66670000L),
            Arguments.of(List.of(
                Ranking.FOUR
            ), 1000, 5000),
            Arguments.of(List.of(
                Ranking.THREE,
                Ranking.TWO,
                Ranking.TWO,
                Ranking.NONE,
                Ranking.NONE
            ), 5000, 1230000),
            Arguments.of(List.of(
                Ranking.NONE,
                Ranking.NONE,
                Ranking.NONE,
                Ranking.NONE
            ), 4000, 0)
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

    @DisplayName("당첨결과에 따른 총 상금을 계산한다.")
    @ParameterizedTest(name = "랭킹들 : {0}, 총 상금 : {1}")
    @MethodSource("getRankingsAndTotalPrize")
    void getTotalPrize(List<Ranking> rankings, long expect) {
        // given
        WinningResult winningResult = new WinningResult(rankings);

        // when
        long totalPrize = winningResult.calculateTotalPrize();

        // then
        assertThat(totalPrize).isEqualTo(expect);
    }

    @DisplayName("당첨결과에 따른 수익률을 반환한다.")
    @ParameterizedTest(name = "랭킹들 : {0}, 구매 금액 : {1}, 수익률 : {2}")
    @MethodSource("getRankingsAndpurchaseMoneyAndRateOfReturn")
    void getRateOfReturn(List<Ranking> rankings, long purchaseMoney, double expect) {
        // given
        WinningResult winningResult = new WinningResult(rankings);

        // when
        double rateOfReturn = winningResult.getRateOfReturn(new Money(purchaseMoney));

        // then
        assertThat(rateOfReturn).isEqualTo(expect);
    }

}
