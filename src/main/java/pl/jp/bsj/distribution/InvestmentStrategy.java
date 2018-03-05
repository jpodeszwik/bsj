package pl.jp.bsj.distribution;

import pl.jp.bsj.domain.InvestmentFund;

import java.util.Set;

public interface InvestmentStrategy {
    InvestmentResult distribute(Set<InvestmentFund> funds, long amount);
}
