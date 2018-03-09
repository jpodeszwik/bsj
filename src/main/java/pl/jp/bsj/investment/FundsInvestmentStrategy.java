package pl.jp.bsj.investment;

import pl.jp.bsj.domain.InvestmentFund;
import pl.jp.bsj.domain.InvestmentResult;

import java.util.Set;

public interface FundsInvestmentStrategy {
    InvestmentResult invest(Set<InvestmentFund> funds, long amount);
}
