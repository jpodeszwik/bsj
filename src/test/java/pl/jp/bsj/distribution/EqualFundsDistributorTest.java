package pl.jp.bsj.distribution;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import pl.jp.bsj.domain.InvestmentFund;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EqualFundsDistributorTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFundsAreEmpty() {
        EqualFundsDistributor equalFundsDistributor = new EqualFundsDistributor();


        equalFundsDistributor.distribute(ImmutableList.of(), 1L);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIfFundsCannotBeProperlyDivided() {
        EqualFundsDistributor equalFundsDistributor = new EqualFundsDistributor();
        List<InvestmentFund> funds = ImmutableList.of(Funds.POLISH_1, Funds.POLISH_2);


        equalFundsDistributor.distribute(funds, 3L);
    }

    @Test
    public void shouldDistributeAllFundsToOneFundIfOnlyOneWasProvided() {
        EqualFundsDistributor equalFundsDistributor = new EqualFundsDistributor();
        List<InvestmentFund> funds = ImmutableList.of(Funds.POLISH_1);
        long amount = 3L;


        Map<InvestmentFund, Long> distribution = equalFundsDistributor.distribute(funds, amount);


        assertEquals(ImmutableMap.of(Funds.POLISH_1, amount), distribution);
    }

    @Test
    public void shouldDistributeFundsEqually() {
        EqualFundsDistributor equalFundsDistributor = new EqualFundsDistributor();
        List<InvestmentFund> funds = ImmutableList.of(Funds.POLISH_1, Funds.POLISH_2);
        long amount = 2L;


        Map<InvestmentFund, Long> distribution = equalFundsDistributor.distribute(funds, amount);


        assertEquals(ImmutableMap.of(Funds.POLISH_1, 1L, Funds.POLISH_2, 1L), distribution);
    }
}