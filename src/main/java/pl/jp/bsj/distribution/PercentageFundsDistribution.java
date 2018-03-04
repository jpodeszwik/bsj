package pl.jp.bsj.distribution;

import pl.jp.bsj.domain.FundType;

import java.util.Map;
import java.util.Optional;

class PercentageFundsDistribution {
    private final Map<FundType, Integer> percentageDistribution;

    PercentageFundsDistribution(Map<FundType, Integer> percentageDistribution) {
        if (percentageDistribution == null) {
            throw new IllegalArgumentException("Percentage distribution must not be null");
        }

        if (percentageDistribution.values().stream().mapToInt(i -> i).sum() != 100) {
            throw new IllegalArgumentException("Funds distribution should sum up to 100");
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
