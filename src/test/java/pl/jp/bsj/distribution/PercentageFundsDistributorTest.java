package pl.jp.bsj.distribution;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import pl.jp.bsj.domain.FundType;
import pl.jp.bsj.domain.InvestmentFund;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PercentageFundsDistributorTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrowExceptionForEmptyFunds() {
        Map<FundType, Integer> distributionMap = ImmutableMap.of(FundType.POLISH, 100);
        PercentageFundsDistribution percentageFundsDistribution = new PercentageFundsDistribution(distributionMap);
        EqualFundsDistributor equalFundsDistributor = new EqualFundsDistributor();
        PercentageFundsDistributor percentageFundsDistributor = new PercentageFundsDistributor(percentageFundsDistribution, equalFundsDistributor);


        percentageFundsDistributor.distribute(ImmutableSet.of(), 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForAmountThatCantBeDistributed() {
        Map<FundType, Integer> distributionMap = ImmutableMap.of(FundType.POLISH, 50, FundType.FOREIGN, 50);
        PercentageFundsDistribution percentageFundsDistribution = new PercentageFundsDistribution(distributionMap);
        EqualFundsDistributor equalFundsDistributor = new EqualFundsDistributor();
        PercentageFundsDistributor percentageFundsDistributor = new PercentageFundsDistributor(percentageFundsDistribution, equalFundsDistributor);
        Set<InvestmentFund> funds = ImmutableSet.of(Funds.POLISH_1, Funds.FOREIGN_1);


        percentageFundsDistributor.distribute(funds, 101);
    }

    @Test
    public void shouldDistributeFundForSingleType() {
        Map<FundType, Integer> distributionMap = ImmutableMap.of(FundType.POLISH, 100);
        PercentageFundsDistribution percentageFundsDistribution = new PercentageFundsDistribution(distributionMap);
        EqualFundsDistributor equalFundsDistributor = new EqualFundsDistributor();
        PercentageFundsDistributor percentageFundsDistributor = new PercentageFundsDistributor(percentageFundsDistribution, equalFundsDistributor);
        Set<InvestmentFund> funds = ImmutableSet.of(Funds.POLISH_1, Funds.POLISH_2);


        Map<InvestmentFund, Long> distribution = percentageFundsDistributor.distribute(funds, 100);


        assertEquals(ImmutableMap.of(Funds.POLISH_1, 50L, Funds.POLISH_2, 50L), distribution);
    }

    @Test
    public void shouldDistributeFundForEachType() {
        Map<FundType, Integer> distributionMap = ImmutableMap.of(FundType.POLISH, 10, FundType.FOREIGN, 40, FundType.MONETARY, 50);
        PercentageFundsDistribution percentageFundsDistribution = new PercentageFundsDistribution(distributionMap);
        EqualFundsDistributor equalFundsDistributor = new EqualFundsDistributor();
        PercentageFundsDistributor percentageFundsDistributor = new PercentageFundsDistributor(percentageFundsDistribution, equalFundsDistributor);
        Set<InvestmentFund> funds = ImmutableSet.of(Funds.POLISH_1, Funds.FOREIGN_1, Funds.MONETARY_1);


        Map<InvestmentFund, Long> distribution = percentageFundsDistributor.distribute(funds, 100);


        assertEquals(ImmutableMap.of(Funds.POLISH_1, 10L, Funds.FOREIGN_1, 40L, Funds.MONETARY_1, 50L), distribution);
    }

}