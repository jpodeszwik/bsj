package pl.jp.bsj.distribution;

import pl.jp.bsj.domain.InvestmentFund;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class EqualFundsDistributor {
    InvestmentResult distribute(Collection<InvestmentFund> funds, long amount) {
        if (funds == null || funds.size() == 0) {
            throw new IllegalArgumentException("Can't divide amount if there are no funds");
        }

        long amountPerFund = amount / funds.size();
        long remainder = amount % funds.size();

        Map<InvestmentFund, Long> investedMoney = funds.stream().collect(Collectors.toMap(Function.identity(), fund -> amountPerFund));

        return new InvestmentResult(investedMoney, remainder);
    }
}
