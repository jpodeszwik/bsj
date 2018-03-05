package pl.jp.bsj.distribution;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import pl.jp.bsj.domain.InvestmentFund;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class InvestmentStrategiesTest {
    @Test
    public void safeInvestmentStrategyTest() {
        InvestmentStrategy investmentStrategy = InvestmentStrategies.newSafeInvestmentStrategy();
        Set<InvestmentFund> investmentFunds = ImmutableSet.of(
                Funds.POLISH_1, Funds.POLISH_2, Funds.FOREIGN_1, Funds.FOREIGN_2, Funds.FOREIGN_3, Funds.MONETARY_1);
        long amount = 10000;


        InvestmentResult result = investmentStrategy.distribute(investmentFunds, amount);


        Map<InvestmentFund, Long> expectedInvestments = new ImmutableMap.Builder<InvestmentFund, Long>()
                .put(Funds.POLISH_1, 1000L)
                .put(Funds.POLISH_2, 1000L)
                .put(Funds.FOREIGN_1, 2500L)
                .put(Funds.FOREIGN_2, 2500L)
                .put(Funds.FOREIGN_3, 2500L)
                .put(Funds.MONETARY_1, 500L)
                .build();
        assertEquals(InvestmentResult.of(expectedInvestments), result);
    }

    @Test
    public void balancedInvestmentStrategyTest() {
        InvestmentStrategy investmentStrategy = InvestmentStrategies.newBalancedInvestmentStrategy();
        Set<InvestmentFund> investmentFunds = ImmutableSet.of(
                Funds.POLISH_1, Funds.POLISH_2, Funds.FOREIGN_1, Funds.FOREIGN_2, Funds.FOREIGN_3, Funds.MONETARY_1);
        long amount = 10000;


        InvestmentResult result = investmentStrategy.distribute(investmentFunds, amount);


        Map<InvestmentFund, Long> expectedInvestments = new ImmutableMap.Builder<InvestmentFund, Long>()
                .put(Funds.POLISH_1, 1500L)
                .put(Funds.POLISH_2, 1500L)
                .put(Funds.FOREIGN_1, 2000L)
                .put(Funds.FOREIGN_2, 2000L)
                .put(Funds.FOREIGN_3, 2000L)
                .put(Funds.MONETARY_1, 1000L)
                .build();

        assertEquals(InvestmentResult.of(expectedInvestments), result);
    }

    @Test
    public void aggressiveInvestmentStrategyTest() {
        InvestmentStrategy investmentStrategy = InvestmentStrategies.newAggressiveInvestmentStrategy();
        Set<InvestmentFund> investmentFunds = ImmutableSet.of(
                Funds.POLISH_1, Funds.POLISH_2, Funds.FOREIGN_1, Funds.FOREIGN_2, Funds.FOREIGN_3, Funds.MONETARY_1);
        long amount = 10000;


        InvestmentResult result = investmentStrategy.distribute(investmentFunds, amount);


        Map<InvestmentFund, Long> expectedInvestments = new ImmutableMap.Builder<InvestmentFund, Long>()
                .put(Funds.POLISH_1, 2000L)
                .put(Funds.POLISH_2, 2000L)
                .put(Funds.FOREIGN_1, 666L)
                .put(Funds.FOREIGN_2, 666L)
                .put(Funds.FOREIGN_3, 666L)
                .put(Funds.MONETARY_1, 4000L)
                .build();
        long expectedRemainder = 2;

        assertEquals(new InvestmentResult(expectedInvestments, expectedRemainder), result);
    }
}