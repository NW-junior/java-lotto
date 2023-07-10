package lotto.domain;

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

    public List<Lotto> getValue() {
        return List.copyOf(this.lottos);
    }

}
