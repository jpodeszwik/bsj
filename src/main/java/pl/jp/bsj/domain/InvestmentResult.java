package pl.jp.bsj.domain;

import lombok.Value;

import java.util.Map;

@Value
public class InvestmentResult {
    private final Map<InvestmentFund, Long> investedMoney;
    private final long remainder;

    public static InvestmentResult withoutRemainder(Map<InvestmentFund, Long> money) {
        return new InvestmentResult(money, 0);
    }
}
