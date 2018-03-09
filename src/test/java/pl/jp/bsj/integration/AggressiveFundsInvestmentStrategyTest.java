package pl.jp.bsj.integration;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import pl.jp.bsj.investment.Funds;
import pl.jp.bsj.domain.InvestmentResult;
import pl.jp.bsj.investment.InvestmentStrategyFactory;
import pl.jp.bsj.investment.FundsInvestmentStrategy;
import pl.jp.bsj.domain.InvestmentFund;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AggressiveFundsInvestmentStrategyTest {
    @Test
    public void shouldInvest40PercentIntoPolish20PercentIntoForeign40PercentIntoMonetaryFunds
            () {
        FundsInvestmentStrategy fundsInvestmentStrategy = InvestmentStrategyFactory.newAggressiveInvestmentStrategy();
        Set<InvestmentFund> investmentFunds = ImmutableSet.of(
                Funds.POLISH_1, Funds.POLISH_2, Funds.FOREIGN_1, Funds.FOREIGN_2, Funds.FOREIGN_3, Funds.MONETARY_1);
        long amount = 10000;


        InvestmentResult result = fundsInvestmentStrategy.invest(investmentFunds, amount);


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
