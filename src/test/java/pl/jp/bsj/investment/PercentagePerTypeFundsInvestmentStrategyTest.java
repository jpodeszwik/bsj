package pl.jp.bsj.investment;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import pl.jp.bsj.domain.FundType;
import pl.jp.bsj.domain.InvestmentFund;
import pl.jp.bsj.domain.InvestmentResult;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PercentagePerTypeFundsInvestmentStrategyTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrowExceptionForEmptyFunds() {
        Map<FundType, Integer> distributionMap = ImmutableMap.of(FundType.POLISH, 100);
        PercentageFundsPerTypeDistribution percentageFundsPerTypeDistribution = new PercentageFundsPerTypeDistribution(distributionMap);
        EqualFundsFundsInvestmentStrategy equalFundsInvestmentStrategy = new EqualFundsFundsInvestmentStrategy();
        PercentagePerTypeFundsInvestmentStrategy percentageFundsPerTypeInvestmentStrategy = new PercentagePerTypeFundsInvestmentStrategy(percentageFundsPerTypeDistribution, equalFundsInvestmentStrategy);


        percentageFundsPerTypeInvestmentStrategy.invest(ImmutableSet.of(), 100);
    }

    @Test
    public void shouldReturnRemainderForAmountThatCannotBeDistributed() {
        Map<FundType, Integer> distributionMap = ImmutableMap.of(FundType.POLISH, 50, FundType.FOREIGN, 50);
        PercentageFundsPerTypeDistribution percentageFundsPerTypeDistribution = new PercentageFundsPerTypeDistribution(distributionMap);
        EqualFundsFundsInvestmentStrategy equalFundsInvestmentStrategy = new EqualFundsFundsInvestmentStrategy();
        PercentagePerTypeFundsInvestmentStrategy percentageFundsPerTypeInvestmentStrategy = new PercentagePerTypeFundsInvestmentStrategy(percentageFundsPerTypeDistribution, equalFundsInvestmentStrategy);
        Set<InvestmentFund> funds = ImmutableSet.of(Funds.POLISH_1, Funds.FOREIGN_1);


        InvestmentResult distribution = percentageFundsPerTypeInvestmentStrategy.invest(funds, 101);


        long expectedRemainder = 1;
        assertEquals(new InvestmentResult(ImmutableMap.of(Funds.POLISH_1, 50L, Funds.FOREIGN_1, 50L), expectedRemainder), distribution);
    }

    @Test
    public void shouldDistributeFundForSingleType() {
        Map<FundType, Integer> distributionMap = ImmutableMap.of(FundType.POLISH, 100);
        PercentageFundsPerTypeDistribution percentageFundsPerTypeDistribution = new PercentageFundsPerTypeDistribution(distributionMap);
        EqualFundsFundsInvestmentStrategy equalFundsInvestmentStrategy = new EqualFundsFundsInvestmentStrategy();
        PercentagePerTypeFundsInvestmentStrategy percentageFundsPerTypeInvestmentStrategy = new PercentagePerTypeFundsInvestmentStrategy(percentageFundsPerTypeDistribution, equalFundsInvestmentStrategy);
        Set<InvestmentFund> funds = ImmutableSet.of(Funds.POLISH_1, Funds.POLISH_2);


        InvestmentResult distribution = percentageFundsPerTypeInvestmentStrategy.invest(funds, 100);


        assertEquals(InvestmentResult.withoutRemainder(ImmutableMap.of(Funds.POLISH_1, 50L, Funds.POLISH_2, 50L)), distribution);
    }

    @Test
    public void shouldDistributeFundForEachType() {
        Map<FundType, Integer> distributionMap = ImmutableMap.of(FundType.POLISH, 10, FundType.FOREIGN, 40, FundType.MONETARY, 50);
        PercentageFundsPerTypeDistribution percentageFundsPerTypeDistribution = new PercentageFundsPerTypeDistribution(distributionMap);
        EqualFundsFundsInvestmentStrategy equalFundsInvestmentStrategy = new EqualFundsFundsInvestmentStrategy();
        PercentagePerTypeFundsInvestmentStrategy percentageFundsPerTypeInvestmentStrategy = new PercentagePerTypeFundsInvestmentStrategy(percentageFundsPerTypeDistribution, equalFundsInvestmentStrategy);
        Set<InvestmentFund> funds = ImmutableSet.of(Funds.POLISH_1, Funds.FOREIGN_1, Funds.MONETARY_1);


        InvestmentResult distribution = percentageFundsPerTypeInvestmentStrategy.invest(funds, 100);


        assertEquals(InvestmentResult.withoutRemainder(ImmutableMap.of(Funds.POLISH_1, 10L, Funds.FOREIGN_1, 40L, Funds.MONETARY_1, 50L)), distribution);
    }

}