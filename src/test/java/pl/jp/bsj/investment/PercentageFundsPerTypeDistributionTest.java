package pl.jp.bsj.investment;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import pl.jp.bsj.domain.FundType;

import static org.junit.Assert.assertEquals;

public class PercentageFundsPerTypeDistributionTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForFundsNotSummingUpTo100() {
        new PercentageFundsPerTypeDistribution(ImmutableMap.of(FundType.POLISH, 1));
    }

    @Test
    public void shouldReturnZeroForNotMentionedFundType() {
        PercentageFundsPerTypeDistribution percentageFundsPerTypeDistribution = new PercentageFundsPerTypeDistribution(ImmutableMap.of(FundType.POLISH, 100));


        assertEquals(0, percentageFundsPerTypeDistribution.getFundTypePercentage(FundType.MONETARY));
    }

    @Test
    public void shouldReturnCorrectValuesForTypes() {
        PercentageFundsPerTypeDistribution percentageFundsPerTypeDistribution = new PercentageFundsPerTypeDistribution(ImmutableMap.of(FundType.POLISH, 33, FundType.FOREIGN, 22, FundType.MONETARY, 45));


        assertEquals(33, percentageFundsPerTypeDistribution.getFundTypePercentage(FundType.POLISH));
        assertEquals(22, percentageFundsPerTypeDistribution.getFundTypePercentage(FundType.FOREIGN));
        assertEquals(45, percentageFundsPerTypeDistribution.getFundTypePercentage(FundType.MONETARY));
    }
}