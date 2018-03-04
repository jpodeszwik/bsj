package pl.jp.bsj.distribution;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import pl.jp.bsj.domain.FundType;
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
        List<InvestmentFund> funds = ImmutableList.of(
                new InvestmentFund(1L, "Name", FundType.POLISH),
                new InvestmentFund(2L, "name2", FundType.POLISH)
        );


        equalFundsDistributor.distribute(funds, 3L);
    }

    @Test
    public void shouldDistributeAllFundsToOneFundIfOnlyOneWasProvided() {
        EqualFundsDistributor equalFundsDistributor = new EqualFundsDistributor();
        InvestmentFund fund = new InvestmentFund(1L, "Name", FundType.POLISH);
        long amount = 3L;


        Map<InvestmentFund, Long> distribution = equalFundsDistributor.distribute(ImmutableList.of(fund), amount);


        assertEquals(ImmutableMap.of(fund, amount), distribution);
    }

    @Test
    public void shouldDistributeFundsEqually() {
        EqualFundsDistributor equalFundsDistributor = new EqualFundsDistributor();
        InvestmentFund fund1 = new InvestmentFund(1L, "Name", FundType.POLISH);
        InvestmentFund fund2 = new InvestmentFund(2L, "Name2", FundType.POLISH);
        long amount = 2L;


        Map<InvestmentFund, Long> distribution = equalFundsDistributor.distribute(ImmutableList.of(fund1, fund2), amount);


        assertEquals(ImmutableMap.of(fund1, 1L, fund2, 1L), distribution);
    }
}