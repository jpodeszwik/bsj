package pl.jp.bsj;

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

        long distributedAmount = amount / funds.size();

        if (distributedAmount * funds.size() != amount) {
            throw new IllegalStateException("Tried to split amount that isn't divisible");
        }

        return funds.stream().collect(Collectors.toMap(Function.identity(), fund -> distributedAmount));
    }
}
