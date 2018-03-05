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
class PercentageFundsDistributor implements InvestmentStrategy {
    private final PercentageFundsDistribution percentageFundsDistribution;
    private final EqualFundsDistributor equalFundsDistributor;

    public InvestmentResult distribute(Set<InvestmentFund> funds, long total) {
        if (funds == null || funds.size() == 0) {
            throw new IllegalArgumentException("Funds should not be empty");
        }

        Map<FundType, List<InvestmentFund>> fundsGroups = funds.stream().collect(Collectors.groupingBy(InvestmentFund::getFundType));

        Map<FundType, Long> fundTypeAmount = fundsGroups.keySet().stream()
                .collect(Collectors.toMap(Function.identity(), fundType -> fundTypeAmount(fundType, total)));

        List<InvestmentResult> investmentsPerType = fundsGroups.entrySet().stream()
                .map(entry -> investInFunds(fundTypeAmount, entry))
                .collect(Collectors.toList());

        InvestmentResult investmentResult = combine(investmentsPerType);

        long remainder = total - CollectionUtil.sumAsLongs(investmentResult.getInvestedMoney().values());

        return new InvestmentResult(investmentResult.getInvestedMoney(), remainder);
    }

    private InvestmentResult combine(List<InvestmentResult> investmentsPerType) {
        Map<InvestmentFund, Long> totalInvestedMoney = investmentsPerType.stream()
                .map(InvestmentResult::getInvestedMoney)
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        long totalRemainder = investmentsPerType.stream()
                .mapToLong(InvestmentResult::getRemainder)
                .sum();

        return new InvestmentResult(totalInvestedMoney, totalRemainder);
    }

    private InvestmentResult investInFunds(Map<FundType, Long> fundTypeAmount, Map.Entry<FundType, List<InvestmentFund>> entry) {
        return equalFundsDistributor.distribute(entry.getValue(), fundTypeAmount.get(entry.getKey()));
    }

    private long fundTypeAmount(FundType fundType, long total) {
        return percentageFundsDistribution.getFundTypePercentage(fundType) * total / 100;
    }
}
