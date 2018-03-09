package pl.jp.bsj.investment;

import pl.jp.bsj.domain.FundType;
import pl.jp.bsj.util.CollectionUtil;

import java.util.Map;
import java.util.Optional;

class PercentageFundsPerTypeDistribution {
    private final Map<FundType, Integer> percentageDistribution;

    PercentageFundsPerTypeDistribution(Map<FundType, Integer> percentageDistribution) {
        if (percentageDistribution == null) {
            throw new IllegalArgumentException("Percentage investment must not be null");
        }

        if (CollectionUtil.sumAsLongs(percentageDistribution.values()) != 100) {
            throw new IllegalArgumentException("Funds investment should sum up to 100");
        }

        this.percentageDistribution = percentageDistribution;
    }

    int getFundTypePercentage(FundType fundType) {
        if (fundType == null) {
            throw new IllegalArgumentException("fundType must not be null");
        }

        return Optional.ofNullable(percentageDistribution.get(fundType))
                .orElse(0);
    }
}
