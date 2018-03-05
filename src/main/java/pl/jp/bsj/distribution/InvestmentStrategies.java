package pl.jp.bsj.distribution;

import com.google.common.collect.ImmutableMap;
import pl.jp.bsj.domain.FundType;

import java.util.Map;

public class InvestmentStrategies {
    public static InvestmentStrategy newSafeInvestmentStrategy() {
        return newInvestmentStrategy(ImmutableMap.of(FundType.POLISH, 20, FundType.FOREIGN, 75, FundType.MONETARY, 5));
    }

    public static InvestmentStrategy newBalancedInvestmentStrategy() {
        return newInvestmentStrategy(ImmutableMap.of(FundType.POLISH, 30, FundType.FOREIGN, 60, FundType.MONETARY, 10));
    }

    private static InvestmentStrategy newInvestmentStrategy(Map<FundType, Integer> fundsDistribution) {
        PercentageFundsDistribution percentageFundsDistribution = new PercentageFundsDistribution(fundsDistribution);
        return new PercentageFundsDistributor(percentageFundsDistribution, new EqualFundsDistributor());
    }
}
