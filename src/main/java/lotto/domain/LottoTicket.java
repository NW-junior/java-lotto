package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;

public class LottoTicket {

    private final List<Lotto> lottos;

    public LottoTicket(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static LottoTicket publishLottoTicket(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(Lotto.publishRandomLotto());
        }
        return new LottoTicket(lottos);
    }

    public List<Ranking> calculateRankings(WinningLotto winningLotto) {
        return lottos.stream()
            .map(winningLotto::calculateRanking)
            .collect(toUnmodifiableList());
    }

    public List<Lotto> getValue() {
        return List.copyOf(this.lottos);
    }

}
