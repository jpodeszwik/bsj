package pl.jp.bsj.distribution;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.jp.bsj.domain.FundType;
import pl.jp.bsj.domain.InvestmentFund;
import pl.jp.bsj.util.CollectionUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class PercentageFundsDistributor {
    private final PercentageFundsDistribution percentageFundsDistribution;
    private final EqualFundsDistributor equalFundsDistributor;

    Map<InvestmentFund, Long> distribute(Set<InvestmentFund> funds, long total) {
        if (funds == null || funds.size() == 0) {
            throw new IllegalArgumentException("Funds should not be empty");
        }

        Map<FundType, List<InvestmentFund>> fundsGroups = funds.stream().collect(Collectors.groupingBy(InvestmentFund::getFundType));

        Map<FundType, Long> fundTypeAmount = fundsGroups.keySet().stream()
                .collect(Collectors.toMap(Function.identity(), fundType -> fundTypeAmount(fundType, total)));

        if (CollectionUtil.sumAsLongs(fundTypeAmount.values()) != total) {
            throw new IllegalArgumentException("Provided amount didn't distribute properly");
        }

        return fundsGroups.entrySet().stream()
                .map(entry -> equalFundsDistributor.distribute(entry.getValue(), fundTypeAmount.get(entry.getKey())))
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private long fundTypeAmount(FundType fundType, long total) {
        return percentageFundsDistribution.getFundTypePercentage(fundType) * total / 100;
    }
}
