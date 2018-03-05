package pl.jp.bsj.distribution;

import pl.jp.bsj.domain.InvestmentFund;

import java.util.Map;
import java.util.Set;

public interface InvestmentStrategy {
    Map<InvestmentFund, Long> distribute(Set<InvestmentFund> funds, long amount);
}
