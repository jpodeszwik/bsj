package pl.jp.bsj.distribution;

import pl.jp.bsj.domain.InvestmentFund;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class EqualFundsDistributor {
    Map<InvestmentFund, Long> distribute(Collection<InvestmentFund> funds, long amount) {
        if (funds == null || funds.size() == 0) {
            throw new IllegalArgumentException("Can't divide amount if there are no funds");
        }

        if (amount % funds.size() != 0) {
            throw new IllegalArgumentException("Amount cannot be split equally to " + funds.size() + " funds");
        }

        long distributedAmount = amount / funds.size();

        return funds.stream().collect(Collectors.toMap(Function.identity(), fund -> distributedAmount));
    }
}
