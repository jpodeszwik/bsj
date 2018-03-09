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

public class BalancedFundsInvestmentStrategyTest {
    @Test
    public void shouldInvest30PercentIntoPolish60PercentIntoForeignAnd10PercentIntoMonetaryFunds() {
        FundsInvestmentStrategy fundsInvestmentStrategy = InvestmentStrategyFactory.newBalancedInvestmentStrategy();
        Set<InvestmentFund> investmentFunds = ImmutableSet.of(
                Funds.POLISH_1, Funds.POLISH_2, Funds.FOREIGN_1, Funds.FOREIGN_2, Funds.FOREIGN_3, Funds.MONETARY_1);
        long amount = 10000;


        InvestmentResult result = fundsInvestmentStrategy.invest(investmentFunds, amount);


        Map<InvestmentFund, Long> expectedInvestments = new ImmutableMap.Builder<InvestmentFund, Long>()
                .put(Funds.POLISH_1, 1500L)
                .put(Funds.POLISH_2, 1500L)
                .put(Funds.FOREIGN_1, 2000L)
                .put(Funds.FOREIGN_2, 2000L)
                .put(Funds.FOREIGN_3, 2000L)
                .put(Funds.MONETARY_1, 1000L)
                .build();

        assertEquals(InvestmentResult.withoutRemainder(expectedInvestments), result);
    }
}
