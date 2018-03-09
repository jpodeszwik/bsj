package pl.jp.bsj.investment;

import pl.jp.bsj.domain.InvestmentFund;
import pl.jp.bsj.domain.InvestmentResult;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

class EqualFundsFundsInvestmentStrategy {

    InvestmentResult invest(Set<InvestmentFund> funds, long amount) {
        if (funds == null || funds.size() == 0) {
            throw new IllegalArgumentException("Can't divide amount if there are no funds");
        }

        long amountPerFund = amount / funds.size();
        long remainder = amount % funds.size();

        Map<InvestmentFund, Long> investedMoney = funds.stream().collect(Collectors.toMap(Function.identity(), fund -> amountPerFund));

        return new InvestmentResult(investedMoney, remainder);
    }
}
