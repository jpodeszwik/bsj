package pl.jp.bsj.investment;

import com.google.common.collect.ImmutableMap;
import pl.jp.bsj.domain.FundType;

import java.util.Map;

public class InvestmentStrategyFactory {
    public static FundsInvestmentStrategy newSafeInvestmentStrategy() {
        return newInvestmentStrategy(ImmutableMap.of(FundType.POLISH, 20, FundType.FOREIGN, 75, FundType.MONETARY, 5));
    }

    public static FundsInvestmentStrategy newBalancedInvestmentStrategy() {
        return newInvestmentStrategy(ImmutableMap.of(FundType.POLISH, 30, FundType.FOREIGN, 60, FundType.MONETARY, 10));
    }

    public static FundsInvestmentStrategy newAggressiveInvestmentStrategy() {
        return newInvestmentStrategy(ImmutableMap.of(FundType.POLISH, 40, FundType.FOREIGN, 20, FundType.MONETARY, 40));
    }

    private static FundsInvestmentStrategy newInvestmentStrategy(Map<FundType, Integer> fundsDistribution) {
        PercentageFundsPerTypeDistribution percentageFundsPerTypeDistribution = new PercentageFundsPerTypeDistribution(fundsDistribution);
        return new PercentagePerTypeFundsInvestmentStrategy(percentageFundsPerTypeDistribution, new EqualFundsFundsInvestmentStrategy());
    }
}
