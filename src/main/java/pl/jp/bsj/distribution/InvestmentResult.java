package pl.jp.bsj.distribution;

import lombok.Value;
import pl.jp.bsj.domain.InvestmentFund;

import java.util.Map;

@Value
class InvestmentResult {
    private final Map<InvestmentFund, Long> investedMoney;
    private final long remainder;

    static InvestmentResult of(Map<InvestmentFund, Long> money) {
        return new InvestmentResult(money, 0);
    }
}
