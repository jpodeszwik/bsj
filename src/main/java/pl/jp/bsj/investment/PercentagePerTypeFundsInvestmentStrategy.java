package pl.jp.bsj.investment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.jp.bsj.domain.FundType;
import pl.jp.bsj.domain.InvestmentFund;
import pl.jp.bsj.domain.InvestmentResult;
import pl.jp.bsj.util.CollectionUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class PercentagePerTypeFundsInvestmentStrategy implements FundsInvestmentStrategy {
    private final PercentageFundsPerTypeDistribution percentageFundsPerTypeDistribution;
    private final EqualFundsFundsInvestmentStrategy equalFundsInvestmentStrategy;

    public InvestmentResult invest(Set<InvestmentFund> funds, long total) {
        if (funds == null || funds.size() == 0) {
            throw new IllegalArgumentException("Funds should not be empty");
        }

        Map<FundType, Set<InvestmentFund>> fundsForTypes = funds.stream()
                .collect(Collectors.groupingBy(InvestmentFund::getFundType, Collectors.toSet()));

        Map<FundType, Long> fundsPerTypeInvestmentAmounts = fundsForTypes.keySet().stream()
                .collect(Collectors.toMap(Function.identity(), fundType -> calculateFundTypeAmount(fundType, total)));

        List<InvestmentResult> investmentsPerType = investInFundTypes(fundsForTypes, fundsPerTypeInvestmentAmounts);

        InvestmentResult totalInvestment = combineInvestments(investmentsPerType);

        long remainder = total - CollectionUtil.sumAsLongs(totalInvestment.getInvestedMoney().values());

        return new InvestmentResult(totalInvestment.getInvestedMoney(), remainder);
    }

    private List<InvestmentResult> investInFundTypes(Map<FundType, Set<InvestmentFund>> fundsForTypes, Map<FundType, Long> fundsPerTypeInvestmentAmounts) {
        return fundsForTypes.entrySet().stream()
                .map(entry -> equalFundsInvestmentStrategy.invest(entry.getValue(), fundsPerTypeInvestmentAmounts.get(entry.getKey())))
                .collect(Collectors.toList());
    }

    private InvestmentResult combineInvestments(List<InvestmentResult> investmentsPerType) {
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

    private long calculateFundTypeAmount(FundType fundType, long total) {
        return percentageFundsPerTypeDistribution.getFundTypePercentage(fundType) * total / 100;
    }
}
