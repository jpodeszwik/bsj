package pl.jp.bsj.investment;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import pl.jp.bsj.domain.InvestmentFund;
import pl.jp.bsj.domain.InvestmentResult;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class EqualFundsInvestmentStrategyTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFundsAreEmpty() {
        EqualFundsFundsInvestmentStrategy equalFundsInvestmentStrategy = new EqualFundsFundsInvestmentStrategy();


        equalFundsInvestmentStrategy.invest(ImmutableSet.of(), 1L);
    }

    @Test
    public void shouldAssignRemainderToFirstFund() {
        EqualFundsFundsInvestmentStrategy equalFundsInvestmentStrategy = new EqualFundsFundsInvestmentStrategy();
        Set<InvestmentFund> funds = ImmutableSet.of(Funds.POLISH_1, Funds.POLISH_2);


        InvestmentResult distribution = equalFundsInvestmentStrategy.invest(funds, 3L);


        Map<InvestmentFund, Long> expectedInvestments = ImmutableMap.of(Funds.POLISH_1, 1L, Funds.POLISH_2, 1L);
        long expectedRemainder = 1;
        assertEquals(new InvestmentResult(expectedInvestments, expectedRemainder), distribution);

    }

    @Test
    public void shouldDistributeAllFundsToOneFundIfOnlyOneWasProvided() {
        EqualFundsFundsInvestmentStrategy equalFundsInvestmentStrategy = new EqualFundsFundsInvestmentStrategy();
        Set<InvestmentFund> funds = ImmutableSet.of(Funds.POLISH_1);
        long amount = 3L;


        InvestmentResult distribution = equalFundsInvestmentStrategy.invest(funds, amount);


        assertEquals(InvestmentResult.withoutRemainder(ImmutableMap.of(Funds.POLISH_1, amount)), distribution);
    }

    @Test
    public void shouldDistributeFundsEqually() {
        EqualFundsFundsInvestmentStrategy equalFundsInvestmentStrategy = new EqualFundsFundsInvestmentStrategy();
        Set<InvestmentFund> funds = ImmutableSet.of(Funds.POLISH_1, Funds.POLISH_2);
        long amount = 2L;


        InvestmentResult distribution = equalFundsInvestmentStrategy.invest(funds, amount);


        assertEquals(InvestmentResult.withoutRemainder(ImmutableMap.of(Funds.POLISH_1, 1L, Funds.POLISH_2, 1L)), distribution);
    }
}