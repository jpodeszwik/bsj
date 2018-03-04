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
        InvestmentFund polish1 = new InvestmentFund(1L, "Polish", FundType.POLISH);
        InvestmentFund polish2 = new InvestmentFund(2L, "Foreign", FundType.FOREIGN);
        Set<InvestmentFund> funds = ImmutableSet.of(polish1, polish2);


        percentageFundsDistributor.distribute(funds, 101);
    }

    @Test
    public void shouldDistributeFundForSingleType() {
        Map<FundType, Integer> distributionMap = ImmutableMap.of(FundType.POLISH, 100);
        PercentageFundsDistribution percentageFundsDistribution = new PercentageFundsDistribution(distributionMap);
        EqualFundsDistributor equalFundsDistributor = new EqualFundsDistributor();
        PercentageFundsDistributor percentageFundsDistributor = new PercentageFundsDistributor(percentageFundsDistribution, equalFundsDistributor);
        InvestmentFund polish1 = new InvestmentFund(1L, "Polish1", FundType.POLISH);
        InvestmentFund polish2 = new InvestmentFund(2L, "Polish2", FundType.POLISH);
        Set<InvestmentFund> funds = ImmutableSet.of(polish1, polish2);


        Map<InvestmentFund, Long> distribution = percentageFundsDistributor.distribute(funds, 100);


        assertEquals(ImmutableMap.of(polish1, 50L, polish2, 50L), distribution);
    }

    @Test
    public void shouldDistributeFundForEachType() {
        Map<FundType, Integer> distributionMap = ImmutableMap.of(FundType.POLISH, 10, FundType.FOREIGN, 40, FundType.MONETARY, 50);
        PercentageFundsDistribution percentageFundsDistribution = new PercentageFundsDistribution(distributionMap);
        EqualFundsDistributor equalFundsDistributor = new EqualFundsDistributor();
        PercentageFundsDistributor percentageFundsDistributor = new PercentageFundsDistributor(percentageFundsDistribution, equalFundsDistributor);
        InvestmentFund polish = new InvestmentFund(1L, "Polish", FundType.POLISH);
        InvestmentFund foreign = new InvestmentFund(2L, "Foreign", FundType.FOREIGN);
        InvestmentFund monetary = new InvestmentFund(3L, "Monetary", FundType.MONETARY);
        Set<InvestmentFund> funds = ImmutableSet.of(polish, foreign, monetary);


        Map<InvestmentFund, Long> distribution = percentageFundsDistributor.distribute(funds, 100);


        assertEquals(ImmutableMap.of(polish, 10L, foreign, 40L, monetary, 50L), distribution);
    }

}